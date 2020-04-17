package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static Restaurant chosenOne = null;
	
	private Integer id;
	private String name;
	private List<Vote> votes;
	
	public Restaurant(String name) {
		this.id = getId();
		this.name = name;
		this.votes = new ArrayList<Vote>();
	}
	
	private Integer getId() {
		incId++;
		return incId;
	}
	
	public HashMap<Integer, Restaurant> getRestaurants(){
		return (HashMap<Integer, Restaurant>) Restaurant.restaurants;
	}
	
	public Integer totalVotes() {
		return this.votes.size();
	}

	public void addVote(Vote vote) {
		this.votes.add(vote);
	}
	
	public void checkRestaurant(Restaurant restaurant) {
		restaurant = Restaurant.unvisitedRestaurants.remove(restaurant.getId());
		if (restaurant != null) {
			Restaurant.visitedRestaurants.put(restaurant.getId(), restaurant);
		}
	}
	
	
	public static HashMap<Integer, Restaurant> availableRestaurants() {
		return (HashMap<Integer, Restaurant>) unvisitedRestaurants;
	}
	
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", votes=" + totalVotes() + "]";
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
