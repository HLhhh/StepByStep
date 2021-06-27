package com.csyl.web.login;

import com.csyl.deserialization.JsonUtil;
import com.csyl.dto.UserDTO;
import com.csyl.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 霖
 */
@RestController
@RequestMapping("/login/v1")
public class Login {


    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "put", method = RequestMethod.PUT)
    public String put(@RequestBody UserDTO userDTO) {
        userService.put(userDTO.bulider(userDTO));
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "get/{loginName}", method = RequestMethod.GET)
    public String get(@PathVariable String loginName) {
        return JsonUtil.objToJson(new UserDTO().reverse(userService.getUUser(loginName)));
    }
}
