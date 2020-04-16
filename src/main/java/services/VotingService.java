package services;

import java.util.Map;

import org.springframework.stereotype.Service;

import models.Restaurant;
import models.User;

@Service
public class VotingService {

	private Map votingTable;
	
	public VotingService() {
		
	}
	
	public void vote(String document, String password, Restaurant restaurant) {
		User user = User.getUserByDoc(document);
		if ((user != null) && (user.isValidPassword(password))) {
			
		}
	}
}
