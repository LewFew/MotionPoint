package org.motionpoint.main;

public class Node {

	public static enum ConnectionType {STRAIGHT, CURVED, NONE};
	
	public Node parentNode;
	public int action;
	
	public int rad = 15;
	
	private int x, y;
	private ConnectionType type;
	private double slope;
	
	private Node childNode;
	
	public Node(Node parentNode, int action, int x, int y, ConnectionType type) {
		this.parentNode = parentNode;
		this.action = action;
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public Node getChildNode() {
		return childNode;
	}
	
	public void setChildNode(Node childNode) {
		this.childNode = childNode;
	}
	
	public double getDeltaX() {
		if (parentNode != null)
			return (double)x - (double)parentNode.getX();
		else
			return 0;
	}
	
	public double getDeltaY() {
		if (parentNode != null)
			return -((double)y - (double)parentNode.getY());
		else
			return 0;
	}
	
	public double getAngle() {
		if (parentNode != null) {
			double deltaX = (double)x - (double)parentNode.getX(), deltaY = -((double)y - (double)parentNode.getY());	
			if (Math.toDegrees(Math.atan2(deltaY, deltaX)) < 0)  {
				return 360 - Math.abs(Math.toDegrees(Math.atan2(deltaY, deltaX)));
			}else {
				return Math.toDegrees(Math.atan2(deltaY, deltaX));
			}
		} else {
			return 90;
		}
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
