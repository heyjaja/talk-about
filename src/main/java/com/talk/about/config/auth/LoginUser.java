package com.talk.about.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션이 생성될 수 있는 위치 지정 -> 메서드의 파라미터
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // @interface: 어노테이션 클래스로 지정
}
