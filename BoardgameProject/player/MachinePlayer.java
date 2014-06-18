package player;

/* MachinePlayer.java */


/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {

  private int color;
  private int searchDepth;
  protected Gameboard gameBoard;
  private int numMoves;
  private Move best;
  private int opponentColor;

  // Creates a machine player with the given color.  Color is either 0 (black)
  // or 1 (white).  (White has the first move.)
  public MachinePlayer(int color) {
    this(color, 1);
  }

  // Creates a machine player with the given color and search depth.  Color is
  // either 0 (black) or 1 (white).  (White has the first move.)
  public MachinePlayer(int color, int searchDepth) {
    this.searchDepth = searchDepth;
    numMoves= 0;
    if(color == 0){
      this.color = 0;
      opponentColor = 1;
    }
    else if(color == 1){
      this.color = 1;
      opponentColor = 0;
    }
    else{
      System.out.println("Not a valid color!");
    }
    gameBoard = new Gameboard();
  }
  
  public Move chooseMove() {
    Best bestMove = minimax(true, Integer.MIN_VALUE, Integer.MAX_VALUE, searchDepth, gameBoard);
    System.out.println(bestMove.score);
    gameBoard.setSquare(bestMove.move);
    return bestMove.move;

  } 


  // performing minimax tree search on all the valid moves(implement alpha/
  // beta pruning) 
  private Best minimax(boolean maximizingPlayer, int alpha, int beta, int depth, Gameboard gBoard) {
    Best myBest = new Best(); // My best move
    Best reply; // Opponents best reply
    DList moves;
    int clr = color;
    int oppclr = opponentColor;
    int coefficient = 1;
    //System.out.println("Alpha: " + alpha + "," + "Beta: " + beta);
    if(maximizingPlayer) {
    	 moves = gBoard.allValidMoves(color);
    } else {
    	 moves = gBoard.allValidMoves(opponentColor);
    	 coefficient = -1;
    	 clr = opponentColor;
    	 oppclr = color;
    }
    if (gBoard.hasWin()) {
      //System.out.println(alpha);
      //System.out.println("hasWin:" + moves.head.item);
      return new Best(new Move(), evaluate((Move)moves.head.item, clr, oppclr)*coefficient); //return a Best with grid score, no move
    }
    if(depth == 0){
      //System.out.println("depth == 0");
      return new Best(null, evaluate((Move)moves.head.item, clr, oppclr)*coefficient); // return heuristic value of node
    }
    if (maximizingPlayer) {
      myBest.score = alpha;
    } else {
      myBest.score = beta;
    }
    DListNode m = moves.head;
    while (m != null) {
      //System.out.println(m.item);
      gBoard.setSquare((Move)m.item); // Modifies "this" Grid
      reply = minimax(!maximizingPlayer, alpha, beta, depth -1, gBoard);
      gBoard.removeSquare((Move)m.item); // Restores "this" Grid
      if (maximizingPlayer && reply.score > myBest.score) {
        myBest.move = (Move)m.item;
        myBest.score = reply.score;
        alpha = reply.score;
      } else if (!maximizingPlayer && reply.score < myBest.score) {
        myBest.move = (Move)m.item;
        myBest.score = reply.score;
        beta = reply.score;
      }
      if (alpha >= beta) 
        { return myBest; }
      m = m.next;
}
return myBest;
}


  // If the Move m is legal, records the move as a move by the opponent
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method allows your opponents to inform you of their moves.
  //Boolean 
  public boolean opponentMove(Move m) {
    if (gameBoard.isValidMove(m)) {
    	gameBoard.setSquare(m); 
    	return true;
    } else {
    	return false;
    }
  }

  // If the Move m is legal, records the move as a move by "this" player
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method is used to help set up "Network problems" for your
  // player to solve.
  public boolean forceMove(Move m) {
    if (gameBoard.isValidMove(m)) {
    	gameBoard.setSquare(m);
    	return true;
    } else {
    	return false;
    }
  }
  
//!!!!MOVE TO MACHINEPLAYER!!!!
	//Determines the value of a given "move" using "this" gameboard
	protected int evaluate(Move move, int color, int opponentColor){
		int score = 0;
		gameBoard.setSquare(move); 
		/* Checks if "move" causes a win */ 
		if(gameBoard.formsNetwork2(color)){ 
			score = 100;
			gameBoard.removeSquare(move);
			return score;
		} gameBoard.removeSquare(move);
		/* checks if opponent will win and if "move" will stop it */
		gameBoard.numMoves++;
		DListNode head = gameBoard.allValidMoves(opponentColor).getHead();
		gameBoard.numMoves--;
		while(head != null){ 
			gameBoard.forceMove((Move)head.item, opponentColor);
			Square dummy = gameBoard.getSquareEval((Move)head.item);
			int dummyX = dummy.getXCoordinate(); 
			int dummyY = dummy.getYCoordinate();
			if(gameBoard.formsNetwork2(opponentColor) && move.x1 == dummyX && move.y1 == dummyY){ 
					gameBoard.forceRemove((Move)head.item, opponentColor);
					score = 100;
					return score;
				} 
				gameBoard.forceRemove((Move)head.item, opponentColor);
				head = head.getNext();
			}
		//if move forms a connection, score increases depending on how many connections it forms, otherwise it decreases
		gameBoard.setSquare(move); 
		int neighbors = 0;
		for(int i = 0; i < gameBoard.formConnection(gameBoard.getSquare(move)).length; i++){
			if(gameBoard.formConnection(gameBoard.getSquare(move))[i] != null){
				neighbors++;
			}
		}if(neighbors == 0){
			score -= 4; 
		}else if(neighbors == 1){
			score += 5; 
		}else if(neighbors == 2){
			score += 8; 	
		}else if(neighbors == 3){
			score += 12; 
		}else if(neighbors >= 4){
			score += 15; 
		}else if(neighbors > 5){
			score += 15; 
		}//Checks if there are squares in the endzones if "move" is in an endzone
		gameBoard.removeSquare(move); 
		endzone1:
		if(move.x1 == 0){  
			for(int y = 1; y < 7; y++){
				if(gameBoard.getSquare(0, y).getColor() != -1){
					break endzone1;
				}
			}score += 15; 
		}endzone2:	
		if(move.x1 == 7){ 
			for(int y = 1; y < 7; y++){
				if(gameBoard.getSquare(7, y).getColor() != -1){
					break endzone2;
				}
			}score += 15; 
		}endzone3:
		if(move.y1 == 0){ 
			for(int x = 1; x < 7; x++){
				if(gameBoard.getSquare(x, 0).getColor() != -1){
					break endzone3;
				}
			}score += 15; 
		}endzone4:
		if(move.y1 == 7){  
			for(int x = 1; x < 7; x++){
				if(gameBoard.getSquare(x, 7).getColor() != -1){
					break endzone4;
				}
			}score += 15; 
		}/*Checks if "move" diminshes the opponent's available connections by 
		comparing the length of opponent's allValidMoves before the "move" is made and after */
		int before = 0;
		gameBoard.numMoves++;
		DListNode square = gameBoard.allValidMoves(opponentColor).getHead();
		gameBoard.numMoves--;
		while(square != null){
			before += 1;
			square = square.getNext();
		}gameBoard.setSquare(move);
		int after = 0;
		square = gameBoard.allValidMoves(opponentColor).getHead();
		while(square != null){
			after += 1;
			square = square.getNext();
		}gameBoard.removeSquare(move);
		if((before - after) == 1){
			score -= 3;
		}else if((before - after) >= 2){
			score += 3;
		}else if((before - after) >= 4){
			score += 7;
		}else if((before - after) >= 6){
			score += 10;
		}
		connectionBreaker:
		for(int x = move.x1 - 1; x < move.x1 + 2; x++){
			for(int y = move.y1 - 1; y < move.y1 + 2; y++){
				if(x > 0 && y > 0 && x < 8 && y < 8 && gameBoard.getSquare(x,y).getColor() == opponentColor){
					int connections = 0;
					for(int i = 0; i < gameBoard.formConnection(gameBoard.getSquare(x,y)).length; i++){
						if(gameBoard.formConnection(gameBoard.getSquare(x,y))[i] != null){
							connections++;
						}
					}
					gameBoard.setSquare(move);
					int newConnections = 0;
					for(int i = 0; i < gameBoard.formConnection(gameBoard.getSquare(x,y)).length; i++){
						if(gameBoard.formConnection(gameBoard.getSquare(x,y))[i] != null){
							newConnections++;
						}
					}gameBoard.removeSquare(move);
					if(connections - newConnections != 0){
						score += (connections - newConnections) * 4;
						//break connectionBreaker;
					}
				}
			}
		}
	return score;	
	}

}

