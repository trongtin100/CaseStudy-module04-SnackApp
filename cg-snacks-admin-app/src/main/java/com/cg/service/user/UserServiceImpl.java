package com.cg.service.user;

import com.cg.model.entity.User;
import com.cg.model.dto.RoleDto;
import com.cg.model.dto.UserDto;
import com.cg.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Iterable<UserDto> findAll() {
        Iterable<User> entities = userRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        Optional <User> user = userRepository.findById(id);
        return Optional.ofNullable(modelMapper.map(user, UserDto.class));
    }

    @Override
    public void save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (!userDto.getPassword().isEmpty()) {
            String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(5));
            user.setPassword(hashedPassword);
        }
        userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Iterable<UserDto> findAllByRole(RoleDto roleDto) {
        Iterable<User> entities = userRepository.findAllByRole(roleDto);
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        Page<User> entities = userRepository.findAll(pageable);
        List<UserDto> dtos = new ArrayList<>(
                entities.stream()
                        .parallel()
                        .map(entity -> modelMapper.map(entity, UserDto.class))
                        .collect(Collectors.toList()));
        return new PageImpl<>(dtos);
    }

    @Override
    public Page<UserDto> findAllByFullNameContaining(String fullName, Pageable pageable) {
        Page<User> entities = userRepository.findAllByFullNameContaining(fullName, pageable);
        List<UserDto> dtos = new ArrayList<>(
                entities.stream()
                        .parallel()
                        .map(entity -> modelMapper.map(entity, UserDto.class))
                        .collect(Collectors.toList()));
        return new PageImpl<>(dtos);
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null
                || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
