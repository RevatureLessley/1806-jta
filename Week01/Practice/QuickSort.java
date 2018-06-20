
import java.lang.StringBuffer;

public class QuickSort {

    int[] _sorted;

    public QuickSort(int[] array) {
	this._sorted =new int[array.length];
	
	for (int i = 0; i < array.length; i++) {
	    _sorted[i] = array[i];
	}
    }
    
    public void quicksort() {
	quicksort(0, this._sorted.length - 1);
    }

    public void quicksort(int lowerBound, int higherBound) {
	int pivot;
	
	if (lowerBound < higherBound) {
	    pivot = partition(lowerBound, higherBound);
	    quicksort(lowerBound, pivot);
	    quicksort(pivot + 1, higherBound);
	}
    }

    public int partition(int lowerBound, int higherBound) {
	int pivot = this._sorted[lowerBound];
	
	int i = lowerBound - 1;
	int j = higherBound + 1;

	while (true) {
	
	    do {
		i += 1;
	    } while (this._sorted[i] < pivot);

	    do {
		j -= 1;
	    } while (this._sorted[j] > pivot);

	    if (i >= j ) {
		return j;
	    }
	

	    System.out.println("Element " + this._sorted[i] + " at index " + i);
	    System.out.println("is swapped with element " + this._sorted[j] + " at index " + j);
	    swap(i, j);
	}
    }

    private void swap(int i, int j) {
	int temp = this._sorted[i];
	this._sorted[i] = this._sorted[j];
	this._sorted[j] = temp;
    }

    public int[] getSorted() {
	return this._sorted;
    }

    public static void main(String[] args) {
	StringBuffer stringBuffer = new StringBuffer();
	int[] array = {5, 1, 8, 7, 4};
	int[] array2 = {1, 3, 5, 3};
	int[] array3= {10, 10, 8, 9, 4, 3, 1, 7, 6};
	QuickSort quickSort = new QuickSort(array);
	QuickSort quickSort2 = new QuickSort(array2);
	QuickSort quickSort3 = new QuickSort(array3);
	
	quickSort.quicksort();
	quickSort2.quicksort();
	quickSort3.quicksort();
	
	stringBuffer.append("\n(array) Original: ");
	for (int i : array){
	    stringBuffer.append(i + "  ");
	}
	stringBuffer.append("\n(array) sorted: ");
	for (int i : quickSort.getSorted()) {
	    stringBuffer.append(i + "  ");
	}
	
	stringBuffer.append("\n(array2) Original: ");
	for (int i : array2){
	    stringBuffer.append(i + "  ");
	}
	stringBuffer.append("\n(array2) sorted: ");
	for (int i : quickSort2.getSorted()) {
	    stringBuffer.append(i + "  ");
	}
	
	stringBuffer.append("\n(array3) Original: ");
	for (int i : array3){
	    stringBuffer.append(i + "  ");
	}
       
	stringBuffer.append("\n(array3) sorted: ");
	for (int i : quickSort3.getSorted()) {
	    stringBuffer.append(i + "  ");
	}
	stringBuffer.append("\n");

	System.out.println(stringBuffer.toString());
    }
}
