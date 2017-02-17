package com.prettymuch.blackjack;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Record {

	
	private int round;
	private int chipsRecord;
	private String name;
	private String date;
	
	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public int getRound() {
		return round;
	}
	@XmlElement
	public void setRound(int round) {
		this.round = round;
	}
	public int getChipsRecord() {
		return chipsRecord;
	}
	@XmlElement
	public void setChipsRecord(int chipsRecord) {
		this.chipsRecord = chipsRecord;
	}

	public String getTime() {
		return date;
	}

	@XmlElement
	public void setTime(String string) {
		this.date = string;
	}
	

	

}
