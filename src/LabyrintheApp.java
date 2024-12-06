import controller.GameController;
import model.GameBoard;
import view.GameDisplay;

public class LabyrintheApp {
    public static void main(String[] args) {
        GameBoard p = new GameBoard();

        GameController gameController = new GameController(p);

        GameDisplay observer = new GameDisplay(p);

    }
}
