package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;


public class Control implements Initializable {




    private Inventory inventory=new Inventory();



    @FXML
    private TableView<Item> tableView;

    @FXML private ComboBox ComboBoxID;

    @FXML private TextField SellIn;

    @FXML private TextField Quality;

    @FXML private PieChart pieChart;







    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Item> data = FXCollections.observableArrayList(inventory.getItems());
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


        int []tab = inventory.count();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("+5 Dexterity Vest", tab[0]),
                        new PieChart.Data("Aged Brie", tab[1]),
                        new PieChart.Data("Elixir of the Mongoose", tab[2]),
                        new PieChart.Data("Sulfuras, Hand of Ragnaros", tab[3]),
                        new PieChart.Data("Backstage passes to a TAFKAL80ETC concert", tab[4]),
                        new PieChart.Data("Conjured Mana Cake", tab[5]));
        pieChart.setLabelsVisible(false);
        pieChart.setTitle("Items");
        pieChart.setData(pieChartData);
        pieChart.setLabelLineLength(10);



    }


    public  void countItems()
    {
        int []tab = inventory.count();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("+5 Dexterity Vest", tab[0]),
                        new PieChart.Data("Aged Brie", tab[1]),
                        new PieChart.Data("Elixir of the Mongoose", tab[2]),
                        new PieChart.Data("Sulfuras, Hand of Ragnaros", tab[3]),
                        new PieChart.Data("Backstage passes to a TAFKAL80ETC concert", tab[4]),
                        new PieChart.Data("Conjured Mana Cake", tab[5]));
        pieChart.setLabelsVisible(false);
        pieChart.setTitle("Items");
        pieChart.setData(pieChartData);
        pieChart.setLabelLineLength(10);
    }


    public void addItem(javafx.event.ActionEvent actionEvent) {

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
        countItems();


    }




    @FXML
    public void updateItem(ActionEvent actionEvent) {

        inventory.updateQuality();
        tableView.refresh();



    }
}
