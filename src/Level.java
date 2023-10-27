import javax.swing.SwingUtilities;

public class Level{


    private int width_x;
    private int height_y;
    private int numOfBombs;

    
    // Constructor that allows manual definition of grid size & bomb count
    public Level (int width_x, int height_y, int numOfBombs) {
        

        this.width_x = width_x;
        this.height_y = height_y;
        this.numOfBombs = numOfBombs;

        // new GUI(height_y, width_x);      
        // GameGrid game = new GameGrid(height_y, width_x, numOfBombs);
        // game.runner();
        // game.printToString();


        SwingUtilities.invokeLater(() -> {
            new GUI(height_y, width_x);
            GameGrid game = new GameGrid(height_y, width_x, numOfBombs);
            game.runner();
            game.printToString();
        });

    }

    // Constructor that accepts a String to select one of the default levels
    public Level (String selectedLevel) {

        switch (selectedLevel){
            case "Very Easy":
                    this.width_x = 5;
                    this.height_y = 5;
                    this.numOfBombs = 5; 
                break;
            case "Easy":
                    this.width_x = 8;
                    this.height_y = 8;
                    this.numOfBombs = 10;
                break;
            case "Medium":
                    this.width_x = 16;
                    this.height_y = 16;
                    this.numOfBombs = 40;
                break;
            case "Hard":
                this.width_x = 30;
                this.height_y = 16;
                this.numOfBombs = 99;
                break;
            default:
            
        }
        // System.out.println("Selected level: " + selectedLevel);
        // System.out.println("1 .. Still Executing in Level Constructor");
        // new GUI(height_y, width_x);      
        // System.out.println("2 .. Still Executing in Level Constructor");
        // GameGrid game = new GameGrid(height_y, width_x, numOfBombs);
        // System.out.println("3 .. Still Executing in Level Constructor");
        // game.runner();
        // game.printToString();
        // System.out.println("4 .. Still Executing in Level Constructor");


        SwingUtilities.invokeLater(() -> {
            new GUI(height_y, width_x);
            GameGrid game = new GameGrid(height_y, width_x, numOfBombs);
            game.runner();
            game.printToString();
        });

    }



    //Getter & Setter methods for numOfBombs
    public int getNumOfBombs() {
        return numOfBombs;
    }


    public void setNumOfBombs(int numOfBombs) {
        this.numOfBombs = numOfBombs;
    }


}
