public class BowlingGame {
	
	
	// call the 2-arg constructor with (true, true) to
	// override these, and cause extra DEBUG messages
	// to stdout (Sstem.out.println()).
	// F=disable printing, T = enable printing.
	
	private boolean debug = false;
	private boolean showResult = false;

	
	/**
	 * no args (default) constructor
	 */
	public BowlingGame()
	{
		this( false, false );	// turn off debug
	}

	/**
	 * 2-args comprehensive consructor
	 * 
	 * @param debug
	 * @param showResult
	 */
	public BowlingGame( boolean debug, boolean showResult )
	{
		this.debug = debug;
		this.showResult = showResult;
	}
	
//	public static void main(String[] args)
//	{
//		System.out.println("hello, world");
//	}
	
	/**
	 * simulate one user rolling the ball the correct number of times.
	 * 
	 * @parm is an array of pins knocked down per roll,
	 * for ONLY one player.  In real life, player #1 and
	 * player #2 alternate turns after the end of each frame.
	 * 
	 * @return returns the score of the game after 10 frames
	 * (note that getting a strike (10 down) on 10th frame
	 * you roll 2 more balls).
	 */
	public int play_game( int pinsDown[] )
	{
		int frame = 1;
		int totalScore = 0;	// 300 is a perfect score.
		
		boolean firstRoll = true;	// first roll in a frame
		int scoreFirstBall = 0;     // per frame
		
		int inx;		// declare *BEFORE* for-loop so we can check it *AFTER* the for-loop.
		
		for ( inx = 0; (inx < pinsDown.length) && (frame <= 10); ++inx )
		{
			
			final int knockedDown = pinsDown[inx];
			
			if (debug)
				System.out.println("DEBUG: BEFORE: inx is: " + inx
						+ " firstRoll is: " + firstRoll
						+ " and score is: " + totalScore
						+ " and frame is: " + frame 
						+ " and pins knocked down is: " + knockedDown );

			
			totalScore += knockedDown;
			
			int extra = 0;
			
			
			// BUG FIX #2: add test for firstRoll, so that testHundred() passes
			
			if (firstRoll && (knockedDown == 10) )  // a strike! (on FIRST roll).
			{
				int nextRoll = pinsDown[inx+1 ];
				int secondRoll = pinsDown[inx+2];
				extra = 2;
				totalScore += nextRoll + secondRoll;
				
				++frame;
			}
			else if (firstRoll)
			{
				// already added thisRoll to totalScore
				// toggle T->F
				
				firstRoll = false;	// give another ball to this frame
				scoreFirstBall = knockedDown;	// remember first pin so we can tell if 2nd pin adds up to 10 (spare)
			}
			else   // must be the second roll (within the same frame)
			{
				firstRoll = true;	// toggle F->T (because its the next frame)
				if ((scoreFirstBall + knockedDown) == 10)
				{
					// a spare, so add in next roll in NEXT frame
					totalScore += pinsDown[inx+1];
					extra = 1;
				}
				++ frame;	// BUG FIX #1 - move out of above IF test
				
			}
			
			if (frame > 10)
			{
				inx += extra;	// count extra rolls *AFTER* tenth frame
//				break;			// BUG FIX #3 and to handle wrong length array checkss
			}
			
			if (debug)
				System.out.println("DEBUG: AFTER : inx is: " + inx
						+ " firstRoll is: " + firstRoll
						+ " and score is: " + totalScore
						+ " and frame is: " + frame );

						
		}
		
		if (frame != 11)
			throw new IndexOutOfBoundsException( "array length is wrong, got "
					+ pinsDown.length
					+ " elements and got " + frame + " frames, but expect 10 frames"  );
		
		// make sure every slot in array was consumed properly.
		// Add this test for test90().
		
		if (inx != pinsDown.length)
			throw new IndexOutOfBoundsException( "extra rolls not used: inx = " + inx
					+ " pinsDown[" + inx + "]=" + pinsDown[inx] );
		
		if (showResult)
			System.out.println("RESULT: total frames is: " + frame
					+ " and totalScore is: " + totalScore );
		return totalScore;
	}
}
