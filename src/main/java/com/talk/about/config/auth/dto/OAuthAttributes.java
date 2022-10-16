package com.talk.about.config.auth.dto;

import com.talk.about.domain.users.Role;
import com.talk.about.domain.users.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    // OAuth2UserService를 통해 가져온 OAuthUser의 attribute를 담을 클래스
   private Map<String, Object> attributes;
   private String nameAttributeKey;
   private String name;
   private String email;
   private String picture;

   @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
                           String name, String email, String picture) {
       this.attributes = attributes;
       this.nameAttributeKey = nameAttributeKey;
       this.name = name;
       this.email = email;
       this.picture = picture;
   }

   // OAuth 반환 사용자 정보: Map -> 하나하나 변환 필요
   public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
       if("naver".equals(registrationId)) {
           return ofNaver("id", attributes);
       }

       return ofGoogle(userNameAttributeName, attributes);
   }

   public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
       return OAuthAttributes.builder()
               .name((String)attributes.get("name"))
               .email((String)attributes.get("email"))
               .picture((String)attributes.get("picture"))
               .attributes(attributes)
               .nameAttributeKey(userNameAttributeName)
               .build();
   }

   public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {

       Map<String, Object> response = (Map<String, Object>) attributes.get("response");

       return OAuthAttributes.builder()
               .name((String) response.get("name"))
               .email((String) response.get("email"))
               .picture((String) response.get("profile_image"))
               .attributes(response)
               .nameAttributeKey(userNameAttributeName)
               .build();
   }

   // 가입할 때 User Entity 생성, 기본 권한: GUEST
   public Users toEntity() {
       return Users.builder()
               .name(name)
               .email(email)
               .picture(picture)
               .role(Role.GUEST)
               .build();
   }
}
