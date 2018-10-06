package org.motionpoint.main;

public class MotionPoint {
	
	public static boolean running = true;
	public static Window window;
	public static InformationWindow infoWindow;

	public static void main(String[] args) {
		//create new world
		World world = new World();
		
		infoWindow = new InformationWindow();
		window = new Window(world, infoWindow);
	}

}
