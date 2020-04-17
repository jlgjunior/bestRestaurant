package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

	private static final Map<String, User> users = new HashMap<String, User>() {
		private static final long serialVersionUID = 1L;
		{
		    put("1", new User("1", "Marcos", "0", false));
		    put("2", new User("2", "Johnny B. Good", "1", false));
		    put("3", new User("3", "Tom Bombadil", "2", false));
		    put("4", new User("4", "Elodin", "3", true));
		}};
	
	private static Integer incId = 0;
	
	private Integer id;
	private String document;
	private String name;
	private String password;
	private boolean facilitator;
	private List<Vote> votes;
	
	public User(String document, String name, String password, boolean facilitator) {
		this.id = getId();
		this.document = document;
		this.name = name;
		this.password = password;
		this.votes = new ArrayList<Vote>();
		this.facilitator = facilitator;
	}
	
	public Integer getId() {
		incId++;
		return incId;
	}
	
	public boolean isValidPassword(String password) {
		return this.password == password;
	}
	
	public boolean isFacilitator() {
		return this.facilitator;
	}
	
	public HashMap<String, User> getUsers(){
		return (HashMap<String, User>) User.users;
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
			User other = (User) obj;
		
			if (other == null)
				return false;
			else
				return this.id == other.id;
		}
	}
	
	public void addVote(Vote vote) {
		this.votes.add(vote);
	}

	public static User getUserByDoc(String document) {
		return User.users.get(document);
	}
}
