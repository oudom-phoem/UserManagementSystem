package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString

public class UserDto {
    private int id;
    private String uuid;
    private String name;
    private String email;
}
