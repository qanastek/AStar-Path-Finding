package sample.Models;

import javafx.scene.input.MouseEvent;
import sample.Config.BoardSettings;
import sample.Vues.Main.Controller;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Board {

    // The board
    public ArrayList<ArrayList<Tile>> matrix;

    // The departure
    public Tile departure;

    // The arrival
    public Tile arrival;

    /**
     * Constructor
     */
    public Board() {

        // Instantiate the board
        this.matrix = new ArrayList<ArrayList<Tile>>();

        // Initialize the board
        this.initialize();

        // Add departure and arrival
        this.generateDeparturesAndArrival();

        // Add walls
        // By hand
    }

    /**
     * Add departure and arrival
     */
    private void generateDeparturesAndArrival() {

        int min = 0;
        int max = BoardSettings.DIMENSIONS-1;

        // Departure
        int x1 = ThreadLocalRandom.current().nextInt(min, max + 1);
        int y1 = ThreadLocalRandom.current().nextInt(min, max + 1);

        // Set coordinates and place it
        this.departure = this.matrix.get(x1).get(y1);
        this.departure.setType(TileType.DEPARTURE);

        // Arrival
        int x2 = ThreadLocalRandom.current().nextInt(min, max + 1);
        int y2 = ThreadLocalRandom.current().nextInt(min, max + 1);

        // Set coordinates and place it
        this.arrival = this.matrix.get(x2).get(y2);
        this.arrival.setType(TileType.ARRIVAL);
    }

    /**
     * Initialize the board
     */
    private void initialize() {

        // X axis
        for (int i = 0; i < BoardSettings.DIMENSIONS; i++) {

            // Create
            ArrayList<Tile> row = new ArrayList<Tile>();

            // Y axis
            for (int j = 0; j < BoardSettings.DIMENSIONS; j++) {

                // Instantiate the tile
                Tile tile = new Tile(TileType.GROUNDS);

                // Set coordinates
                tile.setX(i);
                tile.setY(j);

                // System.out.println(tile);

                // Add it to the row
                row.add(tile);
            }

            // Insert
            this.matrix.add(row);

            System.out.println(row);
        }
    }

    /**
     * Copy constructor
     */
    public Board(ArrayList<ArrayList<Tile>> matrix) {
        this.matrix = matrix;
    }

    /**
     * Copy constructor
     */
    public Board(Board board) {
        this.matrix = board.matrix;
    }

    public static Tile projection(MouseEvent event) {

        double xRes = event.getSceneX();
        double yRes = event.getSceneY();

        int x = (int) (xRes / BoardSettings.TILE_WIDTH);
        int y = (int) (yRes / BoardSettings.TILE_HEIGHT);

        return Controller.BOARD.matrix.get(x).get(y);
    }

    /**
     * Reset all walls
     */
    public void resetWalls() {

        for (ArrayList<Tile> row : this.matrix) {

            for (Tile tile: row) {

                if (tile.getType() == TileType.WALLS) {

                    // Change It to ground
                    tile.setType(TileType.GROUNDS);
                }
            }
        }
    }
}
