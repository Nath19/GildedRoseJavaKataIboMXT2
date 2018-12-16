package edu.insightr.gildedrose;

public class Inventory {


    public void setItems(Item[] items) {
        this.items = items;
    }

    private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        };

    }

    public Item[] getItems()
    {
        return items;
    }

    public void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (items[i].getName() != "Aged Brie"
                    && items[i].getName() != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].getQuality() > 0) {
                    if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                        items[i].setQuality(items[i].getQuality() - 1);
                    }
                    if (items[i].getName()=="Conjured Mana Cake")
                    {
                        items[i].setQuality(items[i].getQuality() - 1);
                    }
                }
            } else {
                if (items[i].getQuality() < 50) {
                    items[i].setQuality(items[i].getQuality() + 1);

                    if (items[i].getName() == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getSellIn() < 11) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(items[i].getQuality() + 1);
                            }
                        }

                        if (items[i].getSellIn() < 6) {
                            if (items[i].getQuality() < 50) {
                                items[i].setQuality(items[i].getQuality() + 1);
                            }
                        }
                    }
                }
            }

            if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                items[i].setSellIn(items[i].getSellIn() - 1);
            }

            if (items[i].getSellIn() < 0) {
                if (items[i].getName() != "Aged Brie") {
                    if (items[i].getName() != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].getQuality() > 0) {
                            if (items[i].getName() != "Sulfuras, Hand of Ragnaros") {
                                items[i].setQuality(items[i].getQuality() - 1);
                            }
                        }
                    } else {
                        items[i].setQuality(items[i].getQuality() - items[i].getQuality());
                    }
                } else {
                    if (items[i].getQuality() < 50) {
                        items[i].setQuality(items[i].getQuality() + 1);
                    }
                }
            }
        }
    }

    public int[] count()
    {
        int[] tab = new int[]{0,0,0,0,0,0};

        for(int i=0;i<items.length;i++)
        {
            if (items[i].getName() == "+5 Dexterity Vest")
            {
                tab[0]++;
            }
            if(items[i].getName() == "Aged Brie")
            {
                tab[1]++;
            }
            if(items[i].getName() == "Elixir of the Mongoose")
            {
                tab[2]++;
            }
            if(items[i].getName() == "Sulfuras, Hand of Ragnaros")
            {
                tab[3]++;
            }
            if(items[i].getName() == "Backstage passes to a TAFKAL80ETC concert")
            {
                tab[4]++;
            }
            if(items[i].getName() == "Conjured Mana Cake")
            {
                tab[5]++;
            }

        }

        for(int j=0;j<tab.length;j++)
        {
            System.out.print(tab[j]);
            System.out.print(" ");

        }
        System.out.println(" ");
        return tab;

    }

    public Item[] delete(Item item)
    {
        int capteur=0;
        Item[] newItems = new Item[items.length-1];
        for(int i=0;i<items.length;i++)
        {
            if(items[i].isEqual(item)==1)
            {
                capteur=i;

            }
        }
        for(int i=0;i<capteur;i++)
        {
            newItems[i]=items[i];
        }
        for(int i=capteur+1;i<items.length;i++)
        {
            newItems[i-1]=items[i];
        }
        return newItems;

    }






    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        for (int i = 0; i < 10; i++) {
            inventory.updateQuality();
            inventory.printInventory();
        }
    }
}
