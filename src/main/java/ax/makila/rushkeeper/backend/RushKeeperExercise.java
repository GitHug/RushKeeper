package ax.makila.rushkeeper.backend;

import java.io.Serializable;
import java.util.Date;

public class RushKeeperExercise implements Serializable, Cloneable {

	private static final long serialVersionUID = 5393428770933617486L;

	private Long id;
	
	private String distance = "";
	private Date when;
	private String type = "";
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public Date getWhen() {
		return when;
	}
	public void setWhen(Date when) {
		this.when = when;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
