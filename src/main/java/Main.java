public class Main {

    public static void main(String[] args) throws Exception {

        Display display = new Display(500, 500, "Hello World !");
        Renderer renderer = new Renderer();

        while (!display.shouldClose()) {
            display.update();
            renderer.render();
        }

        renderer.cleanup();
        display.dispose();

    }

}