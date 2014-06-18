package player;


public class FNTest {
	
	public static void main(String[] args) {
		Test1();
		Test2();
		Test3();
		Test4();
		Test5();
		Test6();
		Test7();
		Test8();
		Test9();
		Test10();
		Test11();
		Test12();
		Test13();
		Test14();
		//Test15();
		Test16();
		Test17();
	}
	
	/**
	 * Test1: Testing a regular board where BLACK wins
	 * @return boolean
	 */
	
	//Regular Board, black wins
	static protected void Test1() {
		Gameboard board = new Gameboard();
		tPopulate(board, 2, 0, 0); 
		tPopulate(board, 2, 1, 1);
		tPopulate(board, 4, 1, 1);
		tPopulate(board, 7, 1, 1);
		tPopulate(board, 2, 2, 0);
		tPopulate(board, 4, 2, 0);
		tPopulate(board, 1, 3, 0);
		tPopulate(board, 5, 3, 1);
		tPopulate(board, 0, 4, 1);
		tPopulate(board, 4, 4, 1);
		tPopulate(board, 3, 5, 0);
		tPopulate(board, 1, 7, 0);
		System.out.println("Starting Test1");
		System.out.println(board.formsNetwork());
		
	}
	
	//Empty Board, neither wins
	static protected void Test2() {
		Gameboard board = new Gameboard();
		System.out.println("Starting Test2");
		System.out.println(board.formsNetwork());
	}
	
	//Regulard Board, White wins
	static protected void Test3() {
		Gameboard board = new Gameboard();
		tPopulate(board, 1, 0, 0);
		tPopulate(board, 3, 1, 0);
		tPopulate(board, 0, 2, 1);
		tPopulate(board, 2, 2, 1);
		tPopulate(board, 4, 2, 0);
		tPopulate(board, 5, 3, 1);
		tPopulate(board, 7, 3, 1);
		tPopulate(board, 2, 4, 1);
		tPopulate(board, 4, 4, 1);
		tPopulate(board, 2, 5, 0);
		tPopulate(board, 5, 5, 0);
		tPopulate(board, 3, 7, 0);
		System.out.println("Starting Test3");
		System.out.println(board.formsNetwork());
	}
	
	//Regular Board, White and Black both win
	static protected void Test4(){
		Gameboard board = new Gameboard();
		tPopulate(board, 2, 0, 0);
		tPopulate(board, 3, 1, 0);
		tPopulate(board, 0, 2, 1);
		tPopulate(board, 3, 2, 1);
		tPopulate(board, 4, 2, 1);
		tPopulate(board, 1, 3, 0);
		tPopulate(board, 1, 4, 1);
		tPopulate(board, 4, 4, 1);
		tPopulate(board, 3, 5, 0);
		tPopulate(board, 6, 5, 0);
		tPopulate(board, 7, 5, 1);
		tPopulate(board, 4, 7, 0);
		System.out.println("Starting Test 4");
		System.out.println(board.formsNetwork());
	}
	
	//Regular Board, White would have won but black blocked
	static protected void Test5() {
		Gameboard board = new Gameboard();
		tPopulate(board, 3, 0, 0);
		tPopulate(board, 0, 1, 1);
		tPopulate(board, 5, 1, 0);
		tPopulate(board, 6, 1, 0);
		tPopulate(board, 2, 3, 1);
		tPopulate(board, 4, 3, 1);
		tPopulate(board, 1, 5, 0);
		tPopulate(board, 4, 5, 1);
		tPopulate(board, 5, 5, 0);
		tPopulate(board, 6, 5, 1);
		tPopulate(board, 7, 4, 1);
		tPopulate(board, 4, 7, 0);
		System.out.println("Starting Test 5");
		System.out.println(board.formsNetwork());
	}
	
