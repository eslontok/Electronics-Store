package projectElectronicsStore;

import java.text.DecimalFormat;

/**
 * Employee class creates an Employee object
 * @author Earl Lontok
 */
public class Employee implements Comparable<Employee>{

    private Profile profile;
    private String ID;
    private Department department;
    private double salary;
    private boolean isFull;

    private DecimalFormat dfSalary = new DecimalFormat("#,###.00");

    /**
     * Constructor for the Employee object
     * @param profile Employee profile
     * @param ID Employee ID
     * @param department Employee department
     * @param salary Employee salary
     * @param isFull Employee type of employment
     */
    public Employee(Profile profile, String ID, Department department, double salary, boolean isFull){

        this.profile = profile;
        this.ID = ID;
        this.department = department;
        this.salary = salary;
        this.isFull = isFull;

    }

    /**
     * Converts the Employee object into a String with the format (profile, ID, department, salary, full/part time)
     * @return the Employee object as a String
     */
    @Override
    public String toString(){

        return "Profile: " + this.profile + "\n" + "Employee ID: " + this.ID + "\n" + "Department: "
                + this.department + "\n" + "Annual Salary: $" + dfSalary.format(this.salary) + "\n" + "Full Time: " + this.isFull;

    }

    /**
     * Checks if this Employee is equal to input Employee
     * @param obj input Employee
     * @return true if the two Employees are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){

        if(obj instanceof Employee){
            Employee employee = (Employee)obj;
            if(this.profile.equals(employee.profile) && this.ID.equals(employee.ID)){
                return true;
            }
        }
        return false;

    }

    /**
     * Compares this Employee to input Employee - checks which Employee's Profile comes first alphabetically (last name, first name)
     * @param employee input Profile
     * @return -1 if this Employee's Profile comes first alphabetically, 0 if same name, 1 if this Employee's Profile comes later alphabetically
     */
    @Override
    public int compareTo(Employee employee){

        return this.profile.compareTo(employee.profile);

    }

    /**
     * Gets the Employee's department
     * @return the Employee's department
     */
    public Department getDepartment(){

        return this.department;

    }

    /**
     * Gets the Employee's salary
     * @return the Employee's salary
     */
    public double getSalary(){

        return this.salary;

    }

    /**
     * Gets the Employee's type of employment (full/part time)
     * @return the Employee's type of employment
     */
    public boolean getIsFull(){

        return this.isFull;

    }

}
