package edu.insightr.gildedrose;

public class Sulfuras extends Item {

    public Sulfuras() {


        super();
        this.name = "Sulfuras, Hand of Ragnaros";
        this.sellIn = 0;
        this.quality = 80;
    }


    public Sulfuras(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }
}