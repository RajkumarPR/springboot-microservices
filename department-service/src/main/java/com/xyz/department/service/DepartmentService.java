package com.xyz.department.service;

import com.xyz.department.entity.Department;
import com.xyz.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        log.info("Inside save department");
        return departmentRepository.save(department);
    }

    public Department getDepartmentId(Long departmentId) {
        log.info("inside getDepartmentId");
        return departmentRepository.findByDepartmentId(departmentId);
    }

}
