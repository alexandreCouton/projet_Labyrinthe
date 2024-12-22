package model;


/**
 * The Pawn class represents a game pawn with an image path,
 * its current position, and its starting position.
 */
public class Pawn {
    private String m_path;
    private Position m_position;
    private Position m_startPosition;

    /**
     * Constructs a Pawn with a specified image path and initial position.
     *
     * @param path the path to the image representing the pawn
     * @param position the initial position of the pawn
     */
    public Pawn(String path, Position position){
        this.m_path = path;
    }

    /**
     * @param path : path of the image
     */
    public void setPath(String path){
        this.m_path = path;
    }

    /**
     * @return the path of the image
     */
    public String getPath() {
        return m_path;
    }

    /**
     * @param m_position : the position where the Pawn should be
     */
    public void setPosition(Position m_position) {
        this.m_position = m_position;
    }

    /**
     * @return get the pawn's position
     */
    public Position getPosition() {
        return m_position;
    }

    /**
     * Prints the current position of the pawn to the console.
     * Outputs the X and Y coordinates of the position.
     */
    public void toStr(){
        System.out.println("Pawn position : "+ m_position.getPositionX() +" "+ m_position.getPositionY());
    }

    /**
     * Sets the starting position of the pawn and also updates
     * the current position to match the starting position.
     *
     * @param pos the starting position of the pawn
     */
    public void setStartPosition(Position pos){
        this.m_startPosition = pos;
        this.m_position = pos;
    }
    /**
     * Gets the starting position of the pawn.
     *
     * @return the starting position of the pawn
     */
    public Position getStartPosition(){
        return m_startPosition;
    }

}

