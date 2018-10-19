import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Entity {

    private Mesh mesh;
    private WorldLocation location;
    private float scale;

    public Entity(Mesh mesh) {
        this.mesh = mesh;
        this.location = new WorldLocation(new Vector3f(0, 0, 0), new Vector3f(0));
        this.scale = 1f;
    }

    private Matrix4f worldMatrix() {
        return this.location.worldMatrix().scale(this.scale);
    }

    public void render(ShaderProgram program) {
        program.setUniform(Renderer.WORLD, this.worldMatrix());
        this.mesh.render();
    }

    public void rotate(Vector3f vec) {
        this.location.rotate(vec);
    }
}
