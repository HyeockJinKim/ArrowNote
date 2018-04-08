package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable{
    @FXML
    private VBox root;
    @FXML
    private TabPane textTab;
    @FXML
    private Tab settings;
    @FXML
    private Tab arrowTab;

    @FXML
    private TextArea textArea;
    @FXML
    private TextArea arrowNote;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initUI();
        initHandler();
    }

    private void initUI() {
        textTab.prefHeightProperty().bind(root.heightProperty());
        textTab.prefWidthProperty().bind(root.widthProperty());

        textArea.prefHeightProperty().bind(textTab.heightProperty().subtract(35));
        textArea.prefWidthProperty().bind(textTab.widthProperty().subtract(5));
        textArea.setLayoutX(2.5);
        textArea.setWrapText(true);

        arrowNote.prefHeightProperty().bind(textArea.heightProperty());
        arrowNote.prefWidthProperty().bind(textArea.widthProperty());
        arrowNote.setLayoutX(2.5);
        arrowNote.setWrapText(true);
    }

    private void initHandler() {
        arrowTab.setOnSelectionChanged(e -> makeArrowNote());
    }

    private void makeArrowNote() {
        arrowNote.setText(textArea.getText());
    }
}
