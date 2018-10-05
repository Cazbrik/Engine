import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Entity {

    private Mesh mesh;
    private Vector3f location;
    private Vector3f rotation;
    private float scale;

    public Entity(Mesh mesh) {
        this.mesh = mesh;
        this.location = new Vector3f(0);
        this.rotation = new Vector3f(0);
        this.scale = 1f;
    }

    private Matrix4f worldMatrix() {
        return new Matrix4f().identity().translate(this.location).
                rotateX((float) Math.toRadians(this.rotation.x))
                .rotateY((float) Math.toRadians(this.rotation.y))
                .rotateZ((float) Math.toRadians(this.rotation.z))
                .scale(this.scale);
    }

    public void render(ShaderProgram program) {
        program.setUniform(Renderer.WORLD,this.worldMatrix());
        this.mesh.render();
    }

}
