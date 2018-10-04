
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private ShaderProgram program;
    private int vao;
    private int vbo;

    public Renderer() throws Exception {

        this.program = new ShaderProgram("/shader/Vertex.vs", "/shader/Fragment.fs");
        this.program.link();

        float[] vertices = new float[]{
                0.0f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f
        };

        FloatBuffer verticesBuffer = null;

        try {

            verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
            verticesBuffer.put(vertices).flip();

            this.vao = glGenVertexArrays();
            glBindVertexArray(this.vao);

            this.vbo = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, this.vbo);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

            glBindBuffer(GL_ARRAY_BUFFER, 0);
            glBindVertexArray(0);

        } finally {
            if (verticesBuffer != null) MemoryUtil.memFree(verticesBuffer);
        }

    }

    public void render() {

        this.program.bind();

        glBindVertexArray(this.vao);
        glEnableVertexAttribArray(0);

        glDrawArrays(GL_TRIANGLES, 0, 3);

        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

        this.program.unbind();
    }

    public void cleanup() {

        this.program.cleanup();

        glDisableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(this.vbo);

        glBindVertexArray(0);
        glDeleteVertexArrays(this.vao);
    }

}