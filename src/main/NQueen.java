package main;

import java.awt.Color;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author nash2t
 */
public class NQueen extends Thread {

    private final int nQueens;
    private final JLabel[][] boardCells;
    private final int col;
    private final ImageIcon queenIcon;
    private static boolean shouldExit = false;
    private final Object lock = new Object();

    //jLabel3.setIcon();
    public NQueen(int nQueens, JLabel[][] boardCells, int col) {
        setName("Thread " + col);
        this.nQueens = nQueens;
        this.boardCells = boardCells;
        this.col = col;
        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/images/Queen.png"));
        Image queen = icon.getImage();
        Image queenScale = queen.getScaledInstance(this.boardCells[0][0].getWidth(), this.boardCells[0][0].getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledQueenIcon = new ImageIcon(queenScale);
        this.queenIcon = scaledQueenIcon;
        this.boardCells[0][col].setIcon(queenIcon);
    }

    private boolean findSol(JLabel[][] boardCells, int col) throws Exception {

        if (shouldExit == true) {
            throw new Exception("stop");
        }
        if (col == this.col) {
            col += 1;
        }
        if (col >= nQueens) {
            return true;
        }

        for (int row = 1; row < nQueens; row++) {
            if (shouldExit == true) {
                throw new Exception("stop");
            }
            try {
                boolean placeQueen = placeQueenOrNot(boardCells, row, col);

                // boardCells[row][col].setText("Q");
                boardCells[row][col].setIcon(queenIcon);
                NQueen.sleep(1000);
                if (placeQueen == true) {
                    if (findSol(boardCells, col + 1) == true) {
                        return true;
                    }
//                    boardCells[row][col].setText("");
                    boardCells[row][col].setIcon(null);
                } else {
                    boardCells[row][col].setIcon(null);
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(NQueen.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;
    }

    private boolean placeQueenOrNot(JLabel[][] boardCells, int row, int col) {

        //col
        for (int i = 0; i < col; i++) {
            if (boardCells[row][i].getIcon() != null) {
                return false;
            }
        }
        //left top diag
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (boardCells[i][j].getIcon() != null) {
                return false;
            }
        }

        //left bottom diag
        for (int i = row, j = col; j >= 0 && i < nQueens; i++, j--) {
            if (boardCells[i][j].getIcon() != null) {
                return false;
            }
        }

        //right top diag
        for (int i = row, j = col; i >= 0 && j < nQueens; i--, j++) {
            if (boardCells[i][j].getIcon() != null) {
                return false;
            }
        }

        //right bottom diag
        for (int i = row, j = col; i < nQueens && j < nQueens; i++, j++) {
            if (boardCells[i][j].getIcon() != null) {
                return false;
            }
        }

        return true;
    }

    public static void setShouldExit(boolean shouldExit) {
        NQueen.shouldExit = shouldExit;
    }

    public void setShouldExit(boolean shouldExit, String threadName) {
        synchronized (lock) {
            NQueen.shouldExit = shouldExit;
            System.out.println("Solution found in Thread: " + threadName);
            System.out.println("Stopping all Threads");
            for (int i = 0; i < this.nQueens; i++) {
                for (int j = 0; j < this.nQueens; j++) {
                    if (boardCells[i][j].getIcon() != null) {
                        boardCells[i][j].setBackground(Color.green);
                    }
                }
            }
        }

    }

    @Override
    public void run() {
        System.out.println(currentThread().getName() + " has started");

        try {
            if (findSol(boardCells, 0) == false) {
                System.out.println(currentThread().getName() + " Faild");
            } else {
                setShouldExit(true, currentThread().getName());
            }
        } catch (Exception e) {
        }

    }

}

//            Dimension preferredSize = new Dimension(512, 100);
//            JLabel solFound = new JLabel();
//            solFound.setText("Solution Found");
//            solFound.setForeground(Color.green);
//            solFound.setPreferredSize(preferredSize);
//            solFound.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//            solFound.setFont(new java.awt.Font("Arial", 3, 24));
//            System.out.println("halawa");
//            gamePanel.add(solFound);
