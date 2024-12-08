package model;

import java.util.*;

public class GameBoard {
    private final Tiles[][] m_lstTuilesPlateaus;
    private final Stack<TilesCorner> m_StackAngle;
    private final Stack<TilesT> m_stackT;
    private final Stack<TilesLinear> m_stackDroite;
    private Stack<Objective> m_stackObjective;
    private Tiles m_flyingTile;
    private ArrayList<PlateauObserver> m_lstObserver;

    public GameBoard() {
        m_lstTuilesPlateaus = new Tiles[7][7];
        m_StackAngle = new Stack<>();
        m_stackT = new Stack<>();
        m_stackDroite = new Stack<>();
        m_stackObjective = new Stack<>();
        m_flyingTile = null;
        m_lstObserver = new ArrayList<>();

        initObjective();
        initTuiles();
        initPlateau();
        placerTuile();
    }

    public void addObserver(PlateauObserver obs) {
        m_lstObserver.add(obs);
    }

    public void removeObserver(PlateauObserver obs) {
        m_lstObserver.remove(obs);
    }

    public void notifyObserverTiles(Position pos) {
        for (PlateauObserver obs : m_lstObserver) {
            obs.updateTile(pos);
        }
    }

    public Tiles getFlyTile() {
        return m_flyingTile;
    }

    public Tiles[][] getGameBoard() {
        return this.m_lstTuilesPlateaus;
    }

    private void initObjective() {
        ImageHelper imgHelper = new ImageHelper();
        List<String> lstPath = imgHelper.getPathImg("../../img/imgObjectif");
        for (int i = 0; i < 24; i++) {
            if (lstPath.isEmpty()) {
                lstPath = imgHelper.getPathImg("../../img/imgObjectif/argent.png");
            } else {
                m_stackObjective.push(new Objective(lstPath.remove(lstPath.size() - 1)));
            }
        }
        Collections.shuffle(m_stackObjective);
    }

    private void initTuiles() {
        TuileFactory m_factory = new TuileFactory();
        for (int i = 0; i < 20; i++) {
            m_StackAngle.push(m_factory.createTileCorner());
        }
        for (int i = 0; i < 18; i++) {
            m_stackT.push(m_factory.createTileT());
        }
        for (int i = 0; i < 12; i++) {
            m_stackDroite.push(m_factory.createTileLinear());
        }
    }

    private void initPlateau() {
        for (int y = 0; y < m_lstTuilesPlateaus.length; y++) {
            for (int x = 0; x < m_lstTuilesPlateaus[y].length; x++) {
                m_lstTuilesPlateaus[y][x] = null;
            }
        }
    }

    public void insertFlyingTile(Position pos) {
        int x = pos.getPositionX();
        int y = pos.getPositionY();
        Tiles replacedTile = null;
        if (x == 0) {
            replacedTile = m_lstTuilesPlateaus[y][6];
            for (int i = 6; i > 0; i--) {
                m_lstTuilesPlateaus[y][i] = m_lstTuilesPlateaus[y][i - 1];
            }
            m_lstTuilesPlateaus[y][0] = m_flyingTile;

            notifyObserverTiles(new Position(0, y));
        }
        // Déplacer les tuiles vers la gauche
        else if (x == 6) {
            replacedTile = m_lstTuilesPlateaus[y][0];
            for (int i = 0; i < 6; i++) {
                m_lstTuilesPlateaus[y][i] = m_lstTuilesPlateaus[y][i + 1];
                notifyObserverTiles(new Position(i, y));
            }
            m_lstTuilesPlateaus[y][6] = m_flyingTile;
            notifyObserverTiles(new Position(6, y));
        }
        // Déplacer les tuiles vers le haut
        else if (y == 0) {
            replacedTile = m_lstTuilesPlateaus[6][x];
            for (int i = 6; i > 0; i--) {
                m_lstTuilesPlateaus[i][x] = m_lstTuilesPlateaus[i - 1][x];
                notifyObserverTiles(new Position(x, i));
            }
            m_lstTuilesPlateaus[0][x] = m_flyingTile;
            notifyObserverTiles(new Position(x, 0));
        }
        // Déplacer les tuiles vers le bas
        else if (y == 6) {
            replacedTile = m_lstTuilesPlateaus[0][x];
            for (int i = 0; i < 6; i++) {
                m_lstTuilesPlateaus[i][x] = m_lstTuilesPlateaus[i + 1][x];
                notifyObserverTiles(new Position(x, i));
            }
            m_lstTuilesPlateaus[6][x] = m_flyingTile;
            notifyObserverTiles(new Position(x, 6));
        }

        m_flyingTile = replacedTile;
    }

