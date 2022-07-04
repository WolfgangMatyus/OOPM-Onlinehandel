package Onlinehandel.entities;

public class Item extends Object {

    private int amount = 1;
    private String description;
    private int value = 1;


    public Item(String description, int amount, int value){
        this.description = ensureNonNullNonEmpty(description);
        this.amount = amount;
        this.value = value;

    }

    public Item(Item it) {
        this.amount = it.amount;
        this.value = it.value;
        this.description = it.description;
    }

    private final String ensureNonNullNonEmpty(String str){
        if(str == null || str.isEmpty())
            throw new IllegalArgumentException();
        return str;
    }

    public int totalValue(){
        return value * amount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
