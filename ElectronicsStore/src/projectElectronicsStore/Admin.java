package projectElectronicsStore;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

import java.util.NoSuchElementException;

/**
 * Admin class serves as the user interface class
 * @author Earl Lontok
 */
public class Admin{

    private Roster roster = new Roster();
    private Electronics electronics = new Electronics();
    private Sales sales = new Sales();
    private Scanner input;
    private StringTokenizer line;
    DecimalFormat df = new DecimalFormat("#,###.00");

    /**
     * Runs the project - continually prompts the user for input until the user logs out
     */
    public void run(){

        System.out.print("Password: ");
        Scanner scanPW = new Scanner(System.in);
        String password = scanPW.nextLine();
        while(!password.equals(Constant.PASSWORD)){
            if(password.equals("FORGOT PASSWORD")){
                forgotPassword();
                System.out.print("Password: ");
            }else{
                System.out.println("Authentication failed. Try again.");
                System.out.println("Enter \"FORGOT PASSWORD\" if you forgot your password.");
                System.out.print("Password: ");
            }
            password = scanPW.nextLine();
        }
        System.out.println("Authentication Successful: Welcome!");
        System.out.println("Admin is running. Enter \"HELP\" to view the list of commands.");

        input = new Scanner(System.in);
        while(true){
            String lineCommand = input.nextLine();
            line = new StringTokenizer(lineCommand, " ");
            if(line.hasMoreTokens()){
                String action = line.nextToken();

                //* * * * * * * * HELP COMMANDS * * * * * * * *//

                if(action.equals("HELP")){ //prints the list of available commands and the appropriate inputs
                    helpCommands();

                //* * * * * * * * ROSTER COMMANDS * * * * * * * *//

                }else if(action.equals("A")){ //adds (hires) an Employee to the Roster
                    add();
                }else if(action.equals("R")){ //removes (terminates) an Employee from the Roster
                    remove();
                }else if(action.equals("PP")){ //displays all Employees sorted by Profile
                    roster.printByProfile();
                }else if(action.equals("PD")){ //displays all Employees sorted by Department
                    roster.printByDepartment();
                }else if(action.equals("POD")){ //displays all Employees in a given Department
                    printOneDepartment();
                }else if(action.equals("PSA")){ //displays all Employees sorted by Salary (ascending)
                    roster.printBySalaryAscending();
                }else if(action.equals("PSD")) { //displays all Employees sorted by Salary (descending)
                    roster.printBySalaryDescending();
                }else if(action.equals("TS")){ //displays the total amount spent on Employees (salaries)
                    sumTotalSalaries();

                //* * * * * * * * ELECTRONICS COMMANDS * * * * * * * *//

                }else if(action.equals("AP")){ //adds (or updates) a Product to the Stock
                    addProduct();
                }else if(action.equals("RP")){ //removes a Product from the Stock
                    removeProduct();
                }else if(action.equals("PEN")){ //displays all Products sorted by Name
                    electronics.printByName();
                }else if(action.equals("PEB")){ //displays all Products sorted by Brand
                    electronics.printByBrand();
                }else if(action.equals("PEOB")){ //displays all Products in a given Brand sorted by Name
                    printElectronicsOneBrand();
                }else if(action.equals("PEPA")){ //displays all Products sorted by Price (ascending)
                    electronics.printByPriceAscending();
                }else if(action.equals("PEPD")){ //displays all Products sorted by Price (descending)
                    electronics.printByPriceDescending();
                }else if(action.equals("TE")){ //displays the total amount spent on Electronics (equals to total cost)
                    sumTotalElectronicsCost();

                //* * * * * * * * SALES COMMANDS * * * * * * * *//

                }else if(action.equals("SP")){ //adds (or updates) a Product to Sales
                    sellProduct();
                }else if(action.equals("REP")){ //removes (or updates) a Product from Sales
                    refundProduct();
                }else if(action.equals("PS")){ //displays all Products sold and their amount
                    printSales();

                //* * * * * * * * BUSINESS SUMMARY COMMANDS * * * * * * * *//

                }else if(action.equals("CGS")){
                    calculateGrossSales();
                }else if(action.equals("CP")){
                    calculateProfit();

                    //* * * * * * * * LOGOUT/INVALID COMMANDS * * * * * * * *//

                }else if(action.equals("LO")){ //logs out of Admin
                    System.out.println("Successfully Logged Out.");
                    System.exit(0);
                }else{ //command is invalid
                    System.out.println(action + " is not a valid action");
                }
            }
        }

    }

