package controller;

import au.edu.uts.ap.javafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.*;

public class ReturnMovieController extends Controller<Kiosk> {

    @FXML private TextField userIDTf;
    @FXML private TableView <Movie> moviesTv;
    @FXML private Button selectCustomerBtn;
    @FXML private Button returnMovieBtn;
    @FXML private Text feedback;
    
    @FXML public void initialize() {
        
        userIDTf.textProperty().addListener(
            (observable, oldText, newText) ->
                selectCustomerBtn.setDisable(!(userIDTf.getText().matches("[0-9]+"))));
        
        moviesTv.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldMovie, newMovie) ->
                returnMovieBtn.setDisable(newMovie == null));
        
    }

    public final Kiosk getKiosk() {
        return model;
    }
    
    public Movie getMovie(){
        return moviesTv.getSelectionModel().getSelectedItem();
    }
    
    public int getID(){
        return Integer.parseInt(userIDTf.getText());
    }
    
    @FXML public void handleSelectCustomer(ActionEvent event) throws Exception {
        int ID = getID();

        Customer customer = getKiosk().getCustomer(ID);
        
        if(customer != null)
            moviesTv.setItems(customer.currentlyRented());

    }
    
    @FXML public void handleReturnMovie(ActionEvent event) throws Exception {
        Movie movie = getMovie();
        Customer customer = getKiosk().getCustomer(getID());

        boolean isRented = getKiosk().getCatalogue().returnMovieFromCustomer(movie, customer);

        if(isRented)
            feedback.setText("Movie Returned.");
        
        else
            feedback.setText("Movie not Returned.");
    }

    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}