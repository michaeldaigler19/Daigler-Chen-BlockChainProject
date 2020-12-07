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

    public Artefact getArtefact(){
        return this.artefact;
    }

    public Long getTime(){
        return this.TimeStamp;
    }

    public StakeHolder getSeller(){
        return this.Seller;
    }

    public StakeHolder getBuyer(){
        return this.Buyer;
    }

    public StakeHolder getAuctionhouse(){
        return this.AuctionHouse;
    }

    public Double getPrice(){
        return this.Price;
    }

    public void setArtefact(Artefact artefact){
        this.artefact = artefact;
    }

    public void setTime(Long time){
        this.TimeStamp = time;
    }

    public void setSeller(StakeHolder seller){
        this.Seller=seller;
    }

    public void setBuyer(StakeHolder buyer){
        this.Buyer = buyer;
    }

    public void setAuctionhouse(StakeHolder auctionhouse){
        this.AuctionHouse = auctionhouse;
    }

    public void setPrice(Double price){
        this.Price = price;
    }

    public String toString (){
        return artefact.toString()+ ","+TimeStamp+ ","+Seller.toString() +","+Buyer.toString()+""+AuctionHouse.toString()+""+Price  ;
    }


}
