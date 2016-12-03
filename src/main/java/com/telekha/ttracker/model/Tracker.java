package com.telekha.ttracker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("T")
public class Tracker extends User{

	@OneToMany(cascade={CascadeType.ALL},mappedBy="tracker")
	private Set<Subscribe> subscriptions = new HashSet<Subscribe>();

	public Set<Subscribe> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscribe> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
}
