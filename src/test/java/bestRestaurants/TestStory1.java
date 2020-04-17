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

public class TestStory1 {

	@Test
	public void shouldNotVoteMoreThanOnceADay() {
		Calendar calendar = GregorianCalendar.getInstance();
		Integer year, month, day;
		calendar.setTime(new Date()); 
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 11, 0);
		
		VotingService votingService = new VotingService(DateUtils.addDays(calendar.getTime(), 7));
		User user = User.getUserByDoc("1");
		Restaurant restaurant = Restaurant.getRestaurantById(2);
		votingService.vote(user, restaurant);
		restaurant = Restaurant.getRestaurantById(3);
		assertFalse(votingService.vote(user, restaurant));
	}
}
