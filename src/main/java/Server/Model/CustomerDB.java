package Server.Model;

import CommonPacage.Controller.Exceptions.CustomerNotFoundException;
import CommonPacage.Controller.Exceptions.ExistCustomerException;
import CommonPacage.Model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is database(DB) of all Customers
 */
public class CustomerDB {
    /**
     * @see Customer
     */
    private List<Customer> customers;

    /**
     * Create new CustomerDB
     */
    public CustomerDB(){
        customers = new ArrayList<Customer>();
    }

    /**
     * Writes all Customers in DB
     * @param customers ArrayList of Customers
     */
    public void setCustomers(ArrayList<Customer> customers){
        this.customers = customers;
    }

    /**
     * Get all customers
     * @return ArrayList<Customer>
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Add new customer in DB
     * @param customer new customer
     * @throws ExistCustomerException
     * @see ExistCustomerException
     */
    public synchronized void addCustomer(Customer customer) throws ExistCustomerException{
        if(getCustomer(customer.getId()) == null) customers.add(customer);
        else throw new ExistCustomerException("with this id = " + customer.getId() + " in CreateCustomer command");
    }

    /**
     * Get customer by his id
     * If customer with this id does not exist, returns null
     * @param id customer's id
     * @return Customer | null
     */
    public Customer getCustomer(int id){
        for (Customer customer : customers){
            if(customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Delete customer from DB by his id
     * @param id customer's id
     * @throws CustomerNotFoundException if customer not found
     * @see CustomerNotFoundException
     */
    public synchronized void deleteCustomer(int id) throws CustomerNotFoundException{
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getId() == id) {
                customers.remove(i);
                return;
            }
        }
        //if customer not found:
        throw new CustomerNotFoundException("id = " + id + " not found in DB");
    }

    /**
     * Delete all customers from DB
     */
    public void deleteAllCustomers(){
        customers.clear();
    }

    /**
     * Get customer's index in DB
     * @param customer whose index you want to get
     * @return index
     */
    public int getIndexOfCustomer(Customer customer){
        return customers.indexOf(customer);
    }

    /**
     * Replace customer with new customer in DB by his id
     * @param newCustomer which you want to add in DB
     */
    public void replaceCustomer(Customer newCustomer){
        int id = newCustomer.getId();
        Customer customer = getCustomer(id);

        int index = getIndexOfCustomer(customer);
        customers.set(index, newCustomer);
    }

    /**
     * Find customer equals by name
     * @param name who do you want to find
     * @param cust ArrayList of found customers
     */
    public void findByEquals(String name, List<Customer> cust){
        for (Customer c : customers){
            if(c.getName().equals(name))
                cust.add(c);
        }
    }

    /**
     * Find customer contains name
     * @param name who do you want to find
     * @param cust ArrayList of found customers
     */
    public void findByContains(String name, List<Customer> cust){
        for (Customer c : customers){
            if(c.getName().contains(name))
                cust.add(c);
        }
    }

    /**
     * Find customer whose name starts with @param name
     * @param name who do you want to find
     * @param cust ArrayList of found customers
     */
    public void findByStartWith(String name, List<Customer> cust){
        for (Customer c : customers){
            if(c.getName().startsWith(name))
                cust.add(c);
        }
    }

    /**
     * Find customers whose name matches with the pattern
     * @param name who do you want to find (may contains '?' and '*' characters)
     * @param cust ArrayList of found customers
     */
    public void findByLike(String name, List<Customer> cust) {
        //create regular expression: replace all ? on . (single character) and all * on .* (many characters)
        name = name.replaceAll("\\?", ".");
        name = name.replaceAll("\\*", ".*");

        for (Customer c : customers) {
            if (c.getName().matches(name))
                cust.add(c);
        }
    }
}
