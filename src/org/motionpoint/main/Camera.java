package org.motionpoint.main;

import java.awt.Graphics;

public class Camera {

	private int x, y;
	private Graphics g;
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void updateGraphics(Graphics g) {
		this.g = g;
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void translate(int dx, int dy) {
		x -= dx;
		y += dy;
	}
	
	public void update() {
		g.translate(x, y);
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
	
	
}