    /**
     * Reveals the password to the user
     */
    private void forgotPassword(){

        System.out.println("Your password is: " + Constant.PASSWORD);

    }

    /**
     * Prints the list of available commands and the appropriate inputs
     */
    private void helpCommands(){

        //* * * * * * * * HELP COMMANDS * * * * * * * *//

        System.out.println("* * * * * * * * Help Commands * * * * * * * *");
        System.out.println("Format: The first letter(s) is the command followed by the appropriate input");
        System.out.println(Constant.SEPARATOR);

        //* * * * * * * * ROSTER COMMANDS * * * * * * * *//

        System.out.println("Add - adds (hires) an Employee to the Roster");
        System.out.println("A firstName lastName dob ID department salary full-time/part-time");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Remove - removes (terminates) an Employee from the Roster");
        System.out.println("R firstName lastName dob ID");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Print By Profile - displays all Employees sorted by Profile");
        System.out.println("PP");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Print By Department - displays all Employees sorted by Department");
        System.out.println("PD");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Print One Department - displays all Employees in a given Department");
        System.out.println("POD department");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Print Salary Ascending - displays all Employees sorted by Salary (ascending)");
        System.out.println("PSA");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Print Salary Descending - displays all Employees sorted by Salary (descending)");
        System.out.println("PSD");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Total Salaries - displays the total amount spent on Employees (salaries)");
        System.out.println("TS");
        System.out.println(Constant.SEPARATOR);

        //* * * * * * * * ELECTRONICS COMMANDS * * * * * * * *//

        System.out.println("Add Product - adds a Product to the Stock");
        System.out.println("AP name brand price cost stock");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Remove Product - removes a product from the Stock");
        System.out.println("RP name brand price cost");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Print Electronics By Name - displays all Products sorted by Name");
        System.out.println("PEN");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Print Electronics By Brand - displays all Products sorted by Brand");
        System.out.println("PEB");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Print Electronics By One Brand - displays all Products in a given Brand sorted by Name");
        System.out.println("PEOB brand");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Print Electronics By Price Ascending - displays all Products sorted by Price (ascending)");
        System.out.println("PEPA");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Print Electronics By Price Descending - displays all Products sorted by Price (descending)");
        System.out.println("PEPD");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Total Electronics - displays the total amount spent on Electronics (equals to total cost)");
        System.out.println("TE");
        System.out.println(Constant.SEPARATOR);

        //* * * * * * * * SALES COMMANDS * * * * * * * *//

        System.out.println("Sell Product - adds (or updates) a Product to Sales");
        System.out.println("SP name brand price cost itemsSold");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Refund Product - removes (or updates) a Product from Sales");
        System.out.println("REP name brand price cost itemsSold");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Print Sales - displays all Products sold and their amount");
        System.out.println("PS");
        System.out.println(Constant.SEPARATOR);

        //* * * * * * * * BUSINESS SUMMARY COMMANDS * * * * * * * *//

        System.out.println("Calculate Gross Sales - calculates and displays gross Sales");
        System.out.println("CGS");
        System.out.println(Constant.SEPARATOR);
        System.out.println("Calculate Profit - calculates and displays profit");
        System.out.println("CP");
        System.out.println(Constant.SEPARATOR);

        //* * * * * * * * LOGOUT/INVALID COMMANDS * * * * * * * *//

        System.out.println("Logout - logs out of Admin");
        System.out.println("LO");
        System.out.println(Constant.SEPARATOR);
        System.out.println("* * * * * * * * End of Help Commands * * * * * * * *");

    }

    //* * * * * * * * ROSTER * * * * * * * *//

    /**
     * A helper method that checks if a given ID contains 5 integers
     * @param ID the ID to be checked
     * @return true if the ID is valid, false otherwise
     */
    private boolean isValidID(String ID){

        if(ID.length() != Constant.REQ_ID_LENGTH){
            System.out.println("Error: " + ID + " does not contain 5 integers");
            return false;
        }
        for(int i = 0; i < ID.length(); i++){
            char c = ID.charAt(i);
            if(!Character.isDigit(c)){
                System.out.println("Error: " + ID + " does not contain 5 integers");
                return false;
            }
        }
        return true;

    }

