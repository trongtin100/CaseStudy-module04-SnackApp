package com.cg.service.user;

import com.cg.model.dto.RoleDto;
import com.cg.model.dto.UserDto;
import com.cg.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService extends IGeneralService<UserDto> {
    Iterable<UserDto> findAllByRole(RoleDto roleDto);
    Iterable<UserDto> findAll();
    Page<UserDto> findAll(Pageable pageable);
    Page<UserDto> findAllByFullNameContaining(String fullName, Pageable pageable);

    boolean isAuthenticated();
}
