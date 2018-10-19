import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.GL_DEPTH_BUFFER_BIT;

public class Renderer {

    private static final String PROJECTION = "projectionMatrix";
    public static final String WORLD = "worldMatrix";
    private static final String VIEW = "viewMatrix";

    private Display display;
    private ShaderProgram program;
    private Camera camera;

    public Renderer(Display display, Camera camera) throws Exception {

        this.display = display;
        this.camera = camera;

        this.program = new ShaderProgram("/shader/Vertex.vs", "/shader/Fragment.fs");
        this.program.link();
        this.program.createUniform(Renderer.PROJECTION);
        this.program.createUniform(Renderer.WORLD);
        this.program.createUniform(Renderer.VIEW);

    }

    public void render(Entity entity) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        this.program.bind();
        this.program.setUniform(Renderer.PROJECTION, this.camera.projectionMatrix(this.display.aspectRatio()));
        this.program.setUniform(Renderer.VIEW,this.camera.viewMatrix());
        entity.render(this.program);
        this.program.unbind();
    }

    public void dispose() {
        this.program.dispose();
    }

}