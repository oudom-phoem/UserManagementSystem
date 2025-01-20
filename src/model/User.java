package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString

public class User {
    private static int idCounter = 1;
    private int id;
    private String uuid;
    private String name;
    private String email;
    private boolean isDeleted;

    public User(String name, String email) {
        this.id = idCounter++;
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.isDeleted = false;
    }

}


