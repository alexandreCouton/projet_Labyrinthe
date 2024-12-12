package model;

public interface PlayerObserver {
    public void movePlayer(Position oldPos, Position newPos, String path);
}