    private void placerTuileSurPlateauInit(Position pos, Tiles tiles) {
        int x = pos.getPositionX();
        int y = pos.getPositionY();
        m_lstTuilesPlateaus[y][x] = tiles;
    }

    private void initPlaceTuileT() {
        m_stackT.peek().rotate(2);
        this.placerTuileSurPlateauInit(new Position(2, 0), m_stackT.pop());
        m_stackT.peek().rotate(2);
        this.placerTuileSurPlateauInit(new Position(4, 0), m_stackT.pop());
        m_stackT.peek().rotate(3);
        this.placerTuileSurPlateauInit(new Position(6, 2), m_stackT.pop());
        m_stackT.peek().rotate(3);
        this.placerTuileSurPlateauInit(new Position(6, 4), m_stackT.pop());
        this.placerTuileSurPlateauInit(new Position(4, 6), m_stackT.pop());
        this.placerTuileSurPlateauInit(new Position(2, 6), m_stackT.pop());
        m_stackT.peek().rotate();
        this.placerTuileSurPlateauInit(new Position(0, 4), m_stackT.pop());
        m_stackT.peek().rotate();
        this.placerTuileSurPlateauInit(new Position(0, 2), m_stackT.pop());
    }

    private void initPlaceTuileAng() {
        try {
            TilesCorner tuile = m_StackAngle.pop();

            this.placerTuileSurPlateauInit(new Position(0, 0), tuile);

            tuile = m_StackAngle.pop();
            tuile.rotate();
            this.placerTuileSurPlateauInit(new Position(6, 0), tuile);

            tuile = m_StackAngle.pop();
            tuile.rotate(3);
            this.placerTuileSurPlateauInit(new Position(0, 6), tuile);

            tuile = m_StackAngle.pop();
            tuile.rotate(2);
            this.placerTuileSurPlateauInit(new Position(6, 6), tuile);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void placerTuile() {
        initPlaceTuileAng();
        initPlaceTuileT();

        Random rand = new Random();

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (this.getGameBoard()[i][j] == null) {
                    boolean placed = false;

                    while (!placed) {
                        int n = rand.nextInt(3);
                        switch (n) {
                            case 0:
                                if (!m_StackAngle.isEmpty()) {
                                    TilesCorner tuile = m_StackAngle.pop();
                                    tuile.rotate(rand.nextInt(3));
                                    this.placerTuileSurPlateauInit(new Position(j, i), tuile);
                                    placed = true;
                                }
                                break;
                            case 1:
                                if (!m_stackT.isEmpty()) {
                                    TilesT tuile = m_stackT.pop();
                                    tuile.rotate(rand.nextInt(3));
                                    this.placerTuileSurPlateauInit(new Position(j, i), tuile);
                                    placed = true;
                                }
                                break;
                            case 2:
                                if (!m_stackDroite.isEmpty()) {
                                    TilesLinear tuile = m_stackDroite.pop();
                                    tuile.rotate(rand.nextInt(3));
                                    this.placerTuileSurPlateauInit(new Position(j, i), tuile);
                                    placed = true;
                                }
                                break;
                        }
                    }
                }
            }
        }
        if (!m_StackAngle.isEmpty()) {
            m_flyingTile = m_StackAngle.pop();
        } else if (!m_stackT.isEmpty()) {
            m_flyingTile = m_stackT.pop();
        } else {
            m_flyingTile = m_stackDroite.pop();
        }
    }



    public void placerTuileSurPlateau(Position pos, Tiles tile){

    }
}
