package model.Object;

public class Level {

    private int numLevel;
    private int width;
    private int height;
    private int numberCoins;
    private int numberWalls;
    private String map;
    private int timeLimit;

    public Level(int numLevel, int width, int height, int numberCoins, int numberWalls, String map, int timeLimit) {
        this.numLevel = numLevel;
        this.width = width;
        this.height = height;
        this.numberCoins = numberCoins;
        this.numberWalls = numberWalls;
        this.map = map;
        this.timeLimit = timeLimit;
    }

    public String[][] createMap(){

        String[][] mapArray = new String[width][height];
        String[] line = map.split("\n");

        for(int i = 0; i < line.length; i++){
            String row = line[i];
            for(int j = 0; j < row.length(); j++){
                mapArray[i][j] = row.charAt(j)+"";
            }
        }

        return mapArray;

    }

    public int getNumLevel() {
        return numLevel;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumberCoins() {
        return numberCoins;
    }

    public int getNumberWalls() {
        return numberWalls;
    }

    public String getMap() {
        return map;
    }

    public int getTimeLimit() {
        return timeLimit;
    }


    public void setNumLevel(int numLevel) {
        this.numLevel = numLevel;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setNumberCoins(int numberCoins) {
        this.numberCoins = numberCoins;
    }

    public void setNumberWalls(int numberWalls) {
        this.numberWalls = numberWalls;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
}
