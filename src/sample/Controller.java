package sample;

import javafx.application.*;
import javafx.fxml.*;
import javafx.beans.value.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.input.*;

import java.io.File;

public class Controller {

    private FileSelector fs = new FileSelector();

    @FXML private ListView fileList;
    @FXML private ComboBox folderSelector;
    @FXML private Button cancelButton;
    @FXML private Button openButton;

    private String path;
    private String selected;

    //changes current path
    public void changePath(String path){
        this.path = path;
        fileList.getItems().setAll(fs.getListFile(path));
        folderSelector.getItems().setAll(fs.getListRepParent(path));
        folderSelector.getSelectionModel().select(path);
        selected = path;
    }

    //"open" action
    public void open(){
        if(selected.endsWith(File.separator))
            changePath(selected);
        else{
            System.out.println(selected);
            Platform.exit();
        }
    }

    private ChangeListener<String> selectedListener;
    private ChangeListener<String> folderListener;
    private EventHandler openListener;
    private EventHandler cancelListener;
    private EventHandler doubleClickListener;

    @FXML public void initialize(){
        //initialize path
        changePath(System.getProperty("user.dir") + File.separator);
        //disable openButton
        openButton.setDisable(true);

        //selecting items from the list
        selectedListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selected = path + newValue;
                openButton.setDisable(false);
            }
        };

        fileList.getSelectionModel().selectedItemProperty().addListener(selectedListener);

        //select folders from the ComboBox
        folderListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                changePath(newValue);
                selected = newValue;
            }
        };

        folderSelector.valueProperty().addListener(folderListener);

        //open button
        openListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                open();
            }
        };

        openButton.setOnMouseClicked(openListener);

        //cancel button closes application
        cancelListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                Platform.exit();
            }
        };

        cancelButton.setOnMouseClicked(cancelListener);

        //double click
        doubleClickListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    open();
                }
            }
        };

        fileList.setOnMouseClicked(doubleClickListener);
    }
}
