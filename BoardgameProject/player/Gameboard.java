package player;

/* Gameboard creates an accurate representation of the current gameboard of the 
 * Network game by storing every move made by each player inside of a 2-dimensional array.
 */
 
public class Gameboard{
	
	
	private Square[][] board = new Square[8][8]; 
	protected int numBlack;
	protected int numWhite;
	
	/**
	 * empty constructor for a gameboard
	 */
	public Gameboard() {
		for(int x =0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				board[x][y] = new Square(x, y);
			}
		}
	}
	
	/**
	 *  Iterates through entire board and returns an exact copy of "this" gameboard 
	 * @returns a square[][] of this Gameboard's representation
	 */
	protected Gameboard copyBoard() {
		Gameboard copy = new Gameboard();
		for (int i = 0; i < 8; i++){
			for (int j =0; j < 8; j++){
				int clr = this.board[i][j].getColor();
				copy.board[i][j] = (new Square(i,j,clr));

			}
		}
		copy.numWhite = numWhite;
		copy.numBlack = numBlack;
		return copy;
	}
	
	


	
	/* Sets a square on to the board with given "color" and coordinates given by
	 * "move. Only use if you're going to remove right after!
	 */
	//
	protected void forceMove1(Move move,int color){
		int x = move.x1;
		int y = move.y1;
		if(move.moveKind == 2){
			board[move.x2][move.y2] = new Square(move.x2, move.y2);
			board[x][y] = new Square(x, y, color);
		}else{
			board[x][y] = new Square(x, y, color);
		}
	}
	
	/* forces a move on this gameboard
	 * no return
	 */
	protected void forceRemove(Move move, int color){
		int x = move.x1;
		int y = move.y1;
		if(move.moveKind == 2){
			board[move.x2][move.y2] = new Square(move.x2, move.y2, color);
			board[x][y] = new Square(x, y);
		}else{
			board[x][y] = new Square(x,y);
		}
	}
	
	
	//Updates "this" Gameboard to include the inputted move
	//CHECK NUM MOVES
	protected void setSquare(Move move, int clr){
		if (!isValidMove(move,clr)) {
			return;
		}
		if(move.moveKind == 1){
			board[move.x1][move.y1].setColor(clr);
		}
		else{
			board[move.x2][move.y2].setColor(-1);
			board[move.x1][move.y1].setColor(clr);
			return;
		}
		if(clr == 0){
			numBlack++;
		}else{
			numWhite++;
		}
	}
	
	
	
	//Returns the square determined by this move, used by eval
	protected Square getSquareEval(Move move) {
		if (move.moveKind == 2) {
			int x = move.x1;
			int y = move.y1;
			return board[x][y];
		} else {
		int x = move.x1;
		int y = move.y1;
		return board[x][y];
	}
	}
	
	//Returns the square determined by this move, used by all else
	protected Square getSquare(Move move){
		if (move.moveKind == 2) {
			int x = move.x2;
			int y = move.y2;
			return board[x][y];
		} else {
		int x = move.x1;
		int y = move.y1;
		return board[x][y];
		}
	}
	//Returns the Square at the x and y coordinates
	protected Square getSquare(int x, int y){
    	return board[x][y];
  	}
	
	//Updates "this" Gameboard to remove whatever piece is at the coordinates of move
	protected void removeSquare(Move move, int clr){
		if (!isValidMove(move,clr)) {
			return;
		}
		if(move.moveKind == 2){
			board[move.x2][move.y2].setColor(clr);
			board[move.x1][move.y1].setColor(-1);
		}else{
			board[move.x1][move.y1].setColor(-1);
		}
		if(clr == 0){
			numBlack--;
		}else{
			numWhite--;
		}
	}
	
	//Returns an array off all Black squares on the board
	protected Square[] blackLocation(){
		Square[] black = new Square[10];
		int i = 0;
		loop:
			for(int x = 0; x < 8; x++){
				for(int y = 0; y < 8; y++){
					if (board[x][y].getColor() == 0){
						black[i] = board[x][y];
						i++;
						if(i >= 10){
							break loop;
						}
					}
				}
			}
		return black;
	}

	//Returns an array off all White squares on the board
	protected Square[] whiteLocation(){
		Square[] white = new Square[10];
		int i = 0;
		loop:
			for(int x = 0; x < 8; x++){
				for(int y = 0; y < 8; y++){
					if (board[x][y].getColor() == 1){
						white[i] = board[x][y];
						i++;
						if(i >= 10){
							break loop;
						}
					}
				}
			}
		return white;
	}
	
	/*Returns an array of the squares directly above or below the inputted square
	 * if they form a connection */
	private Square[] vertConnect(Square square){
		Square[] neighbors = new Square[2];
		int count = 0;
		int x = square.getXCoordinate();
		loop:
		for(int y = square.getYCoordinate() + 1; y < 8; y++){
			if (board[x][y].getColor() != -1){
				if(board[x][y].getColor() == square.getColor()){
					neighbors[count] = board[x][y];
					count++;
					break loop;
				} else {
					break loop;
				}
			}
		}
		loop2:
		for(int y = square.getYCoordinate() - 1; y >= 0; y--){
			if (board[x][y].getColor() != -1){
				if(board[x][y].getColor() == square.getColor()){
					neighbors[count] = board[x][y];
					count++; //unnecessary 
					break loop2;
				} else {
					break loop2;
				}
			}
		}
		return neighbors;
	}
		
	/*Returns an array of the squares directly right or left the inputted square if
	* they form a connection */
	private Square[] horizConnect(Square square){
		Square[] neighbors = new Square[2];
		int count = 0;
		int y = square.getYCoordinate();
		loop:
		for(int x = square.getXCoordinate() + 1; x < 8; x++){
			if (board[x][y].getColor() != -1){
				if(board[x][y].getColor() == square.getColor()){
					neighbors[count] = board[x][y];
					count++;
					break loop;
				} else {
					break loop;
				}
			}
		}
		loop2:
		for(int x = square.getXCoordinate() - 1; x >= 0; x--){
			if (board[x][y].getColor() != -1){
				if(board[x][y].getColor() == square.getColor()){
					neighbors[count] = board[x][y];
					count++; //unnecessary
					break loop2;
				} else {
					break loop2;		
				}
			}
		}
		return neighbors;
	}
	
	/* Returns an array of the squares located diagonally from the inputted square if 
	* they form a connection */
	private Square[] diagConnect(Square square){
		Square[] neighbors = new Square[4];
		int count = 0;
		int x = square.getXCoordinate();
		int y = square.getYCoordinate();
		loop1:
		for(int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--){ //upper-left diagonal
			if(board[i][j].getColor() != -1){
				if(board[i][j].getColor() == square.getColor()){
					neighbors[count] = board[i][j];
					count++;
					break loop1;
				} else {
					break loop1;	
				}
			}
		}
		loop2:
		for(int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++){ //bottom-left diagonal
			if(board[i][j].getColor() != -1){
				if(board[i][j].getColor() == square.getColor()){
					neighbors[count] = board[i][j];
					count++;
					break loop2;
				} else {
					break loop2;
				}
			}
		}
		loop3:
		for(int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--){ //upper-right diagonal
			if(board[i][j].getColor() != -1){
				if(board[i][j].getColor() == square.getColor()){
					neighbors[count] = board[i][j];
					count++;
					break loop3;
				} else {
					break loop3;
				}
			}
		}
		loop4:
		for(int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++){ //bottom-right diagonal
			if(board[i][j].getColor() != -1){
				if(board[i][j].getColor() == square.getColor()){
					neighbors[count] = board[i][j];
					count++; //unnecessary
					break loop4;
				} else {
					break loop4;
				}
			}
		}
		return neighbors;
	}
	
	/* Returns an array of all the squares that form a connection with "square" 
	* Only returns the squares connected to the inputted square, not the entire connection */
	protected Square[] formConnection(Square square){
		Square[] neighbors = new Square[8];
		int count = 0;
		for(int i = 0; i < vertConnect(square).length; i++){
			neighbors[count] = vertConnect(square)[i];
			count++;
		}
		for(int i = 0; i < horizConnect(square).length; i++){
			neighbors[count] = horizConnect(square)[i];
			count++;
		}
		for(int i = 0; i < diagConnect(square).length; i++){
			neighbors[count] = diagConnect(square)[i];
			count++;
		}
		return neighbors;
	}
  
  	// checks if a move follows the four rules of Network, returns true if it does,
  	// returns false otherwise
	private boolean isValidMove(Move move, int color, boolean isStep){
		if(isStep){
			Square potentialMove = new Square(move.x1, move.y1);
	    	potentialMove.setColor(color);
	    	
	    	if(potentialMove.getXCoordinate() > 7 || potentialMove.getYCoordinate() > 7 || potentialMove.getXCoordinate() < 0 || potentialMove.getYCoordinate() < 0){
	    		return false;
	    	}else if(move.moveKind == 2 && potentialMove.getXCoordinate() == move.x2 && potentialMove.getYCoordinate() == move.y2){
	    		return false;
	    	}else if(isCorner(potentialMove)){
	    		return false;
	    	}else if(inOppositeGoal(potentialMove)){
	    		return false;
	    	}else if(isOccupied(potentialMove)){
	    		return false;
	    	}else if(createsConnectedGroup(potentialMove)){
	    		return false;
	    	}else{
	      		return true;
	    	}
		}else{
			return false;
		}
	}
  	protected boolean isValidMove(Move move, int color){
  		if(move.moveKind == Move.QUIT){
     	 return true;
  		}
     	 
    	if(move.moveKind == 2){
    	    if(move.x1 == move.x2 && move.y1 == move.y2 || numBlack +  numWhite< 20){
    	        return false;
    	        }
    	        Gameboard boardCopy = copyBoard();
    	        boardCopy.board[move.x2][move.y2].setColor(-1);
    	        Move testerMove = new Move(move.x1, move.y1);
    	        return boardCopy.isValidMove(testerMove, color, true);
    	}
    	if(move.moveKind == 1 && numBlack + numWhite >= 20){
    		return false;
    	}
    	Square potentialMove = new Square(move.x1, move.y1);
    	potentialMove.setColor(color);
    	
    	if(potentialMove.getXCoordinate() > 7 || potentialMove.getYCoordinate() > 7 || potentialMove.getXCoordinate() < 0 || potentialMove.getYCoordinate() < 0){
    		return false;
    	}else if(move.moveKind == 2 && potentialMove.getXCoordinate() == move.x2 && potentialMove.getYCoordinate() == move.y2){
    		return false;
    	}else if(isCorner(potentialMove)){
    		return false;
    	}else if(inOppositeGoal(potentialMove)){
    		return false;
    	}else if(isOccupied(potentialMove)){
    		return false;
    	}else if(createsConnectedGroup(potentialMove)){
    		return false;
    	}else{
      		return true;
    	}
  	}
  
  	// Returns false if move is into a corner (Rule #1)
  	protected boolean isCorner(Square square){
    	return ((square.getXCoordinate() == 0 && square.getYCoordinate() == 0) || (square.getXCoordinate() == 0 && square.getYCoordinate() == 7)
    	|| (square.getXCoordinate() == 7 && square.getYCoordinate() == 0) || (square.getXCoordinate() == 7 && square.getYCoordinate() == 7));
    }
  
  	// Returns false if move is into opponents goal area (Rule #2)
  	protected boolean inOppositeGoal(Square square){
    	return ((square.getColor() == 0 && (square.getXCoordinate() == 0 || square.getXCoordinate() == 7))
    	|| (square.getColor() == 1 && (square.getYCoordinate() == 0 || square.getYCoordinate() == 7)));
  	}
  	// Returns true if move is into an already occupied space (Rule #3)
  	protected boolean isOccupied(Square square){
    	return board[square.getXCoordinate()][square.getYCoordinate()].getColor() != -1;
  	}
  	/*//
  	protected boolean createsConnectedGroup(Square square){
  		int inCluster = 0;
  		for(Square sq: getSurroundingSquares(square)){
  			if(sq.getColor() == -1){
  				continue;
  			}
 
  		if(sq.getColor() == square.getColor()){
  			inCluster++;
  		}
  		for(Square sq2: getSurroundingSquares(square)){
  			if(sq2.getColor() != -1 && !(sq2.equals(square)) && sq.getColor() == square.getColor()
  			  && sq2.getColor() == square.getColor()){
  				return true;
  			}
  		}
  				
  		}
  		return false;
  	}
  	*/
  	
  	//Returns false if move creates a group of more than 2 chips with the same color (Rule #4)
  	protected boolean createsConnectedGroup(Square square){
    	Square[] surroundings = getSurroundingSquares(square);
    	Square[] surroundings2;
    	int sameColorSurroundings = 0;
    	for(int i = 0; i< surroundings.length; i++){
      		if(surroundings[i].getColor() == square.getColor()){
        		sameColorSurroundings++;
          		surroundings2 = getSurroundingSquares(surroundings[i]);
        		for(int j = 0; j<surroundings2.length; j++){
        			if(surroundings2[j].getColor() == square.getColor() && surroundings2[j] != square){
        				return true;
        			}
        		}
      		}
    	}
    	if(sameColorSurroundings >= 2){
      		return true;
    	}
    	else{
      		return false;
    	}
  	}

  	/* returns an array of the surrounding squares
  	 * 
  	 */
  protected Square[] getSurroundingSquares(Square square) {
    Square[] surroundings;
    if (isCorner(square)) {
    	return new Square[0];
    }
    if(square.getYCoordinate() == 0){
      surroundings = new Square[5];
      surroundings[0] = getSquare(square.getXCoordinate()-1, square.getYCoordinate());
      surroundings[1] = getSquare(square.getXCoordinate()+1, square.getYCoordinate());
      surroundings[2] = getSquare(square.getXCoordinate(), square.getYCoordinate()+1);
      surroundings[3] = getSquare(square.getXCoordinate()+1, square.getYCoordinate()+1);
      surroundings[4] = getSquare(square.getXCoordinate()-1, square.getYCoordinate()+1);
      }else if(square.getYCoordinate() == 7){
      surroundings = new Square[5];
      surroundings[0] = getSquare(square.getXCoordinate()-1, square.getYCoordinate());
      surroundings[1] = getSquare(square.getXCoordinate()+1, square.getYCoordinate());
      surroundings[2] = getSquare(square.getXCoordinate(), square.getYCoordinate()-1);
      surroundings[3] = getSquare(square.getXCoordinate()+1, square.getYCoordinate()-1);
      surroundings[4] = getSquare(square.getXCoordinate()-1, square.getYCoordinate()-1);
    }else if(square.getXCoordinate() == 0){
      surroundings = new Square[5];
      surroundings[0] = getSquare(square.getXCoordinate(), square.getYCoordinate()+1);
      surroundings[1] = getSquare(square.getXCoordinate(), square.getYCoordinate()-1);
      surroundings[2] = getSquare(square.getXCoordinate()+1, square.getYCoordinate());
      surroundings[3] = getSquare(square.getXCoordinate()+1, square.getYCoordinate()-1);
      surroundings[4] = getSquare(square.getXCoordinate()+1, square.getYCoordinate()+1);
    }else if(square.getXCoordinate() == 7){
      surroundings = new Square[5];
      surroundings[0] = getSquare(square.getXCoordinate(), square.getYCoordinate()+1);
      surroundings[1] = getSquare(square.getXCoordinate(), square.getYCoordinate()-1);
      surroundings[2] = getSquare(square.getXCoordinate()-1, square.getYCoordinate());
      surroundings[3] = getSquare(square.getXCoordinate()-1, square.getYCoordinate()-1);
      surroundings[4] = getSquare(square.getXCoordinate()-1, square.getYCoordinate()+1);
    }else{
      surroundings = new Square[8];
      surroundings[0] = getSquare(square.getXCoordinate()-1, square.getYCoordinate());
      surroundings[1] = getSquare(square.getXCoordinate()-1, square.getYCoordinate()+1);
      surroundings[2] = getSquare(square.getXCoordinate()-1, square.getYCoordinate()-1);
      surroundings[3] = getSquare(square.getXCoordinate()+1, square.getYCoordinate());
      surroundings[4] = getSquare(square.getXCoordinate()+1, square.getYCoordinate()+1);
      surroundings[5] = getSquare(square.getXCoordinate()+1, square.getYCoordinate()-1);
      surroundings[6] = getSquare(square.getXCoordinate(), square.getYCoordinate()+1);
      surroundings[7] = getSquare(square.getXCoordinate(), square.getYCoordinate()-1);
    }
    return surroundings;
    }
    
  // generating a list of all valid moves based on the current Gameboard board 
  protected DList allValidMoves(int clr){
	  DList validMoves = new DList();
	  Move test;
	  int i;
	  int j;
	  if(numBlack + numWhite < 20){
		  for(i = 0; i<8; i++){
			  for(j = 0; j<8; j++){
				  test = new Move(i,j);
				  if(isValidMove(test,clr)){
					  validMoves.insertFront(test);
				  }
			  }
		  }
	  }else if(clr == 0){
		  Square[] blackLocations = blackLocation();
		  for(int l = 0; l<blackLocations.length; l++){
			  int x = blackLocations[l].getXCoordinate();
			  int y = blackLocations[l].getYCoordinate();
			  for(i = 0; i<8; i++){
				  for(j = 0; j<8; j++){
					  if(isValidMove(new Move(i,j,x,y),0)){
						  validMoves.insertFront(new Move(i,j,x,y));
					  }
				  }
			  }
		  }
	  } else {//white step move
		  Square[] whiteLocations = whiteLocation();
		  for(int l = 0; l<whiteLocations.length; l++){
			  int x = whiteLocations[l].getXCoordinate();
			  int y = whiteLocations[l].getYCoordinate();
			  for(i = 0; i<8; i++){
				  for(j = 0; j<8; j++){
					  if(isValidMove(new Move(i,j,x,y),1)){
						  validMoves.insertFront(new Move(i,j,x,y));
					  }
				  }
			  }
		  }
	  } 
	  return validMoves;
  }
  
	
	//Returns a Victory String if a network has been formed
	/*protected String hasWin(){
		if(formsNetwork()){
			return "You have won the game"; //Change so it says who won the game
		}else{
			return;
		}
	} */
	
	protected boolean hasWin(){
		if(formsNetwork()) {
			return true;
		} else {
			return false;
		}
	}
	

	/* returns true if this gameboard has a network
	 * 
	 */
	protected boolean formsNetwork() {
		boolean result = formsNetwork2(0) || formsNetwork2(1);
		return result;
	}
	
	/* returns true if this color has a network
	 * 
	 */
	protected boolean formsNetwork2(int color) {
		Square[] fsquarelist = findFSquares(color);
		int flength = fsquarelist.length;
		boolean networkiter = false;
		for (int i = 0; i < flength; i++) {
			if (networkiter == true) {
				return true;
			}
			if (fsquarelist[i] == null) {
				return networkiter;
			}
			networkiter = hasNetwork(fsquarelist[i], color);
		}
		return networkiter;
	}
	
	/* helper function for formsNetwork
	 * 
	 */
	protected boolean hasNetwork(Square mainsquare, int color) {
		int initdepth = 0;
		DList parentlist = new DList(mainsquare);
		return recurseNetwork(mainsquare, parentlist, initdepth, color, 20);
	}
	
	/*
	 * helper function for hasNetwork
	 */
	protected boolean recurseNetwork(Square currentsquare, DList plist,int depth, int color, int direction) {
		boolean endcondition = endSquare(currentsquare) && depth == 5;
		if (endcondition == true) {
			return true;
		}
		if (depth == 5) {
			return false;
		}
		if (endSquare(currentsquare)) {
			return false;
		} else {
			Square[] connectors = removeMatching(strongFormsConnection(currentsquare, direction, color), plist);
			boolean networkiter = false;
			int clength = connectors.length;
			for (int i = 0; i < clength; i++) {
				if (networkiter == true) {
					return true;
				} else {
					if (connectors[i] == null) {
						continue;
					}
					Square isquare = connectors[i];
					int depth2 = depth + 1;
					plist.insertFront(isquare);
					DList plist2 = plist;
					int direct = generateDirection(currentsquare, isquare);
					networkiter = recurseNetwork(isquare, plist2, depth2, color, direct);
					plist.removeFront();
				}
			}
			return networkiter;
		}
	}
	
	/*
	 * Helper functions for hasNetwork
	 */
	protected Square[] removeMatching(Square[] masterarray, DList keylist) {
		DListNode proxy = keylist.getHead();
		for (int i = 0; i < keylist.getSize(); i++) {
			for (int j = 0; j < masterarray.length; j++) {
				if (proxy == null) {
					continue;
				}
				if (masterarray[j] == null) {
					continue;
				} else {
					if (masterarray[j].equals(proxy.item)) {
						masterarray[j] = null;
					}
				}
			}
			proxy = proxy.next;
		}
		int counter = 0;
		for (int i = 0; i < masterarray.length; i++) {
			if (masterarray[i] == null) {
				continue;
			} else {
				counter = counter + 1;
			}
		}
		Square[] farray = new Square[counter];
		int next = 0;
		for (int i = 0; i < masterarray.length; i++) {
			if (masterarray[i] == null) {
				continue;
			} else {
				farray[next] = masterarray[i];
				next = next + 1;
			}
		}
		return farray;
	}
	
	/*
	 * helper function for network
	 */
	protected Square[] strongFormsConnection(Square mainsquare, int direction, int color) {
		Square[] mainarray = removeStartingSquares(formConnection(mainsquare), color);
		int arraylength = mainarray.length;
		for (int i = 0; i < arraylength; i++) {
			if (mainarray[i] == null) {
				continue;
			} else {
				Square currentsquare = mainarray[i];
				if (direction == generateDirection(mainsquare, currentsquare)) {
					mainarray[i] = null;
					return mainarray;
				}
			}
		}
		return mainarray;
	}
	
	/*
	 * Helper function for network
	 */
	protected Square[] removeStartingSquares(Square[] sarray, int color) {
		int slength = sarray.length;
		for (int i = 0; i < slength; i++) {
			if (sarray[i] == null) {
				continue;
			}
			if (isStartingSquare(sarray[i], color)) {
				sarray[i] = null;
			}
		}
		return sarray;
	}
	

	protected boolean isStartingSquare(Square square, int color) {
		int xcord = square.getXCoordinate();
		int ycord = square.getYCoordinate();
		if (color == 1) {
			if (xcord == 0) {
				return true;
			} else {
				return false;
			}
		}
		if (color == 0) {
			if (ycord == 0) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
			
	
	protected int generateDirection(Square mainsquare, Square subsquare) {
		int xmain = mainsquare.getXCoordinate();
		int ymain = mainsquare.getYCoordinate();
		int xsub = subsquare.getXCoordinate();
		int ysub = subsquare.getYCoordinate();
		if (xmain > xsub && ymain > ysub) {
			return 0;
		}
		if (xmain == xsub && ymain > ysub) {
			return 1;
		}
		if (xmain < xsub && ymain > ysub) {
			return 2;
		}
		if (xmain > xsub && ymain == ysub) {
			return 3;
		}
		if (xmain < xsub && ymain == ysub) {
			return 4;
		}
		if (xmain > xsub && ymain < ysub) {
			return 5;
		}
		if (xmain == xsub && ymain < ysub) {
			return 6;
		}
		if (xmain < xsub && ymain < ysub) {
			return 7;
		} else {
			return -1;
		}
	}
	protected boolean endSquare(Square square) {
		int xcord = square.getXCoordinate();
		int ycord = square.getYCoordinate();
		int color = (square.getColor());
		if (color == 1) {
			if (xcord == 7) {
				return true;
			} else {
				return false;
			}
		}
		if (color == 0) {
			if (ycord == 7) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	protected Square[] findFSquares(int color) {
		if (color == 1) {
			int counter = 0;
			for (int i = 0; i < 6; i++) {
				if ((board[0][i].getColor()) == 1) {
					counter = counter + 1;
				}
			}
			int start = 0;
			Square[] sarray = new Square[counter];
			for (int i = 0; i < 6; i++) {
				if ((board[0][i].getColor()) == 1) {
					sarray[start] = board[0][i];
					start = start + 1;
				}
			}
			return sarray;
		}
		if (color == 0) {
			int counter = 0;
			for (int i = 0; i < 6; i++) {
				if ((board[i][0].getColor()) == 0) {
					counter = counter + 1;
				}
			}
			int start = 0;
			Square[] sarray = new Square[counter];
			for (int i = 0; i < 6; i++) {
				if ((board[i][0].getColor()) == 0) {
					sarray[start] = board[i][0];
					start = start + 1;
				}
			}
			return sarray;
		}
		return null;
	}
	
	protected int convertColor(String color) {
		if (color.equals("WHITE")) {
			return 0;
		} 
		if (color.equals("BLACK")) {
			return 1;
		} else {
			return -5;
		}
	}	
	
	protected boolean inEndzone(int color){
		if(color == 0){
			for(int x = 1; x < 7; x++){
				if(board[x][0].getColor() == 0 || board[x][7].getColor() == 0){
					return true;
				}
			}return false;
		}else if(color == 1){
			for(int y = 1; y < 7; y++){
				if(board[0][y].getColor() == 1 || board[7][y].getColor() == 1){
					return true;
				}
			}return false;
		}return false;
	}
	
	protected void setTest(Square square) {
		int x = square.getXCoordinate();
		int y = square.getYCoordinate();
		board[x][y] = square;
	}
	
	protected int numPieces() {
		int counter = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getColor() != -1) {
					System.out.println(i + "" + j + board[i][j].getColor());
					counter++;
				}
			}
		}
		return counter;
	}
	
	protected int getPieces() {
		int counter = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getColor() != -1) {
					counter++;
				}
			}
		}
		return counter;
	}
}
	
	