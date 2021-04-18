package com.xyz.user.service;

import com.xyz.user.VO.Department;
import com.xyz.user.VO.ResponseTempleteVO;
import com.xyz.user.entity.User;
import com.xyz.user.respository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.debug("inside saveUser");
        return userRepository.save(user);
    }

    public ResponseTempleteVO findbyUserId(Long userId) {
        log.info("inside ResponseTempleteVO");
        ResponseTempleteVO vo = new ResponseTempleteVO();
        User user = userRepository.findByUserId(userId);

        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                Department.class);

        vo.setUser(user);
        vo.setDepartment(department);
        log.info("exit ResponseTempleteVO");
        return vo;
    }
}
