package com.werkstuck.demo.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.werkstuck.demo.Data.userDAO;
import com.werkstuck.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserManagementController {
    @Autowired
    public userDAO users;

    @GetMapping("/login")
    public String getLoginView(){

        return "fragments/LoginTemp :: login-page";
    }

    /* Überprüft ob der eingegebene Username und Password mit einem Registrierten User übereinstimmen, und erstellt eine HTTPSession Cookie mit den Username. */
    @PostMapping("/login")
    public String postLogin(HttpServletRequest request, @RequestBody String payload, HttpServletResponse response) throws JsonProcessingException {
        User requestUser = new ObjectMapper().readValue(payload, User.class);
        try {
            User loginUser = users.findByUsername(requestUser.getUsername());
            if (loginUser != null && loginUser.getUsername().equals(requestUser.getUsername()) && loginUser.getPassword().equals(requestUser.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("name", loginUser.getUsername());
                session.setAttribute("id", loginUser.getUserId());
                session.setMaxInactiveInterval(30 * 60);
                response.addHeader("login-result", "success");
                return "fragments/LoginTemp :: login-success";
            } else if (loginUser == null) {
                    return "fragments/LoginTemp :: no-user";
            } else {
                response.addHeader("login-result", "fail");
                return "fragments/LoginTemp :: login-fail";
            }
        } catch (IndexOutOfBoundsException e) {
            return "fragments/LoginTemp :: login-fail";
        }
    }
    /* Invalidiert die Session falls eine existiert, und setzt alles Cookies auf null um die vom Browser zu löschen. */
    @GetMapping("/logout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return "index";
    }

    @GetMapping("/register")
    public String getRegisterView(){
        return "fragments/RegisterView :: register-page";
    }

    @PostMapping("/register")
    public String postRegister(@RequestBody String payload) throws JsonProcessingException {
        User newUser = new ObjectMapper().readValue(payload, User.class);
        if(newUser.getUsername().contains("@") && newUser.getUsername().contains(".")){
            if(users.findByUsername(newUser.getUsername()) == null){
            users.save(newUser);
            return "fragments/LoginTemp :: register-success";}
            else
                return "fragments/RegisterView :: user-exists";
        }
        else {

            return "fragments/RegisterView :: register-fail";
        }
    }
}
