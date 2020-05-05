/**
 * Homework 2 - B; Jack Lesemann, jwl4vg
 * 
 * @author jackw
 */
public class DateLibrary {

    /**
     * Sees if given String date is in proper date format
     * 
     * @param date | String date
     * @return true or false depending on whether date matches format
     */

    public static boolean isValidDateFormat(String date) {
        if (date.length() == 10) { // date must be 10 characters long, including hyphens
            char c1 = date.charAt(4); // 1st hyphen
            char c2 = date.charAt(7); // 2nd hyphen

            if ((c1 == '-') && (c2 == '-')) { // see if hyphens are there
                String year = date.substring(0, 4); // year
                String month = date.substring(5, 7); // month
                String day = date.substring(date.length() - 2); // day
                String rawDate = year + month + day;

                for (int i = 0; i < rawDate.length(); i++) {
                    // loop through string and see if characters are numbers
                    char c = rawDate.charAt(i);
                    if (Character.isDigit(c)) {
                        continue;
                    } else {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * gets year from String date
     * 
     * @param date
     * @return year as an int or -1 if date is invalid
     */
    public static int getYear(String date) {

        if (!isValidDateFormat(date)) { // return -1 if date is invalid
            return -1;
        } else {
            String year = date.substring(0, 4);
            char year_Start = year.charAt(0);
            char year_End = year.charAt(3);

            if (year_Start == '0' && year_End == '0') { // if year is 0--0, return invalid
                return -1;
            } else {
                return Integer.parseInt(year);
            }
        }

    }

    /**
     * gets the month from String date
     * 
     * @param date
     * @return month as an int or -1 if date is invalid
     */
    public static int getMonth(String date) {

        if (isValidDateFormat(date)) { // continue if date is correct format
            int month = Integer.parseInt(date.substring(5, 7));
            if (month <= 12 && month >= 1) { // returns month if it is valid #
                return month;
            } else {
                return -1;
            }
        } else {
            return -1;
        }

    }

    /**
     * gets day from String date
     * 
     * @param date
     * @return day as an int or -1 if date is invalid
     */
    public static int getDay(String date) {

        if (isValidDateFormat(date)) {
            String daY = date.substring(date.length() - 2);
            int day = Integer.parseInt(daY);
            if (day <= 31 && day >= 1) { // returns valid if it is valid #
                return day;
            } else {
                return -1;
            }
        } else {
            return -1;
        }

    }

    /**
     * sees if int year is a leap year
     * 
     * @param year
     * @return true or false depending on whether year is leap year or not
     */
    public static boolean isLeapYear(int year) {

        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else if (year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * check if date is a valid date, takes into account leap year and # of days in month
     * 
     * @param date
     * @return true or false depending on whether date is valid
     */
    public static boolean isValidDate(String date) {

        if (isValidDateFormat(date)) {
            int year = getYear(date);
            int month = getMonth(date);
            int day = getDay(date);

            if (year < 0000 || year > 9999) { // date checker
                return false;
            }

            if (month < 1 || month > 12) { // month checker
                return false;
            }

            if (day < 1) {
                return false; // minimum day checker
            }

            int[] days = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; // create list that represents months of
                                                                                       // year
                                                                                       // and their respective days

            if (month == 2) { // february
                if (isLeapYear(year)) { // long or short february
                    if (day <= 29) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (day <= 28) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            if (day <= days[month - 1]) { // maximum day checker
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * compares two dates and returns a negative num if date1 is before date2, positive for opposite, and 0 if they're equal
     * or invalid
     * 
     * @param date1
     * @param date2
     * @return +, -, 0
     */
    public static int compare(String date1, String date2) {

        if (isValidDate(date1) && isValidDate(date2)) { // use comparTo to compare order of dates
            String num1 = date1.replaceAll("-", ""); // remove hyphens
            String num2 = date2.replaceAll("-", "");

            return num1.compareTo(num2);

        } else {
            return 0;
        }

    }

    public static void main(String[] args) {
        String validDate = "1999-10-24";
        String inValidDate = "1999-02-31";

        // isValidDateFormat tests
        // System.out.println(isValidDateFormat(validDate));
        // System.out.println(isValidDateFormat(inValidDate));

        // getYear tests
        System.out.println(getYear(validDate));
        System.out.println(getYear(inValidDate));

        // getMonth tests
        System.out.println(getMonth(validDate));
        System.out.println(getMonth(inValidDate));

        // getDay tests
        System.out.println(getDay(validDate));
        System.out.println(getDay(inValidDate));
        /**
         * //getisLeapYear tests System.out.println(isLeapYear(getYear(validDate)));
         * System.out.println(isLeapYear(getYear(inValidDate))); int year1 = 2020; int year2 = 2100; int year3 = 2200;
         * System.out.println(isLeapYear(year1)); System.out.println(isLeapYear(year2)); System.out.println(isLeapYear(year3));
         * //isValidDate tests System.out.println(isValidDate(validDate)); System.out.println(isValidDate(inValidDate));
         * //compare tests System.out.println(compare(validDate, inValidDate)); System.out.println(compare(validDate,
         * validDate));
         */

    }

}
