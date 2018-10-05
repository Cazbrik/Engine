public class Renderer {

    private static final String PROJECTION = "projectionMatrix";
    public static final String WORLD = "worldMatrix";

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

    }

    public void render(Entity entity) {
        this.program.bind();
        this.program.setUniform(Renderer.PROJECTION, this.camera.projectionMatrix(this.display.aspectRatio()));
        entity.render(this.program);
        this.program.unbind();
    }

    public void dispose() {
        this.program.dispose();
    }

}