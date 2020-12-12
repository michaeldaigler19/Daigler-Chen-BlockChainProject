import javax.swing.*;
import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import java.util.Date;
import java.util.concurrent.TimeUnit;

// An AWT GUI program inherits from the top-level container java.awt.Frame
public class GUI extends JFrame implements ActionListener {
    protected JButton enter, next, toggle;
    private Label lblInput;     // Declare input Label

    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4, label5, label6, label7, label8, label9, label10;


    private TextField Input1;  // Declare input TextField
    private TextField Input2;
    private TextField Input3;
    private TextField Input4, Input5, Input6, Input7, Input8;




    int counter=0;
    String oId ="" ;
    String oName="";
    String oAddress="";
    String bId="";
    String bName="";
    String bAddress="";
    String cId="";
    String cName="";
    String cAddress="";
    String aId="";
    String aName="";
    String aAddress="";
    Double oBalance=0.0;
    Double aBalance=0.0;
    Double bBalance=0.0;
    Double cBalance=0.0;

    // Constructor to setup the GUI components and event handlers
    public GUI() {




        setLayout(new FlowLayout());


        lblInput = new Label("Enter the ID of this artefact:"); // Construct Label
        add(lblInput);
        Input1 = new TextField(20); // Construct TextField
        add(Input1);
        label1 = new Label("Enter the name of this artefact:"); // Construct Label
        add(label1);// "super" Frame adds TextField
        Input2 = new TextField(20); // Construct TextField
        add(Input2);

        label7 = new Label("Please enter the information for the owner of this artefact");
        add(label7);
        label3 = new Label("Enter the ID of this stakeholder:"); // Construct Label
        add(label3);
        Input3 = new TextField(20); // Construct TextField
        add(Input3);
        label4 = new Label("Enter the name of this stakeholder:"); // Construct Label
        add(label4);
        Input4 = new TextField(20); // Construct TextField
        add(Input4);
        label5 = new Label("Enter the address of this stakeholder:"); // Construct Label
        add(label5);
        Input5 = new TextField(20); // Construct TextField
        add(Input5);
        label6 = new Label("Enter the balance of this stakeholder:"); // Construct Label
        add(label6);
        Input6 = new TextField(20); // Construct TextField
        add(Input6);
        next = new JButton("Confirm Stakeholder");
        add(next);
        toggle = new JButton("Enter new Stakeholder");
        add(toggle);
        toggle.setEnabled(false);
        label8 = new Label("Enter the price of this transaction:"); // Construct Label
        add(label8);
        Input7 = new TextField(20); // Construct TextField
        add(Input7);
        enter = new JButton("Create Transaction");
        add(enter);



        enter.addActionListener(this);
        next.addActionListener(this);
        toggle.addActionListener(this);

        setTitle("Transaction creator");
        setSize(350, 120);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent evt) {

        boolean reset=false;
        if(evt.getSource() == enter) {

            Main main = new Main();
            StakeHolder owner = new StakeHolder(oId, oName, oAddress, oBalance);
            StakeHolder countryOfOrigin = new StakeHolder(cId, cName, cAddress, cBalance);
            StakeHolder buyer = new StakeHolder(bId, bName, bAddress, bBalance);
            StakeHolder auctionHouse = new StakeHolder(aId, aName, aAddress, aBalance);
            Artefact artefact = new Artefact(Input1.getText(), Input2.getText(), countryOfOrigin, owner);
            Transaction transaction = new Transaction(artefact, new Date().getTime(), owner, buyer, auctionHouse, Double.parseDouble(Input7.getText()));
            System.out.println("User created block submitted");
            main.createBlock(transaction, 4, main.blockchain);
            main.writeTransactionToFile(main.blockchain);
            setTitle("Transaction successfully submitted");
            counter=0;
            label7.setText("Please enter the information for the owner of this artefact");

            Input3.setText("");
            Input4.setText("");
            Input5.setText("");
            Input6.setText("");
            Input1.setText("");
            Input2.setText("");
            Input7.setText("");
            Input3.setEditable(true);
            Input4.setEditable(true);
            Input5.setEditable(true);
            Input6.setEditable(true);



        }

        if(evt.getSource() == next&&counter==0){
            oId = Input3.getText();
            oName = Input4.getText();
            oAddress = Input5.getText();
            oBalance = Double.parseDouble(Input6.getText());
            label7.setText("Please enter the information of the country of origin of this artefact");
            Input3.setEditable(false);
            Input4.setEditable(false);
            Input5.setEditable(false);
            Input6.setEditable(false);
            toggle.setEnabled(true);
            next.setEnabled(false);

        }

        if(evt.getSource()==toggle){
            Input3.setText("");
            Input4.setText("");
            Input5.setText("");
            Input6.setText("");
            counter++;
            Input3.setEditable(true);
            Input4.setEditable(true);
            Input5.setEditable(true);
            Input6.setEditable(true);
            toggle.setEnabled(false);
            next.setEnabled(true);
            if (counter>3){
                Input3.setEditable(false);
                Input4.setEditable(false);
                Input5.setEditable(false);
                Input6.setEditable(false);
            }

        }
        if(evt.getSource() == next&&counter==3&&reset==false){
            aId = Input3.getText();
            aName = Input4.getText();
            aAddress = Input5.getText();
            aBalance = Double.parseDouble(Input6.getText());
            label7.setText("Thank you, please proceed to the next section");
            Input3.setEditable(false);
            Input4.setEditable(false);
            Input5.setEditable(false);
            Input6.setEditable(false);
            toggle.setEnabled(true);
            next.setEnabled(false);


        }

        if(evt.getSource() == next&&counter==2&&reset==false){
            bId = Input3.getText();
            bName = Input4.getText();
            bAddress = Input5.getText();
            bBalance = Double.parseDouble(Input6.getText());
            label7.setText("Please enter the information of the auctionhouse in this transaction");
            Input3.setEditable(false);
            Input4.setEditable(false);
            Input5.setEditable(false);
            Input6.setEditable(false);
            toggle.setEnabled(true);
            next.setEnabled(false);

        }

        if(evt.getSource() == next&&counter==1&&reset==false){

            cId = Input3.getText();
            cName = Input4.getText();
            cAddress = Input5.getText();
            cBalance = Double.parseDouble(Input6.getText());
            label7.setText("Please enter the information of the buyer in this transaction");
            Input3.setEditable(false);
            Input4.setEditable(false);
            Input5.setEditable(false);
            Input6.setEditable(false);
            toggle.setEnabled(true);
            next.setEnabled(false);


        }







    }
}