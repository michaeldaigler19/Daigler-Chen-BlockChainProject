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

    public String GetId (){
        return this.Id;
    }

    public String Getname() {
        return this.Name;
    }

    public StakeHolder GetCountry (){
        return this.CountryOfOrigin;
    }

    public StakeHolder GetOwner() {
        return this.stakeHolderOwner;
    }

    public void SetId (String id) {
        this.Id = id;
    }

    public void SetName (String name) {
        this.Name = name;
    }

    public void SetCountry (StakeHolder country) {
        this.CountryOfOrigin = country;
    }

    public void SetOwner (StakeHolder owner) {
        this.stakeHolderOwner = owner;
    }

    public String ToString (){
        return Id+ ","+Name+ ","+CountryOfOrigin +","+stakeHolderOwner.ToString();
    }


    //Need setters and getters for each
}
