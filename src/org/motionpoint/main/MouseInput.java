package org.motionpoint.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MouseInputListener;

public class MouseInput implements MouseInputListener {
	
	static boolean[] buttonPressed = {false, false, false, false};
	static boolean[] buttonReleased = {false, false, false, false};
	static boolean[] buttonClicked = {false, false, false, false};
	
	static private int x, y;
	
	public static int mouseX() {
		return x;
	}
	
	public static int mouseY() {
		return y;
	}
	
	public static boolean isButtonPressed(int button) {
		return buttonPressed[button];
	}
	
	public static boolean isButtonReleased(int button) {
		return buttonReleased[button];
	}
	
	public static boolean isButtonClicked(int button) {
		if (buttonClicked[button]) {
			buttonClicked[button] = false;
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		buttonClicked[e.getButton()] = true;
		buttonReleased[e.getButton()] = false;
		
		x = e.getX();
		y = e.getY();
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
		buttonPressed[e.getButton()] = true;
		buttonReleased[e.getButton()] = false;
		
		x = e.getX();
		y = e.getY();
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
		buttonReleased[e.getButton()] = true;
		buttonPressed[e.getButton()] = false;
		
		x = e.getX();
		y = e.getY();
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
