import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;
public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Block> blockchain = new ArrayList<>();
        int prefix = 4;   //we want our hash to start with four zeroes
        String prefixString = new String(new char[prefix]).replace('\0', '0');

        //data1-data3 should be filled by the user
       TestData data1 = new TestData();
       TestData data2 = new TestData();
       TestData data3 = new TestData();
       createBlock(data1.transaction1,prefix, blockchain);
       createBlock(data2.transaction2,prefix, blockchain);
       createBlock(data3.transaction3,prefix, blockchain);

       Transaction transaction = new Transaction();
       Boolean end = false;
       while(end==false) {

           createBlock(askForTransaction(), prefix, blockchain);
           System.out.println("Do you wish to enter another transaction? Answer 'yes' or 'no'");
           String response = scanner.nextLine();
           if (response.equals("no")){
               end=true;
           }
           else {
               end = false;
           }
       }
        System.out.println (blockchain.get(0).Data.ToString());
        FileOutputStream output = null;
        PrintWriter fileWriter;
        try {
            output = new FileOutputStream("Transaction data.txt", true);
        }
        catch (FileNotFoundException e){
            System.out.println ("File could not be opened for output- closing program");
            System.exit(1);
        } // ends catch

        fileWriter=new PrintWriter(output, true);


        for(int i =0; i<blockchain.size();i++){ //writes all the information to a file
            fileWriter.println(blockchain.get(i).Data.ToString());
        }








    }

    public static void createBlock(Transaction transaction, int prefix ,ArrayList<Block> blockchain){
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        if(blockchain.size()==0) {
            Block genesisBlock = new Block(transaction, "0000", new Date().getTime());

            genesisBlock.mineBlock(prefix, blockchain);
            if (genesisBlock.getHash().substring(0, prefix).equals(prefixString) && verify_Blockchain(blockchain))
                blockchain.add(genesisBlock);
            else
                System.out.println("Malicious block, not added to the chain");
        }
        else {
            Block regularBlock = new Block(transaction, blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
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

    public static boolean verify_Blockchain2(ArrayList<Block> BC){
        int indexOfLastBlock = BC.size() - 1;
        for (int i = indexOfLastBlock; i > 0; i--) {
            if (!currentPreviousHashEqualsCurrentHashOfPreviousBlock(BC, i)) {
                return false;
            } else if (!storedHashOfCurrentEqualsWhatItCalculates(BC, indexOfLastBlock)){
                return false;
            } else if (!currentBlockHasBeenMined(BC, indexOfLastBlock)) {
                return false;
            }
        }
        return true;
    }

    public static boolean currentPreviousHashEqualsCurrentHashOfPreviousBlock(ArrayList<Block> BC, int idx) {
        return BC.get(idx).PreviousBlockHash.equals(BC.get( idx - 1).getHash());
    }
    public static boolean storedHashOfCurrentEqualsWhatItCalculates(ArrayList<Block> BC, int indexOfLastBlock) {
        return BC.get(indexOfLastBlock).calculateBlockHash().equals(BC.get(indexOfLastBlock).getHash());
    }
    public static boolean currentBlockHasBeenMined(ArrayList<Block> BC, int indexOfLastBlock) {
        return BC.get(indexOfLastBlock).getHash() != null;
    }


    public static boolean verify_Blockchain(ArrayList<Block> BC){
        int indexOfLastBlock = BC.size() - 1;
        for (int i = indexOfLastBlock; i > 0; i--) {
            if (!BC.get(i).PreviousBlockHash.equals(BC.get( i- 1).getHash())) {
                return false;
            } else if (!BC.get(indexOfLastBlock).calculateBlockHash().equals(BC.get(indexOfLastBlock).getHash())){
                return false;
            } else if (BC.get(indexOfLastBlock).getHash().substring(0,3).equals("0000")) {
                return false;
            }
        }
        return true;
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
        Double balance = scanner.nextDouble();
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

        double price= 0.00;
        boolean priceNotEntered= true;
        boolean enteredIncorrectly = false;
        while (priceNotEntered) {
            while (!scanner.hasNextDouble()) {
                System.out.println("Try again");
                scanner.next();
            }
            price = scanner.nextDouble();

                priceNotEntered = false;
        }

//        while(priceNotEntered) {
//            try {
//                price = scanner.nextDouble();
//                priceNotEntered=false;
//            } catch (InputMismatchException e1) {
//                System.out.println("the input is not a valid number");
//                //continue;
//            }
//
//        }
        Transaction transaction = new Transaction(artefact, new Date().getTime(),buyer,seller,auctionhouse,price);
        return transaction ;
    }


}
