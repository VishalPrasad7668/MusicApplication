package com.niit.UserTrackService.proxy;

import com.niit.UserTrackService.domain.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentication-service1",url = "http://authentication-service1:8098")
public interface UserProxy {
    @PostMapping("/userservice/register")
    public ResponseEntity<?> saveUser(@RequestBody Users user);
}
