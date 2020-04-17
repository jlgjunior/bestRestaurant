package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.apache.commons.lang.time.DateUtils;

import models.Restaurant;
import models.User;
import models.Vote;

public class VotingService {

	private Date date;
	
	public VotingService(Date date) {
		this.date = date;
	}
	
	public boolean vote(User user, Restaurant restaurant) {
		boolean result = false;
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(this.date);
		if (!(calendar.get(Calendar.HOUR_OF_DAY) >= 12) && (!user.hasVoted(this.date))) {
			Vote vote = new Vote(restaurant, user, this.date);
			if ((Vote.getVotesByDate(this.date).contains(vote))
					|| (!Restaurant.availableRestaurants().containsKey(restaurant.getId()))) {
				vote.rollbackVote();
			}
			else {
				user.addVote(vote);
				restaurant.addVote(vote);
				Vote.addVote(vote);
				result = true;
			}
		}
		return result;
	}
	
	public ArrayList<Restaurant> availableRestaurants(){
		ArrayList<Restaurant> list = new ArrayList<Restaurant>();
		Iterator<Restaurant> it = Restaurant.availableRestaurants().values().iterator();
		Restaurant topVotedRestaurant = Restaurant.getChosenRestaurant();
		if (topVotedRestaurant != null) {
			list.add(topVotedRestaurant);
		}
		while (it.hasNext()) {
			Restaurant restaurant = it.next();
			if (restaurant != topVotedRestaurant)
				list.add(restaurant);
		}
		
		return list;	
	}
	
	public void changeDate(Date date) {
		Date oldDate = this.date;
		this.date = date;
		if (!DateUtils.isSameDay(this.date, oldDate)) {
			Restaurant.changeDay();
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(this.date);
			calendar.setMinimalDaysInFirstWeek(7);
			Integer week = calendar.get(Calendar.WEEK_OF_YEAR);
			calendar.setTime(oldDate);
			calendar.setMinimalDaysInFirstWeek(7);
			if (week != calendar.get(Calendar.WEEK_OF_YEAR)) {
				Restaurant.resetOptions();
			}
		}
	}
}
