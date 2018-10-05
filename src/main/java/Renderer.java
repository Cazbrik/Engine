
public class Renderer {

    private ShaderProgram program;

    public Renderer() throws Exception {
        this.program = new ShaderProgram("/shader/Vertex.vs", "/shader/Fragment.fs");
        this.program.link();
    }

    public void render(Mesh mesh) {
        this.program.bind();
        mesh.render();
        this.program.unbind();
    }

    public void cleanup() {
        this.program.cleanup();
    }

}