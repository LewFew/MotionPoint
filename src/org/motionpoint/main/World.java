package org.motionpoint.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
	public void drawCurve(Graphics2D g2d, int x, int y, int startTheta, int dispTheta) {
		
	}
 	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i = 0; i < nodes.size(); i++) {
			
			Node currentNode = nodes.get(i);
			
			if (i > 0) {
				nodes.get(i).parentNode = nodes.get(i - 1);
			}
			
			g.setColor((i == primedNode) ? new Color(150, 255, 30, 200) : new Color(30, 150, 255, 200));
			
			g.fillOval(currentNode.getX() - currentNode.getRad(), currentNode.getY() - currentNode.getRad()
					, currentNode.getRad() * 2, currentNode.getRad() * 2);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", 1, 16));
			g.drawString(String.valueOf(i), currentNode.getX() - (int)(currentNode.getRad() / 3.5),
					currentNode.getY() + currentNode.getRad() / 4);
			
			g.setColor(Color.BLACK);
			g.drawString(String.valueOf(currentNode.getSlope()), currentNode.getX() - 50, currentNode.getY());
			
			if (i > 0 && nodes.get(i - 1).getType() == Node.ConnectionType.STRAIGHT) {
				g.setColor(Color.GREEN);
				g.drawLine(currentNode.getX(), currentNode.getY(),
						nodes.get(i - 1).getX(), nodes.get(i - 1).getY());
			} else if (i > 1 && nodes.get(i - 1).getType() == Node.ConnectionType.CURVED) {
				g2d.setColor(Color.GREEN);
				
				int deltaX, deltaY;
				int radius = 50;
				int theta = (int)((180 * Math.atan(nodes.get(i - 1).getSlope())) / Math.PI);
				
				deltaX = (int) ((radius * Math.cos(Math.toRadians(theta))) + (100 * Math.cos(Math.toRadians(135))));
				deltaY = (int) ((radius * Math.sin(Math.toRadians(theta))) + (100 * Math.sin(Math.toRadians(135))));
				
				System.out.println(deltaX + " " + deltaY);
				
				if (Math.atan(nodes.get(i - 1).getSlope()) >= 0) {
					g2d.drawArc(nodes.get(i - 1).getX() - nodes.get(i - 1).getRad() - deltaX - 33,
							nodes.get(i - 1).getY() - nodes.get(i - 1).getRad() - deltaY - 33,
					100, 100, 90 + Math.abs(theta), 100);
				} else {
					g2d.drawArc(nodes.get(i - 1).getX() - nodes.get(i - 1).getRad() - deltaX - 33,
							nodes.get(i - 1).getY() - nodes.get(i - 1).getRad() - deltaY - 33,
					100, 100, 270 - Math.abs(theta), 100);
				}
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
