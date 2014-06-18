package player;


class Square{

	private int color;
	private int xCoordinate;
	private int yCoordinate;

	public Square(int x, int y) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.color = -1;
	}
	
	public Square(int x, int y, int clr) {
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.color = clr;
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
		yCoordinate = y;
	}

	protected int getColor() {
		return color;
	}

}
