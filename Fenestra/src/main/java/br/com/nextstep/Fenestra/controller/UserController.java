package br.com.nextstep.Fenestra.controller;

import br.com.nextstep.Fenestra.model.User;
import br.com.nextstep.Fenestra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserRepository repository;

    @RequestMapping("/login")
    public String login(User user){
        return "login";
    }

    @RequestMapping("/log")
    public String log(){
        return "log";
    }

    @PostMapping("/login")
    public String save (User user, BindingResult result, HttpSession session, HttpServletRequest request){
        if(result.hasErrors()){
            return  "login";
        }
        User usuario = repository.findByNameAndPassword(user.getName(),user.getPassword());
        System.out.println(usuario);

        //adicionando os tributos do cliente na sessão
        session.setAttribute("user",user);
        request.getSession().setAttribute("user",user);
        return "redirect:/components";
    }


}
