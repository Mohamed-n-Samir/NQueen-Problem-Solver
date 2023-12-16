package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nash2t
 */
public class GamePanel extends JPanel {

    private final int nQueens;
    private final JLabel[][] boardCells;             
    

    public GamePanel(int nQueens,int col) {
        this.nQueens = nQueens;
        this.boardCells = new JLabel[nQueens][nQueens];          
        setBounds(0, 0, 512, 512);
        setLayout(new GridLayout(nQueens, nQueens));
        setBoardCells();
    }

    private void setPanelSize(int width,int height) {
        Dimension size = new Dimension(width,height);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }

    private void setBoardCells() {
        for (int i = 0; i < this.nQueens; i++) {
            for (int j = 0; j < this.nQueens; j++) {
                boardCells[i][j] = new JLabel();
                boardCells[i][j].setSize(512 / this.nQueens, 512 / this.nQueens);
                boardCells[i][j].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                boardCells[i][j].setOpaque(true);
                if ((i + j) % 2 == 1) {
                    boardCells[i][j].setBackground(Color.black);
//                    boardCells[i][j].setForeground(Color.white);
                } else {
                    boardCells[i][j].setBackground(Color.white);
                }
                add(boardCells[i][j]);
            }
        }

    }
    
    public JLabel[][] getBoardCells() {
        return this.boardCells;
    }

}
