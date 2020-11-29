import java.util.*;
public class Main {
    public static void main(String args[]) {

        ArrayList<Block> blockchain = new ArrayList<>();
        int prefix = 4;   //we want our hash to start with four zeroes
        String prefixString = new String(new char[prefix]).replace('\0', '0');

        //data1-data3 should be filled by the user

        Object data1 = new Object();
        Block genesisBlock = new Block(data1,   blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
        Block newBlock;
        newBlock.mineBlock(prefix);
        if (genesisBlock.getHash().substring(0, prefix).equals(prefixString) &&  verify_Blockchain(blockchain))
            blockchain.add(newBlock);
        else
            System.out.println("Malicious block, not added to the chain");

        Block secondBlock = new Block(data2, blockchain.get(blockchain.size() - 1).getHash(),new Date().getTime());
        newBlock.mineBlock(prefix);
        if (secondBlock.getHash().substring(0, prefix).equals(prefixString) &&  verify_Blockchain(blockchain))
            blockchain.add(newBlock);
        else
            System.out.println("Malicious block, not added to the chain");

        Block newBlock = new Block(data3,blockchain.get(blockchain.size() - 1).getHash(),
                new Date().getTime());
        newBlock.mineBlock(prefix);
        if (newBlock.getHash().substring(0, prefix).equals(prefixString) &&  verify_Blockchain(blockchain))
            blockchain.add(newBlock);
        else
            System.out.println("Malicious block, not added to the chain");


    }

    public static ArrayList<Transaction> retrieveProvenance(String id, ArrayList<Block> blockchain ) {
        ArrayList<Transaction> previousTransactions = new ArrayList<Transaction>();
        for (int i = 0; i < blockchain.size(); i++) {
            if (id.equals( blockchain.get(i).Data.artefact.GetId())) {
                previousTransactions.add(blockchain.get(i).Data);

            }
        }
        return previousTransactions;
    }

    public static ArrayList<Transaction> retrieveProvenanceByYear(String id, ArrayList<Block> blockchain, long timestamp ) {
        ArrayList<Transaction> previousTransactions = new ArrayList<Transaction>();
        for (int i = 0; i < blockchain.size(); i++) {
            if (id.equals( blockchain.get(i).Data.artefact.GetId())) {
                if(blockchain.get(i).Data.GetTime()>=timestamp){
                    previousTransactions.add(blockchain.get(i).Data);

            }}
        }
        return previousTransactions;
    }

    public Boolean TreatySC(Transaction transaction, ArrayList<Block> blockchain) {
        if (retrieveProvenanceByYear(transaction.GetArtefact().GetId(), blockchain, 2001).size()>=2){
            return false;
        }
        else if (transaction.GetBuyer().GetBalance()<= transaction.GetPrice()){
            return false;

        }
        else{
            return true;
        }
    }
    public void DistributeCash(Transaction transaction, ArrayList<Block> blockchain){
        if (TreatySC(transaction, blockchain)==true){
            //distribute profits
            transaction.GetAuctionhouse().SetBalance(transaction.GetAuctionhouse().GetBalance()+transaction.GetPrice()*0.1);
            transaction.GetSeller().SetBalance(transaction.GetSeller().GetBalance()+transaction.GetPrice()*0.7);
            transaction.GetBuyer().SetBalance(transaction.GetBuyer().GetBalance()-transaction.GetPrice());
            transaction.GetArtefact().GetCountry().SetBalance(transaction.GetArtefact().GetCountry().GetBalance()+transaction.GetPrice()*0.2);

            // reassigning owner
            transaction.GetArtefact().SetOwner(transaction.GetBuyer());
        }
    }

    public static boolean verify_Blockchain(ArrayList<Block> BC){
        int indexOfLastBlock = BC.size() - 1;
        BC.get(indexOfLastBlock).retrieveProvenance(BC.get(BC.size() - 1).Data.artefact.Id);
        return true;
    }

}
