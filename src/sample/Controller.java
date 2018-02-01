package sample;

import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.*;
import javafx.event.*;
import javafx.scene.control.*;

public class Controller {

    private FileSelector fs = new FileSelector();

    @FXML private ListView fileList;

    @FXML private ComboBox folderSelector;

    @FXML private Button cancelButton;

    @FXML private Button openButton;
    
    private String path = System.getProperty("user.dir");

    private ChangeListener<String> folderListener;

    private EventHandler cancelListener;

    @FXML public void initialize(){

        openButton.setDisable(true);

        cancelListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                Platform.exit();
            }
        };

        cancelButton.setOnMouseClicked(cancelListener);

        folderListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

            }
        };

        folderSelector.valueProperty().addListener();

        fileList.getItems().setAll(fs.getListFile(System.getProperty("user.dir")));

        folderSelector.getItems().setAll(fs.getListRepParent(System.getProperty("user.dir")));
    }
}
