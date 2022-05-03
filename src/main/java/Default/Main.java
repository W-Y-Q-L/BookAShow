package Default;

import Default.Helper.Commands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@Slf4j
@SpringBootApplication
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            log.info("Please enter your command: ");
            String command = scanner.nextLine();
            command = command.toLowerCase().trim();
            command = command.replaceAll(" +", " ");
            if (command.startsWith("setup")){
                Commands.Setup(command);
            } else if (command.startsWith("view")){
                Commands.View(command);
            } else if (command.startsWith("remove")){
                Commands.Remove(command);
            } else if (command.startsWith("add")){
                Commands.Add(command);
            } else if (command.startsWith("availability")){
                Commands.Availability(command);
            } else if (command.startsWith("book")){
                Commands.Book(command);
            } else if (command.startsWith("cancel")){
                Commands.Cancel(command);
            } else {
                log.info("Command not found");
            }
        }
    }
}