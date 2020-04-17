package controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import services.VotingService;

@Controller
public class VotingController {

	private VotingService votingService;
	
	public VotingController() {
		this.votingService = new VotingService(new Date());
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model uiModel) {
		uiModel.addAttribute("restaurants", this.votingService.availableRestaurants());
		return "index";
	}

}
