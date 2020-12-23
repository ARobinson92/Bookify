package com.bookify.authentication.app.controllers;

import java.util.List;

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

public class AppController {

	@Controller
	public class AppCtrl {
		private final AppService appService;

		public AppCtrl(AppService appService, UserService userService) {
			this.appService = appService;
		}

		@RequestMapping("/")
		public String index(Model model) {
			return "App/index.jsp";
		}

		@RequestMapping("/trips/new")
		public String addNew(@ModelAttribute("addNew") Trip trip, Model model) {
			return "new.jsp";
		}

		@RequestMapping(value = "/process", method = RequestMethod.POST)
		public String process(@Valid @ModelAttribute("addNew") Trip trip, BindingResult result, Model model) {
			if (result.hasErrors()) {
				List<Trip> trips = appService.getAll();
				model.addAttribute("trips", trips);
				return "new.jsp";
			} else {
				appService.addTrip(trip);
				return "redirect:/dashboard";
			}
		}

		@RequestMapping("/search/topten")
		public String topten(Model model) {
			List<Trip> trips = appService.topTen();
			model.addAttribute("trips", trips);
			return "topten.jsp";
		}

		@RequestMapping("/delete/{id}")
		public String delete(@PathVariable("id") Long id) {
			appService.deleteTrip(id);
			return "redirect:/home";
		}

	}

}