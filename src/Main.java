public class Main implements Runnable{

    GUI gui = new GUI(5, 5);

    public static void main (String [] args) {
        new Thread(new Main()).start();


        GameGrid game = new GameGrid(5, 5);
        game.runner();
        game.printToString();
    }


    @Override
    public void run() {
        while (true) {
            gui.repaint(); 
        }
    }
    
}
