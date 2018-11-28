package edu.insightr.gildedrose;

public class Strategy1 implements UpdateStrategy { // for AgeBried

    public int update(Item it)
    {
        it.setSellIn(it.getSellIn()-1);

        if(it.getQuality()<50)
        {
            it.setQuality(it.getQuality()+1);
        }

        if(it.getSellIn() < 0){
            if(it.getQuality() < 50){
                it.setQuality(it.getQuality()+1);
            }
        }

        if(it.getQuality()>50)
        {
            it.setQuality(50);
        }
        return  it.getQuality();
    }
}
