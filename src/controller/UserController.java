package controller;

import model.dto.UserDto;
import model.service.UserService;
import view.UserView;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserController {
    private UserService userService;
    private UserView userView = new UserView();
    private Scanner scanner;

    public UserController() {
        this.userService = new UserService();
        userView = new UserView();
        this.scanner = new Scanner(System.in);
    }

    public void createUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        userService.createUser(name, email);
    }

    public void searchUserByUuid() {
        System.out.print("Enter UUID: ");
        String uuid = scanner.nextLine();
        Optional<UserDto> userDto = userService.searchUserByUuid(uuid);
        userDto.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("User not found.")
        );
    }

    public void updateUserByUuid() {
        System.out.print("Enter UUID: ");
        String uuid = scanner.nextLine();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Is user deleted? (true/false): ");
        boolean isDeleted = scanner.nextBoolean();
        scanner.nextLine();

        boolean updated = userService.updateUserByUuid(uuid, name, email, isDeleted);
        if (updated) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void deleteUserByUuid() {
        System.out.print("Enter UUID: ");
        String uuid = scanner.nextLine();
        boolean deleted = userService.deleteUserByUuid(uuid);
        if (deleted) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void displayActiveUsersPaginated() {
        List<UserDto> activeUsers = userService.findAllActiveUserDtos();
        if (activeUsers.isEmpty()) {
            userView.displayMessage("No active users to display.");
            return;
        }

        int pageSize = 5;
        int totalPages = (int) Math.ceil((double) activeUsers.size() / pageSize);
        int currentPage = 1;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int start = (currentPage - 1) * pageSize;
            int end = Math.min(start + pageSize, activeUsers.size());
            List<UserDto> usersForPage = activeUsers.subList(start, end);

            userView.displayUserTable(usersForPage, currentPage, totalPages);

            // Pagination options
            System.out.println("\nOptions: [N]ext Page, [P]revious Page, [E]xit");
            String option = scanner.nextLine().toUpperCase();

            if (option.equals("N") && currentPage < totalPages) {
                currentPage++;
            } else if (option.equals("P") && currentPage > 1) {
                currentPage--;
            } else if (option.equals("E")) {
                break;
            } else {
                userView.displayMessage("Invalid option. Please try again.");
            }
        }
    }

}
