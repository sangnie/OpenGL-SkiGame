package entities;

import animation.LocalTransform;
import com.sun.tools.javac.Main;
import engineTester.MainGameLoop;
import models.TexturedModel;

import org.lwjgl.Sys;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import toolbox.Maths;

import java.util.ArrayList;

public class Entity {

	private TexturedModel model;
	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scaleX, scaleY, scaleZ;
	public int jointIndex;
	public Matrix4f bindTransform;
	public ArrayList<Integer> children = new ArrayList<Integer>();
	public int parentID;
//	private float scale;
//	public static ArrayList<Matrix4f> finalRenderTransforms = new ArrayList<Matrix4f>();
//	public static ArrayList<Matrix4f> withoutScaleTransform = new ArrayList<Matrix4f>();
//	public static ArrayList<Matrix4f> scaleTransforms;
//	public static Matrix4f[] finalRenderTransforms = new Matrix4f[6];
    public Matrix4f finalRenderTransform;
	public static Matrix4f[] withoutScaleTransforms = new Matrix4f[6];
	public static Matrix4f[] scaleTransforms = new Matrix4f[6];
	public static ArrayList<LocalTransform> translationsRotations;

	public Entity(int jointIndex, TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ,
				  float scaleX, float scaleY, float scaleZ) {
//			float scale) {
		this.model = model;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.scaleZ = scaleZ;
		this.jointIndex = jointIndex;
		this.bindTransform = Maths.createTransformationMatrix(position,
				rotX, rotY, rotZ,scaleX, scaleY,scaleZ);
//		this.scale = scale;
	}

	// Update all localBindtransforms according to current pose
//	public static void updateTransforms(ArrayList<Matrix4f> updatedTransforms, ArrayList<Entity> entities, int index) {
//		entities.get(index).bindTransform = Matrix4f.mul(updatedTransforms.get(index),entities.get(index).bindTransform,null);
//		for(int child:entities.get(index).children) {
//			updateTransforms(updatedTransforms,entities,child);
//		}
//	}

	public static void generateTransforms(Matrix4f parentTransform, int index) {
		Matrix4f localTransform = new Matrix4f();
        localTransform.translate(translationsRotations.get(index).translation);
        Matrix4f.mul(localTransform, translationsRotations.get(index).rotation.toRotationMatrix(), localTransform);
		Matrix4f globalTransform = Matrix4f.mul(parentTransform,localTransform,null);
		withoutScaleTransforms[index] = globalTransform;
		Matrix4f finalTransform = Matrix4f.mul(globalTransform,scaleTransforms[index],null);
//		finalRenderTransforms[index] = finalTransform;
        MainGameLoop.entities.get(index).finalRenderTransform = finalTransform;
		for(int child: MainGameLoop.entities.get(index).children) {
//		    System.out.println(index + " : " + child);
			generateTransforms(globalTransform,child);
		}
	}

	public static void setBindTransform(ArrayList<Matrix4f> updatedTransforms, ArrayList<Entity> entities) {
//		System.out.println(entities.size());
//		System.out.println(updatedTransforms.size());
		for(int i = 0 ; i < entities.size() ; i++) {
			entities.get(i).bindTransform = updatedTransforms.get(i);
		}
	}

	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}

	public void increaseRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

//	public void setScale(float scale) {
//		this.scale = scale;
//	}
//
//	public float getScale() {
//		return scale;
//	}

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public float getScaleZ() {
		return scaleZ;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}

	public void setScaleZ(float scaleZ) {
		this.scaleZ = scaleZ;
	}
}
