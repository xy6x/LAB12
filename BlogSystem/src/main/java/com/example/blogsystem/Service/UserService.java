package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiExcepation;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public User getUser(Integer id) {
        User myUser = userRepository.findUserById(id);
        if (myUser == null) {
            throw new ApiExcepation("User Not Found!");
        }
        return myUser;
    }
    public void register(User user){
        user.setRole("USER");
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(user);
    }
    public void updateUser(User newUser, Integer id){
        User oldUser=userRepository.findUserById(id);

        newUser.setId(id);
        newUser.setRole(oldUser.getRole());
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));

        userRepository.save(newUser);
    }
    public void deleteUser(Integer id){
        User myUser=userRepository.findUserById(id);
        if(myUser==null){
            throw new ApiExcepation("User Not Found");
        }
        userRepository.delete(myUser);
    }
}
