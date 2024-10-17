package projectElectronicsStore;

import java.util.HashMap;

/**
 * Sales class creates a HashMap (key: Product, value: Items Sold) called sales
 * @author Earl Lontok
 */
public class Sales {

    private HashMap<Product, Integer> sales;

    /**
     * Default constructor for the Sales object
     */
    public Sales(){

        this.sales = new HashMap<>();

    }

    /**
     * Adds/Updates a Product and items sold
     * @param product the Product that will be added (or updated) to Sales
     * @param itemsSold the number of items sold
     */
    public void add(Product product, int itemsSold){

        if(sales.containsKey(product)){
            int newItemsSold = sales.get(product) + itemsSold;
            sales.put(product, newItemsSold);
            System.out.println("*Success* " + product.getName() + " " + product.getBrand() + " sales updated");
            return;
        }
        sales.put(product, itemsSold);
        System.out.println("*Success* " + product.getName() + " " + product.getBrand() + " added to sales");

    }

    /**
     * Removes/Updates a Product and items sold
     * @param product the Product that will be removed (or updated) from Sales
     * @param itemsSold the number of items removed
     * @return true if the Product was successfully removed, false otherwise
     */
    public boolean remove(Product product, int itemsSold){

        if(!sales.containsKey(product)){
            System.out.println("*Failed* " + product.getName() + " " + product.getBrand() + " is not in sales");
            return false;
        }
        if(itemsSold < 0 || itemsSold > sales.get(product)){
            System.out.println("*Failed* " + product.getName() + " " + product.getBrand() + " was not sold " + itemsSold + " times");
            return false;
        }
        int newItemsSold = sales.get(product) - itemsSold;
        if(newItemsSold == 0){
            sales.remove(product);
            System.out.println("*Success* " + product.getName() + " " + product.getBrand() + " removed from sales");
        }else{
            sales.put(product, newItemsSold);
            System.out.println("*Success* " + product.getName() + " " + product.getBrand() + " sales updated");
        }
        return true;

    }

    /**
     * Gets the Sales HashMap
     * @return the Sales HashMap
     */
    public HashMap<Product, Integer> getSales(){

        return this.sales;

    }

}
