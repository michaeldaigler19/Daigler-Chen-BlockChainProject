package src;

public class Artefact {
    String Id;
    String Name;
    StakeHolder CountryOfOrigin;
    StakeHolder stakeHolderOwner;
    public Artefact() {}
    public Artefact(String id, String name, StakeHolder countryOfOrigin, StakeHolder owner) {
        this.Id = id;
        this.Name = name;
        this.CountryOfOrigin = countryOfOrigin;
        this.stakeHolderOwner = owner;
    }

    public String getId (){
        return this.Id;
    }

    public String getName() {
        return this.Name;
    }

    public StakeHolder getCountry (){
        return this.CountryOfOrigin;
    }

    public StakeHolder getOwner() {
        return this.stakeHolderOwner;
    }

    public void setId (String id) {
        this.Id = id;
    }

    public void setName (String name) {
        this.Name = name;
    }

    public void setCountry (StakeHolder country) {
        this.CountryOfOrigin = country;
    }

    public void setOwner (StakeHolder owner) {
        this.stakeHolderOwner = owner;
    }

    public String toString (){
        return Id+ "," + Name + ","+CountryOfOrigin.toString() +","+stakeHolderOwner.toString();
    }


    //Need setters and getters for each
}
