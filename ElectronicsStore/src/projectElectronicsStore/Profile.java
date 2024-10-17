package projectElectronicsStore;

/**
 * Profile class creates a Profile object
 * @author Earl Lontok
 */
public class Profile implements Comparable<Profile>{

    private String lastName;
    private String firstName;
    private Date dob;

    /**
     * Constructor for the Profile object
     * @param lastName the input last name
     * @param firstName the input first name
     * @param dob the input date of birth
     */
    public Profile(String lastName, String firstName, Date dob){

        this.lastName = lastName;
        this.firstName = firstName;
        this.dob = dob;

    }

    /**
     * Converts the Profile object into a string with the format (lastname firstname dob)
     * @return the Profile object as a string
     */
    @Override
    public String toString(){

        return lastName + ", " + firstName + " " + dob;

    }

    /**
     * Checks if this Profile is equal to input Profile
     * @param obj input Profile
     * @return true if the two Profiles are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){

        if(obj instanceof Profile){
            Profile profile = (Profile)obj;
            if((this.lastName.toLowerCase()).equals(profile.lastName.toLowerCase()) &&
                    (this.firstName.toLowerCase()).equals(profile.firstName.toLowerCase()) &&
                    (this.dob.equals(profile.dob))){
                return true;
            }
        }
        return false;

    }

    /**
     * Compares this Profile to input Profile - checks which Profile's name comes first alphabetically
     * @param profile input Profile
     * @return -1 if this Profile's name comes first alphabetically, 0 if same name, 1 if this Profile's name comes later alphabetically
     */
    @Override
    public int compareTo(Profile profile){

        if(this.lastName.toLowerCase().compareTo(profile.lastName.toLowerCase()) < 0){
            return -1;
        }else if(this.lastName.toLowerCase().compareTo(profile.lastName.toLowerCase()) == 0){
            if(this.firstName.toLowerCase().compareTo(profile.firstName.toLowerCase()) < 0){
                return -1;
            }else if(this.firstName.toLowerCase().compareTo(profile.firstName.toLowerCase()) == 0){
                return this.dob.compareTo(profile.dob);
            }
        }
        return 1;

    }

}
