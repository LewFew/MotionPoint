package org.motionpoint.main;

public class Node {

	public static enum ConnectionType {STRAIGHT, CURVED, NONE};
	
	public Node parentNode;
	public int action;
	
	public int rad = 15;
	
	private int x, y;
	private ConnectionType type;
	private double slope;
	
	public Node(Node parentNode, int action, int x, int y, ConnectionType type) {
		this.parentNode = parentNode;
		this.action = action;
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public double getSlope() {
		if (parentNode != null) {
			if (type.equals(ConnectionType.STRAIGHT)) {
				//System.out.println((y - parentNode.getY()) + " " + (x - parentNode.getX()) + " " + (y - parentNode.getY()) / (x - parentNode.getX()));
				return -((double)y - (double)parentNode.getY()) / ((double)x - (double)parentNode.getX());
			} else if (type.equals(ConnectionType.CURVED)) {
				return -((double)y - (double)parentNode.getY()) / ((double)x - (double)parentNode.getX());
				//return 1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public ConnectionType getType() {
		return type;
	}

	public void setType(ConnectionType type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getRad() {
		return rad;
	}
	
	public void setRad(int rad) {
		this.rad = rad;
	}
	
}
