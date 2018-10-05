public class Main {

    public static void main(String[] args) throws Exception {

        Display display = new Display(500, 500, "Hello World !");
        Renderer renderer = new Renderer(display, new Camera((float) Math.toRadians(60.0f), 0.01f, 1000.f));

        float[] positions = new float[]{
                -0.5f, 0.5f, -1.5f,
                -0.5f, -0.5f, -1.5f,
                0.5f, -0.5f, -1.5f,
                0.5f, 0.5f, -1.5f,
        };
        float[] colours = new float[]{0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f,};
        int[] indices = new int[]{0, 1, 3, 3, 1, 2,};

        Mesh mesh = new Mesh(positions, colours, indices);
        Entity entity = new Entity(mesh);

        while (!display.shouldClose()) {
            display.update();
            renderer.render(entity);
        }

        mesh.dispose();
        renderer.dispose();
        display.dispose();

    }

}