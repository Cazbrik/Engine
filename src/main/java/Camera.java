import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Camera {

    private float fov;
    private float zNear;
    private float zFar;

    private WorldLocation location;

    public Camera(float fov, float zNear, float zFar) {
        this.fov = fov;
        this.zNear = zNear;
        this.zFar = zFar;
        this.location = new WorldLocation(new Vector3f(0, 0, 5), new Vector3f(0));
    }

    public Matrix4f viewMatrix() {
        return this.location.viewMatrix();
    }

    public Matrix4f projectionMatrix(float aspectRatio) {
        return new Matrix4f().identity().perspective(this.fov, aspectRatio, this.zNear, this.zFar);
    }

    public void rotate(Vector3f vec) {
        this.location.rotate(vec);
    }

}
