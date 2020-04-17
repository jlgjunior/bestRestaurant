package services;

import java.util.Map;

import org.springframework.stereotype.Service;

import models.Restaurant;
import models.User;
import models.Vote;

@Service
public class VotingService {

	public VotingService() {
		
	}
	
	public void vote(String document, String password, Restaurant restaurant) {
		User user = User.getUserByDoc(document);
		if ((user != null) && (user.isValidPassword(password))) {
			Vote vote = new Vote(restaurant, user);
			if (Vote.todayVotes().contains(vote)) {
				vote.rollbackVote();
			}
			else {
				user.addVote(vote);
				restaurant.addVote(vote);
				Vote.addVote(vote);
			}
		}
	}
}