    /**
     * A helper method that checks if a given department is one of the 6 departments (IT, OPERATIONS, ACCOUNTING, FINANCE, SALES, MARKETING)
     * @param department the department to be checked
     * @return true if the department is one of the 6 departments, false otherwise
     */
    private boolean isValidDepartment(String department){

        department = department.toUpperCase();
        if(department.equals("IT") || department.equals("OPERATIONS")
                || department.equals("ACCOUNTING") || department.equals("FINANCE")
                || department.equals("SALES") || department.equals("MARKETING")){
            return true;
        }
        System.out.println("Error: " + department + " is not a valid department");
        return false;

    }

    /**
     * A helper method that checks if a given salary is valid for a part-time employee (at least 15600 annual salary)
     * @param salary the salary to be checked
     * @return true if the salary is valid, false otherwise
     */
    private boolean isValidSalaryPart(String salary){

        try{
            double sal = Double.parseDouble(salary);
            if(sal < Constant.MIN_WAGE_PARTTIME){
                System.out.println("Error: part-time employees must earn at least $15,600.00 annually");
                return false;
            }
        }catch(NumberFormatException e){
            System.out.println("Error: " + salary + " is not a double");
            return false;
        }
        return true;

    }

    /**
     * A helper method that checks if a given salary is valid for a full-time employee (at least 31200 annual salary)
     * @param salary the salary to be checked
     * @return true if the salary is valid, false otherwise
     */
    private boolean isValidSalaryFull(String salary){

        try{
            double sal = Double.parseDouble(salary);
            if(sal < Constant.MIN_WAGE_FULLTIME){
                System.out.println("Error: full-time employees must earn at least $31,200.00 annually");
                return false;
            }
        }catch(NumberFormatException e){
            System.out.println("Error: " + salary + " is not a double");
            return false;
        }
        return true;

    }

    /**
     * A helper method that checks if the given isFull string is either "true" or "false"
     * @param isFull the isFull string to be checked
     * @return true if the isFull string is either "true" or "false", false otherwise
     */
    private boolean isValidIsFull(String isFull){

        isFull = isFull.toLowerCase();
        if(isFull.equals("true") || isFull.equals("false")){
            return true;
        }
        System.out.println("Error: " + isFull + " is not a boolean value");
        return false;

    }

    /**
     * A helper method that checks if a given Employee is valid with respect to the given parameters
     * @param dob the date of birth to be checked
     * @param ID the ID to be checked
     * @param department the department to be checked
     * @param salary the salary to be checked
     * @param isFull the isFull string to be checked
     * @return true if all parameters are valid, false otherwise
     */
    private boolean isValidEmployee(Date dob, String ID, String department, String salary, String isFull){ //checks if an employee is valid

        if(!dob.isValid()){
            System.out.println("Error: " + dob + " is an invalid date");
            return false;
        }
        if(!isValidID(ID)){
            return false;
        }
        if(!isValidDepartment(department)){
            return false;
        }
        if(!isValidIsFull(isFull)){
            return false;
        }
        boolean iF = Boolean.parseBoolean(isFull);
        if(iF && !isValidSalaryFull(salary)){
            return false;
        }
        if(!iF && !isValidSalaryPart(salary)){
            return false;
        }
        return true;

    }

    /**
     * Adds (hires) an Employee to the Roster
     */
    private void add(){

        try{
            String firstName = line.nextToken();
            String lastName = line.nextToken();
            String date = line.nextToken();
            Date dob = new Date(date);
            Profile profile = new Profile(lastName, firstName, dob);
            try{
                String ID = line.nextToken();
                String department = line.nextToken();
                String salary = line.nextToken();
                String isFull = line.nextToken();
                if(!isValidEmployee(dob, ID, department, salary, isFull)){
                    return;
                }
                Department dep = Department.valueOf(department.toUpperCase());
                double sal = Double.parseDouble(salary);
                boolean iF = Boolean.parseBoolean(isFull.toLowerCase());
                Employee employee = new Employee(profile, ID, dep, sal, iF);
                if(roster.add(employee)){
                    System.out.println("*Success* " + profile + " " + ID + " was hired");
                }else{
                    System.out.println("*Failed* " + profile + " " + ID + " is already an employee");
                }
            }catch(NoSuchElementException e){
                System.out.println("Error: missing ID, department, salary, and/or if full-time/part-time");
            }
        }catch(NoSuchElementException e){
            System.out.println("Error: missing name and/or dob");
        }

    }

