package src;

public class Transaction {
    Artefact artefact;
    Long TimeStamp;
    StakeHolder Seller;
    StakeHolder Buyer;
    StakeHolder AuctionHouse;
    Double Price;

    public Transaction(Artefact artefact, Long timeStamp, StakeHolder seller, StakeHolder buyer, StakeHolder auctionHouse, Double price) {
        this.artefact = artefact;
        this.TimeStamp = timeStamp;
        this.Seller = seller;
        this.Buyer = buyer;
        this.AuctionHouse = auctionHouse;
        this.Price = price;
    }

    // need setters and getters



}
