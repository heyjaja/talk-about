package com.talk.about.config.auth;

import com.talk.about.domain.users.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정을 활성화
public class SpringConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                // URL 별 권한 관리 설정 옵션의 시작
                    .authorizeRequests()
                // antMatchers(): 권한 관리 대상 지정 -> URL, http Method 별로 관리, permitAll(): 전체 열람 권한
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())//USER 권한을 가진 사람만 가능
                    .anyRequest() //설정값 이외 나머지 URL
                    .authenticated() // 인증된 사용자(로그인)에게만 허용
                .and()
                    .logout().logoutSuccessUrl("/") // 로그아웃 성공시 이동할 URL
                .and()
                    .oauth2Login().userInfoEndpoint() // 로그인 이후 사용자 정보를 가져올 때 설정
                        .userService(customOAuth2UserService); // 로그인 성공 시 후속 조치를 진행할 UserService

        return http.build();
    }
}
