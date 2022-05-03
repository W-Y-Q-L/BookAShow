package Default.Helper;

import Default.DataDTO.BookingDetail;
import Default.DataDTO.Show;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Slf4j
public class Commands {

    private static HashMap<Integer, Show> SHOW_DETAILS = new HashMap<>();
    private static int TICKET_INCREMENTOR = 0;

    public static void Setup(String command){
        String[] parameters = command.split(" ");
        if (!Validation.ValidateParametersLength(parameters, 5)){
            return;
        }
        if (!Validation.ValidateInteger(parameters[1]) || !Validation.ValidateInteger(parameters[2]) || !Validation.ValidateInteger(parameters[3]) || !Validation.ValidateInteger(parameters[4])){
            log.error("Parameters' datatype is wrong");
            return;
        }
        int number = Integer.valueOf(parameters[1]);
        if (Validation.CheckIfKeyExistInShowDetails(SHOW_DETAILS, number)){
            log.error("Show already exist");
            return;
        }
        int rows = Validation.ValidateRows(Integer.valueOf(parameters[2]));
        int columns = Validation.ValidateColumns(Integer.valueOf(parameters[3]));
        int cancellationWindow = Integer.valueOf(parameters[4]);
        Show show = new Show(number, rows, columns, new HashMap<>(), new HashSet<>(), cancellationWindow);
        SHOW_DETAILS.put(number, show);
        log.info("Added new show");
    }

    public static void View(String command){
        String[] parameters = command.split(" ");
        if (!Validation.ValidateParametersLength(parameters, 2)){
            return;
        }
        if (!Validation.ValidateInteger(parameters[1])){
            log.error("Parameters' datatype is wrong");
            return;
        }
        int number = Integer.valueOf(parameters[1]);
        if (!Validation.CheckIfKeyExistInShowDetails(SHOW_DETAILS, number)){
            log.error("Show doesn't exist");
            return;
        }
        log.info("Show number: " + number);
        Show show = SHOW_DETAILS.get(number);
        HashMap<Integer, BookingDetail> bookingDetails = show.getBookingDetails();
        for (Map.Entry<Integer, BookingDetail> entry: bookingDetails.entrySet()){
            log.info("Ticket number: " + entry.getKey());
            BookingDetail bookingDetail = entry.getValue();
            log.info("Phone number: " + bookingDetail.getPhone());
            log.info("Seats: " + bookingDetail.getSeats());
        }
    }

    public static void Remove(String command){
        String[] parameters = command.split(" ");
        if (!Validation.ValidateParametersLength(parameters, 3)){
            return;
        }
        if (!Validation.ValidateInteger(parameters[1]) || !Validation.ValidateInteger(parameters[2])){
            log.error("Parameters' datatype is wrong");
            return;
        }
        int number = Integer.valueOf(parameters[1]);
        if (!Validation.CheckIfKeyExistInShowDetails(SHOW_DETAILS, number)){
            log.error("Show doesn't exist");
            return;
        }
        int reduce = Integer.valueOf(parameters[2]);
        int avaliableSeats = 0;
        Show show = SHOW_DETAILS.get(number);
        int rows = show.getRows();
        int columns = show.getColumns() + 1;
        HashSet<String> bookedSeats = show.getBookedSeats();
        for (int i = 0; i < rows; i++){
            for (int j = 1; j < columns; j++){
                String seat = ((char) (i + 'A')) + "" + j;
                if (!bookedSeats.contains(seat)){
                    avaliableSeats++;
                }
            }
        }
        if (!Validation.ValidateSeatReduction(reduce, avaliableSeats)){
            return;
        }
        for (int i = 0; i < rows; i++){
            for (int j = 1; j < columns; j++){
                String seat = ((char) (i + 'A')) + "" + j;
                if (!bookedSeats.contains(seat)){
                    if (reduce > 0) {
                        seat = (Character.toString((char) (i + 'A'))) + "" + j;
                        bookedSeats.add(seat);
                        reduce--;
                    }
                }
            }
        }
        show.setBookedSeats(bookedSeats);
        SHOW_DETAILS.put(number, show);
    }

    public static void Add(String command){
        String[] parameters = command.split(" ");
        if (!Validation.ValidateParametersLength(parameters, 3)){
            return;
        }
        if (!Validation.ValidateInteger(parameters[1]) || !Validation.ValidateInteger(parameters[2])){
            log.error("Parameters' datatype is wrong");
            return;
        }
        int number = Integer.valueOf(parameters[1]);
        if (!Validation.CheckIfKeyExistInShowDetails(SHOW_DETAILS, number)){
            log.error("Show doesn't exist");
            return;
        }
        Show show = SHOW_DETAILS.get(number);
        int rows = Validation.ValidateRows(Integer.valueOf(parameters[2]));
        show.setRows(rows);
        SHOW_DETAILS.put(number, show);
    }

