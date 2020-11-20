public class Transaction {
    Artefact artefact;
    Double TimeStamp;
    StakeHolder Seller;
    StakeHolder Buyer;
    StakeHolder AuctionHouse;
    Double Price;

    public Transaction(Artefact artefact, Double timeStamp, StakeHolder seller, StakeHolder buyer, StakeHolder auctionHouse, Double price) {
        this.artefact = artefact;
        this.TimeStamp = timeStamp;
        this.Seller = seller;
        this.Buyer = buyer;
        this.AuctionHouse = auctionHouse;
        this.Price = price;
    }

    // need setters and getters



}
