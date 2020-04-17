package bestRestaurants;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import models.Restaurant;
import models.User;
import services.VotingService;
import views.VotingView;

public class TestStory3 {

	@Test
	public void showWinner() {
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
		ArrayList<Restaurant> restaurants = votingService.availableRestaurants();
		
		assertEquals(Restaurant.getChosenRestaurant().toString(), new VotingView().renderCurrentWinner(Restaurant.getChosenRestaurant(), restaurants));
	}
	
}
