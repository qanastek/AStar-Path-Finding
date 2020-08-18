package sample.Threads;

import javafx.concurrent.Task;
import javafx.util.Pair;
import sample.Config.SearchSettings;
import sample.Controller;
import sample.Models.Tile;
import sample.Models.TileType;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SearchPath extends Task {

    // Current location of the board
    private Tile currentTile = Controller.BOARD.departure;

    // Current best distance from the arrival
    private int currentBestDistance = Integer.MAX_VALUE;

    // All the already visited Tiles
    private ArrayList<Tile> visited = new ArrayList<Tile>();

    /**
     * Constructor
     */
    public SearchPath() { }

    @Override
    protected Object call() throws Exception {

        // Continuous until we reach the arrival
        while(this.currentTile != Controller.BOARD.arrival) {

            ArrayList<Tile> neighbors = this.tileAroundUs();
            Tile neighbor = neighbors.get(0);

            // Add the tile to the list of visited ones
            this.visited.add(neighbor);

            // Change the current tile
            this.currentTile = neighbor;

            // Set as PATH
            Tile pathTile = new Tile(TileType.PATH);
            pathTile.setX(neighbor.getX());
            pathTile.setY(neighbor.getY());
            Controller.BOARD.matrix.get(neighbor.getX()).set(neighbor.getY(), pathTile);

            // Delay
            Thread.sleep(500);
        }

        System.out.println("Win!!!");

        return null;
    }

    /**
     * Return the tiles around our position (1 tile away from us)
     * @return The tiles around us
     */
    private ArrayList<Tile> tileAroundUs() {

        // Comparator
        Comparator<Pair<Integer,Tile>> comparator = new Comparator<Pair<Integer,Tile>>() {
            @Override
            public int compare(Pair<Integer,Tile> o1, Pair<Integer,Tile> o2) {
                return Integer.compare(o1.getKey(), o2.getKey());
            }
        };

        // The ordered distances
        PriorityQueue<Pair<Integer,Tile>> ordered = new PriorityQueue<Pair<Integer,Tile>>(comparator);

        // For each row
        for (ArrayList<Tile> row : Controller.BOARD.matrix) {

            // For each tile of this row
            for (Tile tile : row) {

                int distance = this.distance(currentTile, tile);

                // One tile away from us
                if (
                    (tile.getType() == TileType.GROUNDS ||
                    tile.getType() == TileType.VISITED ||
                    tile.getType() == TileType.ARRIVAL)
                    && distance == 1
                ) {

                    // Insert the distance and the tile
                    ordered.add(new Pair<Integer,Tile>(
                        distance + this.distance(tile, Controller.BOARD.arrival),
                        tile
                    ));
                }
            }
        }

        // Get the closest tiles around us
        ArrayList<Tile> closest = new ArrayList<Tile>();

        // While left element or doesnt reach the goal
        while (closest.size() <= SearchSettings.TILE_AROUND && ordered.size() > 0) {

            // Pop the N best
            Tile tile = ordered.poll().getValue();

            // Add it to the closest
            closest.add(tile);
        }

        System.out.println("current: " + currentTile);
        System.out.println("closest: " + closest);

        return closest;
    }

    /**
     * Return the distance between the arrival and the current tile
     * @param tileTo Start
     * @param tileFrom End
     * @return The distance
     */
    private int distance(Tile tileFrom, Tile tileTo) {

        // Tile coordinates
        int x1 = tileFrom.getX();
        int y1 = tileFrom.getY();

        // Arrival coordinates
        int x2 = tileTo.getX();
        int y2 = tileTo.getY();

        int distance = (int) Point2D.distance(x1, y1, x2, y2);

//        System.out.println("x1: " + x1 + ";" + " y1: " + y1 + "; x2: " + x2 + "; y2: " + y2 + " : " + distance);

        // Distance between them
        return distance;
    }
}
