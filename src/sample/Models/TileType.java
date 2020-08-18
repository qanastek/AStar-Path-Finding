package sample.Models;

import sample.Config.Colors;

public enum TileType {

    GROUNDS(Colors.GROUNDS),
    WALLS(Colors.WALLS),
    PATH(Colors.PATH),
    DEPARTURE(Colors.DEPARTURE),
    ARRIVAL(Colors.ARRIVAL),
    VISITED(Colors.VISITED);

    // The color of the tile
    private String color = "";

    /**
     * Constructor
     * @param color The color of the tile
     */
    TileType(String color){
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public String toString(){
        return "Color: " + color;
    }
}
