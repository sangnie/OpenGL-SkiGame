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

import static toolbox.Maths.createTransformationMatrix;


public class KeyFrames {

    Humanoid human;
//    public ArrayList<Matrix4f>[] poses = (ArrayList<Matrix4f>[])new ArrayList[3];
    public ArrayList<LocalTransform>[] poses = (ArrayList<LocalTransform>[])new ArrayList[3];

    public KeyFrames(Humanoid human) {
//        human = new Humanoid(loader);


        poses[0] = new ArrayList<>(6);
        poses[1] = new ArrayList<>(6);
        poses[2] = new ArrayList<>(6);
        Matrix4f matrix = new Matrix4f();
        LocalTransform[] orig = new LocalTransform[6];
        orig[0] = new LocalTransform(new Vector3f(0, 0f, 0), 0, new Vector3f(0, 0f, 0));
        orig[1] = new LocalTransform(new Vector3f(0, 2.5f, 0),0, new Vector3f(0, 0f, 0));
        orig[2] = new LocalTransform(new Vector3f(-1.25f, 0f, 0),0, new Vector3f(0, 0f, 0));
        orig[3] = new LocalTransform(new Vector3f(1.25f, 0f, 0),0, new Vector3f(0, 0f, 0));
        orig[4] = new LocalTransform(new Vector3f(-0.5f, -3f, 0),0, new Vector3f(0, 0f, 0));
        orig[5] = new LocalTransform(new Vector3f(0.5f, -3f, 0),0, new Vector3f(0, 0f, 0));

        System.out.println("size : " + poses[0].size());

        for(int i=0; i<6; i++){
//            poses[0].set(i, orig[i]);
//            poses[0].add(human.bodyParts[i].bindTransform);
            poses[0].add(orig[i]);
        }

        for(int i=0; i<4; i++){
            poses[0].add(i, orig[i]);
//            poses[1].add(human.bodyParts[i].bindTransform);
        }

        matrix = new Matrix4f();
//        Matrix4f.translate(new Vector3f(1.5f,-2.5f,0), matrix, matrix);
////        Matrix4f.rotate((float) Math.toRadians(0), new Vector3f(1,0,0), matrix, matrix);
////        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
//        Matrix4f.rotate((float) Math.toRadians(45), new Vector3f(0,0,1), matrix, matrix);
//        Matrix4f.scale(new Vector3f(0.5f,2,1), matrix, matrix);

        poses[1].add(new LocalTransform(new Vector3f(1.5f,-2.5f,0),45,new Vector3f(0,0,1)));
//        poses[1].set(4,matrix);
//        matrix = new Matrix4f();
//        Matrix4f.translate(new Vector3f(-1.5f,-2.5f,0), matrix, matrix);
//        Matrix4f.rotate((float) Math.toRadians(-45), new Vector3f(0,0,1), matrix, matrix);
//        Matrix4f.scale(new Vector3f(0.5f,2,1), matrix, matrix);
//
//        poses[1].add(matrix);
        poses[1].add(new LocalTransform(new Vector3f(1.5f,-2.5f,0),-45,new Vector3f(0,0,1)));

        for(int i=0; i<4; i++){
//            poses[2].add(human.bodyParts[i].bindTransform);
            poses[2].add(i,orig[i]);
        }
//        matrix = new Matrix4f();
//        Matrix4f.translate(new Vector3f(0.5f,-2.5f,0), matrix, matrix);
////        Matrix4f.translate(new Vector3f(0f,0f,0), matrix, matrix);
//        Matrix4f.rotate((float) Math.toRadians(-45), new Vector3f(1,0,0), matrix, matrix);
//        Matrix4f.scale(new Vector3f(0.5f,2,1), matrix, matrix);
//        poses[2].add(matrix);
//
//        matrix = new Matrix4f();
//        Matrix4f.translate(new Vector3f(-.5f,-2.5f,0), matrix, matrix);
////        Matrix4f.translate(new Vector3f(0f,0f,0), matrix, matrix);
//        Matrix4f.rotate((float) Math.toRadians(45), new Vector3f(1,0,0), matrix, matrix);
//        Matrix4f.scale(new Vector3f(0.5f,2,1), matrix, matrix);
//        poses[2].add(matrix);
        poses[2].add(new LocalTransform(new Vector3f(0.5f,-2.5f,0),45,new Vector3f(1,0,0)));
        poses[2].add(new LocalTransform(new Vector3f(0.5f,-2.5f,0),-45,new Vector3f(1,0,0)));

    }
}