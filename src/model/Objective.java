package model;


/**
 * The Objective class represents an objective in the game.
 * An objective has a name and optionally an associated image path.
 */
public class Objective {
    private String m_path;
    private String m_name;

    /**
     * Constructs an Objective with a specified name.
     *
     * @param nom the name of the objective
     */
    public Objective(String nom){
        this.m_name = nom;
    }

    /**
     * Constructs an Objective with a specified name and image path.
     *
     * @param name the name of the objective
     * @param path the path to the image representing the objective
     */
    public Objective(String name, String path){
        this.m_name= name;
        this.m_path=path;
    }

    /**
     * @return the name of the objective
     */
    public String getNameObjectif(){
        return m_name;
    }

    /**
     * @return path : the path of the image
     */
    public String getPath(){
        return m_path;
    }
}
