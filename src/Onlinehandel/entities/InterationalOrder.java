package Onlinehandel.entities;

import Onlinehandel.provided.Costumer;

public class InterationalOrder extends Order{
    private float custom;

    public InterationalOrder(long id, Costumer c, Iterable<Item> items) {
        super(id, c, items);
    }

    InterationalOrder(long id, Costumer c, Iterable<Item> items, float custom){
        super(id, c, items);
        this.custom = custom;
    }

    public InterationalOrder(Order orig) {
        super(orig);
    }

    @Override
    public int getTotal() {
        if(getItems().size() < 1)
            return -1;

        int total = 0;
        for(Item it : getItems().toArray(new Item[0]))
            total += it.totalValue();

        return total;
    }
}
