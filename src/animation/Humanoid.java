package animation;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import toolbox.Maths;

import java.util.ArrayList;
import java.util.Random;

public class Humanoid{
//    public Entity head;
//    public Entity body;
//    public Entity leftarm;
//    public Entity rightarm;
//    public Entity leftleg;
//    public Entity rightleg;

    public float RUN_SPEED = 50;
//    public float TURN_SPEED = 120;
    public float TURN_SPEED = 60;
    public float GRAVITY = -30;
    public float JUMP_POWER = 30;
    public float BASE_HEIGHT = 6.2f;        ////////////// ADD HEIGHT OF CENTRE OF THE MODEL.
    public float FRICTION = -10;

    public float currentSpeed = 0;
    public float currentTurnSpeed = 0;
    public float upSpeed = 0;

    public boolean isInAir = false;

    public float pos_x, pos_y, pos_z;
    public float rot_x, rot_y, rot_z;

    public void increasePosition(float x, float y, float z)
    {
        this.pos_x += x;
        this.pos_y += y;
        this.pos_z += z;
    }


    public void increaseRotation(float x, float y, float z)
    {
        this.rot_x += x;
        this.rot_y += y;
        this.rot_z += z;
    }

//    public void move(Terrain terrain)
//    {
//        checkInputs();
//        currentSpeed += FRICTION * DisplayManager.getFrameTime();
//        if(currentSpeed < 0)
//            currentSpeed = 0;
//        increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTime(), 0);
//        float distance = currentSpeed * DisplayManager.getFrameTime();
//        float dx = (float) (distance * Math.sin(Math.toRadians(rot_y)));
//        float dz = (float) (distance * Math.cos(Math.toRadians(rot_y)));
//        increasePosition( dx, 0 ,  dz);
//        upSpeed += GRAVITY * DisplayManager.getFrameTime();
//        increasePosition(0,upSpeed * DisplayManager.getFrameTime(), 0);
//        float terrainHeight = terrain.getHeightOfTerrain(pos_x,pos_z) + BASE_HEIGHT;
//        if(pos_y < terrainHeight)
//        {
//            upSpeed = 0;
//            isInAir = false;
//            pos_y = terrainHeight;
//        }
//    }

    public Matrix4f move(Terrain terrain)
    {
        checkInputs();
//        currentSpeed += FRICTION * DisplayManager.getFrameTime();
        if(currentSpeed < 0)
            currentSpeed = 0;
        increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTime(), 0);
        float distance = currentSpeed * DisplayManager.getFrameTime();
        float dx = (float) (distance * Math.sin(Math.toRadians(rot_y)));
        float dz = (float) (distance * Math.cos(Math.toRadians(rot_y)));
        increasePosition( dx, 0 ,  dz);
        upSpeed += GRAVITY * DisplayManager.getFrameTime();
        increasePosition(0,upSpeed * DisplayManager.getFrameTime(), 0);


//        float terrainHeight = terrain.getHeightOfTerrain(pos_x,pos_z) + BASE_HEIGHT;
        float terrainHeight = terrain.getHeightOfTerrain(pos_x,pos_z);

        if(pos_y < terrainHeight)
        {
            upSpeed = 0;
            isInAir = false;
            pos_y = terrainHeight;
        }

        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(new Vector3f(pos_x,pos_y,pos_z), matrix, matrix);
//        Matrix4f.scale(new Vector3f(scalex,scaley,scalez), matrix, matrix);

