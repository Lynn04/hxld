package model;

public class Plan {
	
	private int id;
	private String time;
	
	public Plan() {
		super();
	}
	

	public Plan(String time) {
		super();
		this.time = time;
	}
	
	
	public Plan(int id, String time) {
		super();
		this.id = id;
		this.time = time;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

}