	//Black has a unblocked 7 link but not a six link
	static protected void Test6() {
		Gameboard board = new Gameboard();
		tPopulate(board, 4, 0, 0);
		tPopulate(board, 1, 1, 1);
		tPopulate(board, 0, 2, 1);
		tPopulate(board, 3, 2, 0);
		tPopulate(board, 6, 2, 0);
		tPopulate(board, 2, 3, 0);
		tPopulate(board, 5, 3, 0);
		tPopulate(board, 6, 3, 1);
		tPopulate(board, 1, 5, 1);
		tPopulate(board, 7, 5, 1);
		tPopulate(board, 2, 6, 0);
		tPopulate(board, 5, 6, 1);
		tPopulate(board, 2, 7, 0);
		System.out.println("Starting Test 6");
		System.out.println(board.formsNetwork());
	}
	
	//White would have a network but didn't follow turning rule
	static protected void Test7() {
		Gameboard board = new Gameboard();
		tPopulate(board, 4, 0, 0);
		tPopulate(board, 2, 1, 0);
		tPopulate(board, 5, 1, 0);
		tPopulate(board, 0, 2, 1);
		tPopulate(board, 2, 2, 1);
		tPopulate(board, 5, 2, 1);
		tPopulate(board, 7, 2, 1);
		tPopulate(board, 2, 4, 0);
		tPopulate(board, 4, 4, 1);
		tPopulate(board, 5, 5, 1);
		tPopulate(board, 4, 6, 0);
		tPopulate(board, 2, 7, 0);
		System.out.println("Starting Test 7");
		System.out.println(board.formsNetwork());
	}
	
	//8 by 8 both black and white win
	static protected void Test8() {
		Gameboard board = new Gameboard();
		tPopulate(board, 2, 0 ,0);
		tPopulate(board, 6, 0, 0);
		tPopulate(board, 3, 1, 0);
		tPopulate(board, 5, 1, 0);
		tPopulate(board, 0, 2, 1);
		tPopulate(board, 1, 2, 1);
		tPopulate(board, 4, 2, 1);
		tPopulate(board, 6, 2, 1);
		tPopulate(board, 5, 3, 0);
		tPopulate(board, 7, 3, 1);
		tPopulate(board, 0, 4, 1);
		tPopulate(board, 7, 4, 1);
		tPopulate(board, 3, 5, 0);
		tPopulate(board, 4, 5, 1);
		tPopulate(board, 3, 7, 0);
		tPopulate(board, 4, 7, 0);
		System.out.println("Starting Test8");
		System.out.println(board.formsNetwork());
	}
	
	//White wins if white could use two endgoals
	static protected void Test9() {
		Gameboard board = new Gameboard();
		tPopulate(board, 2, 0, 0);
		tPopulate(board, 3, 1, 1);
		tPopulate(board, 4, 1, 0);
		tPopulate(board, 0, 2, 1);
		tPopulate(board, 1, 3, 1);
		tPopulate(board, 5, 3, 1);
		tPopulate(board, 7, 3, 1);
		tPopulate(board, 1, 5, 0);
		tPopulate(board, 7, 5, 1);
		tPopulate(board, 2, 6, 0);
		tPopulate(board, 4, 6, 0);
		System.out.println("Starting Test9");
		System.out.println(board.formsNetwork());
	}
	
	//Black wins if black could use two start squares
	static protected void Test10() {
		Gameboard board = new Gameboard();
		tPopulate(board, 2, 0, 1);
		tPopulate(board, 4, 0, 1);
		tPopulate(board, 2, 1, 0);
		tPopulate(board, 5, 1, 0);
		tPopulate(board, 0, 2, 0);
		tPopulate(board, 4, 2, 1);
		tPopulate(board, 7, 2, 0);
		tPopulate(board, 2, 4, 1);
		tPopulate(board, 5, 4, 0);
		tPopulate(board, 1, 6, 0);
		tPopulate(board, 4, 6, 1);
		tPopulate(board, 4, 7, 1);
		System.out.println("Starting Test10");
		System.out.println(board.formsNetwork());
	}
	static protected void tPopulate(Gameboard gboard, int x, int y, int color) {
		Square square = new Square(x, y);
		square.setColor(color);
		gboard.setTest(square);
	}
	
