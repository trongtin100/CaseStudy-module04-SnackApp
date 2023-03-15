package com.cg.controller;

import com.cg.model.dto.RoleDto;
import com.cg.model.dto.UserDto;
import com.cg.service.role.IRoleService;
import com.cg.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @ModelAttribute("roleDtos")
    public Iterable<RoleDto> roleDtos(){
        return roleService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("userDto", new UserDto());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveUser(@ModelAttribute("userDto") UserDto userDto) {
        userService.save(userDto);
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("userDto", new UserDto());
        modelAndView.addObject("message", "New user created successfully");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView listUsers(@RequestParam("search") Optional<String> search, Pageable pageable){
        Page<UserDto> userDtos;
        if(search.isPresent()){
            userDtos = userService.findAllByFullNameContaining(search.get(), pageable);
        } else {
            userDtos = userService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/user/list");
        modelAndView.addObject("userDtos", userDtos);
        return modelAndView;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<UserDto> userDto = userService.findById(id);
        ModelAndView modelAndView;
        if (userDto.isPresent()) {
            modelAndView = new ModelAndView("/user/edit");
            modelAndView.addObject("userDto", userDto.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView updateUser(@ModelAttribute("userDto") UserDto userDto) {
        userService.save(userDto);
        ModelAndView modelAndView = new ModelAndView("/user/edit");
        modelAndView.addObject("roleDto", userDto.getRole());
        modelAndView.addObject("message", "User updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<UserDto> userDto = userService.findById(id);
        ModelAndView modelAndView;
        if (userDto.isPresent()) {
            modelAndView = new ModelAndView("/user/delete");
            modelAndView.addObject("userDto", userDto.get());
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute("user") UserDto userDto) {
        userService.remove(userDto.getId());
        return "redirect:list";
    }
}