    /**
     * Removes (terminates) an Employee from the Roster
     */
    private void remove(){

        try{
            String firstName = line.nextToken();
            String lastName = line.nextToken();
            String date = line.nextToken();
            Date dob = new Date(date);
            Profile profile = new Profile(lastName, firstName, dob);
            try{
                String ID = line.nextToken();
                if(!isValidID(ID)){
                    return;
                }
                Employee employee = new Employee(profile, ID, null, 0, false);
                if(roster.remove(employee)){
                    System.out.println("*Success* " + profile + " was dismissed");
                }else{
                    System.out.println("*Failed* " + profile + " " + ID + " is not an employee");
                }
            }catch(NoSuchElementException e){
                System.out.println("Error: missing employee ID");
            }
        }catch(NoSuchElementException e){
            System.out.println("Error: missing name and/or dob");
        }

    }

    /**
     * Prints the Employees in a given Department - the Employees are sorted by Profile
     */
    private void printOneDepartment(){ //uses Insertion Sort O(n^2)

        if(roster.getSize() == 0){
            System.out.println("The roster is empty");
            return;
        }
        try{
            String department = line.nextToken().toUpperCase();
            if(!isValidDepartment(department)){
                return;
            }
            for(int i = 1; i < roster.getSize(); i++){
                int j = i;
                while(j > 0 && roster.getRoster()[j].compareTo(roster.getRoster()[j - 1]) < 0){
                    Employee temp = roster.getRoster()[j - 1];
                    roster.getRoster()[j - 1] = roster.getRoster()[j];
                    roster.getRoster()[j] = temp;
                    j--;
                }
            }
            System.out.println("* * * * * * * * Employees in " + department + " * * * * * * * *");
            for(int i = 0; i < roster.getSize(); i++){
                if(department.equals(roster.getRoster()[i].getDepartment().getDepartment())){
                    System.out.println(roster.getRoster()[i]);
                    System.out.println(Constant.SEPARATOR);
                }
            }
            System.out.println("* * * * * * * * End of Roster * * * * * * * *");
        }catch(NoSuchElementException e){
            System.out.println("Error: missing specified department");
        }

    }

    /**
     * Sums and displays the total amount spent on Employees (salaries)
     */
    private void sumTotalSalaries(){

        double total = roster.sumTotalSalaries();
        System.out.println("Amount spent on employees (annually): $" + df.format(total));

    }

    //* * * * * * * * ELECTRONICS * * * * * * * *//

    /**
     * A helper method that checks if a given Product is valid with respect the given parameters
     * @param strPrice the price to be checked
     * @param strStock the stock to be checked
     * @return true if all parameters are valid, false otherwise
     */
    private boolean isValidProduct(String strPrice, String strCost, String strStock){

        try{
            double price = Double.parseDouble(strPrice);
            if(price < Constant.MIN_PRICE){
                System.out.println("Error: price must be at least 0.01");
                return false;
            }
            try{
                double cost = Double.parseDouble(strCost);
                if(cost < Constant.MIN_COST){
                    System.out.println("Error: cost must be at least 0.01");
                    return false;
                }
                try{
                    int stock = Integer.parseInt(strStock);
                    if(stock < Constant.MIN_STOCK){
                        System.out.println("Error: stock must be at least 1");
                        return false;
                    }
                }catch(NumberFormatException e){
                    System.out.println("Error: the stock is not an integer");
                    return false;
                }
            }catch(NumberFormatException e){
                System.out.println("Error: the cost is not a double");
                return false;
            }
        }catch(NumberFormatException e){
            System.out.println("Error: the price is not a double");
            return false;
        }
        return true;

    }

    /**
     * Adds (or updates) a Product to the stock of electronics
     */
    public void addProduct(){

        try{
            String name = line.nextToken();
            String brand = line.nextToken();
            String strPrice = line.nextToken();
            String strCost = line.nextToken();
            String strStock = line.nextToken();
            if (!isValidProduct(strPrice, strCost, strStock)){
                return;
            }
            double price = Double.parseDouble(strPrice);
            double cost = Double.parseDouble(strCost);
            int stock = Integer.parseInt(strStock);
            Product product = new Product(name, brand.toUpperCase(), price, cost, stock);
            electronics.add(product);
        }catch(NoSuchElementException e){
            System.out.println("Error: missing name, brand, price, cost, or stock");
        }

    }

