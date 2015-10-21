package ax.makila.rushkeeper.backend.connector;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ax.makila.rushkeeper.backend.RushKeeperExercise;

public class LocalConnector implements Connectable {
	
		
	public List<RushKeeperExercise> getData() {
		JSONParser parser = new JSONParser();
		List<RushKeeperExercise> things = new ArrayList<>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			JSONArray array = (JSONArray) parser.parse(new FileReader(new File("anders94.json")));
			
			for(Object object : array) {
				JSONObject obj = (JSONObject) object;			
				
				Long activityId = (Long) obj.get("activity_id");
				String date = (String) obj.get("date");
				String distance = (String) obj.get("distance");
				String kind = (String) obj.get("kind");
				
				RushKeeperExercise exercise = new RushKeeperExercise();
				exercise.setDistance(distance);
				exercise.setType(kind);
				exercise.setWhen(sdf.parse(date));
				exercise.setId(activityId);
				
				things.add(exercise);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return things;
	}
		
	

}
