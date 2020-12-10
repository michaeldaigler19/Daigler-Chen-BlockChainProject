package src;
import src.GUIComponents.GlassPane;
import src.GUIComponents.TabbedPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class BlockChainGUI extends JFrame implements ActionListener {
        private Color[] colors = {Color.red, Color.blue, Color.pink};
        private int prefix = 4;

        private JPanel block = new JPanel();
        private JTabbedPane mainPanel = new JTabbedPane();
        private  Frame frame = new Frame("Block Chain Project");
        private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        private GlassPane glassPane = new GlassPane();
        public BlockChainGUI() {

            EventQueue.invokeLater(() -> {

                var ex = new TabbedPane();
                ex.setVisible(true);
            });

        }



        public void setupLayout2() {
            frame.setSize(400,300);
            frame.setBackground(Color.GRAY);
            mainPanel.setLayout(new GridLayout(2,2,5,10));
            addComponentsToPane();
            mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            frame.add(mainPanel);
            frame.setVisible(true);

        }





    public  void addComponentsToPane() {
        block.setBackground(Color.RED);
        block.setSize(50, 50);
        JButton makeTransactionButton = new JButton("Make Transaction");
//        mainPanel.add(block);
        mainPanel.add(makeTransactionButton);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
