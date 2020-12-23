package com.bookify.authentication.app.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookify.authentication.app.models.Trip;
import com.bookify.authentication.app.services.AppService;
import com.bookify.authentication.services.UserService;

@Controller
public class AppController {

	private final AppService appService;

	public AppController(AppService appService, UserService userService) {
		this.appService = appService;
	}

	@RequestMapping("/")
	public String index(Model model) {
		return "App/index.jsp";
	}

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public String process(@Valid @ModelAttribute("trip") Trip trip, BindingResult result, Model model,
			HttpSession session) {
		if (result.hasErrors()) {
			List<Trip> trips = appService.getAll();
			model.addAttribute("trips", trips);
			return "homePage.jsp";
		} else {
			Long userId = (Long) session.getAttribute("user_id");
			Trip t = appService.save(trip);
			t.setCreator(userId);
			return "redirect:/home";
		}
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		appService.deleteTrip(id);
		return "redirect:/home";
	}

}
