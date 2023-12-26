package com.example.blogsystem.Controller;

import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Repository
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService blogService;
    //admin
    @GetMapping("/all-blog")
    public ResponseEntity getAllBlog(){
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAllBlog());
    }
    //user
    @GetMapping("/getId/{id}")
public ResponseEntity getBlogId(@PathVariable Integer id, @AuthenticationPrincipal User user){
    return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogId(id,user.getId()));
}
    @GetMapping("/my-blog")
public ResponseEntity  getMyBlog(@AuthenticationPrincipal User user){
    return ResponseEntity.status(HttpStatus.OK).body(blogService.getMyBolg(user.getId()));
}
@PostMapping("/add")
public ResponseEntity addBolg(@RequestBody @Valid Blog blog,@AuthenticationPrincipal User user){
        blogService.addBolg(blog,user.getId());
    return ResponseEntity.status(HttpStatus.OK).body("added Blog");
}
    @PutMapping("/update/{id}")
public ResponseEntity update(@PathVariable Integer id ,Blog blog,@AuthenticationPrincipal User user){
        blogService.updateBlog(id,blog, user.getId());
    return ResponseEntity.status(HttpStatus.OK).body("update Blog");
}
@DeleteMapping("/delete/{id}")
public ResponseEntity deleteBlog(@PathVariable Integer id,@AuthenticationPrincipal User user){
        blogService.deleteBlog(id, user.getId());
    return ResponseEntity.status(HttpStatus.OK).body("delete Blog");
}
@GetMapping("/getTitle/{title}")
public ResponseEntity titleBlog(@PathVariable String title){

        return ResponseEntity.status(HttpStatus.OK).body(blogService.check(title));
}



}

