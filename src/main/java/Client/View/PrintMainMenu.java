package Client.View;

public class PrintMainMenu {
    /**
     * This method prints start menu
     */
    public void printMenu(){
        System.out.println
                ("(Write 'help' to get a command-list)\n"+
                        "(Write 'exit' to complete the program)");
    }

    /**
     * This method prints the available client commands
     */
    public void printCustomerCommandList() {
        System.out.println
                ("1) CreateCustomer id=<id>; name=<name>; phone=<phone>; address=<address>; " +
                        "\n\tto create new customer");
        System.out.println
                ("2) DeleteAllCustomers" +
                        "\n\tto delete all customer");
        System.out.println
                ("3) DeleteCustomer id=<id>;" +
                        "\n\tto delete customer by id");
        System.out.println
                ("4) GetAllCustomers" +
                        "\n\tto show all customers");
        System.out.println
                ("5) GetCustomer id=<id>;" +
                        "\n\tto show customer by id");
        System.out.println
                ("6) UpdateCustomer id=<id>; ?name=<name>; ?phone=<phone>; ?address=<address>;" +
                        "\n\tto update customer by id" +
                        "\n\t(? - optional argument)");
        System.out.println
                ("7) SaveCustomers file=<fileName>;" +
                        "\n\tto save customers");
        System.out.println
                ("8) LoadCustomers file=<fileName>; dublicatePolicy=<skip | replace>;" +
                        "\n\tto load customers with:" +
                        "\n\ta) skip dublicates" +
                        "\n\tb) replace dublicates (default argument)");
        System.out.println
                ("9) FindCustomer name=<name>; operation=<equals | contains | startWith | like>;" +
                        "\n\tto find customers by" +
                        "\n\ta) equals 'name' argument" +
                        "\n\tb) contains 'name' argument" +
                        "\n\tc) start with 'name' argument" +
                        "\n\ta) like 'name' argument" +
                        "\n\t\tuse '?' for one unknown character" +
                        "\n\t\tuse '*' for several unknown characters" +
                        "\n\t\t(Example: ?a  a?  a?a  *a  a*  a*a  ?a*  *a?)");
    }
}
