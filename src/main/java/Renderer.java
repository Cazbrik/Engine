public class Renderer {

    private static final String PROJECTION = "projectionMatrix";
    public static final String WORLD = "worldMatrix";

    private ShaderProgram program;
    private Camera camera;

    public Renderer(Camera camera) throws Exception {

        this.camera = camera;

        this.program = new ShaderProgram("/shader/Vertex.vs", "/shader/Fragment.fs");
        this.program.link();
        this.program.createUniform(Renderer.PROJECTION);
        this.program.createUniform(Renderer.WORLD);

    }

    public void render(Entity entity) {
        this.program.bind();
        this.program.setUniform(Renderer.PROJECTION, this.camera.projectionMatrix());
        entity.render(this.program);
        this.program.unbind();
    }

    public void dispose() {
        this.program.dispose();
    }

}