# BookAShow
## Expected inputs and outputs
**setup 1234567890 5 5 10**<br>
Added new show<br>

**book 1234567890 11112222 A2,A3,A4**<br>
Ticket number: 1<br>

**book 1234567890 22223333 E2,E3,E4,D2,D3,D4**<br>
Ticket number: 2<br>

**availability 1234567890**<br>
A1, XX, XX, XX, A5<br>
B1, B2, B3, B4, B5<br>
C1, C2, C3, C4, C5<br>
D1, XX, XX, XX, D5<br>
E1, XX, XX, XX, E5<br>

**view 1234567890**<br>
Show number: 1234567890<br>
Ticket number: 1<br>
Phone number: 11112222<br>
Seats: [A2, A3, A4]<br>
Ticket number: 2<br><br>
Phone number: 22223333<br>
Seats: [E2, E3, E4, D2, D3, D4]<br>

**cancel 1234567890 22223333 1**<br>
Cancellation successful<br>

**remove 1234567890 10**<br>
XX, XX, XX, XX, XX<br>
XX, XX, XX, XX, XX<br>
C1, C2, C3, C4, C5<br>
D1, XX, XX, XX, D5<br>
E1, XX, XX, XX, E5<br>

**add 1234567890 10**<br>

**availability 1234567890**<br>
XX, XX, XX, XX, XX<br>
XX, XX, XX, XX, XX<br>
C1, C2, C3, C4, C5<br>
D1, XX, XX, XX, D5<br>
E1, XX, XX, XX, E5<br>
F1, F2, F3, F4, F5<br>
G1, G2, G3, G4, G5<br>
H1, H2, H3, H4, H5<br>
I1, I2, I3, I4, I5<br>
