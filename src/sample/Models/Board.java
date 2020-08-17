package sample.Models;

import sample.Config.BoardSettings;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Board {

    // The board
    public ArrayList<ArrayList<Tile>> matrix;

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

        // Arrival
        int x2 = ThreadLocalRandom.current().nextInt(min, max + 1);
        int y2 = ThreadLocalRandom.current().nextInt(min, max + 1);

        this.matrix.get(x1).set(y1, Tile.DEPARTURE);
        this.matrix.get(x2).set(y2, Tile.ARRIVAL);
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
                row.add(Tile.GROUNDS);
            }

            // Insert
            this.matrix.add(row);
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
}
