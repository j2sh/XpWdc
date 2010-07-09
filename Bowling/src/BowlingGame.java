
public class BowlingGame {
	
	public static void main(String[] args)
	{
		System.out.println("hello, world");
	}
	
	/**
	 * simulate one user rolling the ball the correct number of times.
	 * 
	 * @return returns the score of the game after 10 frames
	 * (note that getting a strike (10 down) on 10th frame
	 * you roll 2 more balls).
	 */
	public int play_game( int pins_down[] )
	{
		int frame = 1;
		int totalScore = 0;	// 300 is a perfect score.
		
		boolean firstRoll = true;	// first roll in a frame
		int scoreFirstBall = 0;     // per frame
		
		for ( int inx = 0; inx < pins_down.length; ++inx )
		{
			
			System.out.println("inx is: " + inx
					+ " firstRoll is: " + firstRoll
					+ " and score is: " + totalScore);

			
			int thisRoll = pins_down[inx];
			
			totalScore += thisRoll;
			
			
			if (thisRoll == 10 )  // a strike!
			{
				int nextRoll = pins_down[inx+1 ];
				int secondRoll = pins_down[inx+2];
				totalScore += nextRoll + secondRoll;
				
				++frame;

			}
			else if (firstRoll)
			{
				// already added this to totalScore
				firstRoll = false;	// give another ball to this frame
				scoreFirstBall = thisRoll;
			}
			else   // must be the second roll (within the same frame)
			{
				firstRoll = true;	// toggle back (because its the next frame)
				if ((scoreFirstBall + thisRoll) == 10)
				{
					totalScore += pins_down[inx+1];
					++ frame;
				}
				
			}
			
			if (frame > 10)
				break;
						
		}
		
		if (frame != 11)
			throw new RuntimeException( "array length is wrong, got only " + frame + " but expect 10 frames"  );
		
		System.out.println("total frames is: " + frame);
		return totalScore;
	}
}
