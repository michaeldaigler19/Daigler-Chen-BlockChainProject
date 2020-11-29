import java.security.PublicKey;

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

    public Artefact GetArtefact(){
        return this.artefact;
    }

    public Long GetTime(){
        return this.TimeStamp;
    }

    public StakeHolder GetSeller(){
        return this.Seller;
    }

    public StakeHolder GetBuyer(){
        return this.Buyer;
    }

    public StakeHolder GetAuctionhouse(){
        return this.AuctionHouse;
    }

    public Double GetPrice(){
        return this.Price;
    }

    public void SetArtefact(Artefact artefact){
        this.artefact = artefact;
    }

    public void SetTime(Long time){
        this.TimeStamp = time;
    }

    public void SetSeller(StakeHolder seller){
        this.Seller=seller;
    }

    public void SetBuyer(StakeHolder buyer){
        this.Buyer = buyer;
    }

    public void SetAuctionhouse(StakeHolder auctionhouse){
        this.AuctionHouse = auctionhouse;
    }

    public void SetPrice(Double price){
        this.Price = price;
    }

    public String ToString (){
        return artefact.ToString()+ ","+TimeStamp+ ","+Seller.ToString() +","+Buyer.ToString()+""+AuctionHouse.ToString()+""+Price  ;
    }

    // need setters and getters



}
