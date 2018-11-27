package edu.insightr.gildedrose;

public class UpdateVisitor implements IVisitor{

    public  void visit(Item i) {
        if (i.getClass() == AgedBrie.class) {
            visit((AgedBrie) i);
        } else if (i.getClass() == ConjuredManaCake.class) {
            visit((ConjuredManaCake) i);
        } else if (i.getClass() == ElexirMongoose.class) {

            visit((ElexirMongoose) i);
        } else if (i.getClass() == Backstage.class) {
            visit((Backstage) i);
        } else if (i.getClass() == DexterityVest.class) {
            visit((DexterityVest) i);
        }
    }

   public void visit(AgedBrie ab)
    {
        ab.setSellIn(ab.getSellIn()-1);

        if(ab.getQuality()<50)
        {
            ab.setQuality(ab.getQuality()+1);
        }

        if(ab.getSellIn() < 0){
            if(ab.getQuality() < 50){
                ab.setQuality(ab.getQuality()+1);
            }
        }

        if(ab.getQuality()>50)
        {
            ab.setQuality(50);
        }

    }

    public void visit(Backstage bk)
    {
        bk.setSellIn(bk.getSellIn()-1);

        if(bk.sellIn>0 )
        {
            if(bk.getQuality()<50)
            {
                if(bk.getSellIn() > 10){
                    bk.setQuality(bk.getQuality()+1);
                }
                if(bk.getSellIn()<=10)
                {
                    bk.setQuality(bk.getQuality()+2);
                }
                if(bk.getSellIn()<=5)
                {
                    bk.setQuality(bk.getQuality()+1);
                }
            }

            else
            {
                bk.setQuality(50);
            }
        }
        else
        {
            bk.setQuality(0);
        }

    }

    public void visit(ElexirMongoose em)
    {
        em.setSellIn(em.getSellIn()-1);

        if(em.getSellIn()>0)
        {
            em.setQuality(em.getQuality()-1);
        }
        else
        {
            em.setQuality(em.getQuality()-2);

        }
        if(em.getQuality()<0)
        {
            em.setQuality(0);
        }
    }

    public void visit(ConjuredManaCake cmc)
    {
        cmc.setSellIn(cmc.getSellIn()-1);

        if(cmc.getSellIn()>0)
        {
            cmc.setQuality(cmc.getQuality()-2);
        }
        else
        {
            cmc.setQuality(cmc.getQuality()-4);

        }
        if(cmc.getQuality()<0)
        {
            cmc.setQuality(0);
        }
    }

    public void visit(DexterityVest dk)
    {
        dk.setSellIn(dk.getSellIn()-1);

        if(dk.getSellIn()>=0)
        {
            dk.setQuality(dk.getQuality()-1);
        }
        else
        {

            dk.setQuality(dk.getQuality()-2);

        }
        if(dk.getQuality()<0)
        {
            dk.setQuality(0);
        }
    }


}
