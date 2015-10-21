package ax.makila.rushkeeper.backend.connector;

import java.util.List;

import ax.makila.rushkeeper.backend.RushKeeperExercise;

public interface Connectable {
	
	public List<RushKeeperExercise> getData();
	
	public static Connectable getConnector(Class<? extends Connectable> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
