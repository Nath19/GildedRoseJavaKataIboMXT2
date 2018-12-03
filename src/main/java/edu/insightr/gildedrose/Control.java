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
import javafx.scene.control.Button;

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

    @FXML private Button buttonaddID;




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
        buttonaddID.setOnAction(event -> {

            String name_value=ComboBoxID.getValue().toString();
            int sellin_value=Integer.parseInt(SellIn.getText());
            int quallity_value=Integer.parseInt(Quality.getText());


            Item nouveau=new Item(name_value,sellin_value,quallity_value);

            Item[] items = new Item[this.inventory.getItems().length+1];


            int i=0;
            while(i<this.inventory.getItems().length)
            {
                items[i]=this.inventory.getItems()[i];
                i++;
            }

            items[this.inventory.getItems().length]=nouveau;

            inventory=new Inventory(items);

            tableView.getItems().setAll(inventory.getItems());
            tableView.getItems();
            tableView.refresh();





        });




    }

    @FXML
    public void updateItem(ActionEvent actionEvent) {

            inventory.updateQuality();
        tableView.refresh();
    }
}
