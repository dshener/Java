/* MachinePlayer.java */

package player;

/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {

  private int color;
  private int searchDepth;
  private Board gameBoard;
  private int numMoves;
  private Move best;

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
    }
    else if(color == 1){
      this.color = 1;
    }
    else{
      System.out.println("Not a valid color!");
    }
  }
  
  public Move chooseMove() {
    Best bestMove = minimax(true, Integer.MIN_VALUE, Integer.MAXVALUE, searchDepth);
    gameBoard.makeMove(bestMove.move);
    return bestMove.move; 

  } 


  // performing minimax tree search on all the valid moves(implement alpha/
  // beta pruning) 
  public Best minimax(boolean maximizingPlayer, int alpha, int beta, int depth) {
    Best myBest = new Best(); // My best move
    Best reply; // Opponent’s best reply
    DList moves = gameBoard.allValidMoves();

    if (gameBoard.hasWin()) {
      return new Best(new Move(), gameBoard.evaluate()); //return a Best with grid score, no move
    }
    if(depth == 0){
      return new Best(null, gameBoard.evaluate) // return heuristic value of node
    }
    if (maximizingPlayer) {
      myBest.score = alpha;
    } else {
      myBest.score = beta;
    }
    myBest.move = new Move();
    DListNode m = moves.head;
    while(m != null) {
      board.makeMove(m); // Modifies "this" Grid
      reply = minimax(! maximizingPlayer, alpha, beta, depth -1);
      board.undoMove(m); // Restores "this" Grid
      if (maximizingPlayer && reply.score > myBest.score) {
        myBest.move = m.item;
        myBest.score = reply.score;
        alpha = reply.score;
      } else if (! maximizingPlayer && reply.score < myBest.score) {
        myBest.move = m.item;
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
  public boolean opponentMove(Move m) {
    return board.makeMove(m);
  }

  // If the Move m is legal, records the move as a move by "this" player
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method is used to help set up "Network problems" for your
  // player to solve.
  public boolean forceMove(Move m) {
    return board.makeMove(m);
  }

}
