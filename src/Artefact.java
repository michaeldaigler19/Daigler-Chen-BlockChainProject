package src;

public class Artefact {
    String Id;
    String Name;
    StakeHolder CountryOfOrigin;
    StakeHolder stakeHolderOwner;

    public Artefact(String id, String name, StakeHolder countryOfOrigin, StakeHolder owner) {
        this.Id = id;
        this.Name = name;
        this.CountryOfOrigin = countryOfOrigin;
        this.stakeHolderOwner = owner;
    }

    //Need setters and getters for each
}
