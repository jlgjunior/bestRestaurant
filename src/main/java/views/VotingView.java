package views;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import models.Restaurant;

public class VotingView {

	public VotingView() {
		
	}
	
	public String renderCurrentRestaurants(ArrayList<Restaurant> restaurants, Date date) {
		String options = "";
		Iterator<Restaurant> it = restaurants.iterator();
		while (it.hasNext()){
			Restaurant restaurant = it.next();
			options += restaurant + " votes: " + restaurant.totalVotesByDate(date) + "\n";
		}
		
		return options;
	}

	public String renderOptions() {
		String options = "1 - Vote\n";
		options += "2 - Next day\n";
		options += "3 - Exit system\n";
		options += "Choose one of the options above:\n";
		return options;
	}

}
