package projectzero.entities;

public class Item {

    protected String name;
    protected int value;
    protected String description;

    public Item(String name, int value, String description) {
	this.name = name;
	this.value = value;
	this.description = description;
    }

    public String getName() {

	return this.name;
    }

    public void setName(String name) {

	this.name = name;
    }

    public int getValue() {

	return this.value;
    }

    public void setValue(int value) {

	this.value = value;
    }

    public String getDescription() {

	return this.description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
    
    public String use() {
	return "Nothing happened.\n";
    }
}
