public class StakeHolder {
    String Id;
    String Name;
    String Address;
    Double Balance;

    public StakeHolder(String id, String name, String address, Double balance) {
        this.Id = id;
        this.Name = name;
        this.Address = address;
        this.Balance = balance;
    }

    public String GetId() {
        return this.Id;
    }

    public String GetName (){
        return this.Name;
    }

    public String GetAddress() {
        return this.Address;
    }

    public Double GetBalance (){
        return this.Balance;
    }

    public void SetId (String id) {
        this.Id = id;
    }

    public void SetName (String name) {
        this.Name = name;
    }

    public void SetAddress (String address) {
        this.Address = address;
    }

    public void SetBalance (Double balance) {
        this.Balance = balance;
    }

    public String ToString (){
        return Id+ ","+Name+ ","+Address+ ","+Balance;
    }

    // Need Setter and Getters for each

}
