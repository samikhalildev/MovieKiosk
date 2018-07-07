package controller;

import au.edu.uts.ap.javafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

public class AddCustomerController extends Controller<Kiosk> {
    
    @FXML private TextField userIDTf;
    @FXML private TextField userNameTf;
    @FXML private TextField userBalanceTf;
    @FXML private Text feedback;
    @FXML private Button addCustomerBtn;

    @FXML
    public void initialize() {
        userIDTf.textProperty().addListener(
                (observable, oldText, newText) ->
                    updateButton());
        
        userNameTf.textProperty().addListener(
                (observable, oldText, newText) ->
                    updateButton());
        
        userBalanceTf.textProperty().addListener(
                (observable, oldText, newText) ->
                    updateButton());
    }
    
    public void updateButton(){
        addCustomerBtn.setDisable(!isIDNameBalanceValid());
    }
    
    public boolean isIDNameBalanceValid(){
        return userIDTf.getText().matches("[0-9]+") && !userNameTf.getText().isEmpty() && userBalanceTf.getText().matches("[0-9]+");
    }


    public final Kiosk getKiosk() {
        return model;
    }
    
    public int getID(){
        return Integer.parseInt(userIDTf.getText());
    }
    
    public String getName(){
        return userNameTf.getText();
    }
    
    public int getBalance(){
        return Integer.parseInt(userBalanceTf.getText());
    }
    
    @FXML public void handleAddCustomer(ActionEvent event) throws Exception{
        int ID = getID();
        String name = getName();
        int balance = getBalance();
        
        if(getKiosk().getCustomer(ID) == null){
           getKiosk().addCustomer(ID, name, balance);
           feedback.setText("Customer added to Kiosk.");
           userIDTf.setText("");
           userNameTf.setText("");
           userBalanceTf.setText("");

        } else
           feedback.setText("The customer already exists.");
    }
    
    @FXML 
    public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}
