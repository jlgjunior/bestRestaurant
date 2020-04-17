package controllers;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.apache.commons.lang.time.DateUtils;

import models.Restaurant;
import models.User;
import services.VotingService;
import views.VotingView;


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
		String option = "";
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println(this.votingView.renderOptions());
			option = scan.nextLine();
			if (option.equals("1")) {
				vote(scan);
			}
			else if (option.equals("2")) {
				nextDay();
			}
			else if (option.equals("3")) {
				setElevenAM();
			}
			else if (option.equals("4")) {
				setOnePM();
			}
			else if (option.equals("5")) {
				showResult(scan);
			}
			else if (!option.equals("6")) {
				System.out.println("Invalid option\n");
			}
		}
		while (!option.equals("6"));
		scan.close();
		
	}
	
	private void showResult(Scanner scan) {
		ArrayList<Restaurant> restaurants = this.votingService.availableRestaurants();
		System.out.println(this.votingView.renderCurrentWinner(Restaurant.getChosenRestaurant(), restaurants));
		System.out.println("Press Enter key to continue...");
		scan.nextLine();
	}

	private void setOnePM() {
		Calendar calendar = GregorianCalendar.getInstance();
		Integer year, month, day;
		calendar.setTime(this.date); 
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 13, 0);
		this.date = calendar.getTime();
		this.votingService.changeDate(this.date);
	}

	private void setElevenAM() {
		Calendar calendar = GregorianCalendar.getInstance();
		Integer year, month, day;
		calendar.setTime(this.date); 
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 11, 0);
		this.date = calendar.getTime();
		this.votingService.changeDate(this.date);
	}

	private void nextDay() {
		this.date = DateUtils.addDays(this.date, 1);
		this.votingService.changeDate(this.date);
	}

	public void vote(Scanner scan) {
		String restaurantsScreen; 
		ArrayList<Restaurant> restaurants = this.votingService.availableRestaurants();
		restaurantsScreen = this.votingView.renderCurrentRestaurants(restaurants, 
					                                          this.date);
		System.out.println(restaurantsScreen);
			
		User user = authenticateUser(scan);
		if (user != null) {
			validateVoting(user, restaurants, scan);
		}
		
		System.out.println("Press Enter key to continue...");
		scan.nextLine();
	}
	

	private void validateVoting(User user, ArrayList<Restaurant> restaurants, Scanner scan) {
		boolean error = true;
		try {
			System.out.println("Enter the id of your chosen restaurant:");
			Integer restaurantId = Integer.valueOf(scan.nextLine());
			Restaurant restaurant = Restaurant.getRestaurantById(restaurantId);
			Integer restaurantIndex = restaurants.indexOf(restaurant);
			if (restaurantIndex > -1) {
				error = !this.votingService.vote(user, restaurants.get(restaurantIndex));
			}
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

	private User authenticateUser(Scanner scan) {
		System.out.println("Enter your document:");
		String strDocument = scan.nextLine();
		
		User user = User.getUserByDoc(strDocument);
		System.out.println("Enter your password:");
		String password = new String(scan.nextLine());

		if ((user == null) || !user.isValidPassword(password)) {
			System.out.println("Invalid username or password\n");
			return null;
		}
		else
			return user;
	}

}
