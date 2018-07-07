package controller;

import au.edu.uts.ap.javafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Kiosk;

public class AdminController extends Controller<Kiosk> {
    @FXML private Button close;
 
    @FXML public void initialize() {

    }

    public final Kiosk getKiosk() {
        return model;
    }
    
    @FXML public void showAllCustomersWindow(ActionEvent event) throws Exception{    
        ViewLoader.showStage(model, "/view/ShowAllCustomers.fxml", "All Customers", new Stage());
    }
    
    @FXML public void addCustomerWindow(ActionEvent event) throws Exception{    
        ViewLoader.showStage(model, "/view/AddCustomer.fxml", "Add Customer", new Stage());
    }
    
    @FXML public void showAllMoviesWindow(ActionEvent event) throws Exception{    
        ViewLoader.showStage(model, "/view/ShowAllMovies.fxml", "All Movies", new Stage());
    }
    
    @FXML public void addMovieWindow(ActionEvent event) throws Exception{    
        ViewLoader.showStage(model, "/view/AddMovie.fxml", "Add Movie", new Stage());
    }
    
    @FXML public void removeCustomerWindow(ActionEvent event) throws Exception{    
        ViewLoader.showStage(model, "/view/RemoveCustomer.fxml", "Remove Customer", new Stage());
    }
    
    @FXML public void removeMovieWindow(ActionEvent event) throws Exception{    
        ViewLoader.showStage(model, "/view/RemoveMovie.fxml", "Remove Movie", new Stage());
    }
    
    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}