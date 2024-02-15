public class Main3 implements Runnable{

    NewGameGUI newGame = new NewGameGUI();

    public static void main (String [] args) {
        new Thread(new Main3()).start();


        // newGame = new NewGameGUI();
    }


    @Override
    public void run() {
        while (true) {
            newGame.repaint(); 
        }
    }
    
}
