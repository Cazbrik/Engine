import org.joml.Matrix4f;

public class Camera {

    private float fov;
    private float zNear;
    private float zFar;
    private float aspectRatio;

    public Camera(float fov, float zNear, float zFar, float aspectRatio) {
        this.fov = fov;
        this.zNear = zNear;
        this.zFar = zFar;
        this.aspectRatio = aspectRatio;
    }

    public Matrix4f projectionMatrix() {
        return new Matrix4f().identity().perspective(this.fov, this.aspectRatio, this.zNear, this.zFar);
    }

}
