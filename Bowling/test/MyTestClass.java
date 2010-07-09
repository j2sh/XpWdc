import junit.framework.TestCase;

public class MyTestClass extends TestCase {

	public void ftest_one()
	{
		System.out.println( "In MyTestClass::test_one"  );
		assertTrue(true);
//		assert(false);
//		assert( 1 == 1 );
//		assert( 1 == 0 );
	}
	
	public void ftest_two()
	{
		System.out.println( "In MyTestClass::test_two"  );
//		assert(false);
		assertTrue( 1 == 1 );
	}
	
	public void ftestRottenPlayer()
	{
		int emptyArray [] = {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
				};
		
		System.out.println( "In MyTestClass::testRottenPlayer" );
		BowlingGame bowlingGame = new BowlingGame();
		int score = bowlingGame.play_game( emptyArray );
		assertEquals( score, 0 );
	}
	
	
	public void ftestPerfectGame()
	{
		int emptyArray [] = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
				10, 10 };
		
		System.out.println( "In MyTestClass::testPerfectGame" );
		BowlingGame bowlingGame = new BowlingGame();
		int score = bowlingGame.play_game( emptyArray );
		assertEquals( score, 300 );
	}
	
	public void ftestWrongRolls()
	{
		// make the array length wrong (9 rolls, instead of 10)
		int badRolls [] = {0, 0, 0, 0, 0, 0, 0, 0, 0 };
		
		System.out.println( "In MyTestClass::testWrongRolls" );
		BowlingGame bowlingGame = new BowlingGame();
		int score = bowlingGame.play_game( badRolls );
		assertEquals( score, 300 );

		
	}
}
