package projectElectronicsStore;

import java.text.DecimalFormat;

/**
 * Electronics class creates an array of Products called electronics
 * @author Earl Lontok
 */
public class Electronics{

    public static double totalCost = 0;
    private Product[] electronics;
    private int size;
    private DecimalFormat dfTotal = new DecimalFormat("#,###.00");

    /**
     * Default constructor for the Electronics object - initializes an array of Products with an initial capacity of 10
     */
    public Electronics(){

        this.electronics = new Product[10];
        this.size = 0;

    }

    /**
     * A helper method the searches for a Product in Electronics
     * @param product the Product of interest
     * @return the index of the Product of interest in the Electronics array
     */
    private int find(Product product){

        for(int i = 0; i < size; i++){
            if(electronics[i].equals(product)){
                return i;
            }
        }
        return Constant.NOT_FOUND;

    }

    /**
     * Increases the Electronics array's capacity by 10
     */
    private void grow(){

        Product[] newElectronics = new Product[electronics.length + 10];
        for(int i = 0; i < size; i++){
            newElectronics[i] = electronics[i];
        }
        electronics = newElectronics;

    }

    /**
     * Checks if a Product is in Electronics
     * @param product the Product of interest
     * @return true if the Product is in Electronics, false otherwise
     */
    public boolean contains(Product product){

        if(find(product) == Constant.NOT_FOUND){
            return false;
        }
        return true;

    }

    /**
     * Adds a Product to the end of Electronics - if the Product is already in Electronics, the Product's stock is updated
     * @param product the Product that will be added (or updated) to Electronics
     */
    public void add(Product product){

        int index = find(product);
        if(index != Constant.NOT_FOUND){
            int newStock = electronics[index].getStock() + product.getStock();
            electronics[index].setStock(newStock);
            totalCost += product.getCost() * product.getStock();
            System.out.println("*Success* " + product.getName() + " " + product.getBrand().toUpperCase() + " stock updated");
            return;
        }
        if(size == electronics.length){
            grow();
        }
        electronics[size] = product;
        totalCost += product.getCost() * product.getStock();
        System.out.println("*Success* " + product.getName() + " " + product.getBrand().toUpperCase() + " added to the stock");
        size++;

    }

    /**
     * Removes a Product from the Roster (order is maintained)
     * @param product the Product that will be removed from Electronics
     * @return true if the Product was successfully removed, false otherwise
     */
    public boolean remove(Product product){

        int removeIndex = find(product);
        if(removeIndex == Constant.NOT_FOUND){
            return false;
        }
        totalCost -= electronics[removeIndex].getCost() * electronics[removeIndex].getStock();
        for(int i = removeIndex; i < size - 1; i++){
            electronics[i] = electronics[i + 1];
        }
        electronics[size - 1] = null;
        size--;
        return true;

    }

    /**
     * Prints the Electronics array sorted by name
     */
    public void printByName(){ //uses Insertion Sort O(n^2)

        if(size == 0){
            System.out.println("There are no electronics.");
            return;
        }
        System.out.println("* * * * * * * * Electronics Sorted By Name * * * * * * * *");
        for(int i = 1; i < size; i++){ //sorts by Name
            int j = i;
            while(j > 0 && electronics[j].compareTo(electronics[j - 1]) < 0){
                Product temp = electronics[j - 1];
                electronics[j - 1] = electronics[j];
                electronics[j] = temp;
                j--;
            }
        }
        for(int i = 0; i < size; i++){
            System.out.println(electronics[i]);
            System.out.println(Constant.SEPARATOR);
        }
        System.out.println("* * * * * * * * End of Electronics * * * * * * * *");

    }

    /**
     * Prints the Electronics array sorted by brand
     */
    public void printByBrand(){ //uses Insertion Sort O(n^2)

        if(size == 0){
            System.out.println("There are no electronics.");
            return;
        }
        System.out.println("* * * * * * * * Electronics Sorted By Brand * * * * * * * *");
        for(int i = 1; i < size; i++){ //sorts by Brand
            int j = i;
            while(j > 0 && electronics[j].getBrand().toLowerCase().compareTo(electronics[j - 1].getBrand().toLowerCase()) < 0){
                Product temp = electronics[j - 1];
                electronics[j - 1] = electronics[j];
                electronics[j] = temp;
                j--;
            }
        }
        for(int i = 1; i < size; i++){ //sorts by Name within each Brand
            int j = i;
            while(j > 0 && electronics[j].getBrand().toLowerCase().equals(electronics[j - 1].getBrand().toLowerCase())
                    && electronics[j].compareTo(electronics[j - 1]) < 0){
                Product temp = electronics[j - 1];
                electronics[j - 1] = electronics[j];
                electronics[j] = temp;
                j--;
            }
        }
        for(int i = 0; i < size; i++){
            System.out.println(electronics[i]);
            System.out.println(Constant.SEPARATOR);
        }
        System.out.println("* * * * * * * * End of Electronics * * * * * * * *");

    }

    /**
     * Prints the Electronics array sorted by price (least to greatest)
     */
    public void printByPriceAscending(){ //uses Insertion Sort O(n^2)

        if(size == 0){
            System.out.println("There are no electronics.");
            return;
        }
        System.out.println("* * * * * * * * Electronics Sorted By Price (Ascending) * * * * * * * *");
        for(int i = 1; i < size; i++){
            int j = i;
            while(j > 0 && electronics[j].getPrice() < electronics[j - 1].getPrice()){
                Product temp = electronics[j - 1];
                electronics[j - 1] = electronics[j];
                electronics[j] = temp;
                j--;
            }
        }
        for(int i = 0;i < size; i++){
            System.out.println(electronics[i]);
            System.out.println(Constant.SEPARATOR);
        }
        System.out.println("* * * * * * * * End of Electronics * * * * * * * *");

    }

    /**
     * Prints the Electronics array sorted by price (greatest to least)
     */
    public void printByPriceDescending(){ //uses Insertion Sort O(n^2)

        if(size == 0){
            System.out.println("There are no electronics.");
            return;
        }
        System.out.println("* * * * * * * * Electronics Sorted By Price (Descending) * * * * * * * *");
        for(int i = 1; i < size; i++){
            int j = i;
            while(j > 0 && electronics[j].getPrice() > electronics[j - 1].getPrice()){
                Product temp = electronics[j - 1];
                electronics[j - 1] = electronics[j];
                electronics[j] = temp;
                j--;
            }
        }
        for(int i = 0;i < size; i++){
            System.out.println(electronics[i]);
            System.out.println(Constant.SEPARATOR);
        }
        System.out.println("* * * * * * * * End of Electronics * * * * * * * *");

    }

    /**
     * Sums the prices of all Products (equals to total cost)
     * @return the sum of the prices of all Products
     */
    public double sumTotalElectronicsCost(){

        return totalCost;

    }

    /**
     * Gets the Electronics array
     * @return the Electronics array
     */
    public Product[] getElectronics(){

        return this.electronics;

    }

    /**
     * Gets the number of Products in Electronics
     * @return the number of Products in Electronics
     */
    public int getSize(){

        return this.size;

    }

}
