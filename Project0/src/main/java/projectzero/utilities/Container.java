package projectzero.utilities;

import java.util.ArrayList;

public class Container extends Input {
    private ArrayList<String> container;
    
    public Container(ArrayList<String> container) {
	this.container = container;
    }

    public ArrayList<String> toArrayList() {
	return this.container;
    }

    public void add(String string) {
	this.container.add(string);
    }
}
