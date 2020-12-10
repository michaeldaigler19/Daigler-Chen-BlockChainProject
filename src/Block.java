package src;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

public class Block {
    String PreviousBlockHash;
    String CurrentBlockHash;
    Transaction Data;
    Long TimeStamp;
    Integer nonce;


    public Block(Transaction data,String previousBlockHash ,Long timeStamp) {
        this.Data = data;
        this.TimeStamp = timeStamp;
        this.nonce = (int)(Math.random() * 100);
        if ( previousBlockHash.equals(Main.PREVIOUS_HASH_FOR_GENISIS_BLOCK) ){
            this.PreviousBlockHash = Main.PREVIOUS_HASH_FOR_GENISIS_BLOCK;
            this.CurrentBlockHash = calculateBlockHash();
        } else {
            this.PreviousBlockHash = previousBlockHash;
            this.CurrentBlockHash = calculateBlockHash();
        }
    }

    public String calculateBlockHash() {

        String dataToHash = this.PreviousBlockHash
                + Long.toString(this.TimeStamp)
                + Integer.toString(this.nonce)
                + this.Data.toString();
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("The encoding is not supported");
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();

    }
    public String mineBlock(int prefix, ArrayList<Block> blockchain) {
        if  (TreatySC(this.Data, blockchain)) {
            String prefixString = new String(new char[prefix]).replace('\0', '0');
            while (!this.CurrentBlockHash.substring(0, prefix).equals(prefixString)) {
                nonce++;
                this.CurrentBlockHash = calculateBlockHash();


            }
            DistributeCash(this.Data, blockchain);
            System.out.println(CurrentBlockHash);
            System.out.println("The block has been successfully mined");
        }
        else {
            System.out.println("This transaction has been terminated, block not mined");
        }


        return this.CurrentBlockHash;


    }
    public Boolean TreatySC(Transaction transaction, ArrayList<Block> blockchain) {
        if (blockchain.size()>=2) {
            if (retrieveProvenanceByYear(transaction.getArtefact().getId(), blockchain, 2001).size() < 2) {
                System.out.println("This artefact does not have 2 transactions after 2001");
                return false;
            }
        }
        if (transaction.getBuyer().getBalance()<= transaction.getPrice()){
            System.out.println("The buyer cannot afford the price");
            return false;

        }
        else{
            return true;
        }
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








    // Getters and setters defined below


    public String getHash() {

        return this.CurrentBlockHash;

    }
}