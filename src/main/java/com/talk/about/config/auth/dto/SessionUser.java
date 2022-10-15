package com.talk.about.config.auth.dto;

import com.talk.about.domain.users.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // 세션에 사용자 정보를 저장하기 위한 클래스 -> 인증된 사용자 정보만 필요
    // User 클래스를 사용하지 않은 이유: 직렬화 에러가 남 -> 엔티티 객체를 직렬화할 수 없음 -> 직렬화 기능을 가진 Dto 생성
    private String name;
    private String email;
    private String picture;

    public SessionUser(Users user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