	static protected void Test11() {
		System.out.println("Starting Test11");
		MachinePlayer player1 = new MachinePlayer(1, 3);
		player1.gameBoard.setSquare(new Move(0, 2));
		player1.gameBoard.setSquare(new Move(1, 0));
		player1.gameBoard.setSquare(new Move(2, 2));
		player1.gameBoard.setSquare(new Move(3, 1));
		player1.gameBoard.setSquare(new Move(2, 4));
		player1.gameBoard.setSquare(new Move(2, 5));
		player1.gameBoard.setSquare(new Move(5, 3));
		player1.gameBoard.setSquare(new Move(4, 2));
		player1.gameBoard.setSquare(new Move(4, 4));
		player1.gameBoard.setSquare(new Move(3, 7));
		Move move1 = new Move(7, 3);
		System.out.println(player1.evaluate(move1, 1, 0));
	}
	
	static protected void Test12() {
		System.out.println("Starting Test12");
		MachinePlayer player1 = new MachinePlayer(1, 3);
		player1.gameBoard.setSquare(new Move(0, 1));//w
		player1.gameBoard.setSquare(new Move(3, 0));//b
		player1.gameBoard.setSquare(new Move(2, 3));//w
		player1.gameBoard.setSquare(new Move(5, 1));//b
		player1.gameBoard.setSquare(new Move(4, 3));//w
		player1.gameBoard.setSquare(new Move(1, 5));//b
		player1.gameBoard.setSquare(new Move(6, 5));//w
		player1.gameBoard.setSquare(new Move(2, 6));//b
		player1.gameBoard.setSquare(new Move(7, 4));//w
		player1.gameBoard.setSquare(new Move(4, 7));//b
		player1.gameBoard.setSquare(new Move(1, 6));//w
		//player1.gameBoard.forceMove(new Move(4, 5), 1);//w
		Move move1 = new Move(4, 5);
		System.out.println(player1.evaluate(move1, 0, 1));
		//System.out.println(player1.gameBoard.numPieces());
	}
	
	static protected void Test13() {
		System.out.println("Starting Test13");
		MachinePlayer player1 = new MachinePlayer(1, 3);
		Move move1 = new Move(0, 1);
		System.out.println(player1.evaluate(move1, 1, 0));
		player1.gameBoard.setSquare(new Move(2, 2));//w
		player1.gameBoard.setSquare(new Move(1, 0));//b
		player1.gameBoard.setSquare(new Move(5, 3));//w
		player1.gameBoard.setSquare(new Move(4, 1));//b
		player1.gameBoard.setSquare(new Move(3, 5));//w
		player1.gameBoard.setSquare(new Move(1, 7));//b
		Move move2 = new Move(3, 3);
		System.out.println(player1.evaluate(move2, 1, 0));
		
	}
	
