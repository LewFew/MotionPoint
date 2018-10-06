package org.motionpoint.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Window {
	
	private JFrame frame;
	private Graphics g;
	private World world;
	
	private int camSpeed = 5;
	
	InformationWindow iWindow;
	
	static Camera camera;
	
	KeyInput kInput;
	MouseInput mInput; 
	
	public static final int WIDTH = 640, HEIGHT = 360;

	public Window(World world, InformationWindow iWindow) {
		
		this.iWindow = iWindow;
		
		frame = new JFrame("Motion Point");
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		frame.setAutoRequestFocus(true);
		
		kInput = new KeyInput();
		mInput = new MouseInput();
		
		frame.addKeyListener(kInput);
		frame.addMouseListener(mInput);
		
		this.world = world;
		
		camera = new Camera(WIDTH/2, HEIGHT/2);
		
		loop();
	}
	
	public void loop() {
		long lastTime = 0;
		while (MotionPoint.running) {
			long currentTime = System.nanoTime();
			if (currentTime - lastTime >= 1000000000/Constants.FPS) {
				update();
				lastTime = currentTime;
			}
		}
	}
	
	public void update() {
		
		BufferStrategy bs;
		
		if (frame.getBufferStrategy() == null) {
			frame.createBufferStrategy(3);
		}
		
		bs = frame.getBufferStrategy();
		
		g = bs.getDrawGraphics();
		
		camera.updateGraphics(g);
		
		camera.update();
		
		g.setColor(Color.WHITE);
		g.fillRect(-camera.getX(), -camera.getY(), WIDTH, HEIGHT);
		
		if (KeyInput.isKeyDown(KeyEvent.VK_LEFT)) {
			camera.translate(-camSpeed, 0);
		}
		if (KeyInput.isKeyDown(KeyEvent.VK_RIGHT)) {
			camera.translate(camSpeed, 0);
		}
		if (KeyInput.isKeyDown(KeyEvent.VK_UP)) {
			camera.translate(0, camSpeed);
		}
		if (KeyInput.isKeyDown(KeyEvent.VK_DOWN)) {
			camera.translate(0, -camSpeed);
		}
		
		if (KeyInput.isKeyDown(KeyEvent.VK_F8)) {
			camera.move(WIDTH / 2, HEIGHT / 2);
		}
		
		if (MouseInput.isButtonClicked(MouseEvent.BUTTON1)) {
			world.addNode(new Node(null, 0, MouseInput.mouseX() - camera.getX(), MouseInput.mouseY() - camera.getY(), Node.ConnectionType.STRAIGHT));
		}
		
		//----------------------------

		world.render(g);
		
		//----------------------------
		
		bs.show();
	}
	
}
