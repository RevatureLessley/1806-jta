package discompanydatcompany.vendingmachine.entities;

import java.io.Serializable;

public class NoItem extends Item implements Serializable {

    public NoItem(String name, int value, String description) {
	this();
    }

    public NoItem() {
	super("@", 0, "This is not an item.");
    }

    @Override
    public String toString() {
	return "@";
    }
}
