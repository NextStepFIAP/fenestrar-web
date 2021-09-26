package br.com.nextstep.Fenestra.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.nextstep.Fenestra.model.Componente;
import br.com.nextstep.Fenestra.model.Log;
import br.com.nextstep.Fenestra.repositories.ComponenteRepository;
import br.com.nextstep.Fenestra.repositories.LogRepository;


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
    
    @RequestMapping("/components/new")
    public ModelAndView newComponent(Componente componente){
		ModelAndView modelAndView = new ModelAndView("components-form");
		return modelAndView;
    }

	@PostMapping("/components")
	public String save(@Valid Componente componente, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) { //If has errors
			return "components-form"; //Return to the same page
		}
		componenteRepository.save(componente);
		return "redirect:components";
	}

}