    /**
     * Removes a Product from the stock of electronics
     */
    public void removeProduct(){

        try{
            String name = line.nextToken();
            String brand = line.nextToken();
            String strPrice = line.nextToken();
            String strCost = line.nextToken();
            String tempStock = "1";
            if(!isValidProduct(strPrice, strCost, tempStock)){
                return;
            }
            double price = Double.parseDouble(strPrice);
            double cost = Double.parseDouble(strCost);
            int stock = Integer.parseInt(tempStock);
            Product product = new Product(name, brand.toUpperCase(), price, cost, stock);
            if(electronics.remove(product)){
                System.out.println("*Success* " + name + " " + brand.toUpperCase() + " was removed");
            }else{
                System.out.println("*Failed* " + name + " " + brand.toUpperCase() + " is not in stock");
            }
        }catch(NoSuchElementException e){
            System.out.println("Error: missing name, brand, price, or cost");
        }

    }

    /**
     * Prints the Products of a given Brand - the Products are sorted by name
     */
    private void printElectronicsOneBrand(){

        if(electronics.getSize() == 0){
            System.out.println("There are no electronics.");
            return;
        }
        try{
            String brand = line.nextToken();
            brand = brand.toUpperCase();
            for(int i = 1; i < electronics.getSize(); i++){
                int j = i;
                while(j > 0 && electronics.getElectronics()[j].compareTo(electronics.getElectronics()[j - 1]) < 0){
                    Product temp = electronics.getElectronics()[j - 1];
                    electronics.getElectronics()[j - 1] = electronics.getElectronics()[j];
                    electronics.getElectronics()[j] = temp;
                    j--;
                }
            }
            System.out.println("* * * * * * * * " + brand + " Products * * * * * * * *");
            for(int i = 0; i < electronics.getSize(); i++){
                if(electronics.getElectronics()[i].getBrand().equals(brand)){
                    System.out.println(electronics.getElectronics()[i]);
                    System.out.println(Constant.SEPARATOR);
                }
            }
            System.out.println("* * * * * * * * End of Electronics * * * * * * * *");
        }catch(NoSuchElementException e){
            System.out.println("Error: missing specified brand");
        }

    }

    /**
     * Sums and displays the total amount spent on Products (cost)
     */
    private void sumTotalElectronicsCost(){

        double total = electronics.sumTotalElectronicsCost();
        System.out.println("Amount spent on electronics: $" + df.format(total));

    }

    //* * * * * * * * SALES * * * * * * * *//

    /**
     * A helper method that searches for a Product in Electronics
     * @param product the Product of interest
     * @return the index of the Product of interest in the Electronics array
     */
    private int findInElectronics(Product product){

        for(int i = 0; i < electronics.getSize(); i++){
            if(electronics.getElectronics()[i].equals(product)){
                return i;
            }
        }
        return Constant.NOT_FOUND;

    }

    /**
     * A helper method that checks if a Product is in Electronics
     * @param product the Product of interest
     * @return true if the Product of interest is in Electronics, false otherwise
     */
    private boolean inElectronics(Product product){

        for(int i = 0; i < electronics.getSize(); i++){
            if(electronics.getElectronics()[i].equals(product)){
                return true;
            }
        }
        return false;

    }

