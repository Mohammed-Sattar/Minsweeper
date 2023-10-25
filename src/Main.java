public class Main implements Runnable{

    GUI gui = new GUI(8, 8);

    public static void main (String [] args) {
        new Thread(new Main()).start();


        GameGrid game = new GameGrid(8,8,16);
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
