package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;

public class Vote implements Comparable<Vote> {
	
	private static List<Vote> votes = new ArrayList<Vote>(){
	    public boolean add(Vote vote) {
	        int index = Collections.binarySearch(this, vote);
	        if (index < 0) index = ~index;
	        super.add(index, vote);
	        return true;
	    }
	};
	private static Integer incId = 0;

	private Integer id;
	private Restaurant restaurant;
	private User user;
	private Date date;
	
	public Vote(Restaurant restaurant, User user) {
		this.id = getId();
		this.restaurant = restaurant;
		this.user = user;
		this.date = DateUtils.truncate(new Date(), Calendar.DATE);
		votes.add(this);
	}

	private Integer getId() {
		incId++;
		return incId;
	}
	
	public void rollbackVote() {
		incId--;
		votes.remove(this);
	}
	
	public static ArrayList<Vote> todayVotes(){
		Iterator<Vote> it = Vote.votes.iterator();
		ArrayList<Vote> votes = new ArrayList<Vote>();
		Vote vote;
		while (it.hasNext()) {
			vote = it.next();
			Date today = DateUtils.truncate(new Date(), Calendar.DATE);
			if (vote.getDate().before(today))
				break;
			else if (DateUtils.isSameDay(vote.getDate(), today)) {
				votes.add(vote);
			}				
		}
		return votes;
	}

	public Date getDate() {
		return this.date;
	}
	
	public int compareTo(Vote anotherVote) {
		if (DateUtils.isSameDay(this.date, anotherVote.getDate()))
			return 0;
		else if (this.date.before(anotherVote.getDate()))
			return 1;
		else
			return -1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
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
			Vote other = (Vote) obj;
		
			if (date == null) {
				if (other.date != null)
					return false;
				else
					return true;
			} 
			else if (!date.equals(other.date))
				return false;
			else if (restaurant == null) {
				return false;
			} 
			else if (!restaurant.equals(other.restaurant))
				return false;
			else if (user == null) {
				return false;
			}
			else if (!user.equals(other.user))
				return false;
			else
				return true;

		}
	}
	
	
}
