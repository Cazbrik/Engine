import org.joml.Matrix4f;
import org.joml.Vector3f;

public class WorldLocation {

    private Vector3f location;
    private Vector3f rotation;

    public WorldLocation() {
        this(new Vector3f(0), new Vector3f(0));
    }

    public WorldLocation(Vector3f location, Vector3f rotation) {
        this.location = location;
        this.rotation = rotation;
    }

    public void rotate(Vector3f vec) {
        this.rotation.add(vec);
    }

    public Matrix4f worldMatrix() {
        return new Matrix4f().identity()
                .translate(this.location)
                .rotateX((float) Math.toRadians(this.rotation.x))
                .rotateY((float) Math.toRadians(this.rotation.y))
                .rotateZ((float) Math.toRadians(this.rotation.z));
    }

    public Matrix4f viewMatrix(){
        return new Matrix4f().identity()
                .rotateX((float) Math.toRadians(this.rotation.x))
                .rotateY((float) Math.toRadians(this.rotation.y))
                .rotateZ((float) Math.toRadians(this.rotation.z))
                .translate(new Vector3f(this.location).mul(-1));
    }

}
