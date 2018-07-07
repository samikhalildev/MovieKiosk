package controller;

import au.edu.uts.ap.javafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.*;

public class ShowAllMoviesController extends Controller<Kiosk> {
    @FXML private TableView <Movie> moviesTv;

    @FXML public void initialize() {

    }

    public final Kiosk getKiosk() {
        return model;
    }
    
    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}