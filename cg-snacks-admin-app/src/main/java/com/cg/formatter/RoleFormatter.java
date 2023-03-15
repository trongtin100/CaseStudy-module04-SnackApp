//package com.cg.formatter;
//
//import com.cg.model.dto.RoleDto;
//import com.cg.service.role.IRoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.Formatter;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.util.Locale;
//import java.util.Optional;
//
//@Component
//public class RoleFormatter implements Formatter<RoleDto> {
//
//    private IRoleService roleService;
//
//    @Autowired
//    public RoleFormatter(IRoleService roleService) {
//        this.roleService = roleService;
//    }
//
//    @Override
//    public RoleDto parse(String text, Locale locale) throws ParseException {
//        Optional<RoleDto> roleDto = roleService.findById(Long.parseLong(text));
//        return roleDto.orElse(null);
//    }
//
//    @Override
//    public String print(RoleDto object, Locale locale) {
//        return "[" + object.getId() + ", "
//                + object.getName() + ", "
//                + object.getDesc() + "]";
//    }
//}
//
