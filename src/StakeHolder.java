package src;

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

    public String getId() {
        return this.Id;
    }

    public String getName (){
        return this.Name;
    }

    public String getAddress() {
        return this.Address;
    }

    public Double getBalance (){
        return this.Balance;
    }

    public void setId (String id) {
        this.Id = id;
    }

    public void setName (String name) {
        this.Name = name;
    }

    public void setAddress (String address) {
        this.Address = address;
    }

    public void setBalance (Double balance) {
        this.Balance = balance;
    }

    public String toString (){
        return Id+ ","+Name+ ","+Address+ ","+Balance;
    }

    // Need Setter and Getters for each

}
