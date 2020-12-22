package com.bookify.authentication.app.controllers;
//package com.bookify.app.controllers;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.bookify.app.models.Trip;
//import com.bookify.app.services.AppService;
//
//public class AppController {
//
//	@Controller
//	public class AppCtrl {
//		private final AppService appService;
//
//		public AppCtrl(AppService appService) {
//			this.appService = appService;
//		}
//
//		@RequestMapping("/")
//		public String index(Model model) {
//			return "App/index.jsp";
//		}
//
//		@RequestMapping("/dashboard")
//		public String dashboard(Model model) {
//			List<Trip> trips = appService.allTrips();
//			model.addAttribute("trips", trips);
//			return "App/dashboard.jsp";
//		}
//
//		@RequestMapping("/trips/{id}")
//		public String trips(@PathVariable("id") Long id, Model model) {
//			Trip trip = appService.findTrip(id);
//			model.addAttribute("trip", trip);
//			return "App/trip.jsp";
//		}
//
//		@RequestMapping("/trips/new")
//		public String addNew(@ModelAttribute("addNew") Trip trip, Model model) {
//			return "App/new.jsp";
//		}
//
//		@RequestMapping(value = "/process", method = RequestMethod.POST)
//		public String process(@Valid @ModelAttribute("addNew") Trip trip, BindingResult result, Model model) {
//			if (result.hasErrors()) {
//				List<Trip> trips = appService.allTrips();
//				model.addAttribute("trips", trips);
//				return "App/new.jsp";
//			} else {
//				appService.addTrip(trip);
//				return "redirect:/dashboard";
//			}
//		}
//
//		@RequestMapping("/search/topten")
//		public String topten(Model model) {
//			List<Trip> trips = appService.getTopTen();
//			model.addAttribute("trips", trips);
//			return "App/topten.jsp";
//		}
//
//		@RequestMapping("/delete/{id}")
//		public String delete(@PathVariable("id") Long id) {
//			appService.deleteTrip(id);
//			return "redirect:/dashboard";
//		}
//
//		@RequestMapping("/search/{artist}")
//		public String search(@PathVariable("artist") String artist, Model model) {
//			List<Trip> trips = appService.getOne(id) (artist);
//			model.addAttribute("artist", artist);
//			model.addAttribute("trips", trips);
//			return "App/search.jsp";
//		}
//
//		@PostMapping("/search")
//		public String search(@RequestParam("artist") String artist) {
//			return "redirect:/search/" + artist;
//		}
//
//	}
//
//}
