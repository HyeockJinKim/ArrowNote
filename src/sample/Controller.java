package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Optional;
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
    @FXML
    private Button saveBtn;
    @FXML
    private Button loadBtn;

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
        saveBtn.setOnMouseClicked(e -> saveFile());
        loadBtn.setOnMouseClicked(e -> loadFile());
    }

    private void makeArrowNote() {
        arrowNote.setText(textArea.getText());
    }

    private void saveFile() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("File Save");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

        Optional<File> file = Optional.ofNullable(fileChooser.showSaveDialog(stage));
        file.ifPresent(this::writeText);
    }

    private void loadFile() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("File Load");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        Optional<File> file = Optional.ofNullable(fileChooser.showOpenDialog(stage));
        file.ifPresent(this::readFile);
    }

    private void writeText(File file) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(textArea.getText());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            while (line != null) {
                stringBuilder.append(line);
                line = br.readLine();
            }
            br.close();
            textArea.setText(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
