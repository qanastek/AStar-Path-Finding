package sample.Models;

import sample.Config.Colors;

public enum Tile {

    GROUNDS(Colors.GROUNDS),
    WALLS(Colors.WALLS),
    PATH(Colors.PATH),
    DEPARTURE(Colors.DEPARTURE),
    ARRIVAL(Colors.ARRIVAL);

    // The color of the tile
    private String color = "";

    /**
     * Constructor
     * @param color The color of the tile
     */
    Tile(String color){
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public String toString(){
        return color;
    }
}
