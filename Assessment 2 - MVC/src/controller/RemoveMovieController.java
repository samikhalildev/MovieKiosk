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

public class RemoveMovieController extends Controller<Kiosk> {
    @FXML private TableView <Movie> moviesTv;
    @FXML private Button removeMovieBtn;

    @FXML public void initialize() {
        moviesTv.setItems(getKiosk().getCatalogue().getAvailableMovies());
        
        moviesTv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldMovie, newMovie) -> 
                removeMovieBtn.setDisable(newMovie == null));
    }
    
    public final Movie getMovie(){
        return moviesTv.getSelectionModel().getSelectedItem();
    }

    public final Kiosk getKiosk() {
        return model;
    }
    
    @FXML public void handleRemoveMovie(ActionEvent event) throws Exception{
        Movie movie = getMovie();
        
        if(movie != null)
            getKiosk().getCatalogue().removeMovie(movie);  
    }
    
    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}