package com.bsuir.spring.service.user;

import com.bsuir.spring.model.User;
import com.bsuir.spring.web.dto.UserRegistrationDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void deleteById(int id);
    User findByUsername(String username);

    User update(User user);

    User save(UserRegistrationDto registrationDto);

    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}