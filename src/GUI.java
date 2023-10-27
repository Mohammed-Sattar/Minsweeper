import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;

public class GUI extends JFrame{

    // Inherited attributes from Game interface
    private int x_cols;
    private int y_rows;
    private int numOfBombs;

    // Local attributes for GUI
    private int window_width;
    private int window_height;
    private int spacing = 2;
    private int cellPixels = 50;

    Date startDate = new Date();
    int sec = 0;
    int countFlags = 0;

    int mouseX = -100;
    int mouseY = -100;

    public GUI (int y_rows, int x_cols) {
        this.x_cols = x_cols;
        this.y_rows = y_rows;

        System.err.println("5 .. Executing in GUI");

        x_cols = (x_cols * (cellPixels + 2)); //window width = total number of boxes x (size of each box + 2 pixels for spacing inbetween)
        y_rows++; //create extra row at top for menu buttons
        y_rows = (y_rows * (cellPixels + 2)); //window height = total number of boxes x (size of each box + 2 pixels for spacing inbetween)

        x_cols += 6; //add 6 pixels to width, 3 for right side & 3 for left
        y_rows += 29; //add 29 pixels to height for the top bar

        this.window_width = x_cols;
        this.window_height = y_rows;

        this.setTitle("Minesweeper");
        this.setSize (window_width, window_height);   //window size = total number of boxes x (size of each box + 2 pixels for spaciing inbetween)
        // this.setSize (((1280) + 6), ((800) + 29));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        Board board = new Board();
        this.setContentPane(board);
        System.out.println("7 .. executing in Graphics (Board)");

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);
    }

    public GUI () {
        this(5, 5);
    }
    





    // Getter & Setter methods for the public static attribute GameGrid.grid
    public int [][] getGrid() {
        return GameGrid.grid;
    }
    public void setGrid (int [][] grid) {
        GameGrid.grid = grid;
    }


    // Getter & Setter methods for the public static attribute GameGrid.firstClick 
    public int[] getFirstClick() {
        return GameGrid.firstClick;
    }
    public void setFirstClick(int [] fc) {
        GameGrid.firstClick = fc;
    }


    public int getCellValue (int y, int x) {
        return GameGrid.grid[y][x];
    }

    public void setCellValue (int y, int x, int value) {
        GameGrid.grid[y][x] = value;
    }


    // Getter & Setter methods for x_cols attribute
    public int getX_cols() {
        return this.x_cols;
    }
    public void setX_cols(int x_cols) {
        this.x_cols = x_cols;
    }


    // Getter & Setter methods for y_rows attribute
    public int getY_rows() {
        return this.y_rows;
    }
    public void setY_rows(int y_rows) {
        this.y_rows = y_rows;
    }


    // Getter & Setter methods for numOfBombs attribute
    public int getNumOfBombs() {
        return this.numOfBombs;
    }
    public void setNumOfBombs(int numOfBombs) {
        this.numOfBombs = numOfBombs;
    }





    public int getWindow_width() {
        return window_width;
    }
    public void setWindow_width(int x_cols) {
        this.window_width = x_cols;
    }

    
    public int getWindow_height() {
        return window_height;
    }
    public void setWindow_height(int y_rows) {
        this.window_height = y_rows;
    }





    public class Board extends JPanel {
        
        public void paintComponent(Graphics g) {
            System.out.println("7 .. executing in Graphics (Board)");
            
            // int cellPixels = 50;

            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, window_width, window_height);

            topBar(g);
            System.out.println("8 .. executing in Graphics (Board)");

            for (int x = 0; x < getX_cols(); x++) {
                for (int y = 0; y < getY_rows(); y++) {
                    g.setColor(Color.GRAY);

                    // the following 2 arrays save the min and max coordinates of each box in the grid
                    // int [] box_Xcoordinates = {6 + spacing + (x * cellPixels), 6 + ((x+1) * cellPixels) - spacing};
                    // int [] box_Ycoordinates = { 29 + spacing + ((y + 1)*cellPixels), 29 + ((y + 2) * cellPixels) - spacing};

                    if (mouseX >= 6 + spacing + (x * cellPixels) && mouseX < 6 + ((x + 1) * cellPixels) - spacing
                            && mouseY >= 29 + spacing + ((y + 1) * cellPixels)
                            && mouseY < 29 + ((y + 2) * cellPixels) - spacing) {

                        g.setColor(Color.LIGHT_GRAY);
                    }
                    fillBoxColors(y, x, g);


                    if (getCellValue(y, x) > 8 && getCellValue(y, x) <= 18 && isCellOpen(y, x)) {
                        
                        g.setColor(Color.YELLOW);
                        fillBoxColors(y, x, g);
                        
                        if (isCellOpen(y, x)) {
                            g.setColor(Color.BLUE);
                            g.setFont(new Font("Tahoma", Font.BOLD, 25));
                            g.drawString(Integer.toString(getCellValue(y, x)%10), (x*cellPixels + 16), y*cellPixels+cellPixels+34);
                        }
                    }

                    if (getCellValue(y, x) > 18 && getCellValue(y, x) <= 28) {

                        g.setColor(new Color(255, 218, 185));
                        fillBoxColors(y, x, g);

                        if (isCellFlagged(y, x)) {
                            g.setColor(Color.RED);
                            g.setFont(new Font("Tahoma", Font.BOLD, 25));
                            g.drawString("!", (x*cellPixels + 19), y*cellPixels+cellPixels+34);
                        }
                    }
                }
            }

            
        }

        // allows the color of each cell to be changed
        private void fillBoxColors (int y, int x, Graphics g) {
            g.fillRect(spacing + (x * cellPixels), spacing + (y * cellPixels) + cellPixels,
                            cellPixels - (2 * spacing), cellPixels - (2 * spacing));
        }

        
        private void topBar (Graphics g) {
            int x_start = (int)(window_width/2-cellPixels) ;
            int y_start = 35;
            int x_span = 90;
            int y_span = 30;

            g.setColor(new Color(100, 100, 100));
            g.fill3DRect(x_start, (int)(y_start/2.5), x_span, y_span, rootPaneCheckingEnabled);

            g.setColor(new Color(230, 230, 250));
            g.setFont(new Font("Tahoma", Font.BOLD, 15));
            g.drawString("New Game", x_start+3, y_start);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 17));
            g.drawString(("!:(" + Integer.toString(countFlags) +"/"+ Integer.toString(getNumOfBombs())+")"), 10, y_start);


            // x_start = (int)(window_width - 1.4*cellPixels);
            // g.setColor(new Color(47, 79, 79));
            // g.fillRoundRect(x_start, (int)(y_start/2.5), 56, y_span, 15, 20);
            // g.setColor(new Color(240, 255, 255));
            // g.setFont(new Font("Times New Roman", Font.ITALIC, 15));

            // sec = (int)((new Date().getTime()-startDate.getTime()) / 1000);
            // g.drawString("time:" + Integer.toString(sec), x_start+5, y_start);

        }


    }


    public class Move implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            // System.out.println("Mouse posistion; x: " + mouseX + ", y: " + mouseY);
        }
        
    }

   
    public class Click implements MouseListener {
        private int count = 0;
        @Override
        public void mouseClicked(MouseEvent e) {

            if (inBox_Y() == -2 && inBox_X() == -2) {
                System.out.println("New Game Clicked!!");
                new NewGameGUI();
                System.exit(0);


            } else if (inBox_Y() != -1 && inBox_X() != -1) {
                int cellVal = getCellValue(inBox_Y(), inBox_X());

                if (e.getButton() == MouseEvent.BUTTON1) {
                    this.count++;
                    if (count == 1) {
                        System.out.print("First click: ");
                        int [] fc = {inBox_Y(), inBox_X()};
                        
                        setFirstClick(fc);
                        countFlags = 0;
                    }

                    if (cellVal == -1) {

                        System.out.println("YOU LOSE!!!");
                        System.exit(0);
                        // setCellValue(inBox_Y(), inBox_X(), cellVal += 10);

                    } else if (cellVal == 0 && count != 1) {
                        openZeros(inBox_Y(), inBox_X());

                    } else if (!isCellOpen(inBox_Y(), inBox_X())) {

                        setCellValue(inBox_Y(), inBox_X(), cellVal += 10);
                    }


                    System.out.print("Left clicked in box ");
                } else if (e.getButton() == MouseEvent.BUTTON3) {

                    if (!isCellOpen(inBox_Y(), inBox_X()) && !isCellFlagged(inBox_Y(), inBox_X())) {

                        setCellValue(inBox_Y(), inBox_X(), cellVal += 20);
                        ++countFlags;

                    } else if (!isCellOpen(inBox_Y(), inBox_X()) && isCellFlagged(inBox_Y(), inBox_X())) {

                        setCellValue(inBox_Y(), inBox_X(), cellVal -= 20);
                        --countFlags;
                    }
                    System.out.print("Right clicked in box ");
                }
                System.out.println("[" + inBox_Y() + "," + inBox_X() + "]");
            } else {
                System.out.println("The mouse is not inside a box");
            }
            
            boolean containsValLess9 = false;
            
            for (int i = 0; i < GameGrid.grid.length; i++) {
                for (int j = 0; j < GameGrid.grid[i].length; j++) {
                    if (GameGrid.grid[i][j] < 9) {
                        containsValLess9 = true;
                        break; // Exit the inner loop since we found a value less than 9
                    }
                }
            }
            if (!containsValLess9) {
                System.out.println("YOU WON!!!");
                System.exit(0);
            }
        }

        private void openZeros (int y_coord, int x_coord) {
            if ((y_coord < 0) || (y_coord >= getY_rows()) || (x_coord < 0) || (x_coord >= getX_cols())) {
                return;   //check if the y & x coordinates are out of bounds
            }

            if (isCellOpen(y_coord, x_coord)) {
                return; // check if the cell has already been opened
            }
            
            if (getCellValue(y_coord, x_coord) > 0) {
                openCell(y_coord, x_coord);
                return;
            }
             

            // A Pre-Order recursionn function of open certain cells
            openCell(y_coord, x_coord);
            
            // All 8 surrounding cells are checked
            openZeros(y_coord-1, x_coord-1);
            openZeros(y_coord, x_coord-1);
            openZeros(y_coord+1, x_coord-1);
            openZeros(y_coord-1, x_coord);
            openZeros(y_coord, x_coord);
            openZeros(y_coord+1, x_coord);
            openZeros(y_coord-1, x_coord + 1);
            openZeros(y_coord, x_coord+1);
            openZeros(y_coord+1, x_coord+1);

        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
        }
        
    }


    public int inBox_X() {
        for (int x = 0; x < getX_cols(); x++) {
            for (int y = 0; y < getY_rows(); y++) {

                if (mouseX >= 6 + spacing + (x * cellPixels) && mouseX < 6 + ((x + 1) * cellPixels) - spacing
                        && mouseY >= 29 + spacing + ((y + 1) * cellPixels)
                        && mouseY < 29 + ((y + 2) * cellPixels) - spacing) {
                    return x;

                } else if (mouseX >= (window_width/2-1.2*cellPixels) && mouseX <= (window_width/2-1.2*cellPixels + 119)
                    && mouseY >= 35 && mouseY <= 65) {

                    return -2;
                }

            }
        }
        return -1;
    }

    public int inBox_Y () {
        for (int x = 0; x < getX_cols(); x++) {
            for (int y = 0; y < getY_rows(); y++) {

                if (mouseX >= 6 + spacing + (x * cellPixels) && mouseX < 6 + ((x + 1) * cellPixels) - spacing
                        && mouseY >= 29 + spacing + ((y + 1) * cellPixels)
                        && mouseY < 29 + ((y + 2) * cellPixels) - spacing) {
                    return y;

                } else if (mouseX >= (window_width/2-1.2*cellPixels) && mouseX <= (window_width/2-1.2*cellPixels + 119)
                    && mouseY >= 35 && mouseY <= 65) {

                    return -2;
                }

            }
        }
        return -1;
    }


    public void openCell (int y, int x) {
        int cellValue = getCellValue(y, x);

        if (!isCellOpen(y, x)) {
            cellValue += 10;
            setCellValue(y, x, cellValue);
        }
        
    }

    
    public boolean isCellOpen (int y, int x) {
        boolean isOpen = false;
        int cellVal = getCellValue(y, x);

        if (cellVal >= 9  && cellVal <= 18) {
            isOpen = true;
        }

        return isOpen;
    }


    public boolean isCellFlagged (int y, int x) {
        boolean isFlagged = false;
        int cellVal = getCellValue(y, x);
        
        if (cellVal >= 19 && cellVal <= 28) {
            isFlagged = true;
        }

        return isFlagged;
    }

}
