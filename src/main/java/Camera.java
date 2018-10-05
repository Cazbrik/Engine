import org.joml.Matrix4f;

public class Camera {

    private float fov;
    private float zNear;
    private float zFar;

    public Camera(float fov, float zNear, float zFar) {
        this.fov = fov;
        this.zNear = zNear;
        this.zFar = zFar;
    }

    public Matrix4f projectionMatrix(float aspectRatio) {
        return new Matrix4f().identity().perspective(this.fov, aspectRatio, this.zNear, this.zFar);
    }

}
