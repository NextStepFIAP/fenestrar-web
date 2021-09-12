package br.com.nextstep.Fenestra.controller;

import br.com.nextstep.Fenestra.model.Componente;
import br.com.nextstep.Fenestra.model.Log;
import br.com.nextstep.Fenestra.repositories.ComponenteRepository;
import br.com.nextstep.Fenestra.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ComponentsController {

    @Autowired
    LogRepository logRepository;
    @Autowired
    ComponenteRepository componenteRepository;

    @RequestMapping("/components")
    public String components(HttpSession session){
        List<Log> logs = new ArrayList<Log>();
        List<Componente> componentes = new ArrayList<Componente>();

       componentes = componenteRepository.findAll();
       logs = logRepository.findAll();

        session.setAttribute("logs",logs);
        session.setAttribute("components",componentes);
        return "components";
    }
}
