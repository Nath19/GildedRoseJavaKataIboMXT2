package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Control implements Initializable {
    //Lien avec notre view.
    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, Integer> sellInColumn;
    @FXML
    private TableColumn<Item, Integer> qualityColumn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inventory inventory = new Inventory();
        ObservableList<Item> data = FXCollections.observableArrayList(inventory.getItems());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sellInColumn.setCellValueFactory(new PropertyValueFactory<>("sellIn"));
        qualityColumn.setCellValueFactory(new PropertyValueFactory<>("quality"));
        tableView.setItems(data);
    }



    public void addItem(javafx.event.ActionEvent actionEvent) {
    }

    public void updateItem(ActionEvent actionEvent) {
    }
}
