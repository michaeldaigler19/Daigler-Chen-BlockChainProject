import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.Year;
import java.util.*;

// Notes about conceptual overview:
// Each transaction there should be a new block
// Ask user all info
/**
 * Enter all users in system
 * User enters transactions -> select a buyer /  seller + artefact -> then pull rest of data from the data set
 * user wants to make a transaction -> go through all steps: make new Block, mine c=block and verify, add to blockchain if verified
 */
public class Main extends Frame implements ActionListener {
    final static public String PREVIOUS_HASH_FOR_GENISIS_BLOCK = "0000";
    //    public static ArrayList<Block> blockchain = new ArrayList<>();
    private static Calendar myCalendar = new GregorianCalendar(2000,2,11);
    final static Date dateBefore2001 = myCalendar.getTime();
    public static ArrayList<Block> blockchain = new ArrayList<>();


    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int prefix = 4;



        //we want our hash to start with four zeroes
        String prefixString = new String(new char[prefix]).replace('\0', '0');

        //data1-data3 should be filled by the user

        TestData data1 = new TestData();
        TestData data2 = new TestData();
        TestData data3 = new TestData();
        createBlock(data1.transaction1, prefix, blockchain);
        System.out.println("Test block successfully mined");
        createBlock(data2.transaction2, prefix, blockchain);
        System.out.println("Test block successfully mined");
        createBlock(data3.transaction3, prefix, blockchain);
        System.out.println("Test block successfully mined");


        //---------------------------------------------------------------

        GUI cl = new GUI();

        // Function to set size of JFrame.
        cl.setSize(500, 400);

        // Function to set visibility of JFrame.
        cl.setVisible(true);

        // Function to set default operation of JFrame.
        cl.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        //-----------------------------------------------------------------------

    //    Transaction transaction = new Transaction();
   //     boolean end = false;

//        while (!end) {
//
//            createBlock(askForTransaction(), prefix, blockchain);
//            System.out.println("Do you wish to enter another transaction? Answer 'yes' or 'no'");
//            String response = scanner.nextLine();
//            if (response.equals("no")) {
//                end = true;
//            } else {
//                end = false;
//            }
//        }

