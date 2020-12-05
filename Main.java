import java.util.*;
public class Main {
    public static void main(String args[]) {

        ArrayList<Block> blockchain = new ArrayList<>();
        int prefix = 4;   //we want our hash to start with four zeroes
        String prefixString = new String(new char[prefix]).replace('\0', '0');

        //data1-data3 should be filled by the user
       // TestData data1 = new TestData();
      //  TestData data2 = new TestData();
      //  TestData data3 = new TestData();
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new Transaction();
        askForTransaction(transaction1);
        askForTransaction(transaction2);
        askForTransaction(transaction3);
        Block genesisBlock = new Block(transaction1,  "0", new Date().getTime());

        genesisBlock.mineBlock(prefix);
        if (genesisBlock.getHash().substring(0, prefix).equals(prefixString) &&  verify_Blockchain(blockchain))
            blockchain.add(genesisBlock);
        else
            System.out.println("Malicious block, not added to the chain");

        Block secondBlock = new Block(transaction2, blockchain.get(blockchain.size() - 1).getHash(),new Date().getTime());
        secondBlock.mineBlock(prefix);
        if (secondBlock.getHash().substring(0, prefix).equals(prefixString) &&  verify_Blockchain(blockchain))
            blockchain.add(secondBlock);
        else
            System.out.println("Malicious block, not added to the chain");

        Block thirdBlock = new Block(transaction3,blockchain.get(blockchain.size() - 1).getHash(),
                new Date().getTime());
        thirdBlock.mineBlock(prefix);
        if (thirdBlock.getHash().substring(0, prefix).equals(prefixString) &&  verify_Blockchain(blockchain))
            blockchain.add(thirdBlock);
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
        for (int i = indexOfLastBlock; i > 0; i--) {
            if (!BC.get(i).PreviousBlockHash.equals(BC.get( i- 1).getHash())) {
                return false;
            } else if (!BC.get(indexOfLastBlock).calculateBlockHash().equals(BC.get(indexOfLastBlock).getHash())){
                return false;
            } else if (BC.get(indexOfLastBlock).getHash() == null) {
                return false;
            }
        }
        return true;
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
