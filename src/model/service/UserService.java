package model.service;

import model.User;
import model.dao.UserDao;
import model.dto.UserDto;
import util.TelegramNotifier;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private UserDao userDao;
    private TelegramNotifier telegramNotifier;

    public UserService() {
        this.userDao = new UserDao();
        this.telegramNotifier = new TelegramNotifier();
    }

    public void createUser(String name, String email) {
        User user = new User(name, email);
        userDao.save(user);
        telegramNotifier.sendNotification("""
                New user created:
                ID: %d
                UUID: %s
                Name: %s
                Email: %s
                """.formatted(user.getId(), user.getUuid(), user.getName(), user.getEmail()));
    }

    public Optional<UserDto> searchUserByUuid(String uuid) {
        return userDao.findByUuid(uuid)
                .map(user -> new UserDto(user.getId(), user.getUuid(), user.getName(), user.getEmail()));
    }

    public boolean updateUserByUuid(String uuid, String name, String email, boolean isDeleted) {
        Optional<User> optionalUser = userDao.findByUuid(uuid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(name);
            user.setEmail(email);
            user.setDeleted(isDeleted);
            return userDao.update(user);
        }
        return false;
    }

    public boolean deleteUserByUuid(String uuid) {
        return userDao.deleteByUuid(uuid);
    }

    public List<UserDto> getAllActiveUsers(int page, int pageSize) {
        List<User> activeUsers = userDao.findAllActive();
        return activeUsers.stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .map(user -> new UserDto(user.getId(), user.getUuid(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public List<UserDto> findAllActiveUserDtos() {
        return userDao.findAll().stream()
                .filter(user -> !user.isDeleted()) // Exclude deleted users
                .map(user -> new UserDto(user.getId(), user.getUuid(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

}
