package view;

import model.dto.UserDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class UserView {

    private final Scanner scanner = new Scanner(System.in);

    public void displayUserTable(List<UserDto> users, int page, int totalPages) {
        if (users == null || users.isEmpty()) {
            System.out.println("No users to display.");
            return;
        }

        // Create the table with borders and headers
        Table table = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        table.addCell("ID");
        table.addCell("UUID");
        table.addCell("Name");
        table.addCell("Email");

        // Add rows to the table
        for (UserDto user : users) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getUuid());
            table.addCell(user.getName());
            table.addCell(user.getEmail());
        }

        // Render the table
        System.out.println(table.render());
        System.out.printf("Page %d of %d\n", page, totalPages);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public boolean promptForConfirmation(String prompt) {
        System.out.print(prompt + " (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }

    public UserDto promptForUserDetails() {
        System.out.println("Enter User Details:");
        String name = promptForString("Name");
        String email = promptForString("Email");

        return new UserDto(0, null, name, email);
    }

    public void displayUserDetails(UserDto user) {
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("User Details:");
        System.out.println("ID      : " + user.getId());
        System.out.println("UUID    : " + user.getUuid());
        System.out.println("Name    : " + user.getName());
        System.out.println("Email   : " + user.getEmail());
    }
}
