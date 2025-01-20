import controller.UserController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("User Management System");
            System.out.println("1. Create User");
            System.out.println("2. Search User by UUID");
            System.out.println("3. Update User by UUID");
            System.out.println("4. Delete User by UUID");
            System.out.println("5. Display Active Users");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userController.createUser();
                    break;
                case "2":
                    userController.searchUserByUuid();
                    break;
                case "3":
                    userController.updateUserByUuid();
                    break;
                case "4":
                    userController.deleteUserByUuid();
                    break;
                case "5":
                    userController.displayActiveUsersPaginated();
                    break;
                case "6":
                    System.out.println("Exiting the application.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

}