        Vector3f y = new Vector3f(0,1,0);
        Vector3f normal = terrain.getNormalOfTerrain(pos_x,pos_z);
        normal.normalise(normal);
        Matrix4f.rotate((float) Math.toRadians(rot_x), new Vector3f(1,0,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rot_y), new Vector3f(0,1,0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rot_z), new Vector3f(0,0,1), matrix, matrix);
//        System.out.println("Axis:" + axis.toString());
//        System.out.println("Axis length:" + axis.lengthSquared());
//        System.out.println("Dot:" + Vector3f.dot(y, normal));
        if(!y.equals(normal)) {
            float angle = (float) Math.acos(Vector3f.dot(y, normal));
            Vector3f axis = new Vector3f();
            Vector3f.cross(y, normal,axis);
            axis.normalise(axis);
//            System.out.println(Math.toDegrees(angle));
//            System.out.println("Normal: " + normal.toString());
//            Matrix4f.rotate((float) Math.toRadians(50), axis, matrix, matrix);
            Matrix4f.rotate(-angle, axis, matrix, matrix);

            currentSpeed += 0.1 * ((Vector3f.dot(new Vector3f(dx,0,dz), normal) >= 0) ? 1 : -1);
        }
//        float sina2 = (float) Math.sin(angle/2);
//        Quaternion rotQ = new Quaternion(axis.x*sina2, axis.y*sina2, axis.z*sina2, (float) Math.cos(angle/2));
//        Matrix4f.mul(matrix, rotQ.toRotationMatrix(), matrix);

//        Vector3f vec = new Vector3f();
//        vec = Vector3f.cross(new Vector3f(0,1,0), terrain.getNormalOfTerrain(pos_x,pos_z),vec);
//        vec.normalise(vec);
//        Matrix3f vhat = new Matrix3f();
//        vhat.m00 = 0;
//        vhat.m01 = -vec.z;
//        vhat.m02 = vec.y;
//        vhat.m10 = vec.z;
//        vhat.m11 = 0;
//        vhat.m12 = -vec.x;
//        vhat.m20 = -vec.y;
//        vhat.m21 = vec.z;
//        vhat.m22 = 0;
//
//        Matrix3f rot3 = new Matrix3f();
//        Matrix3f.add(new Matrix3f(), vhat, rot3);
//        Matrix3f.add(rot3, Matrix3f.mul(vhat,vhat,vhat), rot3); //DIVIDE VHAT2 BY (1+Cos)

//        Matrix4f.mul(matrix, rotQ.toRotationMatrix(), matrix);
//        Matrix4f.scale(new Vector3f(scalex,scaley,scalez), matrix, matrix);

//        return Maths.createTransformationMatrix(new Vector3f(this.pos_x,this.pos_y,this.pos_z), this.rot_x, this.rot_y, this.rot_z, 1, 1,1);

        Matrix4f.translate(new Vector3f(0,BASE_HEIGHT,0), matrix, matrix);
        return matrix;
    }


