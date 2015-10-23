package ax.makila.rushkeeper.backend.connector;

import java.util.List;

//import com.microsoft.hsg.ConnectionFactory;
//import com.microsoft.hsg.request.SimpleRequestTemplate;

import ax.makila.rushkeeper.backend.RushKeeperExercise;
import ax.makila.rushkeeper.experiment.HealthVaultFileSettings;
import ax.makila.rushkeeper.experiment.HealthVaultSettings;

public class HealthVaultConnector implements Connectable {
	
	HealthVaultSettings settings = new HealthVaultFileSettings();
	
	//TODO
	@Override
	public List<RushKeeperExercise> getData() {
		
		HealthVaultService.getInstance().getConnection();
		
		return null;
	}
	
	
	

}
