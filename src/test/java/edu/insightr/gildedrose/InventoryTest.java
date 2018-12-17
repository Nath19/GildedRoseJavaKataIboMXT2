package edu.insightr.gildedrose;

import edu.insightr.gildedrose.edo.insightr.gilderose.item.Inventory;
import edu.insightr.gildedrose.edo.insightr.gilderose.item.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    protected Inventory inv;
    Item[]ancienneListeDesItems;
    @BeforeEach
    void setUp() {
       inv = new Inventory();
        ancienneListeDesItems = inv.getItems();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void printInventory() {
    }

    @Test
    void updateQuality() {
    }

    @Test
    public void testupdateQualityDexterityVest()throws Exception{

        Item DextirityVest = new Item("+5 Dexterity Vest", -10, 20);
        Inventory inventory;
        Item[] items = new Item[]{DextirityVest};
        inventory = new Inventory(items);

        inventory.updateQuality();

        assertEquals(18,inventory.getItems()[0].getQuality());


    }

    @Test
    public void testupdateQualityDexterityVestPos()throws Exception{

        Item DextirityVest = new Item("+5 Dexterity Vest", 10, 20);
        Inventory inventory;
        Item[] items = new Item[]{DextirityVest};
        inventory = new Inventory(items);

        inventory.updateQuality();

        assertEquals(19,inventory.getItems()[0].getQuality());


    }
    @Test
    public void testQualityAgedBrie(){
        Item Aged_Brie=new Item("Aged Brie", 1, 1);
        Item[]Items=new Item[]{Aged_Brie};
        Inventory newInventory=new Inventory(Items);
        newInventory.updateQuality();
        assertEquals(2,newInventory.getItems()[0].getQuality());
    }

    @Test
    public void testQualityAgedBrieNegSellIn(){
        Item Aged_Brie=new Item("Aged Brie", -2, 4);
        Item[]Items=new Item[]{Aged_Brie};
        Inventory newInventory=new Inventory(Items);
        newInventory.updateQuality();
        assertEquals(6,newInventory.getItems()[0].getQuality());
    }

    @Test
    public void testQualityAgedBrieInf50(){
        Item Aged_Brie=new Item("Aged Brie", -25, 49);
        Item[]Items=new Item[]{Aged_Brie};
        Inventory newInventory=new Inventory(Items);
        newInventory.updateQuality();
        assertEquals(50,newInventory.getItems()[0].getQuality());
    }

    @Test
    public void testQualityElixir(){
        Item Elixir=new Item("Elixir of the Mongoose", 4, 6);
        Item[]Items=new Item[]{Elixir};
        Inventory newInventory=new Inventory(Items);
        newInventory.updateQuality();
        assertEquals(5,newInventory.getItems()[0].getQuality());
    }
    @Test
    public void testQualityElixirNegSellIn(){
        Item Elixir=new Item("Elixir of the Mongoose", 0, 2);
        Item[]Items=new Item[]{Elixir};
        Inventory newInventory=new Inventory(Items);
        newInventory.updateQuality();
        assertEquals(0,newInventory.getItems()[0].getQuality());
    }

    @Test
    public void testQualityElixirSup0(){
        Item Elixir=new Item("Elixir of the Mongoose", -1, 0);
        Item[]Items=new Item[]{Elixir};
        Inventory newInventory=new Inventory(Items);
        newInventory.updateQuality();
        assertEquals(0,newInventory.getItems()[0].getQuality());
    }

    @Test
    public void testQualityBackstage10Days(){
        Item Backstage=new Item("Backstage passes to a TAFKAL80ETC concert", 10, 6);
        Item[]Items=new Item[]{Backstage};
        Inventory newInventory=new Inventory(Items);
        newInventory.updateQuality();
        assertEquals(8,newInventory.getItems()[0].getQuality());
    }


    @Test
    public void testQualityBackstage5Days(){
        Item Backstage=new Item("Backstage passes to a TAFKAL80ETC concert", 5, 2);
        Item[]Items=new Item[]{Backstage};
        Inventory newInventory=new Inventory(Items);
        newInventory.updateQuality();
        assertEquals(5,newInventory.getItems()[0].getQuality());
    }

    @Test
    public void testQualityBackstage0(){
        Item Backstage=new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10);
        Item[]Items=new Item[]{Backstage};
        Inventory newInventory=new Inventory(Items);
        newInventory.updateQuality();
        assertEquals(0,newInventory.getItems()[0].getQuality());
    }





    @Test
    void main() {
    }
}