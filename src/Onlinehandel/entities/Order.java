package Onlinehandel.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Onlinehandel.provided.*;

/**
 * An order within the Onlinehandel.<br>
 * <br>
 * 
 * An order holds information on the costumer placing the order, the items
 * ordered and the status of the order which captures collection and delivery
 * date and time.<br>
 * <br>
 * 
 * The usual life cycle is
 * <ul>
 * <li>create an order with id, costumer and at least one item</li>
 * <li>add items</li>
 * <li>collect</li>
 * <li>deliver</li>
 * </ul>
 *
 */
public abstract class Order implements Comparable<Order> {
	private long id = 1;
	private Costumer costumer;
	private DateTime collected;
	private DateTime delivered;
	private java.util.Set<Item> goods;

	public Order(long id, Costumer c, Iterable<Item> items){
		this.id = id;
		this.costumer = c;
		goods = new HashSet<>();
		for(Item it : items){
			goods.add(new Item(it));
		}
	}

	public Order(Order orig){
		this.id = orig.id;
		this.costumer = new Costumer(orig.costumer);
		this.goods = new HashSet<>();
		for(Item it : orig.goods){
			this.goods.add(it);
		}
	}

	public abstract int getTotal();

	public boolean isCollected(){
		return collected != null;
	}

	public boolean isDelivered(){
		return delivered != null;
	}

	public final boolean addItems(Item item){
		if(!isCollected() || !isDelivered())
			return false;
		return goods.add(item);
	}

	public final boolean addGoods(Iterable<Item> items) {
		if(isCollected() || isDelivered())
			return false;

		boolean added = false;
		for (Item it : items) {
			if(goods.add(it))
				added = true;
		}
		return added;
	}

	public java.util.Set<Item> getItems(){
		Set<Item> copy = new HashSet<>();
		for(Item it : goods){
			copy.add(it);
		}
		return copy;
	}

	public final boolean collect(DateTime toc){
		if(isCollected())
			return false;

			collected = new DateTime(toc);
		return true;
	}

	public final boolean deliver(DateTime tod){
		if(isDelivered())
			return false;

			delivered = new DateTime(tod);
		return true;
	}

	private final String ensureNonNullNonEmpty(String str){
		if(str == null || str.isEmpty())
			throw new IllegalArgumentException();
		return str;
	}

	public int compareTo(Order arg0){
		return Long.compare(this.id, arg0.id);
	}

	public Costumer getCostumer(){
		return this.costumer;
	}


	/**
	 * creates a string representation of this delivery.<br>
	 * 
	 * @ProgrammingProblem.Hint provided
	 * 
	 */
	@Override
	public String toString() {
		return String.format("%d " + "[%scollected, %sdelivered] (EUR %.2f)\n" + "%s", //
				id, isCollected() ? "" : "not ", isDelivered() ? "" : "not ", getTotal() / 100.,
				goods == null ? "no stock" : goods);
	}

}