    public static void Availability(String command){
        String[] parameters = command.split(" ");
        if (!Validation.ValidateParametersLength(parameters, 2)){
            return;
        }
        if (!Validation.ValidateInteger(parameters[1])){
            log.error("Parameters' datatype is wrong");
            return;
        }
        int number = Integer.valueOf(parameters[1]);
        if (!Validation.CheckIfKeyExistInShowDetails(SHOW_DETAILS, number)){
            log.error("Show doesn't exist");
            return;
        }
        Show show = SHOW_DETAILS.get(number);
        int rows = show.getRows();
        int columns = show.getColumns() + 1;
        HashSet<String> bookedSeats = show.getBookedSeats();
        for (int i = 0; i < rows; i++){
            String seatAllocations = "";
            for (int j = 1; j < columns; j++){
                String seat = ((char) (i + 'A')) + "" + j;
                if (bookedSeats.contains(seat)){
                    if (j != columns - 1) {
                        seatAllocations += "XX, ";
                    } else {
                        seatAllocations += "XX";
                    }
                } else {
                    seat = (Character.toString((char) (i + 'A'))) + "" + j;
                    if (j != columns - 1) {
                        seatAllocations += seat + ", ";
                    } else {
                        seatAllocations += seat;
                    }
                }
            }
            log.info(seatAllocations);
        }
    }

    public static void Book(String command){
        String[] parameters = command.split(" ");
        if (!Validation.ValidateParametersLength(parameters, 4)){
            return;
        }
        if (!Validation.ValidateInteger(parameters[1]) || !Validation.ValidateInteger(parameters[2]) || !Validation.ValidateString(parameters[3])){
            log.error("Parameters' datatype is wrong");
            return;
        }
        int number = Integer.valueOf(parameters[1]);
        if (!Validation.CheckIfKeyExistInShowDetails(SHOW_DETAILS, number)){
            log.error("Show doesn't exist");
            return;
        }
        int phone = Integer.valueOf(parameters[2]);
        if (!Validation.ValidatePhoneLength(phone)){
            return;
        }
        Show show = SHOW_DETAILS.get(number);
        HashMap<Integer, BookingDetail> bookingDetails = show.getBookingDetails();
        for (Map.Entry<Integer, BookingDetail> entry: bookingDetails.entrySet()){
            BookingDetail bookingDetail = entry.getValue();
            if (bookingDetail.getPhone() == phone){
                log.error("Phone number already exist for this show");
                return;
            }
        }
        String seatsParameter = parameters[3];
        seatsParameter = seatsParameter.replaceAll(" ", "");
        String[] listOfSeats = seatsParameter.split(",");
        HashSet<String> bookedSeats = show.getBookedSeats();
        ArrayList<String> seats = new ArrayList<>();
        for (int i = 0; i < listOfSeats.length; i++){
            String seat = listOfSeats[i];
            if (!Validation.ValidateSeat(seat, show.getRows(), show.getColumns())){
                log.error("Seat " + seat.toUpperCase() + " failed validation");
                continue;
            }
            seat = (seat.charAt(0) + seat.substring(1)).toUpperCase();
            if (bookedSeats.contains(seat)){
                log.error("Seat " + seat + " already exist");
                continue;
            }
            seats.add(seat);
            bookedSeats.add(seat);
        }
        if (seats.size() > 0) {
            TICKET_INCREMENTOR++;
            bookingDetails.put(TICKET_INCREMENTOR, new BookingDetail(phone, seats, LocalDateTime.now()));
            show.setBookingDetails(bookingDetails);
            show.setBookedSeats(bookedSeats);
            SHOW_DETAILS.put(number, show);
            log.info("Ticket number: " + TICKET_INCREMENTOR);
        } else {
            log.info("No ticket added due to failed validations / duplicate seats");
        }
    }

    public static void Cancel(String command){
        String[] parameters = command.split(" ");
        if (!Validation.ValidateParametersLength(parameters, 4)){
            return;
        }
        if (!Validation.ValidateInteger(parameters[1]) || !Validation.ValidateInteger(parameters[2]) || !Validation.ValidateString(parameters[3])){
            log.error("Parameters' datatype is wrong");
            return;
        }
        int number = Integer.valueOf(parameters[1]);
        if (!Validation.CheckIfKeyExistInShowDetails(SHOW_DETAILS, number)){
            log.error("Show doesn't exist");
            return;
        }
        Show show = SHOW_DETAILS.get(number);
        HashMap<Integer, BookingDetail> bookingDetails = show.getBookingDetails();
        int ticket = Integer.valueOf(parameters[3]);
        if (!bookingDetails.containsKey(ticket)){
            log.error("Ticket doesn't exist");
            return;
        }
        BookingDetail bookingDetail = bookingDetails.get(ticket);
        long minutes = ChronoUnit.MINUTES.between(bookingDetail.getBookingTime(), LocalDateTime.now());
        if (minutes < show.getCancellationWindow()){
            ArrayList<String> seats = bookingDetail.getSeats();
            HashSet<String> bookedSeats = show.getBookedSeats();
            for (int i = 0; i < seats.size(); i++){
                String seat = seats.get(i);
                bookedSeats.remove(seat);
            }
            bookingDetails.remove(ticket);
            show.setBookingDetails(bookingDetails);
            show.setBookedSeats(bookedSeats);
            log.info("Cancellation successful");
        } else {
            log.info("Cancellation not allowed");
        }
    }
}
