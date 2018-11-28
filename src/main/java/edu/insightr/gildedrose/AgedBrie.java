package edu.insightr.gildedrose;

public class AgedBrie extends Item {

    public AgedBrie() {

        super();
        this.name = "Aged Brie";
        this.sellIn = 2;
        this.quality = 0;
        
    }


    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }
}