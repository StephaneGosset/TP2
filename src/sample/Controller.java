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

    public void changePath(String path){
        fileList.getItems().setAll(fs.getListFile(path));
        folderSelector.getItems().setAll(fs.getListRepParent(path));
    }

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

        changePath(System.getProperty("user.dir"));

        folderListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                changePath(newValue);
            }
        };

        folderSelector.valueProperty().addListener(folderListener);



    }
}
