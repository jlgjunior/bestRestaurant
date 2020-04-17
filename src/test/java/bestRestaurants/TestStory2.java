package bestRestaurants;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import models.Restaurant;
import models.User;
import services.VotingService;

public class TestStory2 {

	@Test
	public void chooseRestaurantNumberThree() {
		Calendar calendar = GregorianCalendar.getInstance();
		Integer year, month, day;
		calendar.setTime(new Date()); 
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 11, 0);
		
		VotingService votingService = new VotingService(DateUtils.addDays(calendar.getTime(), 7));
		votingService.changeDate(DateUtils.addDays(calendar.getTime(), 1));
		User user = User.getUserByDoc("3");
		Restaurant restaurant = Restaurant.getRestaurantById(4);
		votingService.vote(user, restaurant);
		user = User.getUserByDoc("4");
		restaurant = Restaurant.getRestaurantById(3);
		votingService.vote(user, restaurant);
		user = User.getUserByDoc("5");
		votingService.vote(user, restaurant);
		assertEquals(Restaurant.getChosenRestaurant().getId(), restaurant.getId());
	}
	
	@Test
	public void cantChooseSameRestaurantDuringTheWeek() {
		Calendar calendar = GregorianCalendar.getInstance();
		Integer year, month, day;
		calendar.setTime(new Date()); 
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 11, 0);
		
		VotingService votingService = new VotingService(DateUtils.addDays(calendar.getTime(), 7));
		User user = User.getUserByDoc("3");
		Restaurant restaurant = Restaurant.getRestaurantById(5);
		votingService.vote(user, restaurant);
		votingService.changeDate(DateUtils.addDays(calendar.getTime(), 1));
		votingService.vote(user, restaurant);
		user = User.getUserByDoc("4");
		votingService.vote(user, restaurant);
		user = User.getUserByDoc("1");
		restaurant = Restaurant.getRestaurantById(4);
		votingService.vote(user, restaurant);
		assertEquals(Restaurant.getChosenRestaurant().getId(), restaurant.getId());
	}

}
