package bestRestaurants;

import java.util.Date;

import controllers.VotingController;

public class App {

	private VotingController votingController;
	
	public App() {
		this.votingController = new VotingController(new Date());
	}
	
	public void run() {
		this.votingController.index();
	}

}
