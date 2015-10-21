package ax.makila.rushkeeper;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.sort.SortOrder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import ax.makila.rushkeeper.backend.ExerciseService;
import ax.makila.rushkeeper.backend.RushKeeperExercise;

@Theme("valo")
public class RushKeeperUI extends UI {

	private static final long serialVersionUID = -5952512888515021458L;

	@Override
	protected void init(VaadinRequest request) {
		configureComponents();
		buildLayout();
	}
	
	private Button login = new Button("Login");
	private Button create = new Button("Add new exercise");
	private Grid exerciseList = new Grid();
	private ExerciseForm exerciseForm = new ExerciseForm();
	private ExerciseService service = ExerciseService.createService();

	public Button getCreate() {
		return create;
	}
	
	public Grid getExerciseList() {
		return exerciseList;
	}
	
	public ExerciseForm getExerciseForm() {
		return exerciseForm;
	}
	
	public ExerciseService getService() {
		return service;
	}
	
	private void configureComponents() {
		create.addClickListener(e -> exerciseForm.edit(new RushKeeperExercise()));
		
		exerciseList.setContainerDataSource(new BeanItemContainer<>(RushKeeperExercise.class, service.findAll()));
		exerciseList.setColumnOrder("type", "distance", "when");
		exerciseList.setSortOrder(Arrays.asList(new SortOrder("when", SortDirection.DESCENDING)));
		exerciseList.removeColumn("id");
		exerciseList.setSelectionMode(Grid.SelectionMode.SINGLE);
		exerciseList.addSelectionListener(e 
			-> exerciseForm.edit((RushKeeperExercise) exerciseList.getSelectedRow()));
		
	}
	
	private void buildLayout() {
		HorizontalLayout top = new HorizontalLayout(login, create);
		top.setSpacing(true);
		
        VerticalLayout left = new VerticalLayout(top, exerciseList);
        left.setSizeFull();
        exerciseList.setSizeFull();
        left.setExpandRatio(exerciseList, 1);
        
        HorizontalLayout mainLayout = new HorizontalLayout(left, exerciseForm);
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);

        // Split and allow resizing
        setContent(mainLayout);
    }
	
	public void refreshExercises() {
        exerciseList.setContainerDataSource(new BeanItemContainer<>(
                RushKeeperExercise.class, service.findAll()));
        exerciseForm.setVisible(false);
    }
	
	@WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = RushKeeperUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
		private static final long serialVersionUID = 6510869347060634588L;
    }

	

}
