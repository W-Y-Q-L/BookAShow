package Default.DataDTO;

import lombok.*;

import java.util.HashMap;
import java.util.HashSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Show {
    private int number;
    private int rows;
    private int columns;
    private HashMap<Integer, BookingDetail> bookingDetails;
    private HashSet<String> bookedSeats;
    private int cancellationWindow;
}