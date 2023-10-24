import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame implements Game{

    // Inherited attributes from Game interface
    private int width_x;
    private int height_y;
    private int numOfBombs;
    private int [][] grid;

    // Local attributes for GUI
    private int window_width;
    private int window_height;
    private int spacing = 2;
    private int cellPixels = 50;

    int mouseX = -100;
    int mouseY = -100;

    public GUI (int width_x, int height_y) {
        this.width_x = width_x;
        this.height_y = height_y;

        width_x = (width_x * (cellPixels + 2)); //window width = total number of boxes x (size of each box + 2 pixels for spacing inbetween)
        height_y++; //create extra row at top for menu buttons
        height_y = (height_y * (cellPixels + 2)); //window height = total number of boxes x (size of each box + 2 pixels for spacing inbetween)

        width_x += 6; //add 6 pixels to width, 3 for right side & 3 for left
        height_y += 29; //add 29 pixels to height for the top bar

        this.window_width = width_x;
        this.window_height = height_y;

        this.setTitle("Minesweeper");
        this.setSize (window_width, window_height);   //window size = total number of boxes x (size of each box + 2 pixels for spaciing inbetween)
        // this.setSize (((1280) + 6), ((800) + 29));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        Board board = new Board();
        this.setContentPane(board);

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);
    }

    public GUI () {
        this(5, 5);
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





    public int getWindow_width() {
        return window_width;
    }
    public void setWindow_width(int width_x) {
        this.window_width = width_x;
    }

    
    public int getWindow_height() {
        return window_height;
    }
    public void setWindow_height(int height_y) {
        this.window_height = height_y;
    }





    public class Board extends JPanel {
        public void paintComponent(Graphics g) {

            // int cellPixels = 50;

            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, window_width, window_height);

            for (int x = 0; x < getWidth_x(); x++) {
                for (int y = 0; y < getHeight_y(); y++) {
                    g.setColor(Color.GRAY);

                    // the following 2 arrays save the min and max coordinates of each box in the grid
                    // int [] box_Xcoordinates = {6 + spacing + (x * cellPixels), 6 + ((x+1) * cellPixels) - spacing};
                    // int [] box_Ycoordinates = { 29 + spacing + ((y + 1)*cellPixels), 29 + ((y + 2) * cellPixels) - spacing};
                    
                    if (mouseX >= 6 + spacing + (x * cellPixels) && mouseX < 6 + ((x+1) * cellPixels) - spacing && mouseY >= 29 + spacing + ((y + 1)*cellPixels) && mouseY < 29 + ((y + 2) * cellPixels) - spacing) {
                        g.setColor(Color.LIGHT_GRAY);
                    }

                    g.fillRect(spacing+(x * cellPixels), spacing+(y * cellPixels)+cellPixels, cellPixels-(2*spacing), cellPixels-(2*spacing));
                }
            }
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
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
            mouseX = e.getX();
            mouseY = e.getY();
            // System.out.println("Mouse posistion; x: " + mouseX + ", y: " + mouseY);
        }
        
    }

   
    public class Click implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
            if (inBox_X() != -1 && inBox_Y() != -1) {
                System.out.println("The mouse is in [" + inBox_X() + "," +inBox_Y() + "]");
            } else {
                System.out.println("The mouse is not inside a box");
            }            
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
        for (int x = 0; x < getWidth_x(); x++) {
            for (int y = 0; y < getHeight_y(); y++) {

                if (mouseX >= 6 + spacing + (x * cellPixels) && mouseX < 6 + ((x + 1) * cellPixels) - spacing
                        && mouseY >= 29 + spacing + ((y + 1) * cellPixels)
                        && mouseY < 29 + ((y + 2) * cellPixels) - spacing) {
                    return x;
                }

            }
        }
        return -1;
    }

    public int inBox_Y () {
        for (int x = 0; x < getWidth_x(); x++) {
            for (int y = 0; y < getHeight_y(); y++) {

                if (mouseX >= 6 + spacing + (x * cellPixels) && mouseX < 6 + ((x + 1) * cellPixels) - spacing
                        && mouseY >= 29 + spacing + ((y + 1) * cellPixels)
                        && mouseY < 29 + ((y + 2) * cellPixels) - spacing) {
                    return y;
                }

            }
        }
        return -1;
    }
    

}
