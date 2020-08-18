package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.Config.BoardSettings;
import sample.Models.Board;
import sample.Models.Tile;
import sample.Models.TileType;
import sample.Threads.AutoRefresh;
import sample.Threads.SearchPath;

public class Controller {

    @FXML
    private Canvas canvas;

    public static GraphicsContext gc;

    // The board matrix
    public static Board BOARD;

    public static Thread searchThread;

    @FXML
    public void initialize() {

        // The 2D context of the canvas
        Controller.gc = canvas.getGraphicsContext2D();

        // Instantiate the board
        Controller.BOARD = new Board();

        // Auto refresh every x ms
        this.searchThread = new Thread(new AutoRefresh(Controller.gc));
        this.searchThread.start();
    }

    @FXML
    public void restart(ActionEvent event) {
        this.searchThread = new Thread(new SearchPath());
        this.searchThread.start();
        BoardSettings.GAME_STATUS = true;
    }

    @FXML
    public void clearPath(ActionEvent event) {

    }

    @FXML
    public void clearWalls(ActionEvent event) {
        Controller.BOARD.resetWalls();
    }

    @FXML
    public void drawWalls(MouseEvent event) {

        // If the game has already start, then exit
        if (BoardSettings.GAME_STATUS) { return; }

        // Fetch the current tile
        Tile tile = Board.projection(event);
        System.out.println(tile);

        // Cannot click on a already set tile
        if (tile.getType() == TileType.DEPARTURE || tile.getType() == TileType.ARRIVAL ) {
            return;
        }
        // When click on a wall remove it
        else if (tile.getType() == TileType.WALLS) {

            // Remove the wall
            Controller.BOARD.matrix.get(tile.getX()).get(tile.getY()).setType(TileType.GROUNDS);
        }
        else {

            // Set as wall
            Tile wallTile = new Tile(TileType.WALLS);
            wallTile.setX(tile.getX());
            wallTile.setY(tile.getY());
            Controller.BOARD.matrix.get(tile.getX()).set(tile.getY(), wallTile);
        }
    }

    @FXML
    public void reset(ActionEvent event) {

        // Instantiate the board
        Controller.BOARD = new Board();

        // Allow to play
        BoardSettings.GAME_STATUS = false;
    }
}
