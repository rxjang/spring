package com.example.tutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NonNull
    @Size(min = 3, max = 50)
    private String username;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NonNull
    @Size(min = 3, max = 100)
    private String password;

    @NonNull
    @Size(min = 3, max = 50)
    private String nickname;
}
