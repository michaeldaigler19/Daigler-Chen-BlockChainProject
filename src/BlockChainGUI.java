package src;

import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.Rect;

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
        private JLabel label;
        private JTextField textField;
        private Button btnCount;
        private int count = 0;
        private Panel block = new Panel();
        private ArrayList<Block> blockChain;
        private Random rand;
        private int currentBlockChainIndex;
        private GridLayout gridLayout = new GridLayout(0,2);
        private GridBagConstraints gridBagConstraints;
        final static boolean shouldFill = true;
        final static boolean shouldWeightX = true;
        final static boolean RIGHT_TO_LEFT = false;
        public BlockChainGUI() {

        }


            public  void addComponentsToPane(Container pane) {
                if (RIGHT_TO_LEFT) {
                    pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                }

                JButton button;
                pane.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                if (shouldFill) {
                    //natural height, maximum width
                    c.fill = GridBagConstraints.HORIZONTAL;
                }

                button = new JButton("Make Transaction");
                if (shouldWeightX) {
                    c.weightx = 0.5;
                }
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 0;
                pane.add(button, c);

                button = new JButton("Enter Users");
                c.fill = GridBagConstraints.HORIZONTAL;
                c.weightx = 0.5;
                c.gridx = 1;
                c.gridy = 0;
                pane.add(button, c);

                label = new JLabel("Name:");
                c.fill = GridBagConstraints.PAGE_START;
                c.weightx = 0.5;
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = 1;
                pane.add(label, c);

                textField = new JTextField();
                c.fill = GridBagConstraints.HORIZONTAL;
//                c.ipady = 40;      //make this component tall
                c.weightx = 0.5;
                c.gridwidth = 2;
                c.gridx = 1;
                c.gridy = 1;
                pane.add(textField, c);

                label = new JLabel("Artefact:");
                c.fill = GridBagConstraints.PAGE_START;
                c.weightx = 0.0;
                c.gridwidth = 1;
                c.gridx = 0;
                c.gridy = 2;
                pane.add(label, c);

                textField = new JTextField();
                c.fill = GridBagConstraints.HORIZONTAL;
//                c.ipady = 40;      //make this component tall
                c.weightx = 0.0;
                c.gridwidth = 2;
                c.gridx = 1;
                c.gridy = 2;
                pane.add(textField, c);

                button = new JButton("5");
                c.fill = GridBagConstraints.HORIZONTAL;
                c.ipady = 0;       //reset to default
                c.weighty = 1.0;   //request any extra vertical space
                c.anchor = GridBagConstraints.PAGE_END; //bottom of space
                c.insets = new Insets(10,0,0,0);  //top padding
                c.gridx = 1;       //aligned with button 2
                c.gridwidth = 2;   //2 columns wide
                c.gridy = 2;       //third row
                pane.add(button, c);
            }

            /**
             * Create the GUI and show it.  For thread safety,
             * this method should be invoked from the
             * event-dispatching thread.
             */
            public void createAndShowGUI() {
                //Create and set up the window.
                JFrame frame = new JFrame("BlockChain");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //Set up the content pane.
                addComponentsToPane(frame.getContentPane());

                //Display the window.
                frame.pack();
                frame.setVisible(true);
                frame.setSize(400,400);
            }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

//        public BlockChainGUI (ArrayList<Block> BC) {
////            setLayout(gridLayout);
//            new GridBagLayout();
//            gridBagConstraints = new GridBagConstraints();
//
//            blockChain = BC;
//            currentBlockChainIndex = blockChain.size() - (blockChain.size() - 1);
//
//            block.setBackground(Color.red);
//            block.setSize(20, 20);
//            add(block);
//            lblCount = new Label("Current Block");
//            add(lblCount);
//
//            btnCount = new Button("Mine Block");
//            add(btnCount);
//
//            textField = new JTextField();
//            textField.setSize(100, 50);
//            textField.setBounds(0,0,200,50);
//            add(textField);
//
//
//
//            btnCount.addActionListener(this);
//
//            setTitle("BlockChain Rocks");
////            setSize(250, 100);
//
//
//            setSize(400, 400);
//            setVisible(true);
//
//
//        }
//    @Override
//    public void actionPerformed(ActionEvent evt) {
//            String currHash = blockChain.get(currentBlockChainIndex).mineBlock(prefix);
//            if (currentBlockChainIndex == blockChain.size() - 1 ) {
//                return;
//            } else {
//                currentBlockChainIndex ++;
//                System.out.println(currHash);
//                int colorIdx = (int) (Math.random() * (2));
//
//                block.setBackground(colors[colorIdx]);
//            }
//    }



//    public BlockChainGUI () {}
//
//    public void start() {
//
//        final String title = "BlockChain Rocks!";
//        final int width = 1200;
//
//        final int height = width / 16 * 9;
//
//        JPanel blockChainPanel = new JPanel();
//        Button mineBlockButton = new Button();
//        mineBlockButton.setLabel("Mine Block");
//        blockChainPanel.add(mineBlockButton);
//
//        //Creating the blockChainGUIFrame.
//        JFrame blockChainGUIFrame = new JFrame(title);
//
//        blockChainGUIFrame.setSize(width, height);
//        blockChainGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        blockChainGUIFrame.setLocationRelativeTo(null);
//        blockChainGUIFrame.setResizable(false);
//        blockChainGUIFrame.setVisible(true);
//
//        //Creating the canvas.
//        Canvas canvas = new Canvas();
//
//        canvas.setSize(width, height);
//        canvas.setBackground(Color.BLACK);
//        canvas.setVisible(true);
//        canvas.setFocusable(false);
//
//
//        //Putting it all together.
//        blockChainGUIFrame.add(canvas);
//        blockChainGUIFrame.add(blockChainPanel);
//
//        canvas.createBufferStrategy(3);
//
//        boolean running = true;
//
//        BufferStrategy bufferStrategy;
//        Graphics graphics;
//
//        while (running) {

//            bufferStrategy = canvas.getBufferStrategy();
//
//            graphics = bufferStrategy.getDrawGraphics();
//
//            graphics.clearRect(0, 0, width, height);
//            graphics.setColor(Color.GREEN);
//            graphics.drawString("This is some text placed in the top left corner.", 5, 15);
//            bufferStrategy.show();
//            graphics.dispose();
//        }
//    }