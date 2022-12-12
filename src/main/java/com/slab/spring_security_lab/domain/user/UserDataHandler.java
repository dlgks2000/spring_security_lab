package com.slab.spring_security_lab.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDataHandler {

    public final UserRepository repository;

    public User findUser(String id) {

        UserEntity entity = repository.findById(id).orElse(null);

        if (entity == null) {
            return null;
        }

        return UserMapper.Instance.toModel(entity);
    }
}
