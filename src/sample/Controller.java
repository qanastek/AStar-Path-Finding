package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import sample.Config.BoardSettings;
import sample.Models.Board;
import sample.Threads.AutoRefresh;

public class Controller {

    @FXML
    private Canvas canvas;

    public static GraphicsContext gc;

    // The board matrix
    private static Board BOARD;

    @FXML
    public void initialize() {

        // The 2D context of the canvas
        Controller.gc = canvas.getGraphicsContext2D();

        // Instantiate the board
        Controller.BOARD = new Board();

        // Auto refresh every x ms
        new Thread(new AutoRefresh(Controller.BOARD, Controller.gc)).start();
    }
}
