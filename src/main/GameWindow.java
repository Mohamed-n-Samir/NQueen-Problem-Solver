package main;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author nash2t
 */
public class GameWindow extends JFrame {

    public GameWindow(GamePanel gamePanel,String threadName) {
        super(threadName);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        add(gamePanel);
        setLocationRelativeTo(null);
        setDefaultLookAndFeelDecorated(false);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
