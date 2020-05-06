package com.example.study.model.network.request;

import com.example.study.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor//매개 변수없는 생성자
@AllArgsConstructor// 모든 매개 변수가 있는 생성자
@Builder
public class UserApiRequest {

    private Long id;

    private String account;

    private String password;

    private UserStatus status;

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;
}
