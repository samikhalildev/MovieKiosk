package controller;

import au.edu.uts.ap.javafx.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

public class TopUpAccountController extends Controller<Kiosk> {
    
    @FXML private TextField userIDTf;
    @FXML private TextField userAmountTf;
    @FXML private Button topUpBtn;
    @FXML private Text feedback;

    @FXML
    public void initialize() {
        userIDTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                    updateButton());
        
        userAmountTf.textProperty().addListener(
                (observable, oldText, newText) -> 
                    updateButton());
    }

    public final Kiosk getKiosk() {
        return model;
    }
    
    public int getID(){
        return Integer.parseInt(userIDTf.getText());
    }
    
    public int getAmount(){
        return Integer.parseInt(userAmountTf.getText());
    }
    
    private boolean isIDnAmountValid(){
        return userIDTf.getText().matches("[0-9]+") && userAmountTf.getText().matches("[0-9]+");
    }
    
    private void updateButton(){
        topUpBtn.setDisable(!isIDnAmountValid());
    }
    
    @FXML 
    public void handleAccountTopUp(ActionEvent event) throws Exception{
        int ID = getID();
        
        Customer customer = getKiosk().getCustomer(ID);
        
        if(customer != null){
            customer.topUpAccount(getAmount());
            feedback.setText("Transaction Complete.");
            
        } else
            feedback.setText("Customer doesn't exist.");
        
    }
    
    @FXML 
    public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}