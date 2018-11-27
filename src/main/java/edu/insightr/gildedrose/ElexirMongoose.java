package edu.insightr.gildedrose;

public class ElexirMongoose extends Item {

    public ElexirMongoose()
    {
        super();
        this.name="Elixir of the Mongoose";
        this.sellIn=5;
        this.quality=7;
    }



    public ElexirMongoose(String name, int sellIn, int quality)
    {
        super(name, sellIn,quality);
    }
}
