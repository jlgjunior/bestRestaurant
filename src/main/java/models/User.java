package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

public class User {

	
	private static Integer incId = 0;
	
	private Integer id;
	private String document;
	private String name;
	private String password;
	private List<Vote> votes;
	
	public User(String document, String name, String password) {
		this.id = getNextId();
		this.document = document;
		this.name = name;
		this.password = password;
		this.votes = new ArrayList<Vote>(){
		    public boolean add(Vote vote) {
		        int index = Collections.binarySearch(this, vote);
		        if (index < 0) index = ~index;
		        super.add(index, vote);
		        return true;
		    }
		};
	}
	
	private static final Map<String, User> users = new HashMap<String, User>() {
		private static final long serialVersionUID = 1L;
		{
		    put("1", new User("1", "Marcos", "1"));
		    put("2", new User("2", "Johnny B. Good", "2"));
		    put("3", new User("3", "Tom Bombadil", "3"));
		    put("4", new User("4", "Elodin", "4"));
		    put("5", new User("5", "Noé", "5"));
		}};
	
	public Integer getNextId() {
		incId++;
		return incId;
	}
	
	public boolean isValidPassword(String password) {
		return this.password.equals(password);
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

	public boolean hasVoted(Date date) {
		return (this.votes.size() > 0) && (DateUtils.isSameDay(this.votes.get(0).getDate(), date));
	}
}
