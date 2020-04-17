package controllers;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import models.Restaurant;
import models.User;
import services.VotingService;
import views.VotingView;


@Controller
public class VotingController {

	private VotingService votingService;
	private VotingView votingView;
	private Date date;
	
	public VotingController(Date date) {
		this.date = date;
		this.votingService = new VotingService(date);
		this.votingView = new VotingView();
	}
	
	public void index() {
		Console console = System.console();
		String screen; 
		String options[] = {"1", "2", "3"};
		String option = "";
		do {
			this.votingView.renderOptions();
			option = console.readLine();
			if (option == "1") {
				vote();
			}
			else if (option == "2") {
				nextDay();
			}
			else if (option != "3") {
				System.out.println("Invalid option\n");
			}
		}
		while (!option.equals("3"));
	}
	
	private void nextDay() {
		DateUtils.addDays(this.date, 1);
		this.votingService.nextDay();
	}

	public void vote() {
		String screen; 
		String options[] = {"1", "2", "3"};
		String option = "";
		do {
			showOptions();

			System.out.println("Invalid option\n");
			ArrayList<Restaurant> restaurants = this.votingService.availableRestaurants();
			screen = this.votingView.renderCurrentRestaurants(restaurants, 
					                                          this.date);
			System.out.println(screen);
			
			User user = authenticateUser();
			if (user != null) {
				validateVoting(user, restaurants);
			}
		}
		while (!option.equals("3"));
	}
	

	private void validateVoting(User user, ArrayList<Restaurant> restaurants) {
		boolean error = true;
		try {
			Console console = System.console();
			System.out.println("Enter the id of your chosen restaurant:");
			Integer restaurantId = Integer.valueOf(console.readLine());
			Restaurant restaurant = Restaurant.getRestaurantById(restaurantId);
			Integer restaurantIndex = restaurants.indexOf(restaurant);
			if (restaurantIndex > -1) {
				error = !this.votingService.vote(user, restaurants.get(restaurantIndex));
			}
			else
				error = false;
		}
		catch (Exception e) {
			error = true;
		}
		finally {
			if (error) {
				System.out.println("Vote not computed");
			}
			else
				System.out.println("Vote successful!");
		}
	}

	private User authenticateUser() {
		Console console = System.console();
		System.out.println("Enter your document:");
		String strDocument = console.readLine();
	
		User user = User.getUserByDoc(strDocument);
		System.out.println("Enter your password:");
		String password = new String(console.readPassword());
	
		if ((user == null) || !user.isValidPassword(password)) {
			System.out.println("Invalid username or password\n");
			return null;
		}
		else
			return user;
	}

}
