package projectElectronicsStore;

import java.text.DecimalFormat;

/**
 * Product class creates a Product object
 * @author Earl Lontok
 */
public class Product implements Comparable<Product>{

    private String name;
    private String brand;
    private double price;
    private double cost;
    private int stock;

    private DecimalFormat dfPrice = new DecimalFormat("#,###.00");

    /**
     * Constructor for the Product object
     * @param name Product name
     * @param brand Product brand
     * @param price Product price
     * @param stock Product stock
     */
    public Product(String name, String brand, double price, double cost, int stock){

        this.name = name;
        this.brand = brand;
        this.price = price;
        this.cost = cost;
        this.stock = stock;

    }

    /**
     * Converts the Product object into a String with format (name, brand, price, stock)
     * @return the Product object as a string
     */
    @Override
    public String toString(){

        return "Product: " + this.name + "\n"
                + "Brand: " + this.brand + "\n"
                + "Price: $" + dfPrice.format(this.price) + "\n"
                + "Cost: $" + dfPrice.format(this.cost) + "\n"
                + "In Stock: " + this.stock;

    }

    /**
     * Checks if this Product is equal to input Product
     * @param obj input Product
     * @return true if the two Products are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){

        if(obj instanceof Product){
            Product product = (Product)obj;
            if(this.name.toLowerCase().equals(product.name.toLowerCase())
                    && this.brand.toLowerCase().equals(product.brand.toLowerCase())
                    && this.price == product.price
                    && this.cost == product.cost){
                return true;
            }
        }
        return false;

    }

    /**
     * Ensures that the same hash code value is returned for every Product (forces the HashMap to use Product.equals())
     * Each product will be hashed to the same "bucket"
     * Product.equals() is then used to traverse the linked list in that bucket
     * @return a constant (1) hash code value
     */
    @Override
    public int hashCode(){

        return 1;

    }

    /**
     * Compares this Product to input Product - checks which Product's name comes first alphabetically
     * @param product input Product
     * @return -1 if this Product's name comes first alphabetically, 0 if same name, 1 if this Product's name comes later alphabetically
     */
    @Override
    public int compareTo(Product product){

        if(this.name.toLowerCase().compareTo(product.name.toLowerCase()) < 0){
            return -1;
        }
        if(this.name.toLowerCase().compareTo(product.name.toLowerCase()) == 0){
            return 0;
        }
        return 1;

    }

    /**
     * Gets the Product's name
     * @return the Product's name
     */
    public String getName(){

        return this.name;

    }

    /**
     * Gets the Product's brand
     * @return the Product's brand
     */
    public String getBrand(){

        return this.brand;

    }

    /**
     * Gets the Product's price
     * @return the Product's price
     */
    public double getPrice(){

        return this.price;

    }

    /**
     * Gets the Product's cost
     * @return the Product's cost
     */
    public double getCost(){

        return this.cost;

    }

    /**
     * Gets the Product's stock
     * @return the Product's price
     */
    public int getStock(){

        return this.stock;

    }

    /**
     * Sets the Product's stock
     * @param stock the new stock of the Product
     */
    public void setStock(int stock){

        this.stock = stock;

    }

}
