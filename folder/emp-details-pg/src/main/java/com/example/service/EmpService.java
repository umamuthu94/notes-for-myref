package com.example.service;

import java.util.List;

import com.example.model.EmpDetails;
import com.example.repository.EmpRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmpService {
    
    @Inject
    EmpRepo empRepo;


    public List<EmpDetails> getallEmpDetails() {
        return empRepo.listAll();
    }

    
public EmpDetails updateEmpDetails(Long id, EmpDetails emp) {
    EmpDetails empDetails = empRepo.findById(id);
    empDetails.setName(emp.getName());
    empDetails.setAge(emp.getAge());
    empDetails.setMobilenumber(emp.getMobilenumber());
    return empDetails;
}

public void deletEmpDetails(Long id){
    empRepo.deleteById(id);
}

public void deleteEmpByName(String name) {
    empRepo.deleteByName(name);
}

public void deleteByIdandName(Long id, String name) {
    empRepo.deleteByIdandName(id, name);
}

public EmpDetails getByName(String name) {
    return empRepo.getName(name);
}

public EmpDetails getEmpDetailsByIdandName(Long id, String name) {
    return empRepo.getIdandName(id, name);
}

public EmpDetails getById(Long id) {
    return empRepo.findById(id);
}
}
