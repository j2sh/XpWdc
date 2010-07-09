import junit.framework.TestCase;

// web forums suggest that for JUNIT 4.8.2 we should:
// 1. NOT extends TestCase (in 3.X this was okay).
// 2. Use annotations such as @Before, @After, @Test.
// 3. Method names should NOT contain "test" string.

// BUT I'm having trouble with this, in conjunction
// with ECLIPSE 3.1 and JDK 1.5.8.


public class JunitBowling extends TestCase
{

	// this is not the right way to test.
//	public static void main( String [] argv )
//	{
//		new MyTestClass().testSimple();
//	}

	// flag passed to BowlingGame constructor (true enables debugging, false disables)
	private boolean globalDebug;
	private boolean globalResult;
	
	// Q: why does @Before annotation not compile with JDK 1.5.8 ?
//	@Before
	public void setUp()
	{
		globalDebug = false;
		globalResult = false;
	}

	/**
	 * we can have multiple tests (4 below),
	 * and all must pass for entire method to pass.
	 * However, the first test that fails will
	 * cause other tests to be avoided.
	 * 
	 * If this method passes or fails, other
	 * test methods will still be run.
	 */
//	@Test
	public void testSimple()
	{
		System.out.println( "In MyTestClass::testSimple"  );
		assertTrue(true);
		assertFalse(false);
		assertTrue( 1 == 1 );
		assertFalse( 1 == 0 );
		assertEquals( (2+3), 5 );
	}
	

	/**
	 * worst player doesn't knock down any pins.
	 * 10 frames of {0, 0}, for a total of 20 balls
	 * rolled.
	 */
	public void testRottenPlayer()
	{
		int emptyArray [] = {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
				};
		
		System.out.println( "In MyTestClass::testRottenPlayer" );
		BowlingGame bowlingGame = new BowlingGame( globalDebug, globalResult );
		int score = bowlingGame.play_game( emptyArray );
		assertEquals( score, 0 );
	}
	
	
	/**
	 * a perfect game consists of 12 strikes in a row.
	 * (10*30) = 300.
	 * 
	 * There are two extra rolls (11th and 12th) because of
	 * the strike in frame 10.
	 */
	public void testPerfectGame()
	{
		int allStrikes [] = {
				10, 10, 10, 10, 10, 10, 10, 10, 10, 10,	// frames 1-10
				10, 10 }; // two extra rolls because of strike in frame 10
		
		System.out.println( "In MyTestClass::testPerfectGame" );
		BowlingGame bowlingGame = new BowlingGame( globalDebug, globalResult );
		int score = bowlingGame.play_game( allStrikes );
		assertEquals( score, 300 );
	}
	
	
	/**
	 * each frame has {0, 10} ten times, plus
	 * one extra roll of 0.
	 * 
	 * @return expect that playGames() will return
	 * a score of 100.
	 *
	 */
//	@Test
	public void test100Game()
	{
		int hundredScore [] = {
				0, 10,	// frame 1
				0, 10,
				0, 10,
				0, 10,
				0, 10,	// frame 5
				0, 10,
				0, 10,
				0, 10,
				0, 10,
				0, 10,	// frame 10
				0,
				};
		
		System.out.println( "In MyTestClass::test100Game" );
		BowlingGame bowlingGame = new BowlingGame( globalDebug, globalResult );
		int score = bowlingGame.play_game( hundredScore );
		assertEquals( score, 100 );
	}
	
	
	
	/**
	 * Each frame has a spare (2 balls = 10).
	 * {10+2) + (10+3) + .. + (10+9) + (10+1) + (10+0)=
	 * 10*10 + (2+3+4+5+6+7+8+9+1+0)
	 * = 100 + 46 = 146
	 */
	public void test145Game()
	{
		int score145 [] = {
				1, 9,	// frame 1
				2, 8,
				3, 7,
				4, 6,
				5, 5,	// frame 5
				6, 4,
				7, 3,
				8, 2,
				9, 1,
				1, 9,	// frame 10
				0,
				};
		
		System.out.println( "In MyTestClass::test145Game" );
		BowlingGame bowlingGame = new BowlingGame( globalDebug, globalResult );
		int score = bowlingGame.play_game( score145 );
		assertEquals( score, 145 );
	}
	

	/**
	 * test where each frame uses 2 balls which add
	 * up to less than 10 per frame.  Thus
	 * no addition of any extra balls from
	 * subsequent frame.
	 * 
	 * 10 frames, with 9 pins frame:
	 * 10*9 = 90.
	 *
	 */
	public void test90Game()
	{
		int score90 [] = {
				1, 8,	// frame 1
				2, 7,
				3, 6,
				4, 5,
				5, 4,	// frame 5
				6, 3,
				7, 2,
				8, 1,
				9, 0,
				1, 8,	// frame 10
//				0,		// this EXTRA is wrong!!
				};
		
		System.out.println( "In MyTestClass::test90Game" );
		BowlingGame bowlingGame = new BowlingGame( globalDebug, globalResult );
		int score = bowlingGame.play_game( score90 );
		assertEquals( score, 90 );
	}



	
//	@Test (expected=IndexOutOfBoundsException.class)
	public void wrongRollsCommon()
	{
		// make the array length wrong (9 rolls, instead of 10)
		int badRolls [] = {0, 0, 0, 0, 0, 0, 0, 0, 0 };
		
		System.out.println( "In MyTestClass::testWrongRolls" );
		BowlingGame bowlingGame = new BowlingGame( globalDebug, globalResult );
		int score = bowlingGame.play_game( badRolls );
		
		// score should NOT be returned IF exception is raised,
		// so use illegal negative score since we
		// will never NORMALLY get to the assertEquals().
		// But if we DO, use a negative value that
		// is guaranteed to be wrontg.
		
		assertEquals( score, -1 );
	}
	
	/**
	 * The annotations in 4.8.2 JUNIT don't seem to work with
	 * JDK 1.5.8, so I cannot use @Test annotation.
	 * To get around this, this method has the try-catch
	 * block, and the catch-block is expected to be
	 * hit.
	 *
	 */
	public void testWrongRollsExceptionExpected()
	{
		try
		{
			wrongRollsCommon();
			assertTrue( false );	// should never get here, cuz of exception
		}
		catch (IndexOutOfBoundsException ex )
		{
			assertTrue( true );	// good, we got the expected Exception !
		}
	}
	
	
}
