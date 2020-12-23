package com.bookify.authentication.app.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookify.authentication.app.models.Trip;
import com.bookify.authentication.app.services.AppService;
import com.bookify.authentication.services.UserService;

@Controller
public class AppController {

	private final AppService appService;

		public AppCtrl(AppService appService, UserService userService) {
			this.appService = appService;
		}

		@RequestMapping("/")
		public String index(Model model) {
			return "index.jsp";
		}

		@RequestMapping("/trips/new")
		public String addNew(@ModelAttribute("addNew") Trip trip, Model model) {
			return "new.jsp";
		}

		@RequestMapping(value = "/process", method = RequestMethod.POST)
		public String process(@Valid @ModelAttribute("addNew") Trip trip, BindingResult result, Model model, HttpSession session) {
			if (result.hasErrors()) {
				List<Trip> trips = appService.getAll();
				model.addAttribute("trips", trips);
				return "new.jsp";
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
		
		@GetMapping("/trips/{id}/edit")
		public String editForm(@PathVariable("id") Long id, Model model, HttpSession session) {
			Long userId = (Long) session.getAttribute("user_id");
			Trip trip = appService.getOne(id);
			if(trip.getCreator_id() == userId) {
				model.addAttribute("trip", trip);
				return "edit.jsp";
			} else {
				return "redirect:/home";
			}
		}
		
		@PostMapping("/trips/{id}/edit")
		public String edit(@Valid @ModelAttribute("trip") Trip trip, BindingResult result) {
	    	if(result.hasErrors()) {
	    		return "edit.jsp";
	    	} else {
	    		appService.save(trip);
	    		return "redirect:/home";
	    	}
	    }
}
