import java.io.IOException;
import java.time.DayOfWeek;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TabController
{

	@FXML
	private TextField medName;

	@FXML
	private TextArea medDesc;

	@FXML
	private ComboBox<String> dayCombo;

	@FXML
	private Button addMedicationButton;

	@FXML
	private Button removeMedicationButton;

	@FXML
	private Button addRemButton;

	@FXML
	private ComboBox<String> hourCombo;

	@FXML
	private Tab currentTab;
	
	@FXML
	private Text tabStatusText;
	
	private Medication med;

	@FXML
	void addReminder(ActionEvent event)
	{
		tabStatusText.setText("");
		
		if (hourCombo.getValue() == null)
		{
			tabStatusText.setText("Please select a time.");
		}
		else if (dayCombo.getValue() == null)
		{
			tabStatusText.setText("Please select a day.");
		}
		else
		{
			int time;
			String timeNum = hourCombo.getValue().replaceAll(":", "");
			
			if (timeNum.equals("000"))
				time = 0;
			else
				time = Integer.parseInt(timeNum.replaceAll("0", ""));
			
			DayOfWeek day = DayOfWeek.valueOf(dayCombo.getValue().toUpperCase());
			
			med.addReminder(time, day);
			
			MainController.refresh();
		}
	}

	@FXML
	void addMed(ActionEvent event) throws IOException
	{
		tabStatusText.setText("");
		Medication newMed = new Medication("Enter name");
		MainController.setCurrentMed(newMed);

		Login.getUser().addMed(newMed);
		MainController.refresh();

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Tab.fxml"));
		loader.getNamespace().put("name", med.getName());
		loader.getNamespace().put("desc", med.getDescription());

		Tab temp;
		temp = loader.load();

		MainController.getTabPane().getTabs().add(temp);
	}

	@FXML
	void removeMed(ActionEvent event)
	{
		if (Login.getUser().getMed().size() > 1)
		{
			for (int i = 0; i < Login.getUser().getMed().size(); i++)
			{
				if (med == Login.getUser().getMed().get(i))
				{
					Login.getUser().getMed().remove(i);
				}
			}
			
			MainController.getTabPane().getTabs().remove(currentTab);
			
			MainController.refresh();
		}
		else
		{
			tabStatusText.setText("At least one Medication must be present.");
		}
	}

	@FXML
	void initialize()
	{
		med = MainController.getCurrentMed();

		medDesc.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue,
					final String newValue)
			{
				med.setDescription(medDesc.getText());
				tabStatusText.setText("");
				MainController.refresh();
			}
		});

		medName.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue,
					final String newValue)
			{
				currentTab.setText(medName.getText());
				med.setName(medName.getText());
				tabStatusText.setText("");
				MainController.refresh();
			}
		});
	}
}
