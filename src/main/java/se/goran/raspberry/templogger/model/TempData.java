package se.goran.raspberry.templogger.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class TempData {	
	private Long timeStamp;
	private float temperature;
	private String 	timeFormatted;


	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
		Date d = new Date(timeStamp);		
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");        
		this.timeFormatted = f.format(d);
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;		
	}

	public String getTimeFormatted() {
		return timeFormatted;
	}

	public void setTimeFormatted(String timeFormatted) {
		this.timeFormatted = timeFormatted;
	}
	
	
}
