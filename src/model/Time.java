package model;

public class Time {
	private int hour;
	private int minute;
	
	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	
	public void addMin(int minute) {
		int dhour = minute / 60;
		int dminute = minute % 60;
		this.hour += dhour;
		this.minute += dminute;
		if(this.minute >= 60) {
			this.hour += 1;
			this.minute -=60;
		}
	}
	
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	
	


}
