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
 
public class RemoveCustomerController extends Controller<Kiosk> {
    @FXML private TableView <Customer> customersTv;
    @FXML private Button removeCustomerBtn;

    @FXML public void initialize() {
        customersTv.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldCustomer, newCustomer) -> 
        removeCustomerBtn.setDisable(newCustomer == null));
        //customersTv.setItems(getKiosk().getCustomers());
    }
    
    private final Customer getCustomer(){
        return customersTv.getSelectionModel().getSelectedItem();
    }

    public final Kiosk getKiosk() {
        return model;
    }
    
    @FXML public void handleRemoveCustomer(ActionEvent event) throws Exception{
        Customer customer = getCustomer();
        
        if(customer != null)
            getKiosk().removeCustomer(customer);  
    }
    
    @FXML public void handleClose(ActionEvent event) throws Exception{
        stage.close();
    }
}