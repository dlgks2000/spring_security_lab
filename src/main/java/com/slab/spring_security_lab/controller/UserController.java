package com.slab.spring_security_lab.controller;

import com.slab.spring_security_lab.domain.user.User;
import com.slab.spring_security_lab.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/user/{id}")
    public ResponseEntity< String > findById( @PathVariable String id ) {

        User user = service.findUser(id);

        String userId = null;

        if( user != null ) {
            userId = user.getId();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonObject.toString());
    }
}
