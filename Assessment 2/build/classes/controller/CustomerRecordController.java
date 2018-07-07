package controller;

import au.edu.uts.ap.javafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

public class CustomerRecordController extends Controller<Kiosk> {
    
    @FXML private TableView <Movie> rentedMoviesTv;
    @FXML private TableView <Movie> rentingHistoryTv;
    @FXML private TextField userIDTf;
    @FXML private Button selectCustomerBtn;
    @FXML private Text feedback;
    
    @FXML public void initialize() {
        userIDTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                    selectCustomerBtn.setDisable(!isIDValid()));
    }

    public final Kiosk getKiosk() {
        return model;
    }
    
    public int getID(){
        return Integer.parseInt(userIDTf.getText());
    }
    
    public boolean isIDValid(){
        return userIDTf.getText().matches("[0-9]+");
    }
    
    @FXML public void handleCustomerRecord (ActionEvent event){
        int ID = getID();
        
        Customer customer = getKiosk().getCustomer(ID);
        
        if(customer != null){
            feedback.setText(customer.toString());
            rentedMoviesTv.setItems(customer.currentlyRented());
            rentingHistoryTv.setItems(customer.rentingHistory());
        
        } else
            feedback.setText("Customer doesn't exist.");
    }
    
    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}