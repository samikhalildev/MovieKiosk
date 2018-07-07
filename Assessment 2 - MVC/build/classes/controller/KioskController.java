package controller;

import au.edu.uts.ap.javafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.*;

public class KioskController extends Controller<Kiosk> {
    @FXML private Button close;
    Catalogue catalogue;

    @FXML public void initialize() {
        
    }

    public final Kiosk getKiosk() {
        return model;
    }
      
    @FXML public void adminWindow(ActionEvent event) throws Exception{    
        ViewLoader.showStage(model, "/view/Admin.fxml", "Administration Menu", new Stage());
    }
    
    @FXML public void catalogueWindow(ActionEvent event) throws Exception{        
        ViewLoader.showStage(model, "/view/Catalogue.fxml", "Catalogue", new Stage());
    }
    
     @FXML public void customerRecordWindow(ActionEvent event) throws Exception{
        ViewLoader.showStage(model, "/view/CustomerRecord.fxml", "Patron Record", new Stage());
    }
 
    @FXML public void topUpAccountWindow(ActionEvent event) throws Exception{
        ViewLoader.showStage(model, "/view/TopUpAccount.fxml", "Account Top-up", new Stage());
    }
    
    @FXML public void favouriteMoviesWindow(ActionEvent event) throws Exception{    
        ViewLoader.showStage(model, "/view/FavouriteMovies.fxml", "Favourites", new Stage());
    }
    
    @FXML 
    public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}
