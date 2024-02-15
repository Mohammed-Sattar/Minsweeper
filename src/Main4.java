import javax.swing.JOptionPane;

public class Main4 implements Runnable{

    private GUI gui;

    public static void main (String [] args) {
        new Thread(new Main4()).start();

        // new Main4().start();


        
    }

    public void start () {
        int x_cols = 0;
        int y_rows = 0;
        int bombs = 0;

        String level = JOptionPane.showInputDialog(null, "Choose a Level:\n" + "1. Very Easy (5x5)\n" + "2. Easy\n" + 
                "3. Medium\n" + "4. Hard\n" + "5. Manual\n", "Choose Level", 0);

        if (level.toLowerCase().equals("very easy") || level.contains("1")) {
            x_cols = 5;
            y_rows = 5;
            bombs = 5;
        } else if (level.toLowerCase().equals("easy") || level.contains("2")) {
            x_cols = 8;
            y_rows = 8;
            bombs = 10;
        } else if (level.toLowerCase().equals("medium") || level.contains("3")) {
            x_cols = 16;
            y_rows = 16;
            bombs = 40;
        } else if (level.toLowerCase().equals("hard") || level.contains("4")) {
            x_cols = 30;
            y_rows = 16;
            bombs = 99;
        } else {
            String [] arrSize = new String[2];

            String sizeIn = JOptionPane.showInputDialog(null, "Enter the number of coloumns (width) & rows (height) you would like,\n"
                +"seperated by a space and comma, e.g. 5, 5", "Manual Size Input", 0);

            arrSize  = sizeIn.split(", ");
            x_cols = Integer.parseInt(arrSize[0]);
            y_rows = Integer.parseInt(arrSize[1]);

            String numbBombs = JOptionPane.showInputDialog(null, "Enter the number of bombs you would like"
                , "Manual Bomb Input", 0);

            bombs = Integer.parseInt(numbBombs);
            
        }

        gui = new GUI(y_rows, x_cols);

        GameGrid game = new GameGrid(y_rows, x_cols, bombs);
        game.printToString();
    }


    @Override
    public void run() {
        while (true) {
            gui.repaint();
        }
    }
    
}
