package projectElectronicsStore;

import java.text.DecimalFormat;

/**
 * Roster class creates an array of Employee objects called roster
 * @author Earl Lontok
 */
public class Roster{

    private Employee[] roster;
    private int size;
    private DecimalFormat dfTotal = new DecimalFormat("#,###.00");

    /**
     * Default constructor for the Roster object - initializes an array of Employees with an initial capacity of 10
     */
    public Roster(){

        this.roster = new Employee[10];
        this.size = 0;

    }

    /**
     * A helper method that searches for an Employee in the Roster
     * @param employee the Employee of interest
     * @return the index of the Employee of interest in the Roster array - returns -1 if not found
     */
    private int find(Employee employee){

        for(int i = 0; i < size; i++){
            if(roster[i].equals(employee)){
                return i;
            }
        }
        return Constant.NOT_FOUND;

    }

    /**
     * Increases the Roster array's capacity by 10
     */
    private void grow(){

        Employee[] newRoster = new Employee[roster.length + 10];
        for(int i = 0; i < size; i++){
            newRoster[i] = roster[i];
        }
        roster = newRoster;

    }

    /**
     * Checks if an Employee is in the Roster
     * @param employee the Employee of interest
     * @return true of the Employee is in the Roster, false otherwise
     */
    public boolean contains(Employee employee){

        if(find(employee) == Constant.NOT_FOUND){
            return false;
        }
        return true;

    }

    /**
     * Adds an Employee to the end of the Roster
     * @param employee the Employee that will be added to the Roster
     * @return true if the Employee was successfully added to the Roster, false otherwise
     */
    public boolean add(Employee employee){

        if(contains(employee)){
            return false;
        }
        if(size == roster.length){
            grow();
        }
        roster[size] = employee;
        size++;
        return true;

    }

    /**
     * Removes an Employee from the Roster (order is maintained)
     * @param employee the Employee that will be removed from the Roster
     * @return true if the Employee was successfully removed from the Roster, false otherwise
     */
    public boolean remove(Employee employee){

        int removeIndex = find(employee);
        if(removeIndex == Constant.NOT_FOUND){
            return false;
        }
        for(int i = removeIndex; i < size - 1; i++){
            roster[i] = roster[i + 1];
        }
        roster[size - 1] = null;
        size--;
        return true;

    }

    /**
     * Prints the Roster array sorted by Profile
     */
    public void printByProfile(){ //uses Insertion Sort O(n^2)

        if(size == 0){
            System.out.println("The Roster is empty.");
            return;
        }
        System.out.println("* * * * * * * * Roster Sorted By Profile * * * * * * * *");
        for(int i = 0; i < size; i++){ //sorts by Profile
            int j = i;
            while(j > 0 && roster[j].compareTo(roster[j - 1]) < 0){
                Employee temp = roster[j - 1];
                roster[j - 1] = roster[j];
                roster[j] = temp;
                j--;
            }
        }
        for(int i = 0; i < size; i++){
            System.out.println(roster[i]);
            System.out.println(Constant.SEPARATOR);
        }
        System.out.println("* * * * * * * * End of Roster * * * * * * * *");

    }

    /**
     * Prints the Roster array sorted by Department - Employees within each Department are sorted by Profile
     */
    public void printByDepartment(){ //uses Insertion Sort O(n^2)

        if(size == 0){
            System.out.println("The Roster is empty.");
            return;
        }
        System.out.println("* * * * * * * * Roster Sorted By Department * * * * * * * *");
        for(int i = 0; i < size; i++){ //sorts by Department
            int j = i;
            while(j > 0 && roster[j].getDepartment().getDepartment().compareTo(roster[j - 1].getDepartment().getDepartment()) < 0){
                Employee temp = roster[j - 1];
                roster[j - 1] = roster[j];
                roster[j] = temp;
                j--;
            }
        }
        for(int i = 0; i < size; i++){ //sorts by Profile within each Department
            int j = i;
            while(j > 0 && roster[j].getDepartment().getDepartment().equals(roster[j - 1].getDepartment().getDepartment())
                    && roster[j].compareTo(roster[j - 1]) < 0){
                Employee temp = roster[j - 1];
                roster[j - 1] = roster[j];
                roster[j] = temp;
                j--;
            }

        }
        for(int i = 0; i < size; i++){
            System.out.println(roster[i]);
            System.out.println(Constant.SEPARATOR);
        }
        System.out.println("* * * * * * * * End of Roster * * * * * * * *");

    }

    /**
     * Prints the Roster array sorted by salary (least to greatest)
     */
    public void printBySalaryAscending(){ //uses Insertion Sort O(n^2)

        if(size == 0){
            System.out.println("The Roster is empty.");
            return;
        }
        System.out.println("* * * * * * * * Roster Sorted By Salary (Ascending) * * * * * * * *");
        for(int i = 0; i < size; i++){
            int j = i;
            while(j > 0 && roster[j].getSalary() < roster[j - 1].getSalary()){
                Employee temp = roster[j - 1];
                roster[j - 1] = roster[j];
                roster[j] = temp;
                j--;
            }
        }
        for(int i = 0; i < size; i++){
            System.out.println(roster[i]);
            System.out.println(Constant.SEPARATOR);
        }
        System.out.println("* * * * * * * * End of Roster * * * * * * * *");

    }

    /**
     * Prints the Roster array sorted by salary (greatest to least)
     */
    public void printBySalaryDescending(){ //uses Insertion Sort O(n^2)

        if(size == 0){
            System.out.println("The Roster is empty");
            return;
        }
        System.out.println("* * * * * * * * Roster Sorted By Salary (Descending) * * * * * * * *");
        for(int i = 0; i < size; i++){
            int j = i;
            while(j > 0 && roster[j].getSalary() > roster[j - 1].getSalary()){
                Employee temp = roster[j - 1];
                roster[j - 1] = roster[j];
                roster[j] = temp;
                j--;
            }
        }
        for(int i = 0; i < size; i++){
            System.out.println(roster[i]);
            System.out.println(Constant.SEPARATOR);
        }
        System.out.println("* * * * * * * * End of Roster * * * * * * * *");

    }

    /**
     * Sums the salaries of all Employees
     * @return the sum of the salaries of all Employees
     */
    public double sumTotalSalaries(){

        double total = 0;
        for(int i = 0; i < size; i++){
            total += roster[i].getSalary();
        }
        return total;

    }

    /**
     * Gets the Roster array
     * @return the Roster array
     */
    public Employee[] getRoster(){

        return this.roster;

    }

    /**
     * Gets the number of Employees in the Roster
     * @return the number of Employees in the Roster
     */
    public int getSize(){

        return this.size;

    }

}
