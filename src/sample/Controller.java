package sample;

import enums.Operation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import service.CalculatorService;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button calculateButton;
    @FXML
    private TextField aTextField;

    @FXML
    private TextField bTextField;

    @FXML
    private TextField resultTextField;

    @FXML
    private Text errorText;

    @FXML
    private ChoiceBox<Operation> operationChoiceBox;

    private CalculatorService calculatorService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calculatorService = new CalculatorService();
        initChoiceBox();
        initCalculateButton();

    }

    private void initCalculateButton() {
        calculateButton.setOnAction(event -> {
            errorText.setText("");

            String aString = aTextField.getText();
            String bString = bTextField.getText();

            Operation operation = operationChoiceBox.getValue();

            try {
                resultTextField.setText(calculatorService.calculate(aString, bString, operation).toString());
            } catch (NumberFormatException e) {
                errorText.setText("Incorrect number format");
            } catch (Exception e) {
                errorText.setText(e.getMessage());
            }
        });
    }

    private void initChoiceBox() {
        ObservableList<Operation> operations = FXCollections.observableArrayList(Operation.values());

        operationChoiceBox.setItems(operations);
        operationChoiceBox.setValue(Operation.PLUS);
    }
}


