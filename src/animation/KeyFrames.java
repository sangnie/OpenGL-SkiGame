package animation;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import textures.ModelTexture;

import java.util.ArrayList;
import java.util.LinkedList;


public class KeyFrames {

    Humanoid human;
    ArrayList<Matrix4f> poses[];

    public KeyFrames(Loader loader) {
        human = new Humanoid(loader);

        poses[0] = new ArrayList<>(6);
        poses[1] = new ArrayList<>(6);

        for(int i=0; i<6; i++){
            poses[0].set(i, human.bodyParts[i].bindTransform);
        }

        for(int i=0; i<4; i++){
            poses[0].set(i, human.bodyParts[i].bindTransform);
        }

        Matrix4f matrix = new Matrix4f();
//        Matrix4f.translate(translation, matrix, matrix);
//        Matrix4f.rotate((float) Math.toRadians(0), new Vector3f(1,0,0), matrix, matrix);
//        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(90), new Vector3f(0,0,1), matrix, matrix);
        poses[1].set(4,matrix);
        matrix = new Matrix4f();
        Matrix4f.rotate((float) Math.toRadians(-90), new Vector3f(0,0,1), matrix, matrix);
        poses[1].set(5,matrix);
    }
}