import java.util.*;

class QuickSort {
    
    public static void quicksort(int[] nums) {
	if (nums == null || nums.length == 0) {
	    return;
	}
	quicksorter(nums, 0, nums.length-1);
    }
    
    private static void quicksorter(int[] nums, int start, int end) {
	if (start >= end) return;
	int pivot = partition(nums, start, end);
	quicksorter(nums, start, pivot-1);
	quicksorter(nums, pivot+1, end);
    }

    private static int partition(int[] nums, int low, int high) {
	int pivot = nums[low];	// choose the first number as pivot
	int i = low+1, j = high;

	while (true) {
	    while (i < high) {
		if (nums[i] > pivot) {
		    break;
		} else {
		    i++;    
		}
	    }
	    while (j > low) {
		if (nums[j] < pivot) {
		    break;
		} else {
		    j--;    
		}
	    }
	    if (i >= j) break;	// invalid indices
	    swap(nums, i, j);  // swap the smaller to left and larger to right
	}
	swap(nums, j, low); // swap pivot to its correct position
	return j;
    } 
    
    private static void swap(int[] nums, int i, int j) {
	int temp = nums[i];
	nums[i] = nums[j];
	nums[j] = temp;
    }

    public static void main(String[] args) {
	int[] array = new int[] {1, 5, 4, 3, 2, 6, 3, 29, 13, 0, 23, 7, 9};
	System.out.println("Before sorting: ");
	for (int i : array) {
	    System.out.print(i + " ");
	}

	quicksort(array);
	System.out.println("\nAfter sorting: ");
	for (int i : array) {
	    System.out.print(i + " ");
	}
	System.out.println();
    }
}
