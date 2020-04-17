package services;

import models.Restaurant;
import models.User;

public class ChoosingService {

	public ChoosingService() {
	
	}

	public void ChooseRestaurant(String document, String password, Restaurant restaurant) {
		User user = User.getUserByDoc(document);
		if ((user != null) && (user.isValidPassword(password)) && (user.isFacilitator())) {
		
		}
	}
	
}
