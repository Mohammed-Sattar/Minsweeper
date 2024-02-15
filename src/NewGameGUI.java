import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class NewGameGUI extends JFrame{


    private int window_width = 260 + 6;
    private int window_height = 260 + 29;

    public NewGameGUI() {

        this.setTitle("Minesweeper New Game");
        this.setSize (window_width, window_height);   //window size = total number of boxes x (size of each box + 2 pixels for spaciing inbetween)
        // this.setSize (((1280) + 6), ((800) + 29));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        NewGamePage page = new NewGamePage();
        this.setContentPane(page);
    }

    
    

    public class NewGamePage extends JPanel {

        Level level;
        GUI playGui;

        private String levelSelected;
        private int width_x;
        private int height_y;
        private int numOfBombs;

        public NewGamePage() {

            setBackground(Color.DARK_GRAY);
            setBorder(new EmptyBorder (30, 50, 10, 50));


            JButton veryEasy = createButton("Very Easy (5x5)");
            JButton easy = createButton("Easy (8x8)");
            JButton medium = createButton("Medium (16x16)");
            JButton hard = createButton("Hard (30x16)");
            JButton manual = createButton("Manual");

            // Add radio buttons to the panel and button group
            add(veryEasy);
            add(easy);
            add(medium);
            add(hard);
            add(manual);


            ActionListener levelSelectionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AbstractButton selectedButton = (AbstractButton) e.getSource();
                    String selectedLevel = selectedButton.getText();
                    // Handle the selected level here
                    if(selectedLevel.contains(" (")) {
                        levelSelected = selectedLevel.substring(0, selectedLevel.indexOf(" ("));
                    } else {
                        levelSelected = selectedLevel;
                    }
                    System.out.println("Selected level: " + levelSelected);
                    checkLevel(levelSelected);
                }
            };

            veryEasy.addActionListener(levelSelectionListener);
            easy.addActionListener(levelSelectionListener);
            medium.addActionListener(levelSelectionListener);
            hard.addActionListener(levelSelectionListener);
            manual.addActionListener(levelSelectionListener);
        }



        private JButton createButton(String text) {
            JButton button = new JButton(text);
            button.setUI(new BasicButtonUI()); // Remove default button decorations
            button.setFocusPainted(false); // Remove focus border
            button.setBackground(new Color(50, 50, 50)); // Set background color
            button.setForeground(Color.WHITE); // Set text color
            button.setPreferredSize(new Dimension(150, 30));
            return button;
        }

        private void checkLevel (String Level) {
            dispose();
            
            switch (Level){
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
                playGui = new GUI(height_y, width_x);
            });
            playGui = new GUI(height_y, width_x);
            
            GameGrid game = new GameGrid(height_y, width_x, numOfBombs);
            game.runner();
            game.printToString();

        }


    }

    
}


