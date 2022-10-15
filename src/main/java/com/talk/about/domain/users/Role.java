package com.talk.about.domain.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");//권한코드 앞에는 꼭 ROLE_이 있어야 한다.

    private final String key;
    private final String title;
}
