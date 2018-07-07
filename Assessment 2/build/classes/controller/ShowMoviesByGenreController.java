package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.*;

public class ShowMoviesByGenreController extends Controller<Kiosk> {
    @FXML private TableView <Movie> moviesTv;
    @FXML private ListView <Genre> genresLv;
    @FXML private Button displayMoviesBtn;
    
    @FXML public void initialize() {
        genresLv.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldGenre, newGenre) ->
                        displayMoviesBtn.setDisable(newGenre == null));
    }

    public final Kiosk getKiosk() {
        return model;
    }
    
    public Genre getGenre(){
        return genresLv.getSelectionModel().getSelectedItem();
    }
    
    @FXML public void handleMovies(ActionEvent event) throws Exception {
        Genre genre = getGenre();
        
        if(genre != null)
            moviesTv.setItems(getKiosk().getCatalogue().getMoviesInGenre(genre));
        
    }
    
    @FXML public void handleClose(ActionEvent event) throws Exception {
        stage.close();
    }

}
