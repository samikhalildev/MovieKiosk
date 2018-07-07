package controller;

import au.edu.uts.ap.javafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

public class AddMovieController extends Controller<Kiosk> {

    @FXML private TextField movieTitleTf;
    @FXML private TextField movieYearTf;
    @FXML private TextField movieGenreTf;
    @FXML private TextField moviePriceTf;
    @FXML private Button addMovieBtn;
    @FXML private Text feedback;

    @FXML
    public void initialize() {
        movieTitleTf.textProperty().addListener(
                (observable, oldTitle, newTitle) ->
                    updateButton());
        
        movieYearTf.textProperty().addListener(
                (observable, oldText, newText) ->
                    updateButton());
        
        movieGenreTf.textProperty().addListener(
                (observable, oldText, newText) ->
                    updateButton());
        
        moviePriceTf.textProperty().addListener(
                (observable, oldText, newText) ->
                    updateButton());

    }
    
    private void updateButton(){
        addMovieBtn.setDisable(!isTitleYearGenrePriceValid());
    }
    
    private boolean isTitleYearGenrePriceValid(){
        return !movieTitleTf.getText().isEmpty() && movieYearTf.getText().matches("[0-9]+") && !movieGenreTf.getText().isEmpty() && moviePriceTf.getText().matches("[0-9]+");
    }

    public final Kiosk getKiosk() {
        return model;
    }

    public String getMovieTitle(){
        return movieTitleTf.getText();
    }
    
    public int getMovieYear(){
        return Integer.parseInt(movieYearTf.getText());
    }
    
    public String getMovieGenre(){
        return movieGenreTf.getText();
    }
    
    public int getMoviePrice(){
        return Integer.parseInt(moviePriceTf.getText());
    }
    
    @FXML public void handleAddMovie(ActionEvent event) throws Exception{
        
        String title = getMovieTitle();
        int year = getMovieYear();
        String genre = getMovieGenre();
        int price = getMoviePrice();
        
        Catalogue catalogue = getKiosk().getCatalogue();
        
        if(catalogue.getMovieByTitle(title) == null){
            
            catalogue.addMovie(title, year, genre, price);
            feedback.setText("Movie added to Catalogue.");
            movieTitleTf.setText("");
            movieYearTf.setText("");
            movieGenreTf.setText("");
            moviePriceTf.setText("");
            
        } else
            feedback.setText("This movie already exists.");
    }
    
    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}
