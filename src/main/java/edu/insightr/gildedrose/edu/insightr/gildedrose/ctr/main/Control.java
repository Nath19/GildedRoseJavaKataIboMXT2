package edu.insightr.gildedrose.edu.insightr.gildedrose.ctr.main;

import com.google.gson.Gson;
import edu.insightr.gildedrose.edo.insightr.gilderose.item.Inventory;
import edu.insightr.gildedrose.edo.insightr.gilderose.item.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Control implements Initializable {

    private Inventory inventory;

    @FXML private TableView<Item> tableView;

    //@FXML private TableColumn<Item, String> sellIns;

    @FXML private ComboBox ComboBoxID;

    @FXML private TextField SellIn;

    @FXML private TextField Quality;

    @FXML private PieChart pieChart;

    //@FXML private transient Button barchar;

    //@FXML private TextField sell_In;

    @FXML private BarChart<?,?> SellIn_NbItems;

    //@FXML private CategoryAxis sellIn;

    //@FXML private NumberAxis y;

    @FXML private BarChart<?,?> dateItem;

    //@FXML private CategoryAxis date;

    //@FXML private NumberAxis y2;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inventory = new Inventory();
        ObservableList<Item> data = FXCollections.observableArrayList(inventory.getItems());
        tableView.setItems(data);

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

        refreshSellButtons();
        countItems();
        barcharItem();
      //  barcharItem2();
        LocalDateCount();
    }

    public void countItems() {
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

    public void refreshSellButtons(){
        for(int i=0;i<inventory.getItems().length;i++)
        {
            int value=i;
            inventory.getItems()[i].getSell().setOnAction(event->
            {
                inventory=new Inventory(inventory.delete(inventory.getItems()[value]));
                tableView.getItems().setAll(inventory.getItems());
                tableView.getItems();
                tableView.refresh();
                countItems();
                barcharItem();
             //   barcharItem2();
                LocalDateCount();
            });
        }
    }

    public void addItem(ActionEvent actionEvent) {
        String name_value=ComboBoxID.getValue().toString();
        int sellIn_value=Integer.parseInt(SellIn.getText());
        int quality_value=Integer.parseInt(Quality.getText());

        Item nouveau=new Item(name_value,sellIn_value,quality_value);

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
        refreshSellButtons();
        countItems();
        barcharItem();
       // barcharItem2();
        LocalDateCount();
    }

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

        Gson gson = new Gson();
        String content = "";

        try{
            BufferedReader bfR = new BufferedReader(new FileReader(json));
            String line;
            while((line = bfR.readLine()) != null){
                content += line;
            }
            Item[] items = gson.fromJson(content, Item[].class);

            Item[] finalInventory = new Item[inventory.getItems().length + items.length];
            int index = 0;
            for(Item i : inventory.getItems()){
                finalInventory[index] = i;
                index++;
            }
            for(Item i : items){
                finalInventory[index] = i;
                index++;
            }
            inventory.setItems(finalInventory);
            tableView.getItems().setAll(inventory.getItems());
            tableView.getItems();
            tableView.refresh();
            refreshSellButtons();
            countItems();
            barcharItem();
            barcharItem2();
        }
        catch(Exception e){
            System.out.println(e);
        }

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
            int sellIn = i.getSellIn();
            if(sellIn < 0) sellIn = 0;
            set1.getData().add(new XYChart.Data(a, sellInTab[sellIn]));

        }

        SellIn_NbItems.getData().addAll(set1);
        tableView.refresh();

    }
public LinkedList<LocalDate> presenceDate() {
    LinkedList<LocalDate> count = new LinkedList<>();


    for (int i = 0; i < inventory.getItems().length; i++) {
        boolean presence = false;
        for (int j = 0; j < count.size(); j++) {

            if (inventory.getItems()[i].getPurchaseDate().compareTo(count.get(j)) == 0) {
                presence = true;
                break;


            }


        }
        if (presence == false) {

            count.add(inventory.getItems()[i].getPurchaseDate());

        }

    }
    return count;
}



public void LocalDateCount()
{
    XYChart.Series set1 = new XYChart.Series<>();
    LinkedList<LocalDate> count = presenceDate();
    LocalDate[] tab1= new LocalDate[count.size()];
    int[] tab2=new int [count.size()];
    for (int i=0;i<count.size();i++)
    {
        tab1[i]=count.get(i);
        System.out.println(tab1[i]);
    }


for (int i=0;i<inventory.getItems().length;i++)
{


    for (int j=0;j<tab2.length;j++)
    {
        if(tab1[j].compareTo(inventory.getItems()[i].getPurchaseDate())==0)
        {

            tab2[j]++;
            break;
        }
        System.out.println(tab2[j]);
    }

}

for (int i=0;i<count.size();i++)
{

    set1.getData().add(new XYChart.Data(tab1[i].toString(), tab2[i]));



}


   dateItem.getData().addAll(set1);
    //   dateItem.getData().addAll(set2);

    tableView.refresh();
}




    public void barcharItem2()
    {
        LocalDateCount();
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
