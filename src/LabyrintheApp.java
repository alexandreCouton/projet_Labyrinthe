import controller.GameController;
import model.Game;
import model.GameBoard;
import model.PlayerObserver;
import view.GameDisplay;
import view.PlateauPanel;

public class LabyrintheApp {
    public static void main(String[] args) {
        Game game = new Game();
        GameController gameController = new GameController(game);
        PlateauPanel obsPlayer = new PlateauPanel(game.getGameBoard());
        GameDisplay observer = new GameDisplay(game, obsPlayer);
        game.getGameBoard().addObserver(observer);
        for (int i = 0; i < 4; i++) {
            game.getPlayer(i).addObserver(obsPlayer);
        }

    }
}
