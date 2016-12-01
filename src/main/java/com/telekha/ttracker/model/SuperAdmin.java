package com.telekha.ttracker.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
public class SuperAdmin extends User{

}
