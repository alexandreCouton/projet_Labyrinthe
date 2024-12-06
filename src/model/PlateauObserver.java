package model;

public interface PlateauObserver {
    public void debutPartie();
    public void deplacementTuile();
    public void deplacementJoueur();
    public void captureObjectif();
    public void finPartie();
    public void updateTile(Position position);
}
