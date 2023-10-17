public class Level {


    private int width_x;
    private int height_y;
    private int numOfBombs;

    
    // Constructor that allows manual definition of grid size & bomb count
    public Level (int width_x, int height_y, int numOfBombs) {
        this.width_x = width_x;
        this.height_y = height_y;
        this.numOfBombs = numOfBombs;

        GameGrid game = new GameGrid(width_x, height_y, numOfBombs);

    }

    // Constructor that accepts a String to select one of the default levels
    public Level (String selectedLevel) {

        switch (selectedLevel){
            case "Very Easy":
                level_VeryEasy();
                break;
            case "Easy":
                level_Easy();
                break;
            case "Medium":
                level_Medium();
                break;
            case "Hard":
                level_Hard();
                break;
            default:
            

        }

    }



    //Getter & Setter methods for numOfBombs
    public int getNumOfBombs() {
        return numOfBombs;
    }


    public void setNumOfBombs(int numOfBombs) {
        this.numOfBombs = numOfBombs;
    }
    


    // Methods that define default grid size and number of bombs according to the level
    public void level_VeryEasy () {
        int width_x = 5;
        int height_y = 5;
        int numOfBombs = 5;

        Level veryEasy = new Level (width_x, height_y, numOfBombs);

    }

    public void level_Easy () {
        int width_x = 8;
        int height_y = 8;
        int numOfBombs = 10;

        Level easy = new Level (width_x, height_y, numOfBombs);

    }

    public void level_Medium () {
        int width_x = 16;
        int height_y = 16;
        int numOfBombs = 40;

        Level medium = new Level (width_x, height_y, numOfBombs);

    }

    public void level_Hard () {
        int width_x = 30;
        int height_y = 16;
        int numOfBombs = 99;

        Level hard = new Level (width_x, height_y, numOfBombs);

    }

    // public void level_Manual (int x, int y, int Bombs) {
    //     Level manual = new Level (x, y, Bombs);

    // }


}
