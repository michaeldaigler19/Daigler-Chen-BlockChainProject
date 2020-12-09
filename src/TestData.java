package src;

import java.util.Date;

// src.Artefact artefact;
//    Double TimeStamp;
//    src.StakeHolder Seller;
//    src.StakeHolder Buyer;
//    src.StakeHolder AuctionHouse;
//    Double Price;
//    public src.Transaction(src.Artefact artefact, Double timeStamp, src.StakeHolder seller, src.StakeHolder buyer, src.StakeHolder auctionHouse, Double price) {
public class TestData {
    public StakeHolder countryOfOrigin1 = new StakeHolder("c1","Uganda", "1234Street", 10.00);
    public StakeHolder owner1 = new StakeHolder("o1", "Bob", "123Road", 5.00);
    public Artefact artefact1 = new Artefact("1", "plate", countryOfOrigin1, owner1);
    public StakeHolder buyer1 = new StakeHolder("b1", "Jim", "456Blvd", 6.00);
    public StakeHolder seller1 = new StakeHolder("s1", "George", "789way", 7.00);
    public StakeHolder autcionHouse1 = new StakeHolder("a1", "BigSales", "000Lane", 34.00);
    public Transaction transaction1 = new Transaction(artefact1, new Date().getTime(),buyer1, seller1, autcionHouse1 ,3.00);

    StakeHolder countryOfOrigin2 = new StakeHolder("c2","Chile", "1234Street", 10.00);
    StakeHolder owner2 = new StakeHolder("o2", "Bob", "123Road", 5.00);
    Artefact artefact2 = new Artefact("2", "vase", countryOfOrigin2, owner2);
    StakeHolder buyer2 = new StakeHolder("b2", "Jim", "456Blvd", 6.00);
    StakeHolder seller2 = new StakeHolder("s2", "George", "789way", 7.00);
    StakeHolder autcionHouse2 = new StakeHolder("a2", "IpsumHouse", "000Lane", 34.00);
    public Transaction transactsion2 = new Transaction(artefact2, new Date().getTime(),buyer2, seller2, autcionHouse2 ,2.00);


    StakeHolder countryOfOrigin3 = new StakeHolder("c3","Egypt", "1234Street", 10.00);
    StakeHolder owner3 = new StakeHolder("o3", "Bob", "123Road", 5.00);
    Artefact artefact3 = new Artefact("3", "tablet", countryOfOrigin3, owner3);
    StakeHolder buyer3 = new StakeHolder("b3", "Jim", "456Blvd", 6.00);
    StakeHolder seller3 = new StakeHolder("s3", "George", "789way", 7.00);
    StakeHolder autcionHouse3 = new StakeHolder("a3", "Uncle Bob's place", "000Lane", 34.00);
    public Transaction transaction3 = new Transaction(artefact3, new Date().getTime(),buyer3, seller3, autcionHouse3 ,1.00);

}
