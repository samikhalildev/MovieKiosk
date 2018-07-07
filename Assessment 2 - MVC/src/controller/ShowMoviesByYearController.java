package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.*;

public class ShowMoviesByYearController extends Controller<Kiosk> {
    @FXML private TableView <Movie> moviesTv;
    @FXML private ListView <Integer> yearsLv;
    @FXML private Button displayMoviesBtn;
    
    @FXML public void initialize() {
        yearsLv.setItems(getKiosk().getCatalogue().getYears());
        
        yearsLv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldYear, newYear) ->
                displayMoviesBtn.setDisable(newYear == null));
    }

    public final Kiosk getKiosk() {
        return model;
    }
    
    public int getYear(){
        return yearsLv.getSelectionModel().getSelectedItem();
    }
    
    @FXML public void handleMovies(ActionEvent event) throws Exception {
        int year = getYear();
        
        moviesTv.setItems(getKiosk().getCatalogue().getMoviesByYear(year));
        
    }
    
    @FXML public void handleClose(ActionEvent event) throws Exception {
        stage.close();
    }

}
