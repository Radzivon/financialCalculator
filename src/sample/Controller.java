package sample;

import enums.Operation;
import enums.RoundingType;
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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
    private ChoiceBox<Operation> operationChoiceBoxFirst;

    @FXML
    private TextField dTextField;

    @FXML
    private TextField cTextField;

    @FXML
    private ChoiceBox<Operation> operationChoiceBoxSecond;

    @FXML
    private ChoiceBox<Operation> operationChoiceBoxThird;

    @FXML
    private ChoiceBox<RoundingType> roundingChoiceBox;

    @FXML
    private Button roundingButton;

    private CalculatorService calculatorService;
    private DecimalFormat decimalFormat;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        calculatorService = new CalculatorService();
        initChoiceBoxes();
        initTextFields();
        initCalculateButton();
        initDecimalFormat();
        initRoundingButton();
        initRoundingChoiceBox();
    }

    private void initDecimalFormat() {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator(' ');
        String pattern = "#,###,###,###,###.##########";
        decimalFormat = new DecimalFormat(pattern, symbols);
    }

    private void initTextFields() {
        aTextField.setText("0");
        bTextField.setText("0");
        cTextField.setText("0");
        dTextField.setText("0");
    }

    private void initRoundingButton() {
        roundingButton.setOnAction(event -> {
            errorText.setText("");

            String str = resultTextField.getText();
            RoundingType roundingType = roundingChoiceBox.getValue();
            try {
                String result =
                        decimalFormat.format(calculatorService.round(str, roundingType));
                resultTextField.setText(result);
            } catch (NumberFormatException e) {
                errorText.setText("Incorrect number format");
                resultTextField.setText(str);
            } catch (Exception e) {
                errorText.setText(e.getMessage());
                resultTextField.setText(str);
            }
        });
    }

    private void initCalculateButton() {
        calculateButton.setOnAction(event -> {
            errorText.setText("");
            resultTextField.setText("");

            String aString = aTextField.getText();
            String bString = bTextField.getText();
            String cString = cTextField.getText();
            String dString = dTextField.getText();
            Operation operationFirst = operationChoiceBoxFirst.getValue();
            Operation operationSecond = operationChoiceBoxSecond.getValue();
            Operation operationThird = operationChoiceBoxThird.getValue();

            try {
                String result =
                        decimalFormat.format(calculatorService.calculate(aString, bString, cString, dString, operationFirst, operationSecond, operationThird));
                resultTextField.setText(result);
            } catch (NumberFormatException e) {
                errorText.setText("Incorrect number format");
            } catch (Exception e) {
                errorText.setText(e.getMessage());
            }
        });
    }

    private void initChoiceBoxes() {
        ObservableList<Operation> operations = FXCollections.observableArrayList(Operation.values());

        operationChoiceBoxFirst.setItems(operations);
        operationChoiceBoxFirst.setValue(Operation.PLUS);
        operationChoiceBoxSecond.setItems(operations);
        operationChoiceBoxSecond.setValue(Operation.PLUS);
        operationChoiceBoxThird.setItems(operations);
        operationChoiceBoxThird.setValue(Operation.PLUS);
    }

    private void initRoundingChoiceBox() {
        ObservableList<RoundingType> operations = FXCollections.observableArrayList(RoundingType.values());
        roundingChoiceBox.setItems(operations);
        roundingChoiceBox.setValue(RoundingType.ACCOUNTING);
    }
}


