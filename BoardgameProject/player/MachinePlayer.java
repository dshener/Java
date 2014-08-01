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
    gameBoard.setSquare(bestMove.move, color);
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
    if(maximizingPlayer) {
    	 moves = gBoard.allValidMoves(color);
    } else {
    	 moves = gBoard.allValidMoves(opponentColor);
    	 coefficient = -1;
    	 clr = opponentColor;
    	 oppclr = color;
    }
    if (gBoard.hasWin()) {
      return new Best(new Move(), evaluate(gBoard, clr, oppclr)*coefficient); //return a Best with grid score, no move
    }
    if(depth == 0){
      return new Best(null, evaluate(gBoard, clr, oppclr)*coefficient); // return heuristic value of node
    }
    if (maximizingPlayer) {
      myBest.score = alpha;
    } else {
      myBest.score = beta;
    }
    DListNode m = moves.head;
    while (m != null) {

      Gameboard copyBoard = gBoard.copyBoard();
      copyBoard.setSquare((Move)m.item,clr); // Modifies "this" Grid
      reply = minimax(!maximizingPlayer, alpha, beta, depth -1, copyBoard);
      //copyBoard.removeSquare((Move)m.item); // Restores "this" Grid
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
        { 
    	  return myBest;
    	}
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
    if (gameBoard.isValidMove(m,opponentColor)) {
    	gameBoard.setSquare(m, opponentColor); 
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
    if (gameBoard.isValidMove(m,color)) {
    	gameBoard.setSquare(m,color);
    	return true;
    } else {
    	return false;
    }
  }
  
  protected boolean wouldConnect(Gameboard gBoard, int clr, int oppcolor) {
	  Gameboard boardholder = gameBoard.copyBoard();
	  DList moves = boardholder.allValidMoves(oppcolor);
	  DListNode mhead = moves.head;
	  while (mhead != null) {
			  boardholder.setSquare((Move)mhead.item, oppcolor);
			  if (boardholder.formsNetwork2(oppcolor)) {
				  return true;
			  }
			  boardholder.removeSquare((Move)mhead.item, clr);
			  mhead = mhead.next;
	  } 
	  return false;
  }

  protected int evaluate(Gameboard gBoard, int color, int opponentColor){
		int score = 0;
		if(gBoard.formsNetwork2(color)){
			return 100;
		}else if (gBoard.formsNetwork2(opponentColor)){
			return -100;
		}
		if (wouldConnect(gBoard, color, opponentColor)) {
			return -75;
		}
		int numBlackConnections = 0;
		int numWhiteConnections = 0;
		int count = 0;
		Square head = gBoard.blackLocation()[count];
		while(head != null){
			for(int i = 0; i < gBoard.formConnection(head).length; i++){
				if(gBoard.formConnection(head) != null){
					numBlackConnections++;
				}
			}count++;
			 head = gBoard.blackLocation()[count];
		}
		count = 0;
		head = gBoard.whiteLocation()[count];
		while(head != null){
			for(int i = 0; i < gBoard.formConnection(head).length; i++){
				if(gBoard.formConnection(head) != null){
					numWhiteConnections++;
				}
			}count++;
			 head = gBoard.whiteLocation()[count];
		}
		if(color == 0 && (numBlackConnections - numWhiteConnections) >= 0){
			score += (numBlackConnections - numWhiteConnections) * 5;
		}else if (color == 1 && (numWhiteConnections - numBlackConnections) >= 0){
			score += (numWhiteConnections - numBlackConnections) * 5;
		}
		if(gBoard.inEndzone(color)){
			score += 15;
		}else if(gBoard.inEndzone(opponentColor)){
			score -= 5;
		}
		if((gBoard.allValidMoves(color).getSize() - gBoard.allValidMoves(opponentColor).getSize()) != 0){
			score += (gBoard.allValidMoves(color).getSize() - gBoard.allValidMoves(opponentColor).getSize()) * 2;
		}
		return score;
  }
}
  /**
//!!!!MOVE TO MACHINEPLAYER!!!!
	//Determines the value of a given "move" using "this" gameboard
	protected int evaluate(Move move, int color, int opponentColor, Gameboard gBoard){
		int score = 0;
		gBoard.setSquare(move); 
		/* Checks if "move" causes a win */ 
  /**
		if(gBoard.formsNetwork2(color)){ 
			score = 100;
			gBoard.removeSquare(move);
			return score;
		} gBoard.removeSquare(move);
		/* checks if opponent will win and if "move" will stop it */
  /**
		gBoard.numMoves++;
		DListNode head = gBoard.allValidMoves(opponentColor).getHead();
		gBoard.numMoves--;
		while(head != null){ 
			gBoard.forceMove((Move)head.item, opponentColor);
			Square dummy = gBoard.getSquareEval((Move)head.item);
			int dummyX = dummy.getXCoordinate(); 
			int dummyY = dummy.getYCoordinate();
			if(gBoard.formsNetwork2(opponentColor) && move.x1 == dummyX && move.y1 == dummyY){ 
					gBoard.forceRemove((Move)head.item, opponentColor);
					score = 100;
					return score;
				} 
				gBoard.forceRemove((Move)head.item, opponentColor);
				head = head.getNext();
			}
		//if move forms a connection, score increases depending on how many connections it forms, otherwise it decreases
		gBoard.setSquare(move); 
		int neighbors = 0;
		for(int i = 0; i < gBoard.formConnection(gBoard.getSquare(move)).length; i++){
			if(gBoard.formConnection(gBoard.getSquare(move))[i] != null){
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
		gBoard.removeSquare(move); 
		endzone1:
		if(move.x1 == 0){  
			for(int y = 1; y < 7; y++){
				if(gBoard.getSquare(0, y).getColor() != -1){
					break endzone1;
				}
			}score += 15; 
		}endzone2:	
		if(move.x1 == 7){ 
			for(int y = 1; y < 7; y++){
				if(gBoard.getSquare(7, y).getColor() != -1){
					break endzone2;
				}
			}score += 15; 
		}endzone3:
		if(move.y1 == 0){ 
			for(int x = 1; x < 7; x++){
				if(gBoard.getSquare(x, 0).getColor() != -1){
					break endzone3;
				}
			}score += 15; 
		}endzone4:
		if(move.y1 == 7){  
			for(int x = 1; x < 7; x++){
				if(gBoard.getSquare(x, 7).getColor() != -1){
					break endzone4;
				}
			}score += 15; 
		}/*Checks if "move" diminshes the opponent's available connections by 
		comparing the length of opponent's allValidMoves before the "move" is made and after **/
  /**
		int before = 0;
		gBoard.numMoves++;
		DListNode square = gBoard.allValidMoves(opponentColor).getHead();
		gBoard.numMoves--;
		while(square != null){
			before += 1;
			square = square.getNext();
		}gBoard.setSquare(move);
		int after = 0;
		square = gBoard.allValidMoves(opponentColor).getHead();
		while(square != null){
			after += 1;
			square = square.getNext();
		}gBoard.removeSquare(move);
		if((before - after) == 1){
			score -= 3;
		}else if((before - after) >= 2){
			score += 3;
		}else if((before - after) >= 4){
			score += 7;
		}else if((before - after) >= 6){
			score += 10;
		}
		for(int x = move.x1 - 1; x < move.x1 + 2; x++){
			for(int y = move.y1 - 1; y < move.y1 + 2; y++){
				if(x > 0 && y > 0 && x < 8 && y < 8 && gBoard.getSquare(x,y).getColor() == opponentColor){
					int connections = 0;
					for(int i = 0; i < gBoard.formConnection(gBoard.getSquare(x,y)).length; i++){
						if(gBoard.formConnection(gBoard.getSquare(x,y))[i] != null){
							connections++;
						}
					}
					gBoard.setSquare(move);
					int newConnections = 0;
					for(int i = 0; i < gBoard.formConnection(gBoard.getSquare(x,y)).length; i++){
						if(gBoard.formConnection(gBoard.getSquare(x,y))[i] != null){
							newConnections++;
						}
					}gBoard.removeSquare(move);
					if(connections - newConnections != 0){
						score += (connections - newConnections) * 4;
					}
				}
			}
		}
	return score;	
	}

}
**/

