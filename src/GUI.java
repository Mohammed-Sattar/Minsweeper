import javax.swing.*;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame{
     
    private int width_x;
    private int height_y;
    private int numCells_X;
    private int numCells_Y;
    private int spacing = 2;

    public GUI (int width_x, int height_y) {
        this.numCells_X = width_x;
        this.numCells_Y = height_y;
        this.width_x = (width_x *= 50);
        this.height_y = (height_y *= 50);

        this.setTitle("Minesweeper");
        this.setSize ((width_x+ 6), (height_y+ 29));
        // this.setSize (((1280) + 6), ((800) + 29));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        Board board = new Board();
        this.setContentPane(board);
    }

    public GUI () {
        this(5, 5);
    }
    

    public int getWidth_x() {
        return width_x;
    }
    public void setWidth_x(int width_x) {
        this.width_x = width_x;
    }

    
    public int getHeight_y() {
        return height_y;
    }
    public void setHeight_y(int height_y) {
        this.height_y = height_y;
    }


    public int getNumCells_X() {
        return numCells_X;
    }
    public void setNumCells_X(int numCells_X) {
        this.numCells_X = numCells_X;
    }

    
    public int getNumCells_Y() {
        return numCells_Y;
    }
    public void setNumCells_Y(int numCells_Y) {
        this.numCells_Y = numCells_Y;
    }




    public class Board extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.red);
            g.fillRect(0, 0, width_x, height_y);

            g.setColor(Color.GRAY);
            for (int x = 0; x < getNumCells_X(); x++) {
                for (int y = 0; y < getNumCells_Y(); y++) {
                    g.fillRect(spacing+(x * 50), spacing+(y * 50)+50, 50-(2*spacing), 50-(2*spacing));
                }
            }
        }
    }


    

   
    


    
    

}
