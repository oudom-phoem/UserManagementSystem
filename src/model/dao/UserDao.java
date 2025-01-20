package model.dao;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDao {
    private List<User> users = new ArrayList<>();

    public UserDao() {
        preloadUsers();
    }

    private void preloadUsers() {
        users.add(new User("Alice", "alice@example.com"));
        users.add(new User("Bob", "bob@example.com"));
        users.add(new User("Charlie", "charlie@example.com"));
        users.add(new User("Diana", "diana@example.com"));
        users.add(new User("Eve", "eve@example.com"));
    }


    public void save(User user) {
        users.add(user);
        System.out.println("User saved: " + user);
    }

    public Optional<User> findByUuid(String uuid) {
        return users.stream()
                .filter(user -> user.getUuid().equals(uuid))
                .findFirst();
    }

    public boolean update(User updatedUser) {
        Optional<User> existingUser = findByUuid(updatedUser.getUuid());
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setDeleted(updatedUser.isDeleted());
            System.out.println("User updated: " + user);
            return true;
        }
        return false;
    }

    public boolean deleteByUuid(String uuid) {
        return users.removeIf(user -> user.getUuid().equals(uuid));
    }

    public List<User> findAllActive() {
        return users.stream()
                .filter(user -> !user.isDeleted())
                .collect(Collectors.toList());
    }

    public List<User> findAll() {
        return users;
    }

}
