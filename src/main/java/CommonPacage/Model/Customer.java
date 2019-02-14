package CommonPacage.Model;

import java.io.Serializable;

/**
 * This class stores data of customers
 */
public class Customer implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String address;

    public Customer(int id, String name, String phone, String address){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Writes the value of id
     * @param id customer's id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Writes the value of name
     * @param name customer's name
     */
    public synchronized void setName(String name) {
        this.name = name;
    }

    /**
     * Writes the value of phone
     * @param phone customer's phone
     */
    public synchronized void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Writes the value of address
     * @param address customer's address
     */
    public synchronized void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get value of customer's id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Get value of customer's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get value of customer's phone
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Get value of customer's address
     * @return address
     */
    public String getAddress() {
        return address;
    }
}
