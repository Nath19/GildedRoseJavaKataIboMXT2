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
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;



public class Control implements Initializable {




    private Inventory inventory=new Inventory();

    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, Integer> sellInColumn;
    @FXML
    private TableColumn<Item, Integer> qualityColumn;

    @FXML private ComboBox ComboBoxID;

    @FXML private TextField SellIn;

    @FXML private TextField Quality;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inventory inventory = new Inventory();
        ObservableList<Item> data = FXCollections.observableArrayList(inventory.getItems());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sellInColumn.setCellValueFactory(new PropertyValueFactory<>("sellIn"));
        qualityColumn.setCellValueFactory(new PropertyValueFactory<>("quality"));
        tableView.setItems(data);

        //ObservableList<String>listItem= FXCollections.observableArrayList("");
        ObservableList<String> options =
                FXCollections.observableArrayList("+5 Dexterity Vest",
                        "Aged Brie",
                        "Elixir of the Mongoose",
                        "Sulfuras, Hand of Ragnaros",
                        "Backstage passes to a TAFKAL80ETC concert",
                        "Conjured Mana Cake"
                );

        ComboBoxID.setValue("Item");
        ComboBoxID.setItems(options);




    }



    public void addItem(javafx.event.ActionEvent actionEvent) {
    }

    @FXML
    public void updateItem(ActionEvent actionEvent) {

            inventory.updateQuality();
            tableView.refresh();
    }
}
