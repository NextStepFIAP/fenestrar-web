package br.com.nextstep.Fenestra.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.nextstep.Fenestra.model.Log;
import br.com.nextstep.Fenestra.repositories.LogRepository;

@Controller
public class LogController {

	@Autowired
	private LogRepository repository;
	
    @RequestMapping("/log/new")
    public ModelAndView newLog(Log log){
		ModelAndView modelAndView = new ModelAndView("logs-form");
		return modelAndView;
    }

	@PostMapping("/log")
	public String save(@Valid Log log, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) { //If has errors
			return "logs-form"; //Return to the same page
		}
		System.out.println(log.getId());
		System.out.println(log.getDescription());
		System.out.println(log.getDateRegistro());
		repository.save(log);
		return "redirect:components";

	}

}
