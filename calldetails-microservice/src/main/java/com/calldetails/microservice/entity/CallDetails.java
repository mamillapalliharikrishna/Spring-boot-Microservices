package com.calldetails.microservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="call_details")
public class CallDetails {
	@Id
	@Column(name="call_id")
	private  Long  callId;
	
	@Column(name="called_by")
	private  Long  calledBy;
	
	@Column(name="called_to")
	private  Long  calledTo;
	
	@Column(name="called_on")
	@Temporal(TemporalType.DATE)
	private  Date  calledOn;

	@Column(name="duration")
	private  Integer  duration;

	
	public Long getCallId() {
		return callId;
	}

	public void setCallId(Long callId) {
		this.callId = callId;
	}

	public Long getCalledBy() {
		return calledBy;
	}

	public void setCalledBy(Long calledBy) {
		this.calledBy = calledBy;
	}

	public Long getCalledTo() {
		return calledTo;
	}

	public void setCalledTo(Long calledTo) {
		this.calledTo = calledTo;
	}

	public Date getCalledOn() {
		return calledOn;
	}

	public void setCalledOn(Date calledOn) {
		this.calledOn = calledOn;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	
	
	
}