        //writeTransactionToFile(blockchain);
//        BlockChainGUI g = new BlockChainGUI();



    }

    public static void writeTransactionToFile(ArrayList<Block> blockchain) {
        FileOutputStream output = null;
        PrintWriter fileWriter;
        try {
            output = new FileOutputStream("TransactionData.txt", true);
        } catch (FileNotFoundException e) {
            System.out.println("File could not be opened for output- closing program");
            System.exit(1);
        }

        fileWriter = new PrintWriter(output, true);


        for (int i = 0; i < blockchain.size(); i++) {
            fileWriter.println(blockchain.get(i).Data.ToString());
        }
    }

    public static void createBlock(Transaction transaction, int prefix ,ArrayList<Block> blockchain){
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        if(blockchain.size() == 0) {
            Block genesisBlock = new Block(transaction, PREVIOUS_HASH_FOR_GENISIS_BLOCK, dateBefore2001.getTime());

            genesisBlock.mineBlock(prefix, blockchain);
            if (genesisBlock.getHash().substring(0, prefix).equals(prefixString) && verify_Blockchain(blockchain))
                blockchain.add(genesisBlock);
            else
                System.out.println("Malicious block, not added to the chain");
        }
        else {
            Block regularBlock = new Block(transaction, blockchain.get(blockchain.size() - 1).getHash(), dateBefore2001.getTime());
            regularBlock.mineBlock(prefix, blockchain);
            if (regularBlock.getHash().substring(0, prefix).equals(prefixString) &&  verify_Blockchain(blockchain))
                blockchain.add(regularBlock);
            else
                System.out.println("Malicious block, not added to the chain");

        }
    }


    public static ArrayList<Transaction> retrieveProvenance(String id, ArrayList<Block> blockchain ) {
        ArrayList<Transaction> previousTransactions = new ArrayList<Transaction>();
        for (int i = 0; i < blockchain.size(); i++) {
            if (id.equals( blockchain.get(i).Data.artefact.getId())) {
                previousTransactions.add(blockchain.get(i).Data);

            }
        }
        return previousTransactions;
    }

    public static boolean verify_Blockchain(ArrayList<Block> BC){
        int indexOfLastBlock = BC.size() - 1;

        if (BC.size() == 0){
            indexOfLastBlock = 0;
        }

        for (int i = indexOfLastBlock; i > 0; i--) {
            if (!currentPreviousHashEqualsCurrentHashOfPreviousBlock(BC, i)) {
                System.out.println("The hash of the previous block does not equal the previous hash stored in this block");
                return false;
            } else if (!storedHashOfCurrentEqualsWhatItCalculates(BC, indexOfLastBlock)){
                System.out.println("The hash does not equal to what it calculates to be");
                return false;
            } else if (!currentBlockHasBeenMined(BC, indexOfLastBlock)) {
                System.out.println("The block has not been mined");
                return false;
            }
        }
        return true;
    }

    public static boolean currentPreviousHashEqualsCurrentHashOfPreviousBlock(ArrayList<Block> BC, int idx) {
        return BC.get(idx).PreviousBlockHash.equals(BC.get( idx - 1).getHash());
    }
    public static boolean storedHashOfCurrentEqualsWhatItCalculates(ArrayList<Block> BC, int indexOfLastBlock) {
        return BC.get(indexOfLastBlock).getHash().equals(BC.get(indexOfLastBlock).calculateBlockHash());
    }
    public static boolean currentBlockHasBeenMined(ArrayList<Block> BC, int indexOfLastBlock) {
        return BC.get(indexOfLastBlock).getHash().substring(0,4).equals("0000");
    }



    public static StakeHolder askForStakeholder(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the ID of the stakeholder");
        String id = scanner.nextLine();
        System.out.println("Please provide the name of the stakeholder");
        String name = scanner.nextLine();
        System.out.println("Please provide the address of the stakeholder");
        String address = scanner.nextLine();
        System.out.println("Please provide the balance of the stakeholder");
        double balance=checkForInvalidDouble();
        StakeHolder stakeholder = new StakeHolder(id, name, address, balance);
        return stakeholder;



    }

    public static Artefact askForArtefact(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the ID of the artefact");
        String id = scanner.nextLine();
        System.out.println("Please provide the name of the artefact");
        String name = scanner.nextLine();
        System.out.println("Please provide the information of the country of origin of this artefact");
        StakeHolder countryOfOrigin=askForStakeholder();
        System.out.println("Please provide the information of the owner of this artefact");
        StakeHolder owner= askForStakeholder();

        Artefact artefact = new Artefact(id, name, countryOfOrigin, owner );
        return artefact;

    }

    public static Transaction askForTransaction(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the information of the artefact in this transaction");
        Artefact artefact = askForArtefact();
        System.out.println("Please provide the information of the buyer in this transaction");
        StakeHolder buyer= askForStakeholder();
        StakeHolder seller= artefact.getOwner();
        System.out.println("Please provide the information of the auctionhouse in this transaction");
        StakeHolder auctionhouse= askForStakeholder();
        System.out.println("Please provide the price of the transaction");

        double price=checkForInvalidDouble();


//
        Transaction transaction = new Transaction(artefact, new Date().getTime(),buyer,seller,auctionhouse,price);
        return transaction ;
    }
    public static double checkForInvalidDouble(){
        Scanner scanner = new Scanner(System.in);
        double number= 0.00;
        boolean numberNotEntered= true;
        boolean enteredIncorrectly = false;
        while (numberNotEntered) {
            while (!scanner.hasNextDouble()) {
                System.out.println("Please enter a valid number, try again");
                scanner.next();
            }
            number = scanner.nextDouble();

            numberNotEntered = false;

    }
        return number;

}







    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
