package animation;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import textures.ModelTexture;

public class Humanoid{
//    public Entity head;
//    public Entity body;
//    public Entity leftarm;
//    public Entity rightarm;
//    public Entity leftleg;
//    public Entity rightleg;

    public Entity bodyParts[] = new Entity[6];

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

    public Humanoid(Loader loader){

        RawModel model = loader.loadToVAO(vertices,textureCoords,indices);

        TexturedModel headModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("doraemon_face")));
        TexturedModel bodyModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("body")));
        TexturedModel armModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("hand")));
        TexturedModel legModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("leg")));

        //		Entity head = new Entity(staticModel, new Vector3f(0,0,0),0,0,0,1);
        bodyParts[0] = new Entity(1, headModel, new Vector3f(0, 0, 0), 0, 0, 0, 1, 1, 1);
        bodyParts[1] = new Entity(0, bodyModel, new Vector3f(0, -2.5f, 0), 0, 0, 0, 2, 4, 1);
        bodyParts[2] = new Entity(2, armModel, new Vector3f(-1.25f, -1.5f, 0), 0, 0, 0, 0.5f, 2, 1);
        bodyParts[3] = new Entity(3, armModel, new Vector3f(1.25f, -1.5f, 0), 0, 0, 0, 0.5f, 2, 1);
        bodyParts[4] = new Entity(4, legModel, new Vector3f(-0.5f, -5.5f, 0), 0, 0, 0, 0.5f, 2, 1);
        bodyParts[5] = new Entity(5, legModel, new Vector3f(0.5f, -5.5f, 0), 0, 0, 0, 0.5f, 2, 1);
    }
}
