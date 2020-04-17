package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VotingController {

	public VotingController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
