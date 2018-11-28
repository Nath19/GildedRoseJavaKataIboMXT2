package edu.insightr.gildedrose;

public class Strategy4 implements  UpdateStrategy { //for normal items
  public   int update(Item it)
    {
        it.setSellIn(it.getSellIn()-1);

        if(it.getSellIn()>=0)
        {
            it.setQuality(it.getQuality()-1);
        }
        else
        {

            it.setQuality(it.getQuality()-2);

        }
        if(it.getQuality()<0)
        {
            it.setQuality(0);
        }
        return it.getQuality();

    }
}
