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
  // returns a square given x and y coordinates
  protected Square getSquare(int x, int y){
    return gameBoard[x][y];
  }
  // checks if a move follows the four rules of Network, returns true if it does,
  // returns false otherwise
  protected boolean isValidMove(Move move){
    if(move.moveKind == Move.QUIT){
      return true;
    }
    else if((move.moveKind == Move.ADD && numMoves >= 20) || (move.moveKind == Move.STEP && numMoves < 20)){
      return false;
    }

    Square potentialMove = this.getSquare(move.x1, move.y1);
    
    else if(potentialMove.getX() > 7 || potentialMove.getY() > 7){
      return false;
    }
    else if(move.moveKind == STEP && potentialMove.getX() == move.x2 && potentialMove.getY() == move.y2){
      return false;
    }
    else if(potentialMove.isCorner()){
      return false;
    }
    else if(potentialMove.inOppositeGoal()){
      return false;
    }
    else if(potentialMove.isOccupied()){
      return false;
    }
    else if(potentialMove.createsConnectedGroup()){
      return false;
    }
    else{
      return true;
    }
  }
  // Returns false if move is into a corner (Rule #1)
  protected boolean isCorner(){
    return ((this.getX()) == 0 && this.getY == 0) || (this.getX() == 0 && this.getY() == 7) || 
    (this.getX() == 7 && this.getY() == 0) || (this.getX() == 7 && this.getY() == 7));
  }
  // Returns false if move is into opponents goal area (Rule #2)
  protected boolean inOppositeGoal(){
    return ((this.getColor == 0 && (this.getX() == 0 || this.getY() == 7)) || (this.getColor == 1 && (this.getY() == 0 || this.getY() == 7)));
  }
  // Returns false if move is into an already occupied space (Rule #3)
  protected boolean isOccupied(){
    return this.color != -1;
  }

  //Returns false if move creates a group of more than 2 chips (Rule #4)
  protected boolean createsConnectedGroup(){
    Square surroundings = this.getSurroundingSquares();
    int sameColorSurroundings = 0;
    for(int i = 0; i< surroundings.length(); i++){
      if(surroundings[i].color == color){
        sameColorSurroundings++;
      }
    }
    if(sameColorSurroundings >= 2){
      return true;
    }
    else{
      return false;
    }
  }

  protected Square[] getSurroundingSquares(){
    Square[] surroundings;
    if(this.getY() == 0){
      surroundings = new Square[5];
      surroundings[0] = getSquare(this.getX()-1, this.getY());
      surroundings[1] = getSquare(this.getX()+1, this.getY());
      surroundings[2] = getSquare(this.getX(), this.getY()-1);
      surroundings[3] = getSquare(this.getX()+1, this.getY()-1);
      surroundings[4] = getSquare(this.getX()-1, this.getY()-1);
      }
    else if(this.getY() == 7){
      surroundings = new Square[5];
      surroundings[0] = getSquare(this.getX()-1, this.getY());
      surroundings[1] = getSquare(this.getX()+1, this.getY());
      surroundings[2] = getSquare(this.getX(), this.getY()-1);
      surroundings[3] = getSquare(this.getX()+1, this.getY()+1);
      surroundings[4] = getSquare(this.getX()-1, this.getY()+1);
    }
    else if(this.getX() == 0){
      surroundings = new Square[5];
      surroundings[0] = getSquare(this.getX(), this.getY()+1);
      surroundings[1] = getSquare(this.getX(), this.getY()-1);
      surroundings[2] = getSquare(this.getX()+1, this.getY());
      surroundings[3] = getSquare(this.getX()+1, this.getY()-1);
      surroundings[4] = getSquare(this.getX()+1, this.getY()+1);
    }
    else if(this.getY() == 7{
      surroundings = new Square[5];
      surroundings[0] = getSquare(this.getX(), this.getY()+1);
      surroundings[1] = getSquare(this.getX(), this.getY()-1);
      surroundings[2] = getSquare(this.getX()-1, this.getY());
      surroundings[3] = getSquare(this.getX()-1, this.getY()-1);
      surroundings[4] = getSquare(this.getX()-1, this.getY()+1);
    }
    else{
      surroundings = new Square[8];
      surroundings[0] = getSquare(this.getX()-1, this.getY());
      surroundings[1] = getSquare(this.getX()-1, this.getY()+1);
      surroundings[2] = getSquare(this.getX()-1, this.getY()-1);
      surroundings[3] = getSquare(this.getX()+1, this.getY());
      surroundings[4] = getSquare(this.getX()+1, this.getY()+1);
      surroundings[5] = getSquare(this.getX()+1, this.getY()-1);
      surroundings[6] = getSquare(this.getX(), this.getY()+1);
      surroundings[7] = getSquare(this.getX(), this.getY()-1);
    }
    return surroundings;
    }


  // generating a list of all valid moves based on the current Gameboard board 
  protected DList allValidMoves(){
    DList validMoves = new DList();
    Move test;
    int i;
    int j;

    if(numMoves < 20){
      for(i = 0; i<8; i++){
        for(j = 0; j<8; j++){
          test = new Move(i,j);
          if(isValidMove(test)){
            validMoves.insertBack(test);
          }
        }
      }

    }else if(color == 0){
      for(i = 0; i<blackLocation.length; i++){
        for(j = 0; j<blackLocation[i].getSurroundingSquares().length; j++){
          test = new Move((blackLocation[i].getSurroundingSquares())[j].getX(),(blackLocation[i].getSurroundingSquares())[j].getY(),
          blackLocation[i].getX(),blackLocation[i].getY());
          if(isValidMove(test)){
            validMoves.insertBack(test);
          }
        }
      }

    }else{
      for(i = 0; i<blackLocation.length; i++){
        for(j = 0; j<blackLocation[i].getSurroundingSquares().length; j++){
          test = new Move((blackLocation[i].getSurroundingSquares())[j].getX(),(blackLocation[i].getSurroundingSquares())[j].getY(),
          blackLocation[i].getX(),blackLocation[i].getY());
          if(isValidMove(test)){
            validMoves.insertBack(test);
          }
        }
      }
    }

    return validMoves;
  }
  // Returns a new move by "this" player.  Internally records the move (updates
  // the internal game board) as a move by "this" player.
  public Move chooseMove() {
    
  } 
  // performing minimax tree search on all the valid moves(implement alpha/
  // beta pruning) 
  protected Move minimax(Square[] options, int depth, double alpha, double beta, boolean maximizingPlayer){
    DList moves = option.item.allValidMoves();
    if(depth == 0 || options.next == null){
      return evaluation(option.item);
    }
    if(maximizingPlayer){
      for(DListNode move: moves){
        alpha = max(alpha, minimax(move, depth - 1, alpha, beta, false));
        if(beta <= alpha){
          break;
        }
      return alpha;
      }
    }
    else{
      for(DListNode move: moves){
        beta = min(beta, minimax(move, depth - 1, alpha, beta, true));
        if(beta <= alpha){
          break;
        }
      return beta;
      }
    }
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
