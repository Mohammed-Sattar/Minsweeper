import java.util.Random;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
// import java.util.Iterator;


public class GameGrid {
    

    private int width_x;
    private int height_y;
    private int numOfBombs;

    private int [][] grid;
    


    
    
    // Constructor that requires manual entering of values including grid size & bomb count
    public GameGrid (int width_x, int height_y, int numOfBombs) {
        this.width_x = width_x;
        this.height_y = height_y;
        this.numOfBombs = numOfBombs;

        this.grid = new int [width_x][height_y];

    }

    // Constructor the defines a default number of bombs
    public GameGrid (int width_x, int height_y) {
        this(width_x, height_y, 5);
    }

    // Constructor that defines dafault values for everything
    public GameGrid () {
        this (5, 5);
    }


    public int [][] getGrid() {
        return this.grid;
    }

    public int getCellValue (int x, int y) {
        return this.grid[x][y];
    }

    public void setCellValue (int x, int y, int value) {
        this.grid[x][y] = value;
    }

    // Getter & Setter methods for the width_x attribute
    public int getWidth_x() {
        return width_x;
    }

    public void setWidth_x(int width_x) {
        this.width_x = width_x;
    }


    // Getter & Setter methods for the height_y attribute
    public int getHeight_y() {
        return height_y;
    }

    public void setHeight_y(int height_y) {
        this.height_y = height_y;
    }


    // Getter & Setter methods for the numOfBombs attribute
    public int getNumOfBombs() {
        return numOfBombs;
    }

    public void setNumOfBombs(int numOfBombs) {
        this.numOfBombs = numOfBombs;
    }



    // This method randomly generates unique coordinates for where the bombs will be placed & assigns -1 to the coordinate
    private void generateBombCoordinates (int[] firstClick) {
        Random rand = new Random(); 

        int width = getWidth_x(); //5
        int height = getHeight_y(); //5
        int numOfCord = getNumOfBombs(); //5
        int count = 0;


        Set <int[]> bombCoordinatesSet = new HashSet<>(); 
        // The 'Set' interface in Java is used to store a collection of unique elements. A Set does not allow duplicate values.

        // Iterator <int[]> iterator = bombCoordinatesSet.iterator();
        // The iterator() method is a way to get a special "pointer" that can be used to traverse the elements in the bombCoordinatesSet.
        // The int[] part in Iterator<int[]> specifies the type of elements you are going to iterate through


        while (count < numOfCord) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);

            if ((x == firstClick[0]) && (y == firstClick[1])) {
                continue;
            }
            int [] coordinate = {x, y};

            boolean isCoordinateAdded = bombCoordinatesSet.add(coordinate);

            if (isCoordinateAdded) {
                count++;

                this.grid[x][y] = -1;
            }
        }   

        // while (iterator.hasNext()) {    // unnecassary after adding this line: this.grid[x][y] = -1;
        //     int [] xy_coordinate = iterator.next();
        //     assignBombCell(xy_coordinate); //Sends the xy_coordinate to the next funciton
        // }
    }

    // This method assigns the value of -1 to all the bomb coordinates generated by generateBombCoordinates(..)
    // This method becomes unnecassary after adding this line:   this.grid[x][y] = -1;  in generateBombCoordinates()
    // private void assignBombCell (int [] bombCellCoordinate) {
    //     int bombCoord_X = bombCellCoordinate[0]; // the x value of the bomb coordinate
    //     int bombCoord_Y = bombCellCoordinate[1]; // the x value of the bomb coordinate
        
    //     this.grid[bombCoord_X][bombCoord_Y] = -1;
    // }

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
        // System.out.println(Arrays.deepToString(grid));
    }
        

}
