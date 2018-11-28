package edu.insightr.gildedrose;

public class Strategy3 implements UpdateStrategy {//for conjured

  public   int update(Item it)
    {
        it.setSellIn(it.getSellIn()-1);

        if(it.getSellIn()>0)
        {
            it.setQuality(it.getQuality()-2);
        }
        else
        {
            it.setQuality(it.getQuality()-4);

        }
        if(it.getQuality()<0)
        {
            it.setQuality(0);
        }
        return it.getQuality();
    }
}
