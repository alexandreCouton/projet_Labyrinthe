package model;

public interface GameBoardObserver {
    public void endGame();
    public void updateTile(Position position);
}
