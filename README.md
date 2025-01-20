# User Management Console System

This is a Java-based User Management Console System that implements the MVC (Model-View-Controller) design pattern. The system provides features to manage users, including creating, updating, searching, deleting, and displaying user details. Notifications for new user creation are sent to a Telegram channel.

## Features

1. **Create User**: Add a new user. Sends a notification to Telegram upon successful creation.
2. **Search User by UUID**: Retrieve user details by their UUID.
3. **Update User by UUID**: Modify a user's name, email, or deletion status.
4. **Delete User by UUID**: Mark a user as deleted.
5. **Display All Active Users**: Paginated display of active (non-deleted) users in a table format.
6. **Preloaded Users**: The system includes preloaded users.
7. **Exit**: Exit the application.

## Project Structure

```
src/
├── controller/
│   └── UserController.java
├── model/
│   ├── dao/
│   │   └── UserDao.java
│   ├── dto/
│   │   └── UserDto.java
│   └── service/
│   │   └── UserService.java
│   └── User.java
├── resources/
│   └── config.properties
├── util/
│   └── TelegramNotifier.java
├── view/
│   └── UserView.java
└── Main.java
```

## Prerequisites

1. **Java 8 or higher**
2. **Maven** (optional, for dependency management)
3. **Telegram Bot Token**: Configure a bot via the Telegram Bot API and retrieve the token and chat ID.

## Configuration

1. Create a `config.properties` file in the root directory (or adjust the path in `TelegramConfig.java`).
2. Add the following content:

    ```properties
    telegram.token=YOUR_TELEGRAM_BOT_TOKEN
    telegram.chat.id=YOUR_CHAT_ID
    ```
3. Add `config.properties` to `.gitignore` to avoid committing sensitive data.

## How to Run

1. Clone the repository:

    ```bash
    git clone <repository-url>
    cd UserManagementSystem
    ```

2. Compile and run the application:

    ```bash
    javac -d bin src/**/*.java
    java -cp bin Main
    ```

## Dependencies

This project uses the following dependencies:

- [Text-Table-Format](https://github.com/dhlee347/text-table-format) (for displaying tables)

## Usage

Upon running the application, a menu will be displayed with the following options:

1. Create a new user.
2. Search for a user by UUID.
3. Update a user's details by UUID.
4. Delete a user by UUID.
5. Display all active users (paginated).
6. Exit the application.

## Preloaded Users

- The application preloads a set of users for demonstration purposes.
- Preloaded users can be found in the `UserDao` class.

## MVC Design Pattern

The project adheres to the MVC pattern:

- **Model**: Defines the `User` class and related data structures.
- **View**: Handles all user interactions (e.g., `UserView`).
- **Controller**: Processes user input and updates the model or view (e.g., `UserController`).
- **Service**: Contains business logic (e.g., `UserService`).
- **DAO**: Manages data persistence (e.g., `UserDao`).

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contribution

Contributions are welcome! Please submit a pull request or open an issue for suggestions or bug reports.
