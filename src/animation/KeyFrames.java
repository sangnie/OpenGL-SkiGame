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
    public ArrayList<LocalTransform>[] poses = (ArrayList<LocalTransform>[])new ArrayList[7];

    public KeyFrames(Humanoid human) {
//        human = new Humanoid(loader);

        poses[0] = new ArrayList<>(14);
        poses[1] = new ArrayList<>(14);
        poses[2] = new ArrayList<>(14);
        poses[3] = new ArrayList<>(14);
        poses[4] = new ArrayList<>(14);
        poses[5] = new ArrayList<>(14);
        poses[6] = new ArrayList<>(14);
        Matrix4f matrix = new Matrix4f();
        LocalTransform[] orig = new LocalTransform[14];
        orig[0] = new LocalTransform(new Vector3f(0, 0f, 0), 0, new Vector3f(0, 0f, 0));
        orig[1] = new LocalTransform(new Vector3f(0, 2.5f, 0),0, new Vector3f(0, 0f, 0));
        orig[2] = new LocalTransform(new Vector3f(-1.25f, 1f, 0),0, new Vector3f(0, 0f, 0));
        orig[3] = new LocalTransform(new Vector3f(1.25f, 1f, 0),0, new Vector3f(0, 0f, 0));
        orig[4] = new LocalTransform(new Vector3f(-0.5f, -3f, 0),0, new Vector3f(0, 0f, 0));
        orig[5] = new LocalTransform(new Vector3f(0.5f, -3f, 0),0, new Vector3f(0, 0f, 0));
        orig[6] = new LocalTransform(new Vector3f(0f, -2f, 0),0, new Vector3f(0, 0f, 0));
        orig[7] = new LocalTransform(new Vector3f(0f, -2f, 0),0, new Vector3f(0, 0f, 0));
        orig[8] = new LocalTransform(new Vector3f(0f, -2f, 0),0, new Vector3f(0, 0f, 0));
        orig[9] = new LocalTransform(new Vector3f(0f, -2f, 0),0, new Vector3f(0, 0f, 0));
        orig[10] = new LocalTransform(new Vector3f(0f, -1f, -3),0, new Vector3f(1, 0f, 0));
        orig[11] = new LocalTransform(new Vector3f(0f, -1f, -3),0, new Vector3f(1, 0f, 0));
        orig[12] = new LocalTransform(new Vector3f(0f, -1f, 0),0, new Vector3f(1, 0f, 0));
        orig[13] = new LocalTransform(new Vector3f(0f, -1f, 0),0, new Vector3f(1, 0f, 0));

        System.out.println("size : " + poses[0].size());

        for(int i=0; i<14; i++){
//            poses[0].set(i, orig[i]);
//            poses[0].add(human.bodyParts[i].bindTransform);
            poses[0].add(orig[i]);
        }

        for(int i=0; i<14; i++){
            poses[1].add(i, orig[i]);
//            poses[1].add(human.bodyParts[i].bindTransform);
        }

        matrix = new Matrix4f();
//        Matrix4f.translate(new Vector3f(1.5f,-2.5f,0), matrix, matrix);
////        Matrix4f.rotate((float) Math.toRadians(0), new Vector3f(1,0,0), matrix, matrix);
////        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matrix, matrix);
//        Matrix4f.rotate((float) Math.toRadians(45), new Vector3f(0,0,1), matrix, matrix);
//        Matrix4f.scale(new Vector3f(0.5f,2,1), matrix, matrix);

        poses[1].set(4,new LocalTransform(new Vector3f(-1.5f,-2.5f,0),-45,new Vector3f(0,0,1)));
        poses[1].set(5,new LocalTransform(new Vector3f(1.5f,-2.5f,0),45,new Vector3f(0,0,1)));
        poses[1].set(6,new LocalTransform(new Vector3f(0f,-1f,1f),-90,new Vector3f(1,0,0)));
        poses[1].set(7,new LocalTransform(new Vector3f(0f,-1f,1f),-90,new Vector3f(1,0,0)));
        poses[1].set(8,new LocalTransform(new Vector3f(0.707f,-1.707f,0),45,new Vector3f(0,0,1)));
        poses[1].set(9,new LocalTransform(new Vector3f(-0.707f,-1.707f,0),-45,new Vector3f(0,0,1)));
//        poses[1].set(4,matrix);
//        matrix = new Matrix4f();
//        Matrix4f.translate(new Vector3f(-1.5f,-2.5f,0), matrix, matrix);
//        Matrix4f.rotate((float) Math.toRadians(-45), new Vector3f(0,0,1), matrix, matrix);
//        Matrix4f.scale(new Vector3f(0.5f,2,1), matrix, matrix);
//
//        poses[1].add(matrix);

        for(int i=0; i<14; i++){
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
//        poses[2].set(4,new LocalTransform(new Vector3f(-0.5f,-2.5f,0),-45,new Vector3f(1,0,0)));
//        poses[2].set(5,new LocalTransform(new Vector3f(0.5f,-2.5f,0),45,new Vector3f(1,0,0)));
        poses[2].set(0,new LocalTransform(new Vector3f(0f,0f,0f),45,new Vector3f(1,0,0)));
        poses[2].set(2,new LocalTransform(new Vector3f(-1.25f,1f,0.707f),-45,new Vector3f(1,0,0)));
        poses[2].set(3,new LocalTransform(new Vector3f(1.25f,1f,0.707f),-45,new Vector3f(1,0,0)));
        poses[2].set(4,new LocalTransform(new Vector3f(-0.5f,-2.707f,0.707f),-45,new Vector3f(1,0,0)));
        poses[2].set(5,new LocalTransform(new Vector3f(0.5f,-2.707f,0.707f),-45,new Vector3f(1,0,0)));
        poses[2].set(6,new LocalTransform(new Vector3f(0f,-1f,1f),-90,new Vector3f(1,0,0)));
        poses[2].set(7,new LocalTransform(new Vector3f(0f,-1f,1f),-90,new Vector3f(1,0,0)));
        poses[2].set(8,new LocalTransform(new Vector3f(0f,-1.707f,-0.707f),45,new Vector3f(1,0,0)));
        poses[2].set(9,new LocalTransform(new Vector3f(0f,-1.707f,-0.707f),45,new Vector3f(1,0,0)));
        poses[2].set(12,new LocalTransform(new Vector3f(0f,-1f,0f),-45,new Vector3f(1,0,0)));
        poses[2].set(13,new LocalTransform(new Vector3f(0f,-1f,0f),-45,new Vector3f(1,0,0)));

        for(int i=0; i<14; i++){
//            poses[2].add(human.bodyParts[i].bindTransform);
            poses[3].add(i,orig[i]);
        }

        poses[3].set(0,new LocalTransform(new Vector3f(0f,0f,0f),45,new Vector3f(1,0,0)));
        poses[3].set(2,new LocalTransform(new Vector3f(-1.25f,1f,0.707f),-45,new Vector3f(1,0,0)));
        poses[3].set(3,new LocalTransform(new Vector3f(1.25f,1f,0.707f),-45,new Vector3f(1,0,0)));
        poses[3].set(4,new LocalTransform(new Vector3f(-0.5f,-2.707f,0.707f),-45,new Vector3f(1,0,0)));
        poses[3].set(5,new LocalTransform(new Vector3f(0.5f,-2.707f,0.707f),-45,new Vector3f(1,0,0)));
        poses[3].set(8,new LocalTransform(new Vector3f(0f,-1.707f,-0.707f),45,new Vector3f(1,0,0)));
        poses[3].set(9,new LocalTransform(new Vector3f(0f,-1.707f,-0.707f),45,new Vector3f(1,0,0)));
        poses[3].set(12,new LocalTransform(new Vector3f(0f,-1f,0f),-45,new Vector3f(1,0,0)));
        poses[3].set(13,new LocalTransform(new Vector3f(0f,-1f,0f),-45,new Vector3f(1,0,0)));

        for(int i=0; i<14; i++){
//            poses[2].add(human.bodyParts[i].bindTransform);
            poses[4].add(i,orig[i]);
        }

        poses[4].set(0,new LocalTransform(new Vector3f(0f,0f,0f),30,new Vector3f(0,0,1)));
        poses[4].set(2,new LocalTransform(new Vector3f(-1.75f,0.85f,0f),-30,new Vector3f(0,0,1)));
        poses[4].set(3,new LocalTransform(new Vector3f(1.75f,0.85f,0f),30,new Vector3f(0,0,1)));
        poses[4].set(4,new LocalTransform(new Vector3f(-0.5f,-2.707f,0.707f),-45,new Vector3f(1,0,0)));
        poses[4].set(5,new LocalTransform(new Vector3f(0.5f,-2.707f,0.707f),-45,new Vector3f(1,0,0)));
        poses[4].set(6,new LocalTransform(new Vector3f(0f,-1f,1f),-90,new Vector3f(1,0,0)));
        poses[4].set(7,new LocalTransform(new Vector3f(0f,-1f,1f),-90,new Vector3f(1,0,0)));
        poses[4].set(8,new LocalTransform(new Vector3f(0f,-1.707f,-0.707f),45,new Vector3f(1,0,0)));
        poses[4].set(9,new LocalTransform(new Vector3f(0f,-1.707f,-0.707f),45,new Vector3f(1,0,0)));
        poses[4].set(10,new LocalTransform(new Vector3f(-2.121f, -1f, -2.121f),45, new Vector3f(0, 1f, 0)));

        for(int i=0; i<14; i++){
//            poses[2].add(human.bodyParts[i].bindTransform);
            poses[5].add(i,orig[i]);
        }

        poses[5].set(0,new LocalTransform(new Vector3f(0f,0f,0f),-30,new Vector3f(0,0,1)));
        poses[5].set(3,new LocalTransform(new Vector3f(1.75f,0.85f,0f),30,new Vector3f(0,0,1)));
        poses[5].set(2,new LocalTransform(new Vector3f(-1.75f,0.85f,0f),-30,new Vector3f(0,0,1)));
        poses[5].set(4,new LocalTransform(new Vector3f(-0.5f,-2.707f,0.707f),-45,new Vector3f(1,0,0)));
        poses[5].set(5,new LocalTransform(new Vector3f(0.5f,-2.707f,0.707f),-45,new Vector3f(1,0,0)));
        poses[5].set(6,new LocalTransform(new Vector3f(0f,-1f,1f),-90,new Vector3f(1,0,0)));
        poses[5].set(7,new LocalTransform(new Vector3f(0f,-1f,1f),-90,new Vector3f(1,0,0)));
        poses[5].set(8,new LocalTransform(new Vector3f(0f,-1.707f,-0.707f),45,new Vector3f(1,0,0)));
        poses[5].set(9,new LocalTransform(new Vector3f(0f,-1.707f,-0.707f),45,new Vector3f(1,0,0)));
        poses[5].set(11,new LocalTransform(new Vector3f(-2.121f, -1f, -2.121f),45, new Vector3f(0, 1f, 0)));
//        poses[4].set(12,new LocalTransform(new Vector3f(0f,-1f,0f),-45,new Vector3f(1,0,0)));
//        poses[4].set(13,new LocalTransform(new Vector3f(0f,-1f,0f),-45,new Vector3f(1,0,0)));
    }

    public ArrayList<LocalTransform> interpolate(int prev, int next, float t){

        ArrayList<LocalTransform> pose = new ArrayList<LocalTransform>(10);
        ArrayList<LocalTransform> prevPose = poses[prev];
        ArrayList<LocalTransform> nextPose = poses[next];
        for(int i = 0 ; i < nextPose.size() ; i++) {
            float x = prevPose.get(i).translation.x + (nextPose.get(i).translation.x - prevPose.get(i).translation.x) * t;
            float y = prevPose.get(i).translation.y + (nextPose.get(i).translation.y - prevPose.get(i).translation.y) * t;
            float z = prevPose.get(i).translation.z + (nextPose.get(i).translation.z - prevPose.get(i).translation.z) * t;
            Quaternion rot = Quaternion.interpolate(prevPose.get(i).rotation, nextPose.get(i).rotation, t);
            pose.add(new LocalTransform(new Vector3f(x,y,z), rot));
        }
        return pose;
    }
}