package ax.makila.rushkeeper.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import ax.makila.rushkeeper.backend.connector.Connectable;
import ax.makila.rushkeeper.backend.connector.HealthVaultConnector;
import ax.makila.rushkeeper.backend.connector.LocalConnector;

public class ExerciseService {
	
	private static Class<LocalConnector> LOCAL_CONNECTOR = LocalConnector.class;
	@SuppressWarnings("unused")
	private static Class<HealthVaultConnector> HEALTHVAULT_CONNECTOR = HealthVaultConnector.class;
	
	private static ExerciseService instance;
	private Map<Long, RushKeeperExercise> exercises = new HashMap<>(); 

	public static ExerciseService createService() {
		if(instance == null) {
			final ExerciseService service = new ExerciseService();
			
			List<RushKeeperExercise> exercises = getExercises();
			
			//Saving...
			exercises.forEach(ex -> service.save(ex));	
			
			instance = service;
		}
		
		
		return instance;
	}
	
	
	private static List<RushKeeperExercise> getExercises() {
		//Här kan man byta till en annan connector istället
		Connectable connector = Connectable.getConnector(LOCAL_CONNECTOR);
		
		return connector.getData();
	}
	
	public synchronized List<RushKeeperExercise> findAll() {
		List<RushKeeperExercise> exerciseList = new ArrayList<>();
		
		for(RushKeeperExercise exercise : exercises.values()) {
			exerciseList.add(exercise);
		}
		
		return exerciseList;
	}
	
	public synchronized void save(RushKeeperExercise exercise) {
		try {
			exercise = (RushKeeperExercise) BeanUtils.cloneBean(exercise);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		exercises.put(exercise.getId(), exercise);
	}
	
	public synchronized void delete(RushKeeperExercise exercise) {
		exercises.remove(exercise.getId());
	}
	
}
