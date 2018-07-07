package controller;

import au.edu.uts.ap.javafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Kiosk;

public class CatalogueController extends Controller<Kiosk> {

    @FXML public void initialize() {

    }

    public final Kiosk getKiosk() {
        return model;
    }
  
    
    @FXML public void rentMovieWindow(ActionEvent event) throws Exception {
        ViewLoader.showStage(model, "/view/RentMovie.fxml", "Rent Movie", new Stage());
    }
    
    @FXML public void returnMovieWindow(ActionEvent event) throws Exception {
        ViewLoader.showStage(model, "/view/ReturnMovie.fxml", "Return Movie", new Stage());
    }
    
    
    
    @FXML public void showAllMoviesWindow(ActionEvent event) throws Exception {
        ViewLoader.showStage(model, "/view/ShowAllMovies.fxml", "All Movies", new Stage());
    }
    
    @FXML public void showAvailableMoviesWindow(ActionEvent event) throws Exception {
        ViewLoader.showStage(model, "/view/ShowAvailableMovies.fxml", "Avilable Movies", new Stage());
    }
    
    @FXML public void showMoviesbyGenreWindow(ActionEvent event) throws Exception {
        ViewLoader.showStage(model, "/view/ShowMoviesByGenre.fxml", "Movies by Genre", new Stage());
    }
    
    @FXML public void showMoviesbyYearWindow(ActionEvent event) throws Exception {
        ViewLoader.showStage(model, "/view/ShowMoviesByYear.fxml", "Movies by Year", new Stage());
    }
     
    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }

}