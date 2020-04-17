package views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import models.Restaurant;

public class VotingView {

	public VotingView() {
		
	}
	
	public String renderCurrentRestaurants(ArrayList<Restaurant> restaurants, Date date) {
		String options = "";
		Iterator<Restaurant> it = restaurants.iterator();
		Restaurant restaurant;
		if (it.hasNext()) {
			restaurant = it.next();
			options = "Currently most voted: "+restaurant + " votes: " + restaurant.totalVotesByDate(date) + "\n";
		}
		while (it.hasNext()){
			restaurant = it.next();
			options += restaurant + " votes: " + restaurant.totalVotesByDate(date) + "\n";
		}
		
		return options;
	}

	public String renderOptions() {
		String options = "1 - Vote\n";
		options += "2 - Next day\n";
		options += "3 - Set hour to 11AM\n";
		options += "4 - Set hour to 1PM\n";
		options += "5 - Show current winner\n";
		options += "6 - Exit system\n";
		options += "Choose one of the options above:\n";
		return options;
	}

	public String renderCurrentWinner(Restaurant mostVotedRestaurant, ArrayList<Restaurant> restaurants) {
		String winner = "No restaurant registered";
		if (mostVotedRestaurant != null)
			winner = mostVotedRestaurant.toString();
		else if (restaurants.size() > 0)
			winner = restaurants.get(0).toString();
		return winner;
	}

}
