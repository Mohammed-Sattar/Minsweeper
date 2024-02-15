public class Main2 implements Runnable{

    GUI gui = new GUI(5, 5);

    public static void main (String [] args) {
        new Thread(new Main2()).start();


        GameGrid game = new GameGrid(5, 5);
        game.printToString();
    }


    @Override
    public void run() {
        while (true) {
            gui.repaint(); 
        }
    }
    
}
