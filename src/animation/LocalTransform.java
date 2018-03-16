package animation;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class LocalTransform {
    public Vector3f translation;
    public Quaternion rotation;

    public LocalTransform(Vector3f translation, Quaternion rotation){
        this.translation = translation;
        this.rotation = rotation;
    }

    public LocalTransform(Vector3f translation, Matrix4f rotation){
        this.translation = translation;
        this.rotation = Quaternion.fromMatrix(rotation);
    }

    public LocalTransform(Vector3f translation, float rot, Vector3f rotation){
        this.translation = translation;
//        this.rotation = Quaternion.fromMatrix(rotation);
        Matrix4f matrix = new Matrix4f();
        Matrix4f.rotate((float) Math.toRadians(rot), rotation, matrix, matrix);
        System.out.println();
        this.rotation = Quaternion.fromMatrix(matrix);
    }
}
