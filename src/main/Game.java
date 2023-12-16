package main;

/**
 *
 * @author nash2t
 */
public class Game {

    private GameWindow gameWindow;
    private final GamePanel gamePanel;
    private final NQueen nQueen;

    public Game(int nQueens, int col) {
        this.gamePanel = new GamePanel(nQueens, col);
        this.nQueen = new NQueen(nQueens, this.gamePanel.getBoardCells(), col);
        this.gameWindow = new GameWindow(gamePanel, "Thread " + col);
    }

    public void runProblemThread() {
        this.nQueen.start();
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public void disposeWidow (){
        this.gameWindow.dispose();
    }
    
    
}

