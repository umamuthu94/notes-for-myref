package com.practice.service;



import java.util.List;

import com.practice.model.UserInfo;
import com.practice.repository.UserRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.vertx.mutiny.ext.auth.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;



@ApplicationScoped
public class UserService {
    
    @Inject
    UserRepository userRepository;




  
    public List<UserInfo> getallUsers(){
        return userRepository.listAll(); 
    }

    // public void updateUser(UserInfo updatedUserInfo) {
    //     UserInfo existingUserInfo = userRepository.findById(updatedUserInfo.id);
    //     if (existingUserInfo != null) {
    //         existingUserInfo.setName(updatedUserInfo.getName());
    //         existingUserInfo.setAge(updatedUserInfo.getAge());
    //     }
    
    // }

    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserInfo getUserById(Long id) {
       UserInfo info = userRepository.findUserInfo(id);
    // UserInfo info = userRepository.findById(id);
       return info;
    }

    public void updateUser(UserInfo existingUserInfo) {
    }
}