	static protected void Test15() {
		System.out.println("Starting Test15");
		MachinePlayer player1 = new MachinePlayer(0, 3);
		player1.gameBoard.setSquare(new Move(0, 2));//w
		player1.gameBoard.setSquare(new Move(1, 2));//b
		player1.gameBoard.setSquare(new Move(1, 1));//w
		player1.gameBoard.setSquare(new Move(1, 5));//b
		player1.gameBoard.setSquare(new Move(1, 4));//w
		player1.gameBoard.setSquare(new Move(2, 1));//b
		player1.gameBoard.setSquare(new Move(1, 6));//w
		player1.gameBoard.setSquare(new Move(2, 6));//b
		player1.gameBoard.setSquare(new Move(3, 1));//w
		player1.gameBoard.setSquare(new Move(3, 4));//b
		player1.gameBoard.setSquare(new Move(3, 3));//w
		player1.gameBoard.setSquare(new Move(4, 0));//b
		player1.gameBoard.setSquare(new Move(5, 1));//w
		player1.gameBoard.setSquare(new Move(4, 3));//b
		player1.gameBoard.setSquare(new Move(4, 4));//w
		player1.gameBoard.setSquare(new Move(4, 6));//b
		player1.gameBoard.setSquare(new Move(6, 6));//w
		player1.gameBoard.setSquare(new Move(5, 6));//b
		player1.gameBoard.setSquare(new Move(7, 4));//w
		player1.gameBoard.setSquare(new Move(6, 1));//b
		System.out.println(player1.gameBoard.formsNetwork());
		System.out.println(player1.gameBoard.numPieces());
		System.out.println("NumMoves1:" + player1.gameBoard.numMoves);
		System.out.println(player1.gameBoard.numPieces());
		player1.gameBoard.setSquare(new Move(2, 5, 1, 4));
		System.out.println(player1.gameBoard.numPieces());
		System.out.println(player1.gameBoard.numMoves);
		System.out.println(player1.gameBoard.isValidMove(new Move(5,2,4,3)));
		System.out.println(player1.evaluate(new Move(5, 2, 4, 3), 0, 1));
		System.out.println("NumMoves2: " + player1.gameBoard.numMoves);
		Move ourmove = player1.chooseMove();
		System.out.println(ourmove.x1 + " " + ourmove.y1 + " ");
		System.out.println(player1.gameBoard.numPieces());
		
	}
	
	static protected void Test14() {
		System.out.println("Starting Test14");
		MachinePlayer player1 = new MachinePlayer(1, 3);
		player1.gameBoard.setSquare(new Move(0, 1));//w
		player1.gameBoard.setSquare(new Move(3, 0));//b
		player1.gameBoard.setSquare(new Move(2, 3));//w
		player1.gameBoard.setSquare(new Move(5, 1));//b
		player1.gameBoard.setSquare(new Move(4, 3));//w
		player1.gameBoard.setSquare(new Move(1, 5));//b
		player1.gameBoard.setSquare(new Move(6, 5));//w
		player1.gameBoard.setSquare(new Move(2, 6));//b
		player1.gameBoard.setSquare(new Move(7, 4));//w
		player1.gameBoard.setSquare(new Move(4, 7));//b
		player1.gameBoard.setSquare(new Move(1, 6));//w
		player1.gameBoard.setSquare(new Move(1, 1));
		//System.out.println(player1.);
		Move ourmove = player1.chooseMove();
		System.out.println(ourmove.x1 + " " + ourmove.y1 + " ");
		
	} 
	
	static protected void Test16() {
		System.out.println("Starting Test16");
		MachinePlayer player1 = new MachinePlayer(1, 3);
		Move ourmove = player1.chooseMove();
		System.out.println(ourmove.x1 + " " + ourmove.y1 + " ");
		Square square1 = player1.gameBoard.getSquare(ourmove);
		System.out.println(player1.gameBoard.numPieces());
		System.out.println(square1.getXCoordinate() + " " + square1.getYCoordinate() + " " + square1.getColor());
	}
	/**
	 * 
	 */
	static protected void Test17() {
		System.out.println("Starting Test17");
		MachinePlayer player1 = new MachinePlayer(1, 3);
		player1.gameBoard.setSquare(new Move(1, 2));
		player1.gameBoard.setSquare(new Move(1, 0));
		player1.gameBoard.setSquare(new Move(2, 6));
		player1.gameBoard.setSquare(new Move(1, 1));
		player1.gameBoard.setSquare(new Move(7, 3));
		player1.gameBoard.setSquare(new Move(4, 1));
		player1.gameBoard.setSquare(new Move(7, 5));
		player1.gameBoard.setSquare(new Move(3, 2));
		Move move3 = new Move(2, 1);
		System.out.println(player1.evaluate(move3, 1, 0));
		
		
	}
		
	
	
	

}
