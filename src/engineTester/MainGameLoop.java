package engineTester;

import animation.Humanoid;
import animation.KeyFrames;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;

import java.util.ArrayList;


public class MainGameLoop {

	public static int current_pose;
	public static int num_objects;
    public static ArrayList<Entity> entities = new ArrayList<Entity>();

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

//		RawModel model = loader.loadToVAO(vertices,textureCoords,indices);
//
//		TexturedModel headModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("doraemon_face")));
//		TexturedModel bodyModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("body")));
//		TexturedModel armModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("hand")));
//		TexturedModel legModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("leg")));
//

//
////		Entity head = new Entity(staticModel, new Vector3f(0,0,0),0,0,0,1);
//		Entity head = new Entity(1,headModel, new Vector3f(0,0,0),0,0,0,1, 1,1);
//		Entity body = new Entity(0,bodyModel, new Vector3f(0,-2.5f,0),0,0,0,2, 4, 1);
//		Entity leftarm = new Entity(2,armModel, new Vector3f(-1.25f,-1.5f,0),0,0,90,0.5f,2,1);
//		Entity rightarm = new Entity(3,armModel, new Vector3f(1.25f,-1.5f,0),0,0,-90,0.5f,2,1);
//		Entity leftleg = new Entity(4,legModel, new Vector3f(-0.5f,-5.5f,0),0,0,0,0.5f,2,1);
//		Entity rightleg = new Entity(5,legModel, new Vector3f(0.5f,-5.5f,0),0,0,0,0.5f,2,1);

		Humanoid figure = new Humanoid(loader);
		KeyFrames keyframes = new KeyFrames(figure);

//		int num_objects = 6;
		Entity body = figure.bodyParts[0];
		Entity head = figure.bodyParts[1];
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

		entities.add(body);
		entities.add(head);
		entities.add(leftarm);
		entities.add(rightarm);
		entities.add(leftleg);
		entities.add(rightleg);

		num_objects = entities.size();

//		for(int i = 0 ; i < num_objects ; i++) {
//			Entity.finalRenderTransforms.add(new Matrix4f());
//			Entity.withoutScaleTransform.add(new Matrix4f());
//		}


		Entity.scaleTransforms = figure.scales;

		Camera camera = new Camera();

		int previous_pose = 0;
		current_pose = 0;
		Matrix4f rootTransform = new Matrix4f();
        int cycles = 100;
		int cyclesDone = 0;
        boolean polating = false;

		shader.start();
		while(!Display.isCloseRequested()){
//			entity.increaseRotation(0, 1, 0);
//            rootTransform.translate(new Vector3f(0.002f,0.0f,0.0f));
			camera.move();
			renderer.prepare();
			shader.loadViewMatrix(camera);
			if(!polating) {
                detect_pose();
                if(current_pose != previous_pose) {
                    polating = true;
                    cyclesDone = 0;
                }
                Entity.translationsRotations = keyframes.poses[previous_pose];
            }
            else {
//                Entity.translationsRotations = interpolate(keyframes.poses[previous_pose],keyframes.poses[current_pose],1.0*cyclesDone/cycles);
                Entity.translationsRotations = keyframes.interpolate(previous_pose,current_pose,1.0f*cyclesDone/cycles);
                if(++cyclesDone == 100){
                    previous_pose = current_pose;
                    polating = false;
                }

            }
            // updated transform from pose
//			Entity.updateTransforms(keyframes.poses[current_pose],entities,0);
			Entity.generateTransforms(rootTransform,0);
//			Entity.setBindTransform(keyframes.poses[current_pose],entities);
			for(Entity ent:entities) {
				renderer.render(ent,shader);
			}
//			renderer.render(head,shader);
//			renderer.render(body,shader);
//			renderer.render(leftarm,shader);
//			renderer.render(rightarm,shader);
//			renderer.render(leftleg,shader);
//			renderer.render(rightleg,shader);
//			shader.stop();
			DisplayManager.updateDisplay();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

	public static void detect_pose() {
		if(Keyboard.isKeyDown(Keyboard.KEY_0)){
			current_pose = 0;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_1)){
			current_pose = 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_2)){
			current_pose = 2;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_3)){
			current_pose = 3;
		}
	}

}
