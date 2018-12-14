package edu.insightr.gildedrose;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;

public class Control implements Initializable {

    private Inventory inventory;

    //============ Used for test ============
    private Item itemToAdd;
    public Inventory getInventory() {
        return inventory;
    }
    public Item getItemToAdd() {
        return itemToAdd;
    }
    //


    @FXML
    private TableView<Item> tableView;

    @FXML private ComboBox ComboBoxID;

    @FXML private TextField SellIn;

    @FXML private TextField Quality;

    @FXML private PieChart pieChart;

    @FXML private Button barchar;


    // Barchar

    @FXML
    private BarChart<?,?> SellIn_NbItems;

    @FXML
    private CategoryAxis sellIn;

    @FXML
    private NumberAxis y;

    @FXML
    private BarChart<?,?> dateItem;

    @FXML
    private CategoryAxis date;

    @FXML
    private NumberAxis y2;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inventory = new Inventory();
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

        countItems();
        barcharItem();
        barcharItem2();

    }




    public  void countItems()
    {
        int[] tab = inventory.count();
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


    public void addItem(ActionEvent actionEvent) {

        String name_value=ComboBoxID.getValue().toString();
        int sellin_value=Integer.parseInt(SellIn.getText());
        int quallity_value=Integer.parseInt(Quality.getText());


        Item nouveau=new Item(name_value,sellin_value,quallity_value);
        itemToAdd = nouveau;

        Item[] items = new Item[inventory.getItems().length+1];


        int i=0;
        while(i<inventory.getItems().length)
        {
            items[i]=inventory.getItems()[i];
            i++;
        }

        items[inventory.getItems().length]=nouveau;

        inventory=new Inventory(items);

        tableView.getItems().setAll(inventory.getItems());
        tableView.getItems();
        tableView.refresh();
        countItems();
        barcharItem();
        barcharItem2();
    }




    @FXML
    public void updateItem(ActionEvent actionEvent) {

        inventory.updateQuality();
        tableView.refresh();

    }

    public void loadItems(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Json File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Json", "*.json"));
        File json = fileChooser.showOpenDialog(stage);

        ObjectMapper mapper = new ObjectMapper();
        List<Item> cricketer = null;
        try {
            cricketer = mapper.readValue(json, new TypeReference<List<Item>>() { });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Item[] items = new Item[inventory.getItems().length];

        System.out.println("Java object created from JSON String :");
        //System.out.println(cricketer);
        int j=0;
        for(Item i : cricketer){
            items[j]=i;
            j++;
            System.out.println(i);
            // Used to try to fix the get.Name issue.
            if(i.getName() == "Sulfuras, Hand of Ragnaros") System.out.println("test");
        }

        inventory= new Inventory(items);
        tableView.getItems().setAll(inventory.getItems());
        tableView.getItems();
        tableView.refresh();
        countItems();

    }

    public void barcharItem()
    {

        XYChart.Series set1 = new XYChart.Series<>();

        int[] sellInTab = new int[100];

        for(int i=0; i<sellInTab.length;i++) { sellInTab[i]=0;}



        for(Item i : inventory.getItems())
        {
            // set1.getData().add(new XYChart.Data(inventory.getItems()[i].getSellIn(),inventory.getItems().length));
            if(i.getSellIn()>=0){
                sellInTab[i.getSellIn()]++;
            }

            String a = Integer.toString(i.getSellIn());
            set1.getData().add(new XYChart.Data(a, sellInTab[i.getSellIn()]));


        }



        SellIn_NbItems.getData().addAll(set1);
        tableView.refresh();

    }


    public void barcharItem2()
    {

        XYChart.Series set1 = new XYChart.Series<>();



        int[] sellInTab = new int[100];

        for(int i=0; i<sellInTab.length;i++) { sellInTab[i]=0;}

        int ind=0;
        int dif=0;
        boolean different=false;
for (Item j : inventory.getItems()) {
    for (Item i : inventory.getItems()) {
        // set1.getData().add(new XYChart.Data(inventory.getItems()[i].getSellIn(),inventory.getItems().length));
        if (i.getPurchaseDate() != (j).getPurchaseDate())
        {
            ind++;
            sellInTab[ind]++;
            ind--;
        }else
        {

            sellInTab[ind]++;
        }




        LocalDate date = i.getPurchaseDate();

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd mm yyyy");
        //   String formattedString = date.format(formatter);
        //String a = Integer.toString(i.getPurchaseDate());
        set1.getData().add(new XYChart.Data(date.toString(), sellInTab[ind]));
     //   set2.getData().add(new XYChart.Data(date.toString(), sellInTab[dif]));


    }
}


        dateItem.getData().addAll(set1);
     //   dateItem.getData().addAll(set2);

        tableView.refresh();

    }


}
