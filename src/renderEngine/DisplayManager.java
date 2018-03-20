//package renderEngine;
//
//import org.lwjgl.LWJGLException;
//import org.lwjgl.Sys;
//import org.lwjgl.opengl.*;
//
//public class DisplayManager {
//
//	private static final int WIDTH = 1280;
//	private static final int HEIGHT = 720;
//	private static final int FPS_CAP = 120;
//
//	public static void createDisplay(){
//		ContextAttribs attribs = new ContextAttribs(3,2)
//		.withForwardCompatible(true)
//		.withProfileCore(true);
//
//		try {
//			Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
//			Display.create(new PixelFormat(), attribs);
//			Display.setTitle("Our First Display!");
//		} catch (LWJGLException e) {
//			e.printStackTrace();
//		}
//
//		GL11.glViewport(0,0, WIDTH, HEIGHT);
//	}
//
//	public static void updateDisplay(){
//
//		Display.sync(FPS_CAP);
//		Display.update();
//
//	}
//
//	public static void closeDisplay(){
//
//		Display.destroy();
//
//	}
//
//}

package renderEngine;

import org.lwjgl.LWJGLException;
		import org.lwjgl.Sys;
		import org.lwjgl.opengl.ContextAttribs;
		import org.lwjgl.opengl.Display;
		import org.lwjgl.opengl.DisplayMode;
		import org.lwjgl.opengl.GL11;
		import org.lwjgl.opengl.GL13;
		import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {

	private static final String TITLE = "OpenGL-SkiGame";
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final int FPS_CAP = 100;

	private static long lastFrameTime;
	private static float delta;

	public static void createDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			ContextAttribs attribs = new ContextAttribs(3, 2).withProfileCore(true).withForwardCompatible(true);
			Display.create(new PixelFormat().withDepthBits(24).withSamples(4), attribs);
			Display.setTitle(TITLE);
			Display.setInitialBackground(1, 1, 1);
			GL11.glEnable(GL13.GL_MULTISAMPLE);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.err.println("Couldn't create display!");
			System.exit(-1);
		}
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		lastFrameTime = getCurrentTime();
	}

	public static void updateDisplay() {
		Display.sync(FPS_CAP);
		Display.update();
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime) / 1000f;
		lastFrameTime = currentFrameTime;
	}

	public static float getFrameTime() {
		return delta;
	}

	public static void closeDisplay() {
		Display.destroy();
	}

	private static long getCurrentTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

}
