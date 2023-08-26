package com.example.repository;



import com.example.model.EmpDetails;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmpRepo implements PanacheRepository<EmpDetails> {

    public EmpDetails getName(String name) {
        return find("name",name).firstResult();
    }

    public EmpDetails getIdandName(Long id, String name) {
        return find("id=?1 and name=?2",id,name).firstResult();
    }
    public void deleteByName(String name) {
        delete("name", name);
    }

    public void deleteByIdandName(Long id,String name) {
        delete("id=?1 and name=?2",id,name);
    }


}

