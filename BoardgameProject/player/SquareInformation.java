package player;

class Square{

	private int color;
	private int x;
	private int y;
	private boolean isEmpty = true;

	public Square(int color, int x, int y) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	private void populate(){
		isEmpty = false;
	}
	
	protected void setColor(int color) {
		this.color = color;
	}

	protected int getXCoordinate() {
		return xCoordinate;
	}

	protected int getYCoordinate() {
		return yCoordinate;
	}

	protected void setX(int x) {
		xCoordinate = x;
	}

	protected void setY(int y) {
		this.y = y;
	}

	protected int getColor() {
		return color;
	}

}