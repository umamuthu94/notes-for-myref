package com.practice.repository;

import com.practice.model.UserInfo;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.vertx.mutiny.ext.auth.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserInfo, Long>  {
        public UserInfo findUserInfo(Long id){
        UserInfo userInfo = UserInfo.findById(id);
        // UserInfo info = userInfo.firstResult();
        return userInfo; 
        }
}
