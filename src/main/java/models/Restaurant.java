package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

public class Restaurant {
	
	private static final Map<Integer, Restaurant> restaurants = new HashMap<Integer, Restaurant>() {
		private static final long serialVersionUID = 1L;
		{
		    put(1, new Restaurant("Infront"));
		    put(2, new Restaurant("OrangeFlies"));
		    put(3, new Restaurant("Lados do Rio"));
		    put(4, new Restaurant("Xis 86"));
		    put(5, new Restaurant("Zé do Bar"));
		    put(6, new Restaurant("GT Café"));
		    put(7, new Restaurant("Panorama"));
		}};
		

	private static Map<Integer, Restaurant> unvisitedRestaurants = new HashMap<Integer, Restaurant>() {
		private static final long serialVersionUID = 1L;
		{
		    	put(1, new Restaurant("Infront"));
			    put(2, new Restaurant("OrangeFlies"));
			    put(3, new Restaurant("Lados do Rio"));
			    put(4, new Restaurant("Xis 86"));
			    put(5, new Restaurant("Zé do Bar"));
			    put(6, new Restaurant("GT Café"));
			    put(7, new Restaurant("Panorama"));
		}};

	private static Map<Integer, Restaurant> visitedRestaurants = new HashMap<Integer, Restaurant>();
			
	private static Integer incId = 0;
	private static Restaurant mostVoted = null;
	
	private Integer id;
	private String name;
	private List<Vote> votes;
	
	public Restaurant(String name) {
		this.id = getId();
		this.name = name;
		this.votes = new ArrayList<Vote>(){
		    public boolean add(Vote vote) {
		        int index = Collections.binarySearch(this, vote);
		        if (index < 0) index = ~index;
		        super.add(index, vote);
		        return true;
		    }
		};
	}
	
	public Integer getId() {
		incId++;
		return incId;
	}
	
	public HashMap<Integer, Restaurant> getRestaurants(){
		return (HashMap<Integer, Restaurant>) Restaurant.restaurants;
	}
	
	public Integer totalVotesByDate(Date date) {
		Integer total = 0;
		Iterator<Vote> it = this.votes.iterator();
		Vote vote;
		while (it.hasNext()) {
			vote = it.next();
			date = DateUtils.truncate(date, Calendar.DATE);
			if (vote.getDate().before(date))
				break;
			else if (DateUtils.isSameDay(vote.getDate(), date)) {
				++total;
			}				
		}
		
		return total;
	}

	public void addVote(Vote vote) {
		Date voteDate = vote.getDate();
		this.votes.add(vote);
		if ((Restaurant.mostVoted == null)
				|| (this.totalVotesByDate(voteDate) > Restaurant.mostVoted.totalVotesByDate(voteDate))) {
			Restaurant.mostVoted = this;
		}
	}
	
	private static void checkRestaurant(Restaurant restaurant) {
		restaurant = Restaurant.unvisitedRestaurants.remove(restaurant.getId());
		if (restaurant != null) {
			Restaurant.visitedRestaurants.put(restaurant.getId(), restaurant);
		}
	}
	
	public static Restaurant getChosenRestaurant() {
		return Restaurant.mostVoted;
	}
	
	public static void nextDay() {
		if (Restaurant.mostVoted != null) {
			checkRestaurant(Restaurant.mostVoted);
			Restaurant.mostVoted = null;
		}
	}
	
	public static HashMap<Integer, Restaurant> availableRestaurants() {
		return (HashMap<Integer, Restaurant>) unvisitedRestaurants;
	}
	
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + "]";
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else if (obj == null)
			return false;
		else if (getClass() != obj.getClass())
			return false;
		else{
			Restaurant other = (Restaurant) obj;
		
			if (other == null)
				return false;
			else
				return this.id == other.id;
		}
	}

	public static Restaurant getRestaurantById(Integer restaurantId) {
		return Restaurant.unvisitedRestaurants.get(restaurantId);
	}
}
