package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class MainController {
	
	@FXML
	private Label result;
	@FXML
	private Label currentOperation;
	private boolean number1IsEmpty = true;
	private boolean number2IsEmpty = true;
	
	private long number1 = 0;
	private long number2 = 0;
	private String operator = "";
	private boolean start = true;
	private Model model = new Model();
	
	@FXML
	public void processNumber(ActionEvent event) {
		if (start) {
			currentOperation.setText("");
			start = false;
		}
		
		String value = ((Button)event.getSource()).getText();
		if (operator == "") {
			number1 = Long.parseLong(String.valueOf(number1) + String.valueOf(Long.parseLong(value)));
			number1IsEmpty = false;
		} else {
			number2 = Long.parseLong(String.valueOf(number2) + String.valueOf(Long.parseLong(value)));
			number2IsEmpty = false;
			
			float output = model.calculate(number1,  number2, operator);
			System.out.println(output);
			result.setText(String.valueOf(output));
		}
		
		currentOperation.setText(currentOperation.getText() + value);
	}
	
	@FXML
	public void processOperators(ActionEvent event) {
		String value = ((Button)event.getSource()).getText();
		operator = value;
		
		if (!number1IsEmpty && !number2IsEmpty) {
			number2 = 0;
			number2IsEmpty = true;
			
			number1 = Long.parseLong(String.valueOf(result.getText()).replace(".0", ""));
			currentOperation.setText(String.valueOf(number1) + operator);
			result.setText("");
		} else {
			currentOperation.setText(currentOperation.getText() + value);
		}
	}
}
