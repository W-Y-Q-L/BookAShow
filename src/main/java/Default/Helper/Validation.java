package Default.Helper;

import Default.DataDTO.Show;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class Validation {

    private static int MAX_ROWS = 26;
    private static int MAX_SEATS_PER_ROW = 10;

    public static boolean ValidateParametersLength(String[] parameters, int length){
        if (parameters.length == length){
            return true;
        } else {
            log.error("Command have the wrong number of parameters");
            return false;
        }
    }

    public static boolean ValidateString(String string){
        try {
            String.valueOf(string);
        } catch (Exception exception){
            return false;
        }
        return true;
    }

    public static boolean ValidateInteger(String integer){
        try {
            Integer.parseInt(integer);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    public static boolean CheckIfKeyExistInShowDetails(HashMap<Integer, Show> SHOW_DETAILS, int number){
        if (SHOW_DETAILS.containsKey(number)){
            return true;
        } else {
            return false;
        }
    }

    public static int ValidateRows(int rows){
        if (rows > MAX_ROWS){
            rows = MAX_ROWS;
        }
        return rows;
    }

    public static int ValidateColumns(int columns){
        if (columns > MAX_SEATS_PER_ROW){
            columns = MAX_SEATS_PER_ROW;
        }
        return columns;
    }

    public static boolean ValidateSeatReduction(int reduction, int seats){
        if (reduction < 1){
            log.error("Number of seats unutilized is less than 0");
            return false;
        }
        if (reduction > seats){
            log.error("Number of seats unutilized is less than seats to be removed");
            return false;
        }
        return true;
    }

    public static boolean ValidatePhoneLength(int phone){
        if (String.valueOf(phone).length() != 8){
            log.error("Phone's length is wrong");
            return false;
        } else {
            return true;
        }
    }

    public static boolean ValidateSeat(String string, int maxRow, int maxColumn){
        if (string.length() < 2){
            return false;
        }
        char row = string.charAt(0);
        int rowValue = row - 'a' + 1;
        if (rowValue < 1 || rowValue > maxRow){
            return false;
        }
        String column = string.substring(1);
        if (!ValidateInteger(column)){
            return false;
        }
        int columnValue = Integer.valueOf(column);
        if (columnValue < 1 || columnValue > maxColumn){
            return false;
        }
        return true;
    }
}
