package projectElectronicsStore;

/**
 * An enum class that is used to describe a Department
 * Each Department has a department, department code, and location
 */
public enum Department{

    IT("IT", 314, "123 Infote St. New Brunswick, NJ"),
    OPERATIONS("OPERATIONS", 159, "456 Opera St. Los Angeles, CA"),
    ACCOUNTING("ACCOUNTING", 265, "789 Accoun St. Seattle, WA"),
    FINANCE("FINANCE", 358, "101 Finan St. Houston, TX"),
    SALES("SALES", 979, "112 Sal St. New York, NY"),
    MARKETING("MARKETING", 323, "131 Marke St. Bar Harbor, ME");

    private final String department;
    private final int depCode;
    private final String location;

    /**
     * Contructor for the Department enum
     * @param department Department's department
     * @param depCode Department's department code
     * @param location Department's location
     */
    Department(String department, int depCode, String location){

        this.department = department;
        this.depCode = depCode;
        this.location = location;

    }

    /**
     * Converts the Department enum into a string with format (department | department code | location)
     * @return the Department enum as a string
     */
    @Override
    public String toString(){

        return department + " | " + depCode + " | " + location;

    }

    /**
     * Gets the Department's department
     * @return the Department's department
     */
    public String getDepartment(){

        return this.department;

    }

}
