package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiExcepation;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.BlogRepository;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    public List<Blog> getAllBlog(){
        return blogRepository.findAll();

    }
    public Blog getBlogId(Integer blog_id,Integer auth){
        Blog blog =blogRepository.findBlogById(blog_id);
        if (blog == null) {
            throw  new ApiExcepation("bolg not found");
        }
        if (blog.getUser().getId()!= auth) {
            throw  new ApiExcepation("user not found");
        }
        return blog;
    }
    public List getMyBolg(Integer auth){
        User user =userRepository.findUserById(auth);
        List blog=blogRepository.findBlogByUser(user);
        if (blog.isEmpty()) {
            throw  new ApiExcepation("Empty");
        }
        return blog;
    }
    public void addBolg(Blog blog,Integer auth){
        User user =userRepository.findUserById(auth);
        if (user == null) {
            throw new ApiExcepation("user not Found");
        }
        blog.setUser(user);
        blogRepository.save(blog);
    }
    public void updateBlog(Integer blog_id ,Blog blog,Integer auth){
        Blog oldBlog = blogRepository.findBlogById(blog_id);
        User user =userRepository.findUserById(auth);
        if (oldBlog == null) {
            throw  new ApiExcepation("the Bolg not  found");
        }else if (oldBlog.getUser().getId()!=auth) {
            throw  new ApiExcepation("the User not same");
        }
        blog.setId(blog_id);
        blog.setUser(user);
        blogRepository.save(blog);
    }
    public void deleteBlog(Integer blog_id ,Integer auth) {
        Blog blog = blogRepository.findBlogById(blog_id);
        if (blog == null) {
            throw new ApiExcepation("the Bolg not  found");
        } else if (blog.getUser().getId() != auth) {
            throw new ApiExcepation("the User not same");
        }
        blogRepository.delete(blog);
    }

    public List check(String title) {
        List blog =blogRepository.findBlogByTitle(title);
        if (blog == null) {
            throw new ApiExcepation("the title not found");
        }
        return blog;
    }
}
