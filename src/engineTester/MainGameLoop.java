package engineTester;

import animation.Humanoid;
import animation.KeyFrames;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		float[] vertices = {			
				-0.5f,0.5f,0,	
				-0.5f,-0.5f,0,	
				0.5f,-0.5f,0,	
				0.5f,0.5f,0,		
				
				-0.5f,0.5f,1,	
				-0.5f,-0.5f,1,	
				0.5f,-0.5f,1,	
				0.5f,0.5f,1,
				
				0.5f,0.5f,0,	
				0.5f,-0.5f,0,	
				0.5f,-0.5f,1,	
				0.5f,0.5f,1,
				
				-0.5f,0.5f,0,	
				-0.5f,-0.5f,0,	
				-0.5f,-0.5f,1,	
				-0.5f,0.5f,1,
				
				-0.5f,0.5f,1,
				-0.5f,0.5f,0,
				0.5f,0.5f,0,
				0.5f,0.5f,1,
				
				-0.5f,-0.5f,1,
				-0.5f,-0.5f,0,
				0.5f,-0.5f,0,
				0.5f,-0.5f,1
				
		};
		
		float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

				
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};
		
		Humanoid figure = new Humanoid(loader);

		Entity head = figure.bodyParts[0];
		Entity body = figure.bodyParts[1];
		Entity leftarm = figure.bodyParts[2];
		Entity rightarm = figure.bodyParts[3];
		Entity leftleg = figure.bodyParts[4];
		Entity rightleg = figure.bodyParts[5];

		body.children.add(head.jointIndex);
		body.children.add(leftarm.jointIndex);
        body.children.add(rightarm.jointIndex);
        body.children.add(leftleg.jointIndex);
        body.children.add(rightleg.jointIndex);

        body.parentID = -1;
        head.parentID = 0;
        leftarm.parentID = 0;
        rightarm.parentID = 0;
        leftleg.parentID = 0;
        rightleg.parentID = 0;


		Camera camera = new Camera();

        shader.start();
        while(!Display.isCloseRequested()){
//			entity.increaseRotation(0, 1, 0);
            camera.move();
            renderer.prepare();
			shader.loadViewMatrix(camera);
			renderer.render(head,shader);
			renderer.render(body,shader);
			renderer.render(leftarm,shader);
			renderer.render(rightarm,shader);
			renderer.render(leftleg,shader);
			renderer.render(rightleg,shader);
//			shader.stop();
			DisplayManager.updateDisplay();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
