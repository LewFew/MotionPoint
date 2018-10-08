package org.motionpoint.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class World {

	public ArrayList<Node> nodes = new ArrayList<Node>();
	private int primedNode = 0;
	private int lastPrime = -1;
	
	public World() {
		nodes.add(new Node(null, 0, 0, 0, Node.ConnectionType.STRAIGHT));
	}
	
	public void addNode(Node node) {
		nodes.add(node);
		primedNode = nodes.size() - 1;
	}
	
	public void setPrime(int newPrime) {
		primedNode = newPrime;
	}
 	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i = 0; i < nodes.size(); i++) {
			
			Node currentNode = nodes.get(i);
			
			if (i > 0) {
				nodes.get(i).parentNode = nodes.get(i - 1);
			}
			
			if (i < nodes.size() - 1) {
				nodes.get(i).setChildNode(nodes.get(i + 1));
			}
			
			g.setColor((i == primedNode) ? new Color(150, 255, 30, 200) : new Color(30, 150, 255, 200));
			
			g.fillOval(currentNode.getX() - currentNode.getRad(), currentNode.getY() - currentNode.getRad()
					, currentNode.getRad() * 2, currentNode.getRad() * 2);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", 1, 16));
			g.drawString(String.valueOf(i), currentNode.getX() - (int)(currentNode.getRad() / 3.5),
					currentNode.getY() + currentNode.getRad() / 4);
			
			g.setColor(Color.BLACK);
			g.drawString(String.valueOf(currentNode.getAngle()), currentNode.getX() - 50, currentNode.getY());
			
			/*g.setColor(Color.RED);
			g.drawLine(currentNode.getX(), currentNode.getY(),
					currentNode.getX() - (int)currentNode.getDeltaX(),
					currentNode.getY());
			
			g.drawLine(currentNode.getX() - (int)currentNode.getDeltaX(), currentNode.getY(),
					currentNode.getX() - (int)currentNode.getDeltaX(),
					currentNode.getY() + (int)currentNode.getDeltaY());*/
			
			if (i > 0 && nodes.get(i - 1).getType() == Node.ConnectionType.STRAIGHT) {
				g.setColor(Color.GREEN);
				g.drawLine(currentNode.getX(), currentNode.getY(),
						nodes.get(i - 1).getX(), nodes.get(i - 1).getY());
			} else if (i > 1 && nodes.get(i - 1).getType() == Node.ConnectionType.CURVED && i < nodes.size()) {
				
				g2d.setColor(Color.GREEN);
				
				int deltaX = 0, deltaY = 0;
				int radius = 50;
				int theta = (int) nodes.get(i - 1).getAngle();
				int arcAngleLength = 30;
				
				//-------------------------------
				
				boolean mode = (nodes.get(i - 1).getChildNode().getX() > nodes.get(i - 1).getX()) ? true : false;
				//If referring to my notes, a mode of false is mode 1. A mode of true is mode 2
				
				if (0 < theta && theta < 90) {
					
					if (!mode) {
						
						theta = 270 + theta;
						
						deltaX = (int) ((radius * Math.cos(Math.toRadians(theta))) - (radius * Math.cos(Math.toRadians(135))));
						deltaY = (int) ((radius * Math.sin(Math.toRadians(theta))) - (radius * Math.sin(Math.toRadians(135))));
						
					} else {
						
						theta = 90 + theta;
						
						deltaX = (int) ((radius * Math.cos(Math.toRadians(theta))) - (radius * Math.cos(Math.toRadians(135))));
						deltaY = (int) ((radius * Math.sin(Math.toRadians(theta))) - (radius * Math.sin(Math.toRadians(135))));
						
					}
					
				} else if (90 < theta && theta < 180) {
					
					if (!mode) {
						
						theta = theta - 90;
						
						deltaX = (int) ((radius * Math.cos(Math.toRadians(theta))) - (radius * Math.cos(Math.toRadians(135))));
						deltaY = (int) ((radius * Math.sin(Math.toRadians(theta))) - (radius * Math.sin(Math.toRadians(135))));
						
					} else {
						
						theta = theta + 90;
						
						deltaX = (int) ((radius * Math.cos(Math.toRadians(theta))) - (radius * Math.cos(Math.toRadians(135))));
						deltaY = (int) ((radius * Math.sin(Math.toRadians(theta))) - (radius * Math.sin(Math.toRadians(135))));
						
					}
					
				} else if (180 < theta && theta < 270) {
					
					if (!mode) {
						
						theta = theta + 90;
						
						deltaX = (int) ((radius * Math.cos(Math.toRadians(theta))) - (radius * Math.cos(Math.toRadians(135))));
						deltaY = (int) ((radius * Math.sin(Math.toRadians(theta))) - (radius * Math.sin(Math.toRadians(135))));
						
					} else {
						
						theta = theta - 90;
						
						deltaX = (int) ((radius * Math.cos(Math.toRadians(theta))) - (radius * Math.cos(Math.toRadians(135))));
						deltaY = (int) ((radius * Math.sin(Math.toRadians(theta))) - (radius * Math.sin(Math.toRadians(135))));
						
					}
					
				} else if (270 < theta && theta < 360) {
					
					if (!mode) {
						
						theta = theta - 270;
						
						deltaX = (int) ((radius * Math.cos(Math.toRadians(theta))) - (radius * Math.cos(Math.toRadians(135))));
						deltaY = (int) ((radius * Math.sin(Math.toRadians(theta))) - (radius * Math.sin(Math.toRadians(135))));
						
					} else {
						
						theta = theta - 90;
						
						deltaX = (int) ((radius * Math.cos(Math.toRadians(theta))) - (radius * Math.cos(Math.toRadians(135))));
						deltaY = (int) ((radius * Math.sin(Math.toRadians(theta))) - (radius * Math.sin(Math.toRadians(135))));
						
					}
					
				}
				
				g2d.setColor(Color.GREEN);
				g2d.setStroke(new BasicStroke(3));
				g2d.drawArc(nodes.get(i - 1).getX() - nodes.get(i - 1).getRad() - deltaX,
						nodes.get(i - 1).getY() - nodes.get(i - 1).getRad() + deltaY,
				radius * 2, radius * 2, theta, arcAngleLength);
				
				System.out.println(theta);
				
				//-------------------------------
				
//				g2d.setStroke(new BasicStroke(3));
//				
//				if (Math.atan(nodes.get(i - 1).getSlope()) >= 0) {
//					
//					deltaX = (int) ((radius * Math.cos(Math.toRadians(theta))) - (radius * Math.cos(Math.toRadians(135))));
//					deltaY = (int) ((radius * Math.sin(Math.toRadians(theta))) - (radius * Math.sin(Math.toRadians(135))));
//					
//					g2d.drawArc(nodes.get(i - 1).getX() - nodes.get(i - 1).getRad() - deltaX,
//							nodes.get(i - 1).getY() - nodes.get(i - 1).getRad() + deltaY,
//					radius * 2, radius * 2, theta, 30);
//					
//			/*		g2d.setColor(Color.MAGENTA);
//					g2d.fillRect(nodes.get(i - 1).getX() - deltaX,
//							nodes.get(i - 1).getY() + deltaY, 10, 10);
//					
//					g2d.setColor(Color.RED);
//					g2d.fillRect(nodes.get(i - 1).getX(),
//							nodes.get(i - 1).getY(), 10, 10);
//					
//					g2d.drawArc(nodes.get(i - 1).getX() - nodes.get(i - 1).getRad(),
//							nodes.get(i - 1).getY() - nodes.get(i - 1).getRad(),
//					radius * 2, radius * 2, theta, 30);
//					
//					g.drawLine(nodes.get(i - 1).getX(), nodes.get(i - 1).getY(), nodes.get(i - 1).getX() + deltaX, nodes.get(i - 1).getY());
//					g.drawLine(nodes.get(i - 1).getX(), nodes.get(i - 1).getY(), nodes.get(i - 1).getX(), nodes.get(i - 1).getY() - deltaY);*/
//					
//				} else {
//					
//					theta = 270 - theta;
//					
//					deltaX = (int) ((radius * Math.cos(Math.toRadians(theta))) - (radius * Math.cos(Math.toRadians(135))));
//					deltaY = (int) ((radius * Math.sin(Math.toRadians(theta))) - (radius * Math.sin(Math.toRadians(135))));
//					
//					g2d.drawArc(nodes.get(i - 1).getX() - nodes.get(i - 1).getRad() - deltaX,
//							nodes.get(i - 1).getY() - nodes.get(i - 1).getRad() + deltaY,
//					radius * 2, radius * 2, theta, 30);
//					
//				/*	g2d.setColor(Color.MAGENTA);
//					g2d.fillRect(nodes.get(i - 1).getX() - deltaX,
//							nodes.get(i - 1).getY() + deltaY, 10, 10);
//					
//					g2d.setColor(Color.RED);
//					g2d.fillRect(nodes.get(i - 1).getX(),
//							nodes.get(i - 1).getY(), 10, 10);
//					
//					g2d.drawArc(nodes.get(i - 1).getX() - nodes.get(i - 1).getRad(),
//							nodes.get(i - 1).getY() - nodes.get(i - 1).getRad(),
//					radius * 2, radius * 2, theta, 30);
//					
//					g.drawLine(nodes.get(i - 1).getX(), nodes.get(i - 1).getY(), nodes.get(i - 1).getX() + deltaX, nodes.get(i - 1).getY());
//					g.drawLine(nodes.get(i - 1).getX(), nodes.get(i - 1).getY(), nodes.get(i - 1).getX(), nodes.get(i - 1).getY() - deltaY);*/
//					
//				}
			} else if (i == 1 && nodes.get(i - 1).getType() == Node.ConnectionType.CURVED) {
				double startTheta = 0;
			}
			
			if (MouseInput.isButtonPressed(MouseEvent.BUTTON3)) {
				if (Math.sqrt(Math.pow(currentNode.getX() - (MouseInput.mouseX() - Window.camera.getX()), 2)
						+ Math.pow(currentNode.getY() - (MouseInput.mouseY() - Window.camera.getY()), 2))
						<= currentNode.getRad()) {
					primedNode = i;
				}
			}
			
			if (primedNode == i && primedNode != lastPrime) {
				MotionPoint.infoWindow.display(this, i);
				lastPrime = primedNode;
			}
		}
		
	}
	
}
