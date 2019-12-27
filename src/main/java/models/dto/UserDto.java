package models.dto;

import lombok.Data;
import models.entity.user.RoleEnum;
import models.entity.user.StatusEnum;

@Data
public class UserDto {

    private int userId;
    private String email;
    private String password;
    private RoleEnum role;
    private StatusEnum statusEnum;
}