    /***
     * Adds (or updates) a Product to sales (sells a Product)
     */
    private void sellProduct(){

        try{
            String name = line.nextToken();
            String brand = line.nextToken();
            String strPrice = line.nextToken();
            String strCost = line.nextToken();
            String tempStock = "1";
            if(!isValidProduct(strPrice, strCost, tempStock)){
                return;
            }
            double price = Double.parseDouble(strPrice);
            double cost = Double.parseDouble(strCost);
            int stock = Integer.parseInt(tempStock);
            Product product = new Product(name, brand.toUpperCase(), price, cost, stock);
            if(!inElectronics(product)){
                System.out.println("*Failed* " + product.getName() + " " + product.getBrand() + " is not in stock");
                return;
            }
            try{
                String strItemsSold = line.nextToken();
                try{
                    int itemsSold = Integer.parseInt(strItemsSold);
                    int index = findInElectronics(product);
                    if(itemsSold < Constant.MIN_STOCK || itemsSold > electronics.getElectronics()[index].getStock()){
                        System.out.println("*Failed* " + name + " " + brand + " does not have " + itemsSold + " items in stock");
                        return;
                    }
                    sales.add(product, itemsSold);
                    int currStock = electronics.getElectronics()[index].getStock();
                    if(currStock - itemsSold == 0){
                        Electronics.totalCost += electronics.getElectronics()[index].getCost() * electronics.getElectronics()[index].getStock(); //ensures that selling a product does not affect amount spent on Electronics
                        electronics.remove(product);
                    }else {
                        electronics.getElectronics()[index].setStock(currStock - itemsSold);
                    }
                }catch(NumberFormatException e){
                    System.out.println("Error: items sold is not an integer");
                }
            }catch(NoSuchElementException e){
                System.out.println("Error: missing items sold");
            }
        }catch(NoSuchElementException e){
            System.out.println("Error: missing name, brand, price, or cost");
        }

    }

    /**
     * Removes (or updates) a Product from Sales (refunds a Product)
     */
    private void refundProduct(){

        try{
            String name = line.nextToken();
            String brand = line.nextToken();
            String strPrice = line.nextToken();
            String strCost = line.nextToken();
            String tempStock = "1";
            if(!isValidProduct(strPrice, strCost, tempStock)){
                return;
            }
            double price = Double.parseDouble(strPrice);
            double cost = Double.parseDouble(strCost);
            int stock = Integer.parseInt(tempStock);
            try{
                String strItemsSold = line.nextToken();
                try{
                    int itemsSold = Integer.parseInt(strItemsSold);
                    Product product = new Product(name, brand.toUpperCase(), price, cost, stock);
                    if(sales.remove(product, itemsSold)){
                        product = new Product(name, brand.toUpperCase(), price, cost, itemsSold); //refunds a Product (the Product is added back to Electronics)
                        Electronics.totalCost -= product.getCost() * product.getStock(); //ensures that refunding a product does not affect amount spent on Electronics
                        electronics.add(product);
                    }
                }catch(NumberFormatException e){
                    System.out.println("Error: items sold is not an integer");
                }
            }catch(NoSuchElementException e){
                System.out.println("Error: missing items sold");
            }
        }catch(NoSuchElementException e){
            System.out.println("Error: missing name, brand, price, or cost");
        }

    }

    /**
     * Prints all Products sold
     */
    public void printSales(){

        if(sales.getSales().size() == 0){
            System.out.println("There are no sales");
            return;
        }
        System.out.println("* * * * * * * * Sales * * * * * * * *");
        for(Product p: sales.getSales().keySet()){
            int itemsSold = sales.getSales().get(p);
            double total = p.getPrice() * itemsSold;
            System.out.println("Product: " + p.getName());
            System.out.println("Brand: " + p.getBrand());
            System.out.println("Price: $" + p.getPrice());
            System.out.println("Items Sold: " + itemsSold);
            System.out.println("Total: $" + df.format(total));
            System.out.println(Constant.SEPARATOR);
        }
        System.out.println("* * * * * * * * End of Sales * * * * * * * *");

    }

    //* * * * * * * * BUSINESS SUMMARY * * * * * * * *//

    /**
     * Calculates and displays gross Sales
     */
    public void calculateGrossSales(){

        double grossSales = 0;
        for(Product p: sales.getSales().keySet()){
            int itemsSold = sales.getSales().get(p);
            double total = p.getPrice() * itemsSold;
            grossSales += total;
        }
        System.out.println("Gross Sales: $" + df.format(grossSales));

    }

    /**
     * Calculates and displays profit
     */
    public void calculateProfit(){

        double profit = 0;
        double grossSales = 0;
        double totalCost = 0;
        for(Product p: sales.getSales().keySet()){
            int itemsSold = sales.getSales().get(p);
            double total = p.getPrice() * itemsSold;
            grossSales += total;
        }
        totalCost = electronics.sumTotalElectronicsCost();
        profit = grossSales - totalCost;
        System.out.println("Total Profit: $" + df.format(profit));

    }

}
