package services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import models.Restaurant;
import models.User;
import models.Vote;

@Service
public class VotingService {

	private Date date;
	
	public VotingService(Date date) {
		this.date = date;
	}
	
	public void vote(String document, String password, Restaurant restaurant) {
		User user = User.getUserByDoc(document);
		if ((user != null) && (user.isValidPassword(password)) 
				&& (!LocalTime.now().isAfter(LocalTime.NOON))) {
			Vote vote = new Vote(restaurant, user, this.date);
			if ((Vote.getVotesByDate(this.date).contains(vote))
					|| (!Restaurant.availableRestaurants().containsKey(restaurant.getId()))) {
				vote.rollbackVote();
			}
			else {
				user.addVote(vote);
				restaurant.addVote(vote);
				Vote.addVote(vote);
			}
		}
	}
	
	public ArrayList<Restaurant> availableRestaurants(){
		ArrayList<Restaurant> list = new ArrayList<Restaurant>();
		Iterator<Restaurant> it = Restaurant.availableRestaurants().values().iterator();
		Restaurant topVotedRestaurant = Restaurant.getChosenRestaurant();
		if (topVotedRestaurant != null) {
			list.add(topVotedRestaurant);
			while (it.hasNext()) {
				Restaurant restaurant = it.next();
				if (restaurant != topVotedRestaurant)
					list.add(restaurant);
			}
		}
		
		return list;	
	}
	
	public void nextDay() {
		this.date = DateUtils.addDays(this.date, 1);
		Restaurant.nextDay();
	}
}
