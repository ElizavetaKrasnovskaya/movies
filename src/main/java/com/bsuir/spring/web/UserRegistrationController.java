package com.bsuir.spring.web;

import com.bsuir.spring.model.User;
import com.bsuir.spring.repository.UserRepository;
import com.bsuir.spring.service.user.UserService;
import com.bsuir.spring.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        Optional<User> user = userRepository.findByUsername(registrationDto.getUsername());
        if (user.isPresent()) {
            return "redirect:/registration?failed";
        } else {
            userService.save(registrationDto);
        }
        return "redirect:/registration?success";
    }
}