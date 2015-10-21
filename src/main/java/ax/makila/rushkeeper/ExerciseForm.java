package ax.makila.rushkeeper;

import com.vaadin.annotations.Theme;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import ax.makila.rushkeeper.backend.RushKeeperExercise;

public class ExerciseForm extends FormLayout {

	private static final long serialVersionUID = -3536423935463241674L;
	
	Button save = new Button("Save", this::save);
	Button cancel = new Button("Cancel", this::cancel);
	Button delete = new Button("Delete", this::delete);
	
	NativeSelect type = new NativeSelect("Type");
//	TextField type = new TextField("Type of exercise");
	TextField distance = new TextField("Distance");
	DateField when = new DateField("When");
	
	RushKeeperExercise exercise;

    // Easily bind forms to beans and manage validation and buffering
    BeanFieldGroup<RushKeeperExercise> formFieldBindings;
	
	public ExerciseForm() {
		configureComponents();
		buildLayout();
	}
	
	private void configureComponents() {
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		delete.setStyleName(ValoTheme.BUTTON_DANGER);

		type.addItem("Running");
		type.addItem("Walking");
		type.addItem("Hiking");
		type.addItem("Shuffling");

		
		setVisible(false);
	}
	
	private void buildLayout() {
		setSizeUndefined();
		setMargin(true);
		HorizontalLayout actions = new HorizontalLayout(delete, save, cancel);
		actions.setSizeFull();
		
		actions.setSpacing(true);
		
		
		addComponents(actions, type, distance, when);
	}
	
	public void save(Button.ClickEvent event) {
		try {
			formFieldBindings.commit();
			
			getUI().getService().save(exercise);
			
			String msg = String.format("Saved '%s'.", exercise.getType());
			Notification.show(msg, Type.TRAY_NOTIFICATION);
			
			getUI().refreshExercises();
		} catch(CommitException e) {
			e.printStackTrace();
		}
	}
	
	public void cancel(Button.ClickEvent event) {
		Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
		getUI().getExerciseList().select(null);
	}
	
	public void edit(RushKeeperExercise exercise) {
		this.exercise = exercise;
		if(exercise != null) {
			
			formFieldBindings = BeanFieldGroup.bindFieldsBuffered(exercise, this);
			type.focus();
		}
		setVisible(exercise != null);
	}
	
	public void delete(Button.ClickEvent event) {
		Notification.show("Deleted", Type.TRAY_NOTIFICATION);
		getUI().getService().delete(exercise);
		getUI().refreshExercises();
	}

	
	@Override
	public RushKeeperUI getUI() {
		return (RushKeeperUI) super.getUI();
	}
	
}
