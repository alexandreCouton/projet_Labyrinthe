package model;

public interface PlateauObserver {
    public void debutPartie();
    public void deplacementTuile();
    public void deplacementJoueur();
    public void captureObjectif();
    public void endGame();
    public void updateTile(Position position);
}
