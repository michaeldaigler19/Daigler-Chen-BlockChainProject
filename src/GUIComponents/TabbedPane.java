package src.GUIComponents;

import src.Artefact;
import src.Main;
import src.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabbedPane extends JFrame {
    private final int width = 600;
    private final int height = 800;

    DropDownButton dropDownButtonArtefact = new DropDownButton();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public TabbedPane() {

        initUI();



        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 dropDownButtonArtefact.fetchData();
                dropDownButtonArtefact.createAndShowGUI();
            }
        });
    }

    private void initUI() {

        var tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Wallet", createPanel("Your Wallet"));
        tabbedPane.addTab("Transactions", createPanel("Create Transaction"));

//        tabbedPane.getTabComponentAt(0).add();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        createLayout(tabbedPane);
        setLocationRelativeTo(null);
        setSize(width, height);
        setVisible(true);
    }

    private JPanel createPanel(String text) {

        var panel = new JPanel();
        var lbl = new JLabel(text);
        var lbl2 = new JLabel();
        panel.add(lbl);
        panel.add(lbl2);
        JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        if (text.equals("Create Transaction")) {
            CustomTextField artefactNameTextField = new CustomTextField(1);
            artefactNameTextField.setPlaceholder("Artefact Name");
            CustomTextField timestampTextField = new CustomTextField(1);
            timestampTextField.setPlaceholder("Enter TimeStamp");
            CustomTextField buyerTextField = new CustomTextField(1);
            buyerTextField.setPlaceholder("Who's the Buyer?");
            CustomTextField sellerTextField = new CustomTextField(1);
            sellerTextField.setPlaceholder("Who's selling?");
            CustomTextField auctionHouseTextField = new CustomTextField(1);
            auctionHouseTextField.setPlaceholder("Auction House");
            CustomTextField priceTextField = new CustomTextField(1);
            priceTextField.setPlaceholder("Price of artefact");
            JButton creatTransactionButton = new JButton("Create Transaction");
            creatTransactionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Artefact userInputArtefact = new Artefact();
                    Transaction userInputTransaction = new Transaction();
//                    Main.writeTransactionToFile();
                    lbl2.setText("Success!");
                    setTimeout(() -> lbl2.setText(""), 2000);

                }
            });
            panel.setLayout(new GridLayout(6,1,5,50));
            panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            panel.add(artefactNameTextField);
            panel.add(timestampTextField);
            panel.add(buyerTextField);
            panel.add(sellerTextField);
            panel.add(auctionHouseTextField);
            panel.add(priceTextField);
            panel.add(creatTransactionButton);
            panel.add(dropDownButtonArtefact);
            panel.setSize(width, height );
            scrollBar.createHorizontalScrollBar();
            scrollBar.createVerticalScrollBar();
            add(scrollBar);

        }
        return panel;
    }
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }


    private void createLayout(JComponent... arg) {

        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
        );
        pack();
    }



}
