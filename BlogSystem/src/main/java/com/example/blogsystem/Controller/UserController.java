package com.example.blogsystem.Controller;

import com.example.blogsystem.Api.ApiExcepation;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
//     All
    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("Logged in successfully");
    }
    @GetMapping("/getAllUser")
private  ResponseEntity getUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
}
@GetMapping("/getUser")
public ResponseEntity getUser(@AuthenticationPrincipal User user){
    return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(user.getId()));
}
@PostMapping("/add")
public ResponseEntity addUser(@RequestBody @Valid User user){
        userService.register(user);
    return ResponseEntity.status(200).body("added User");

}
@PutMapping("/put")
public ResponseEntity updateUser(@RequestBody @Valid User newUser, @AuthenticationPrincipal User auth){
        userService.updateUser(newUser,auth.getId());
    return ResponseEntity.status(200).body("update user");
}
@DeleteMapping("/delete/{id}")
public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    return ResponseEntity.status(200).body("delete user");
}
    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }



}
