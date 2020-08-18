package sample.Models;

public class Tile {;

    // Coordinates
    private int x = 0;
    private int y = 0;

    // The tile type
    private TileType type;

    /**
     * Constructor
     * @param type The type of the tile
     */
    public Tile(TileType type){
        this.type = type;
    }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public String getColor() { return this.type.getColor(); }

    public TileType getType() { return this.type; }

    public void setType(TileType type) { this.type = type; }

    public String toString(){
        return "Color: " + this.type.getColor() + "; X: " + x + "; Y: " + y;
    }

    /**
     * Return if they are the same tiles
     * @param tile
     * @return
     */
    public boolean isSame(Tile tile) {

        if (this.getX() == tile.getX() && this.getY() == tile.getY()) {
            return true;
        }

        return false;
    }
}