//    public void move(Terrain terrain)
//    {
//        checkInputs();
//        increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTime(), 0);
//        float distance = currentSpeed * DisplayManager.getFrameTime();
//        float dx = (float) (distance * Math.sin(Math.toRadians(rot_y)));
//        float dz = (float) (distance * Math.cos(Math.toRadians(rot_y)));
//        increasePosition( dx, 0 ,  dz);
//        upSpeed += GRAVITY * DisplayManager.getFrameTime();
//        increasePosition(0,upSpeed * DisplayManager.getFrameTime(), 0);
//        float terrainHeight = terrain.getHeightOfTerrain(pos_x,pos_z) + BASE_HEIGHT;
//        if(pos_y < terrainHeight)
//        {
//            upSpeed = 0;
//            isInAir = false;
//            pos_y = terrainHeight;
//        }
//    }

    public void checkInputs(){
//        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
//            this.currentSpeed = -RUN_SPEED;
//        }
//        else if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
//            this.currentSpeed = RUN_SPEED;
//        }
//        else {
//            this.currentSpeed = 0;
//        }
//
//        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
//            this.currentTurnSpeed = -TURN_SPEED;
//        }
//        else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
//            this.currentTurnSpeed = TURN_SPEED;
//        }
//        else {
//            this.currentTurnSpeed = 0;
//        }

        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            if(! isInAir)
            {
                this.upSpeed = JUMP_POWER;
                isInAir = true;
            }
        }
    }


    public static float[] vertices = {
            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,0.5f,-0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,-0.5f,0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            0.5f,0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            -0.5f,-0.5f,0.5f,
            -0.5f,0.5f,0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,-0.5f,0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f

    };

    public static float[] textureCoords = {

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

    public static int[] indices = {
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

    public Matrix4f[] scales = new Matrix4f[14];
    public Entity bodyParts[] = new Entity[14];
    public ArrayList<Entity> flags = new ArrayList<Entity>();

    public Humanoid(Loader loader, Terrain terrain){

        pos_x = 0;
        pos_y = 5;
        pos_z = 0;
        rot_x = 0;
        rot_y = 0;
        rot_z = 0;

        RawModel cube_model = loader.loadToVAO(vertices,textureCoords,indices);
//        RawModel sphere_model = OBJLoader.loadObjModel("sphere_tri",loader);
        RawModel sphere_model = OBJLoader.loadObjModel("new_sphere",loader);
        TexturedModel headModel = new TexturedModel(cube_model, new ModelTexture(loader.loadTexture("doraemon_face")));
        TexturedModel bodyModel = new TexturedModel(cube_model, new ModelTexture(loader.loadTexture("body")));
//        TexturedModel bodyModel = new TexturedModel(sphere_model, new ModelTexture(loader.loadTexture("dress")));
        TexturedModel armModel = new TexturedModel(cube_model, new ModelTexture(loader.loadTexture("hand")));
        TexturedModel legModel = new TexturedModel(cube_model, new ModelTexture(loader.loadTexture("leg")));
        TexturedModel skiModel = new TexturedModel(cube_model, new ModelTexture(loader.loadTexture("orange")));
        TexturedModel flagModel = new TexturedModel(cube_model, new ModelTexture(loader.loadTexture("orange")));


        //		Entity head = new Entity(staticModel, new Vector3f(0,0,0),0,0,0,1);
        bodyParts[0] = new Entity(0, bodyModel, new Vector3f(0, 0f, 0), 0, 0, 0, 1, 2, 1);
        bodyParts[1] = new Entity(1, headModel, new Vector3f(0, 2.5f, 0), 0, 0, 0, 1, 1, 1);
        bodyParts[2] = new Entity(2, armModel, new Vector3f(-1.25f, 0f, 0), 0, 0, 0, 0.5f, 2, 0.5f);
        bodyParts[3] = new Entity(3, armModel, new Vector3f(1.25f, 0f, 0), 0, 0, 0, 0.5f, 2, 0.5f);
        bodyParts[4] = new Entity(4, legModel, new Vector3f(-0.5f, -3f, 0), 0, 0, 0, 0.5f, 2, 0.5f);
        bodyParts[5] = new Entity(5, legModel, new Vector3f(0.5f, -3f, 0), 0, 0, 0, 0.5f, 2, 0.5f);
        bodyParts[6] = new Entity(6, armModel, new Vector3f(0f, -2f, 0), 0, 0, 0, 0.5f, 2, 0.5f);
        bodyParts[7] = new Entity(7, armModel, new Vector3f(0f, -2f, 0), 0, 0, 0, 0.5f, 2, 0.5f);
        bodyParts[8] = new Entity(8, legModel, new Vector3f(0f, -2f, 0), 0, 0, 0, 0.5f, 2, 0.5f);
        bodyParts[9] = new Entity(9, legModel, new Vector3f(0f, -2f, 0), 0, 0, 0, 0.5f, 2, 0.5f);
        bodyParts[10] = new Entity(10, legModel, new Vector3f(0f, -2f, 0), 0, 0, 0, 0.05f, 6, 0.05f);
        bodyParts[11] = new Entity(11, legModel, new Vector3f(0f, -2f, 0), 0, 0, 0, 0.05f, 6, 0.05f);
        bodyParts[12] = new Entity(12, skiModel, new Vector3f(0f, -2f, 0), 0, 0, 0, 0.05f, 6, 0.05f);
        bodyParts[13] = new Entity(13, skiModel, new Vector3f(0f, -2f, 0), 0, 0, 0, 0.05f, 6, 0.05f);

        scales[0] = Matrix4f.scale(new Vector3f(2,4,1f), new Matrix4f(), null);
//        scales[0] = Matrix4f.scale(new Vector3f(1,2,1f), new Matrix4f(), null);
        scales[1] = Matrix4f.scale(new Vector3f(1,1,0.5f), new Matrix4f(), null);
        scales[2] = Matrix4f.scale(new Vector3f(0.5f,2,0.5f), new Matrix4f(), null);
        scales[3] = Matrix4f.scale(new Vector3f(0.5f,2,0.5f), new Matrix4f(), null);
        scales[4] = Matrix4f.scale(new Vector3f(0.5f,2,0.5f), new Matrix4f(), null);
        scales[5] = Matrix4f.scale(new Vector3f(0.5f,2,0.5f), new Matrix4f(), null);
        scales[6] = Matrix4f.scale(new Vector3f(0.5f,2,0.5f), new Matrix4f(), null);
        scales[7] = Matrix4f.scale(new Vector3f(0.5f,2,0.5f), new Matrix4f(), null);
        scales[8] = Matrix4f.scale(new Vector3f(0.5f,2,0.5f), new Matrix4f(), null);
        scales[9] = Matrix4f.scale(new Vector3f(0.5f,2,0.5f), new Matrix4f(), null);
        scales[10] = Matrix4f.scale(new Vector3f(0.05f,0.05f,6f), new Matrix4f(), null);
        scales[11] = Matrix4f.scale(new Vector3f(0.05f,0.05f,6f), new Matrix4f(), null);
        scales[12] = Matrix4f.scale(new Vector3f(0.5f,0.05f,6), new Matrix4f(), null);
        scales[13] = Matrix4f.scale(new Vector3f(0.5f,0.05f,6), new Matrix4f(), null);

        Entity body = bodyParts[0];
        Entity head = bodyParts[1];
        Entity leftarm = bodyParts[2];
        Entity rightarm = bodyParts[3];
        Entity leftleg = bodyParts[4];
        Entity rightleg = bodyParts[5];
        Entity lowerleftarm = bodyParts[6];
        Entity lowerrightarm = bodyParts[7];
        Entity lowerleftleg = bodyParts[8];
        Entity lowerrightleg = bodyParts[9];
        Entity leftpole = bodyParts[10];
        Entity rightpole = bodyParts[11];
        Entity leftski = bodyParts[12];
        Entity rightski = bodyParts[13];

        body.children.add(head.jointIndex);
        body.children.add(leftarm.jointIndex);
        body.children.add(rightarm.jointIndex);
        body.children.add(leftleg.jointIndex);
        body.children.add(rightleg.jointIndex);
        leftarm.children.add(lowerleftarm.jointIndex);
        rightarm.children.add(lowerrightarm.jointIndex);
        leftleg.children.add(lowerleftleg.jointIndex);
        rightleg.children.add(lowerrightleg.jointIndex);
//		lowerleftleg.children.add(leftleg.jointIndex);
//		lowerrightleg.children.add(rightleg.jointIndex);
        lowerleftarm.children.add(leftpole.jointIndex);
        lowerrightarm.children.add(rightpole.jointIndex);
        lowerleftleg.children.add(leftski.jointIndex);
        lowerrightleg.children.add(rightski.jointIndex);

        body.parentID = -1;
        head.parentID = 0;
        leftarm.parentID = 0;
        rightarm.parentID = 0;
        leftleg.parentID = 0;
        rightleg.parentID = 0;
        lowerleftarm.parentID = 2;
        lowerrightarm.parentID = 3;
        lowerleftleg.parentID = 4;
        lowerrightleg.parentID = 5;
        leftski.parentID = 8;
        rightski.parentID = 9;
        leftpole.parentID = 6;
        rightpole.parentID = 7;

        Random random = new Random(676452);
        for(int i = 0 ; i < 20 ; i++)
        {
            float x = random.nextInt(2000);
            float z = -random.nextInt(2000);
            float y = terrain.getHeightOfTerrain(x,z);
            flags.add(new Entity(i,flagModel,new Vector3f(x,y,z),0f,0f,0f,2f,30f,2f));
        }





    }
}


//    float[] vertices = {
//            -0.5f,0.5f,0,
//            -0.5f,-0.5f,0,
//            0.5f,-0.5f,0,
//            0.5f,0.5f,0,
//
//            -0.5f,0.5f,1,
//            -0.5f,-0.5f,1,
//            0.5f,-0.5f,1,
//            0.5f,0.5f,1,
//
//            0.5f,0.5f,0,
//            0.5f,-0.5f,0,
//            0.5f,-0.5f,1,
//            0.5f,0.5f,1,
//
//            -0.5f,0.5f,0,
//            -0.5f,-0.5f,0,
//            -0.5f,-0.5f,1,
//            -0.5f,0.5f,1,
//
//            -0.5f,0.5f,1,
//            -0.5f,0.5f,0,
//            0.5f,0.5f,0,
//            0.5f,0.5f,1,
//
//            -0.5f,-0.5f,1,
//            -0.5f,-0.5f,0,
//            0.5f,-0.5f,0,
//            0.5f,-0.5f,1
//
//    };
//
//    float[] textureCoords = {
//
//            0,0,
//            0,1,
//            1,1,
//            1,0,
//            0,0,
//            0,1,
//            1,1,
//            1,0,
//            0,0,
//            0,1,
//            1,1,
//            1,0,
//            0,0,
//            0,1,
//            1,1,
//            1,0,
//            0,0,
//            0,1,
//            1,1,
//            1,0,
//            0,0,
//            0,1,
//            1,1,
//            1,0
//
//
//    };
//
//    int[] indices = {
//            0,1,3,
//            3,1,2,
//            4,5,7,
//            7,5,6,
//            8,9,11,
//            11,9,10,
//            12,13,15,
//            15,13,14,
//            16,17,19,
//            19,17,18,
//            20,21,23,
//            23,21,22
//
//    };

