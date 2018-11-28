package edu.insightr.gildedrose;

public class Updater {

    public void update(Item item, String categorie) {

        UpdateStrategy updateStrategy ;

        switch (categorie) {
            case "Conjured Mana Cake":
                updateStrategy=new Strategy3();
                break;

            case "+5 Dexterity Vest":
                updateStrategy = new Strategy4();
                break;

            case "Aged Brie":
                updateStrategy = new Strategy1();
                break;

            case "Elixir of the Mongoose":
                updateStrategy = new Strategy4();
                break;

            case "Backstage passes to a TAFKAL80ETC concert":
                updateStrategy = new Strategy2();
                break;


        }


    }

}