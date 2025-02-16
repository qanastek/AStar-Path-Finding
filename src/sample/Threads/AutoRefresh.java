package sample.Threads;

import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.Config.BoardSettings;
import sample.Config.Colors;
import sample.Vues.Main.Controller;
import sample.Models.Tile;

import java.util.ArrayList;

public class AutoRefresh extends Task {

    // The canvas
    private GraphicsContext gc;

    /**
     * Constructor
     */
    public AutoRefresh(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    protected Object call() throws Exception {

        // Thread status
        boolean isRunning = true;

        // Framerate
        int refreshRate = 200;

        while (isRunning) {
            this.refresh();
            Thread.sleep(refreshRate);
        }

        return null;
    }

    /**
     * Refresh the screen
     */
    private void refresh() {

        // Setup the gc
        this.gc.setLineWidth(BoardSettings.STROKE_WIDTH);

        // X axis
        for (int i = 0; i < Controller.BOARD.matrix.size(); i++) {

            // Row
            ArrayList<Tile> row = Controller.BOARD.matrix.get(i);

            // Y axis
            for (int j = 0; j < row.size(); j++) {

                // The current Tile
                Tile tile = row.get(j);

                // Print It with the correct size
                try {

                    // Tile settings
                    int x = i == 0 ? 0 : i * BoardSettings.TILE_WIDTH;
                    int y = j == 0 ? 0 : j * BoardSettings.TILE_HEIGHT;

                    // Draw the tile with the correct size and color
                    this.gc.setFill(Color.web(tile.getColor()));
                    this.gc.fillRoundRect(x,y,BoardSettings.TILE_WIDTH,BoardSettings.TILE_HEIGHT,0,0);

                    // Draw the stroke
                    this.gc.setStroke(Color.web(Colors.STROKE));
                    this.gc.strokeRoundRect(x,y,BoardSettings.TILE_WIDTH,BoardSettings.TILE_HEIGHT,0,0);

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
