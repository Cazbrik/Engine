import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_2;

public class Main {

    public static void main(String[] args) throws Exception {

        Display display = new Display(500, 500, "Hello World !");
        Camera camera = new Camera((float) Math.toRadians(60.0f), 0.01f, 1000.f);
        Renderer renderer = new Renderer(display, camera);

        float[] positions = new float[]{
                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                -0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
        };
        float[] colours = new float[]{
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        int[] indices = new int[]{
                0, 1, 3, 3, 1, 2,
                4, 0, 3, 5, 4, 3,
                3, 2, 7, 5, 3, 7,
                6, 1, 0, 6, 0, 4,
                2, 1, 6, 2, 6, 7,
                7, 6, 4, 7, 4, 5,
        };

        Mesh mesh = new Mesh(positions, colours, indices);
        Entity entity = new Entity(mesh);


        while (!display.shouldClose()) {

            if (display.mouseInput(GLFW_MOUSE_BUTTON_1))
                entity.rotate(new Vector3f(0,5,0));
            if (display.mouseInput(GLFW_MOUSE_BUTTON_2))
                entity.rotate(new Vector3f(5,0,0));

            display.update();
            renderer.render(entity);

        }

        mesh.dispose();
        renderer.dispose();
        display.dispose();

    }

}