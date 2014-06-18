package player;


public class Gameboard2 {
	
	protected Square[][] grid;

	protected Square[] formsConnection(Square square) {
		return null;
	}
	
	protected boolean formsNetwork() {
		boolean result = formsNetwork2(0) || formsNetwork2(1);
		return result;
	}
	
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
	
	protected boolean hasNetwork(Square mainsquare, int color) {
		int initdepth = 0;
		DList parentlist = new DList(mainsquare);
		return recurseNetwork(mainsquare, parentlist, initdepth, color, 20);
	}
	
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
	
	protected Square[] removeMatching(Square[] masterarray, DList keylist) {
		DListNode proxy = keylist.getHead();
		for (int i = 0; i < keylist.getSize(); i++) {
			for (int j = 0; j < masterarray.length; j++) {
				if (proxy == null) {
					continue;
				} else {
					if (masterarray[j].equals(proxy)) {
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
	
	protected Square[] strongFormsConnection(Square mainsquare, int direction, int color) {
		Square[] mainarray = removeStartingSquares(formsConnection(mainsquare), color);
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
				if ((grid[0][i].getColor()) == 1) {
					counter = counter + 1;
				}
			}
			int start = 0;
			Square[] sarray = new Square[counter];
			for (int i = 0; i < 6; i++) {
				if ((grid[0][i].getColor()) == 1) {
					sarray[start] = grid[0][i];
					start = start + 1;
				}
			}
			return sarray;
		}
		if (color == 0) {
			int counter = 0;
			for (int i = 0; i < 6; i++) {
				if ((grid[i][0].getColor()) == 0) {
					counter = counter + 1;
				}
			}
			int start = 0;
			Square[] sarray = new Square[counter];
			for (int i = 0; i < 6; i++) {
				if ((grid[i][0].getColor()) == 0) {
					sarray[start] = grid[i][0];
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
	

}
