package edu.insightr.gildedrose;

public class Strategy2 implements  UpdateStrategy { //for Backstage
    public int update(Item it)
    {
        it.setSellIn(it.getSellIn()-1);

        if(it.getSellIn()>0 )
        {
            if(it.getQuality()<50)
            {
                if(it.getSellIn() > 10){
                    it.setQuality(it.getQuality()+1);
                }
                if(it.getSellIn()<=10)
                {
                    it.setQuality(it.getQuality()+2);
                }
                if(it.getSellIn()<=5)
                {
                    it.setQuality(it.getQuality()+1);
                }
            }

            else
            {
                it.setQuality(50);
            }
        }
        else
        {
            it.setQuality(0);
        }
        return it.getQuality();
    }
}
