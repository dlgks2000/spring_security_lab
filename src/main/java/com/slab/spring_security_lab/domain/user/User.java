package com.slab.spring_security_lab.domain.user;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Setter( value = AccessLevel.NONE )
    private String id;
    private String password;

}
