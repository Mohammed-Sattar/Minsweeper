public class Main implements Runnable{

    GUI gui = new GUI();

    public static void main (String [] args) {
        new Thread(new Main()).start();


        GameGrid game = new GameGrid();
        int [] firstClick = {2, 2};
        game.runner(firstClick);
        game.printToString();
    }


    @Override
    public void run() {
        while (true) {
            gui.repaint(); 
        }
    }
    
}
