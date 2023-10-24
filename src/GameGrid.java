import java.util.Random;
import java.util.Set;
import java.util.HashSet;
// import java.util.Iterator;


public class GameGrid implements Game{
    

    private int width_x;
    private int height_y;
    private int numOfBombs;

    private int [][] grid;
    

    public GameGrid (int width_x, int height_y, int numOfBombs) {
        setWidth_x(width_x);
        setHeight_y(height_y);
        setNumOfBombs(numOfBombs);

        int [][] newGrid = new int [width_x][height_y];
        setGrid(newGrid);

    }

    // Constructor the defines a default number of bombs
    public GameGrid (int width_x, int height_y) {
        this(width_x, height_y, 5);
    }

    // Constructor that defines dafault values for everything
    public GameGrid () {
        this (5, 5);
    }


    // Overriden Getter & Setter methods for grid implementation from interface Game
    @Override
    public int [][] getGrid() {
        return this.grid;
    }
    @Override
    public void setGrid (int [][] grid) {
        this.grid = grid;
    }


    public int getCellValue (int x, int y) {
        return this.grid[x][y];
    }

    public void setCellValue (int x, int y, int value) {
        this.grid[x][y] = value;
    }


    // Overriden Getter & Setter methods for width_x attribute, implementation from interface Game
    @Override
    public int getWidth_x() {
        return this.width_x;
    }
    @Override
    public void setWidth_x(int width_x) {
        this.width_x = width_x;
    }


    // Overriden Getter & Setter methods for height_y attribute, implementation from interface Game
    @Override
    public int getHeight_y() {
        return this.height_y;
    }
    @Override
    public void setHeight_y(int height_y) {
        this.height_y = height_y;
    }


    // Overriden Getter & Setter methods for numOfBombs attribute, implementation from interface Game
    @Override
    public int getNumOfBombs() {
        return this.numOfBombs;
    }
    @Override
    public void setNumOfBombs(int numOfBombs) {
        this.numOfBombs = numOfBombs;
    }



    // this method randomly generates unique coordinates for where the bombs will be placed & assigns -1 to the coordinate
    private void generateBombCoordinates (int[] firstClick) {
        Random rand = new Random(); 

        int width = getWidth_x(); //5
        int height = getHeight_y(); //5
        int numOfCord = getNumOfBombs(); //5
        int count = 0;


        Set <int[]> bombCoordinatesSet = new HashSet<>(); 
        // The 'Set' interface in Java is used to store a collection of unique elements. A Set does not allow duplicate values.


        while (count < numOfCord) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);

            if ((x == firstClick[0]) && (y == firstClick[1])) {
                continue;
            }
            int [] coordinate = {x, y};

            boolean isCoordinateAdded = bombCoordinatesSet.add(coordinate);

            if (isCoordinateAdded) {

                setCellValue(x, y, -1);

                count++;
            }
        }   

    }

    private void loopCells () { 

        // These nested for loops go through the whole grid cell by cell
        for (int i = 0; i < getWidth_x(); i++) {

            for (int j = 0; j < getHeight_y(); j++) {
                int [] cellCoordinate = {i, j};

                assignSafeCell(cellCoordinate);
            }
        }

    }

    private void assignSafeCell(int[] cellCoordinate) { //{0,0}
        int count = 0;
        int x_coordinate = cellCoordinate[0];
        int y_coordinate = cellCoordinate[1];

        if (getCellValue(x_coordinate, y_coordinate) == -1) {
            return;
        }

        for (int x = (x_coordinate - 1); x <= (x_coordinate + 1); x++) {
            if ((x < 0) || (x >= getWidth_x())) {
                continue;   //check if the x coordinate is out of bounds
            }

            for (int y = (y_coordinate - 1); y <= (y_coordinate + 1); y++) {
                if ((y < 0) || (y >= getHeight_y())) {
                    continue;   //check if the y coordinate is out of bounds
                } 

                if (getCellValue(x, y) == -1) {
                    ++count;
                }

            }
        }

        setCellValue(x_coordinate, y_coordinate, count);
    }
    
    public void runner (int[] firstClick) {
        generateBombCoordinates(firstClick);
        loopCells();
    }

    public void printToString () {

        for (int [] arr: this.grid) {

            for (int i: arr) {
                System.out.print(i + "  ");
            }
            System.out.println("\n");
        }
    }
        

}
