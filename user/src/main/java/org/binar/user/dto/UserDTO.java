package org.binar.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String email;
    private String password;
    private List<String> role;

    public UserDTO(String username, String email, String password, List<String> role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

}
