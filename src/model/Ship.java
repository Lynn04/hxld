package model;

/**
 * 船只实体
 * @author lynn
 *
 */
public class Ship {

	private int id;
	private String shipName;
	
	public Ship() {
		super();
	}

	public Ship(String shipName) {
		super();
		this.shipName = shipName;
	}
	

	public Ship(int id, String shipName) {
		super();
		this.id = id;
		this.shipName = shipName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	
	
	
}
