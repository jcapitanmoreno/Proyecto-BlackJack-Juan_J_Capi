import Controller.Controller;
import View.View;

public class App {

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller();

        view.blackJackView();
        view.gameRules();
        controller.start();
    }
}
