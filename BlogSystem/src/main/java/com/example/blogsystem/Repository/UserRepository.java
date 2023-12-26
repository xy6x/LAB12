package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
  User findUserByUsername(String user);
    User  findUserById(Integer id);

}
