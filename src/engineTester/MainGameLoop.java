package engineTester;

import animation.Humanoid;
import animation.KeyFrames;
import entities.Light;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
//import renderEngine.Renderer;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;
import toolbox.Maths;

import java.util.ArrayList;


public class MainGameLoop {

	public static int current_pose;
	public static int num_objects;
    public static ArrayList<Entity> entities = new ArrayList<Entity>();

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
//		StaticShader shader = new StaticShader();
//		Renderer renderer = new Renderer(shader);
		MasterRenderer renderer = new MasterRenderer();

		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
//		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("snow1")),"plain");
		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("snow1")),"heightmap2");
//		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("snow1")),"terrain");

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

		Humanoid figure = new Humanoid(loader,terrain);
		KeyFrames keyframes = new KeyFrames(figure);

//		int num_objects = 6;
		Entity body = figure.bodyParts[0];
		Entity head = figure.bodyParts[1];
		Entity leftarm = figure.bodyParts[2];
		Entity rightarm = figure.bodyParts[3];
		Entity leftleg = figure.bodyParts[4];
		Entity rightleg = figure.bodyParts[5];
		Entity lowerleftarm = figure.bodyParts[6];
		Entity lowerrightarm = figure.bodyParts[7];
		Entity lowerleftleg = figure.bodyParts[8];
		Entity lowerrightleg = figure.bodyParts[9];
		Entity leftpole = figure.bodyParts[10];
		Entity rightpole = figure.bodyParts[11];
		Entity leftski = figure.bodyParts[12];
		Entity rightski = figure.bodyParts[13];


		entities.add(body);
		entities.add(head);
		entities.add(leftarm);
		entities.add(rightarm);
		entities.add(leftleg);
		entities.add(rightleg);
		entities.add(lowerleftarm);
		entities.add(lowerrightarm);
		entities.add(lowerleftleg);
		entities.add(lowerrightleg);
		entities.add(leftpole);
		entities.add(rightpole);
		entities.add(leftski);
		entities.add(rightski);

		num_objects = entities.size();

//		for(int i = 0 ; i < num_objects ; i++) {
//			Entity.finalRenderTransforms.add(new Matrix4f());
//			Entity.withoutScaleTransform.add(new Matrix4f());
//		}

//		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
//		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("snow1")),"plain");
//		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("snow1")),"heightmap1");
//		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("snow1")),"terrain");

		Entity.scaleTransforms = figure.scales;





		Camera camera = new Camera(figure);

		int previous_pose = 0;
		current_pose = 0;
//		Matrix4f rootTransform = new Matrix4f();
//		rootTransform.translate(new Vector3f(0,5,0));
        int CYCLES = 50;
		int cyclesDone = 0;
        boolean polating = false;

//		shader.start();
		while(!Display.isCloseRequested()){
//			entity.increaseRotation(0, 1, 0);

//            rootTransform.translate(new Vector3f(0.005f,0.0f,0.0f));
//            rootTransform.rotate((float)Math.toRadians(0.4f),new Vector3f(0,1,0));

// 			renderer.prepare();
//			shader.loadViewMatrix(camera);
			if(!polating) {
				detect_pose();
				if(current_pose != previous_pose) {
					if(previous_pose == 1)
					{
						figure.currentSpeed += 50;
					}
                    switch (current_pose){
						case 0:
							figure.currentTurnSpeed = 0;
							break;
						case 1:
//                            figure.currentSpeed += 50;
							figure.currentTurnSpeed = 0;
							break;
						case 2:
							figure.currentTurnSpeed = 0;
							break;
						case 3:
							figure.currentTurnSpeed = 0;
							break;
						case 4:
                        	figure.currentTurnSpeed = figure.TURN_SPEED;
                            break;
						case 5:
							figure.currentTurnSpeed = -figure.TURN_SPEED;
							break;
                    }
					polating = true;
					cyclesDone = 0;
				}
				Entity.translationsRotations = keyframes.poses[previous_pose];
			}
            else {
//                Entity.translationsRotations = interpolate(keyframes.poses[previous_pose],keyframes.poses[current_pose],1.0*cyclesDone/cycles);
				Entity.translationsRotations = keyframes.interpolate(previous_pose,current_pose,1.0f*cyclesDone/CYCLES);
				if(++cyclesDone == CYCLES){
					previous_pose = current_pose;
					polating = false;
				}

			}

//			figure.move(terrain);

			Matrix4f rootTransform = new Matrix4f();
//			rootTransform = Maths.createTransformationMatrix(new Vector3f(figure.pos_x,figure.pos_y,figure.pos_z), figure.rot_x, figure.rot_y, figure.rot_z, 1, 1,1);
			rootTransform = figure.move(terrain);
			camera.move();


			// updated transform from pose
//			Entity.updateTransforms(keyframes.poses[current_pose],entities,0);

			Entity.generateTransforms(rootTransform,0);

//			Entity.setBindTransform(keyframes.poses[current_pose],entities);

			for(Entity ent:entities) {
//				renderer.render(ent,shader);
				renderer.processEntity(ent);
			}

			for(Entity flag:figure.flags) {
				renderer.processEntity(flag);
			}

			renderer.processTerrain(terrain);

			renderer.render(light,camera);

//			renderer.render(head,shader);
//			renderer.render(body,shader);
//			renderer.render(leftarm,shader);
//			renderer.render(rightarm,shader);
//			renderer.render(leftleg,shader);
//			renderer.render(rightleg,shader);
//			shader.stop();
			DisplayManager.updateDisplay();
		}

//		shader.cleanUp();
		renderer.cleanUp();
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
		if(Keyboard.isKeyDown(Keyboard.KEY_4)){
			current_pose = 4;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_5)){
			current_pose = 5;
		}
	}

}

// config :  -Djava.library.path=lib/