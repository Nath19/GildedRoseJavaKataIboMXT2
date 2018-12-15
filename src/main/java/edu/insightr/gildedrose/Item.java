package edu.insightr.gildedrose;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.time.LocalDate;

public class Item {

    private String name;
    private int sellIn;
    private int quality;




    private LocalDate purchaseDate;
    private LocalDate sellingDate;



    @FXML

    private Button sell;




    public Item(){}
    public Item(String name, int sellIn, int quality) {
        super();
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.purchaseDate = LocalDate.now();
        this.sellingDate=null;
        this.sell=new Button("Sell");

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }


    public Button getSell() {
        return sell;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;

    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDate getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(LocalDate sellingDate) {
        this.sellingDate = sellingDate;
    }


    public int isEqual(Item item)
    {

        if(item.name.equals(name) && item.sellIn==sellIn && item.quality==quality && item.purchaseDate.equals(purchaseDate) )
        {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", sellIn=" + sellIn +
                ", quality=" + quality +
                '}';
    }
}