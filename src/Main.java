package src;
import javax.swing.*;
import java.awt.*;


import java.awt.image.BufferStrategy;
import java.util.*;

// Notes about conceptual overview:
// Each transaction there should be a new block
// Ask user all info
/**
 * Enter all users in system
 * User enters transactions -> select a buyer /  seller + artefact -> then pull rest of data from the data set
 * user wants to make a transaction -> go through all steps: make new Block, mine c=block and verify, add to blockchain if verified
 */

public class Main {
    final static public String PREVIOUS_HASH_FOR_GENISIS_BLOCK = "0000";
    public static void main(String args[]) {
//        g.start();

        ArrayList<Block> blockchain = new ArrayList<>();
        int prefix = 4;   //we want our hash to start with four zeroes
        String prefixString = new String(new char[prefix]).replace('\0', '0');

        //data1-data3 should be filled by the user

        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new Transaction();
        askForTransaction(transaction1);
        askForTransaction(transaction2);
        askForTransaction(transaction3);
        Block genesisBlock = new Block(transaction1,  "0", new Date().getTime());
        TestData data1 = new TestData();
        TestData data2 = new TestData();
        TestData data3 = new TestData();
        Block genesisBlock = new Block(data1.transaction1, PREVIOUS_HASH_FOR_GENISIS_BLOCK, new Date().getTime());

        genesisBlock.mineBlock(prefix);
        if (genesisBlock.getHash().substring(0, prefix).equals(prefixString) &&  verify_Blockchain(blockchain))
            blockchain.add(genesisBlock);
        else
            System.out.println("Malicious block, not added to the chain");

        Block secondBlock = new Block(data2.transaction2, blockchain.get(blockchain.size() - 1).getHash(),new Date().getTime());
        secondBlock.mineBlock(prefix);
        if (secondBlock.getHash().substring(0, prefix).equals(prefixString) &&  verify_Blockchain(blockchain))
            blockchain.add(secondBlock);
        else
            System.out.println("Malicious block, not added to the chain");

        Block thirdBlock = new Block(data3.transaction3,blockchain.get(blockchain.size() - 1).getHash(),
                new Date().getTime());
        thirdBlock.mineBlock(prefix);
        if (thirdBlock.getHash().substring(0, prefix).equals(prefixString) &&  verify_Blockchain(blockchain))
            blockchain.add(thirdBlock);
        else
            System.out.println("Malicious block, not added to the chain");

        BlockChainGUI g = new BlockChainGUI();
        g.createAndShowGUI();
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

    public static ArrayList<Transaction> retrieveProvenanceByYear(String id, ArrayList<Block> blockchain, long timestamp ) {
        ArrayList<Transaction> previousTransactions = new ArrayList<Transaction>();
        for (int i = 0; i < blockchain.size(); i++) {
            if (id.equals( blockchain.get(i).Data.artefact.getId())) {
                if(blockchain.get(i).Data.getTime()>=timestamp){
                    previousTransactions.add(blockchain.get(i).Data);

            }}
        }
        return previousTransactions;
    }

    public Boolean TreatySC(Transaction transaction, ArrayList<Block> blockchain) {
        if (retrieveProvenanceByYear(transaction.getArtefact().getId(), blockchain, 2001).size()>=2){
            return false;
        }
        else if (transaction.getBuyer().getBalance()<= transaction.getPrice()){
            return false;

        }
        else{
            return true;
        }
    }
    public void DistributeCash(Transaction transaction, ArrayList<Block> blockchain){
        if (TreatySC(transaction, blockchain)){
            //distribute profits
            transaction.getAuctionhouse().setBalance(transaction.getAuctionhouse().getBalance()+transaction.getPrice()*0.1);
            transaction.getSeller().setBalance(transaction.getSeller().getBalance()+transaction.getPrice()*0.7);
            transaction.getBuyer().setBalance(transaction.getBuyer().getBalance()-transaction.getPrice());
            transaction.getArtefact().getCountry().setBalance(transaction.getArtefact().getCountry().getBalance()+transaction.getPrice()*0.2);

            // reassigning owner
            transaction.getArtefact().setOwner(transaction.getBuyer());
        }
    }
// TODO: Refactor else if into separate functions
// check if
    public static boolean verify_Blockchain(ArrayList<Block> BC){
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



    public static void askForStakeholder(StakeHolder stakeholder){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the ID of the stakeholder");
        String id = scanner.nextLine();
        System.out.println("Please provide the name of the stakeholder");
        String name = scanner.nextLine();
        System.out.println("Please provide the address of the stakeholder");
        String address = scanner.nextLine();
        System.out.println("Please provide the balance of the stakeholder");
        Double balance = scanner.nextDouble();
        stakeholder = new StakeHolder(id, name, address, balance);



    }

    public static void askForArtefact(Artefact artefact){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the ID of the artefact");
        String id = scanner.nextLine();
        System.out.println("Please provide the name of the artefact");
        String name = scanner.nextLine();
        System.out.println("Please provide the information of the country of origin of this artefact");
        StakeHolder countryOfOrigin=new StakeHolder();
        askForStakeholder(countryOfOrigin);
        System.out.println("Please provide the information of the owner of this artefact");
        StakeHolder owner= new StakeHolder();
        askForStakeholder(owner);
        artefact = new Artefact(id, name, countryOfOrigin, owner );

    }

    public static void askForTransaction(Transaction transaction){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the information of the artefact in this transaction");
        Artefact artefact = new Artefact();
        askForArtefact(artefact);
        System.out.println("Please provide the information of the buyer in this transaction");
        StakeHolder buyer= new StakeHolder();
        askForStakeholder(buyer);
        System.out.println("Please provide the information of the seller in this transaction");
        StakeHolder seller= new StakeHolder();
        askForStakeholder(seller);
        System.out.println("Please provide the information of the auctionhouse in this transaction");
        StakeHolder auctionhouse= new StakeHolder();
        askForStakeholder(auctionhouse);
        System.out.println("Please provide the price of the transaction");
        double price= scanner.nextDouble();
        transaction = new Transaction(artefact, new Date().getTime(),buyer,seller,auctionhouse,price);
    }

}
