package com.bookify.authentication.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.bookify.authentication.app.models.Trip;
import com.bookify.authentication.app.services.AppService;
import com.bookify.authentication.models.User;
import com.bookify.authentication.services.UserService;
import com.bookify.authentication.validators.UserValidator;

@Controller
public class UserController {
	private final UserService userService;
	private final AppService appService;

    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator, AppService appService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.appService = appService;
    }
    
    @GetMapping("/")
    public String index(@ModelAttribute("user") User user) {
    	return "index.jsp";
    }

	@RequestMapping("/registration")
	public String registerForm(@ModelAttribute("user") User user) {
		return "registrationPage.jsp";
	}

	@RequestMapping("/login")
	public String login() {
		return "loginPage.jsp";
	}

	@RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        userValidator.validate(user, result);
        if(result.hasErrors()) {
            return "registrationPage.jsp";
        }
        User u = userService.registerUser(user);
        session.setAttribute("user_id", u.getId());
        return "redirect:/home";
    }

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session) {
		boolean isAuthenticated = userService.authenticateUser(email, password);
		if(isAuthenticated) {
			User u = userService.findByEmail(email);
			session.setAttribute("user_id", u.getId());
			return "redirect:/home";
		} else {
			model.addAttribute("error", "Invalid Credentials. Please try again.");
			return "loginPage.jsp";
		}
	}

	@RequestMapping("/home")
	public String home(HttpSession session, Model model, @ModelAttribute("user") User user, @ModelAttribute("trip") Trip trip) {
		Long userId = (Long) session.getAttribute("user_id");
		User u = userService.findUserById(userId);
		List<Trip> trips = appService.getAll();
		model.addAttribute("user", u);
		model.addAttribute("trips", trips);
		return "homePage.jsp";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
	@GetMapping("/trips/{id}")
	public String trips(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		User u = userService.findUserById(userId);
		model.addAttribute("userEmail", u.getEmail());
		Trip trip = appService.getOne(id);
		List<User> users = trip.getUsers();
		model.addAttribute("trip", trip);
		model.addAttribute("users", users);
		return "trip.jsp";
	}
	
	@PostMapping("/trips/{id}")
	public String addToTrip(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		User u = userService.findUserById(userId);
		Trip trip = appService.getOne(id);
		trip.getUsers().add(u);
		appService.save(trip);
		return "redirect:/trips/{id}";
	}
}