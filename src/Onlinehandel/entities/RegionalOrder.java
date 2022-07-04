package Onlinehandel.entities;

import Onlinehandel.provided.Costumer;

public class RegionalOrder extends Order{
    private boolean express;

    public RegionalOrder(long id, Costumer c, Iterable<Item> items) {
        super(id, c, items);
    }

    public RegionalOrder(long id, Costumer c, Iterable<Item> items, boolean express){
        super(id, c, items);
        this.express = express;
    }

    public RegionalOrder(Order orig) {
        super(orig);
    }


    @Override
    public int getTotal() {

        int total = 0;
        for(Item it : getItems().toArray(new Item[0]))
            total += it.totalValue();
            if(express)
            total *= 1.2;

        return total;
    }
}
