package Default.DataDTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDetail {
    private int phone;
    private ArrayList<String> seats;
    private LocalDateTime bookingTime;
}
