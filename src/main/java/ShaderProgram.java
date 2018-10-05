
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

    private int id;
    private int vertexShaderId;
    private int fragmentShaderId;
    private Map<String, Integer> uniforms;

    public ShaderProgram(String vertexPath, String fragmentPath) throws Exception {

        if ((this.id = glCreateProgram()) == 0)
            throw new Exception("Could not create Shader");

        this.vertexShaderId = createShader(this.loadShader(vertexPath), GL_VERTEX_SHADER);
        this.fragmentShaderId = createShader(this.loadShader(fragmentPath), GL_FRAGMENT_SHADER);

        this.uniforms = new HashMap<>();

    }

    private int createShader(String shaderCode, int shaderType) throws Exception {

        int shaderId = glCreateShader(shaderType);

        if (shaderId == 0)
            throw new Exception("Error creating shader : " + shaderType);

        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0)
            throw new Exception("Error compiling shader : " + glGetShaderInfoLog(shaderId));

        glAttachShader(this.id, shaderId);
        return shaderId;

    }

    public void createUniform(String uniformName) throws Exception {
        int uniformLocation = glGetUniformLocation(this.id, uniformName);
        if (uniformLocation < 0)
            throw new Exception("Could create uniform:" + uniformName);
        this.uniforms.put(uniformName, uniformLocation);
    }

    public void setUniform(String uniformName, Matrix4f value) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer fb = stack.mallocFloat(16);
            value.get(fb);
            glUniformMatrix4fv(this.uniforms.get(uniformName), false, fb);
        }
    }

    public void link() throws Exception {

        glLinkProgram(this.id);

        if (glGetProgrami(this.id, GL_LINK_STATUS) == 0)
            throw new Exception("Error linking shader : " + glGetProgramInfoLog(id));

        if (this.vertexShaderId != 0) glDetachShader(this.id, this.vertexShaderId);
        if (this.fragmentShaderId != 0) glDetachShader(this.id, this.fragmentShaderId);

        glValidateProgram(this.id);

        if (glGetProgrami(this.id, GL_VALIDATE_STATUS) == 0)
            System.err.println("Warning validating shader : " + glGetProgramInfoLog(id));

    }

    public void bind() {
        glUseProgram(this.id);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void dispose() {
        this.unbind();
        if (this.id != 0) glDeleteProgram(this.id);
    }

    private String loadShader(String fileName) {
        String result;
        try (Scanner scanner = new Scanner(this.getClass().getResourceAsStream(fileName), "UTF-8")) {
            result = scanner.useDelimiter("\\A").next();
        }
        return result;
    }

}