package com.getitnow.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Counter")
public class CounterEntity {
  @Id
  private Integer counter;

public Integer getCounter() {
	return counter;
}

public void setCounter(Integer counter) {
	this.counter = counter;
}
}
