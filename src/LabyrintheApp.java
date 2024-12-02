import controller.GameController;
import view.GameDisplay;
import model.Plateau;
public class LabyrintheApp {
    public static void main(String[] args) {
        Plateau p = new Plateau();
        p.initPartie();
        GameController gameController = new GameController(p);

        GameDisplay observer = new GameDisplay(p);
        gameController.addObserver(observer);
        gameController.initPartie();
    }
}
