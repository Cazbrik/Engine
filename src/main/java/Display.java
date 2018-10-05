import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Display {

    private long id;
    private int height;
    private int width;
    private String title;
    private boolean isResized;

    public Display(int height, int width, String title) throws Exception {

        this.height = height;
        this.width = width;
        this.title = title;
        this.isResized = false;

        /* Todo should not print in System.err */
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new Exception("Unable to initialize GLFW");

        if ((this.id = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL)) == NULL)
            throw new Exception("Failed to create the GLFW window");

        glfwSetKeyCallback(this.id, (window, key, scanCode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });

        glfwSetFramebufferSizeCallback(this.id, (window, newWidth, newHeight) -> {
            this.width = newWidth;
            this.height = newHeight;
            this.isResized = true;
        });

        glfwDefaultWindowHints();
        glfwMakeContextCurrent(this.id);
        glfwSwapInterval(1);
        glfwShowWindow(this.id);
        GL.createCapabilities();
        glClearColor(0f, 0f, 0f, 0.0f);

    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(this.id);
    }

    public void update() {

        //glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwSwapBuffers(this.id);
        glfwPollEvents();

        if (this.isResized) {
            glViewport(0, 0, this.width, this.height);
            this.isResized = false;
        }

    }

    public void dispose() {
        glfwFreeCallbacks(this.id);
        glfwDestroyWindow(this.id);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public float aspectRatio() {
        return ((float) this.width) / ((float) this.height);
    }
}