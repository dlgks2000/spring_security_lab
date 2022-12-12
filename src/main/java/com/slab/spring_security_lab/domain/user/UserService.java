package com.slab.spring_security_lab.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDataHandler dataHandler;

    public User findUser( String id ){

        return dataHandler.findUser(id);
    }
}
