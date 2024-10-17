package projectElectronicsStore;

import java.util.Calendar;

/**
 * Date class creates a Date object
 * @author Earl Lontok
 */
public class Date implements Comparable<Date>{

    private int month;
    private int day;
    private int year;

    /**
     * Default constructor for the Date object - initializes Date to today's date
     */
    public Date(){

        Calendar todayDate = Calendar.getInstance();

        this.month = todayDate.get(Calendar.MONTH) + 1;
        this.day = todayDate.get(Calendar.DAY_OF_MONTH);
        this.year = todayDate.get(Calendar.YEAR);

    }

    /**
     * Constructor for the Date object
     * @param date the input date
     */
    public Date(String date){

        String[] dob = date.split("/");
        this.month = Integer.parseInt(dob[0]);
        this.day = Integer.parseInt(dob[1]);
        this.year = Integer.parseInt(dob[2]);

    }

    /**
     * A helper method that checks if a given year is a leap year
     * @param year the year to be checked
     * @return true if leap year, false otherwise
     */
    private boolean isLeapYear(int year){

        if(year % Constant.QUADRENNIAL == 0){
            if(year % Constant.CENTENNIAL == 0){
                if(year % Constant.QUATERCENTENNIAL == 0){
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;

    }

    /**
     * A helper method that checks if a month has 31 days
     * @param month the month to be checked
     * @return true if month has 31 days, false otherwise
     */
    private boolean isLongMonth(int month){

        if(this.month == Constant.JAN || this.month == Constant.MAR || this.month == Constant.MAY || this.month == Constant.JUL || this.month == Constant.AUG ||this.month == Constant.OCT || this.month == Constant.DEC){
            return true;
        }
        return false;

    }

    /**
     * A helper method that checks if a month has 30 days
     * @param month the month to be checked
     * @return true if month has 30 days, false otherwise
     */
    private boolean isShortMonth(int month){

        if(this.month == Constant.APR || this.month == Constant.JUN || this.month == Constant.SEP || this.month == Constant.NOV){
            return true;
        }
        return false;

    }

    /**
     * Checks if a Date is valid
     * @return true if Date is valid, false otherwise
     */
    public boolean isValid(){

        if(this.day < Constant.FIRST_DAY || this.month < Constant.FIRST_MONTH || this.month > Constant.LAST_MONTH || this.year < Constant.START_YEAR){
            return false;
        }
        if(this.month == Constant.FEB){ //February is a special case - Leap Year vs Nonleap Year
            if(isLeapYear(this.year) && this.day > Constant.LEAP_YEAR_MONTH){
                return false;
            }
            if(!isLeapYear(this.year) && this.day > Constant.NON_LEAP_YEAR_MONTH){
                return false;
            }
        }else if(isLongMonth(this.month) && this.day > Constant.LONG_MONTH){ //31-day months
            return false;
        }else if(isShortMonth(this.month) && this.day > Constant.SHORT_MONTH){ //30-day months
            return false;
        }
        return true;

    }

    /**
     * Converts the Date object into a string with the format (dd/mm/yyyy)
     * @return the Date object as a string
     */
    @Override
    public String toString(){

        return this.month + "/" + this.day + "/" + this.year;

    }

    /**
     * Checks if this Date is equal to input Date
     * @param obj input Date
     * @return true if the two Dates are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){

        if(obj instanceof Date){
            Date date = (Date) obj;
            if(date.month != this.month || date.day != this.day || date.year != this.year){
                return false;
            }
            return true;
        }
        return false;

    }

    /**
     * Compares this Date to input date - checks which date comes earlier/same date/later
     * @param date input Date
     * @return -1 if this Date is earlier, 0 if same date, 1 if this Date is later
     */
    @Override
    public int compareTo(Date date){

        if(this.year < date.year){
            return -1;
        }else if(this.year == date.year){
            if(this.month < date.month){
                return -1;
            }else if(this.month == date.month){
                if(this.day < date.day){
                    return -1;
                }else if(this.day == date.day){
                    return 0;
                }
            }
        }
        return 1;

    }

}
