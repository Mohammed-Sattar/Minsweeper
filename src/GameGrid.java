import java.util.Random;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
// import java.util.Iterator;


public class GameGrid{
    

    public static int [] firstClick;
    public static int [][] grid;
    // public static int displayValue;

    private int x_col;
    private int y_row;
    private int numOfBombs;

    
    
    

    public GameGrid (int y_row, int x_col, int numOfBombs) {
        setX_col(x_col);
        setY_row(y_row);
        setNumOfBombs(numOfBombs);

        int [][] newGrid = new int [y_row][x_col];
        setGrid(newGrid);

    }

    // Constructor the defines a default number of bombs
    public GameGrid (int y_row, int x_col) {
        this(y_row, x_col, 5);
    }

    // Constructor that defines dafault values for everything
    public GameGrid () {
        this (5, 5);
    }


    // Getter & Setter methods for static attribute grid (not neccessary)
    public int [][] getGrid() {
        return GameGrid.grid;
    }
    public void setGrid (int [][] grid) {
        GameGrid.grid = grid;
    }


    // Getter & Setter methods for the firstClick (unnecessary)
    public int[] getFirstClick() {
        return GameGrid.firstClick;
    }
    public void setFirstClick(int [] firstClick) {
        GameGrid.firstClick = firstClick;
    }


    public int getCellValue (int y, int x) {
        return GameGrid.grid[y][x];
    }

    public void setCellValue (int y, int x, int value) {
        GameGrid.grid[y][x] = value;
    }


    // Getter & Setter methods for x_col attribute
    public int getX_col() {
        return this.x_col;
    }
    public void setX_col(int x_col) {
        this.x_col = x_col;
    }


    // Getter & Setter methods for y_row attribute
    public int getY_row() {
        return this.y_row;
    }
    public void setY_row(int y_row) {
        this.y_row = y_row;
    }


    // Getter & Setter methods for numOfBombs attribute
    public int getNumOfBombs() {
        return this.numOfBombs;
    }
    public void setNumOfBombs(int numOfBombs) {
        this.numOfBombs = numOfBombs;
    }



    // this method randomly generates unique coordinates for where the bombs will be placed & assigns -1 to the coordinate
    private void generateBombCoordinates (int[] firstClick) {
        Random rand = new Random(); 

        int coloumns = getX_col(); //5
        int rows = getY_row(); //5
        int count = 0;

        if (getNumOfBombs() >= rows*coloumns) {
            System.out.println("Number of bombs provided is too large!");
            setNumOfBombs(rows*coloumns-1);
            System.out.println(getNumOfBombs() + " Bombs will be generated instead.");
        }

        int numOfCord = getNumOfBombs(); //5

        Set <int[]> bombCoordinatesSet = new HashSet<>(); 
        // The 'Set' interface in Java is used to store a collection of unique elements. A Set does not allow duplicate values.


        while (count < numOfCord) {
            int x = rand.nextInt(coloumns);
            int y = rand.nextInt(rows);

            if ((y == firstClick[0]) && (x == firstClick[1]) ) {
                continue;
            }
            int [] coordinate = {y, x};

            boolean isCoordinateAdded = bombCoordinatesSet.add(coordinate);

            if (isCoordinateAdded && getCellValue(y, x) != -1) {

                setCellValue(y, x, -1);

                count++;
                System.out.println("Generated " + count + " out of " + numOfCord + " coordinates; " + Arrays.toString(coordinate));
            }
        }   

    }

    private void loopCells () { 

        // These nested for loops go through the whole grid cell by cell
        for (int i = 0; i < getX_col(); i++) {

            for (int j = 0; j < getY_row(); j++) {
                int [] cellCoordinate = {i, j};

                assignSafeCell(cellCoordinate);
            }
        }

        // Creating a standard grid that willbe used as the test case
        // int[][] testGrid = {{1, 1, 1, 0, 0}, {3, -1, 2, 0, 0}, {-1, -1, 3, 1, 1}, {2, 2, 3, -1, 2}, {0, 0, 2, -1, 2}};
        // setGrid(testGrid);
        //  1   1   1   0   0

        //  3  -1   2   0   0

        // -1  -1   3   1   1

        //  2   2   3  -1   2

        //  0   0   2  -1   2

        int y_fc = getFirstClick()[0];
        int x_fc = getFirstClick()[1];
        int temp = getCellValue(y_fc, x_fc);
        setCellValue(y_fc, x_fc, temp+=10);

    }

    private void assignSafeCell(int[] cellCoordinate) { //{0,0}
        int count = 0;
        int x_coordinate = cellCoordinate[0];
        int y_coordinate = cellCoordinate[1];

        if (getCellValue(y_coordinate, x_coordinate) == -1) {
            return;
        }

        for (int x = (x_coordinate - 1); x <= (x_coordinate + 1); x++) {
            if ((x < 0) || (x >= getX_col())) {
                continue;   //check if the x coordinate is out of bounds
            }

            for (int y = (y_coordinate - 1); y <= (y_coordinate + 1); y++) {
                if ((y < 0) || (y >= getY_row())) {
                    continue;   //check if the y coordinate is out of bounds
                } 

                if (getCellValue(y, x) == -1) {
                    ++count;
                }

            }
        }

        setCellValue(y_coordinate, x_coordinate, count);
    }
    
    public void runner () {
        while (getFirstClick() == null) {
            // System.out.println(Arrays.toString(getFirstClick()));
            System.out.println("Waiting...");
            try {
                Thread.sleep(500); // Adjust the sleep duration as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        generateBombCoordinates(getFirstClick());
        loopCells();
    }

    public void printToString () {

        for (int [] arr: GameGrid.grid) {

            for (int i: arr) {
                if (i == -1) {
                    System.out.print("  ");
                } else {
                    System.out.print("   ");
                }
                
                System.out.print(i%10);
            }
            System.out.println("\n");
        }
    }
        

}
