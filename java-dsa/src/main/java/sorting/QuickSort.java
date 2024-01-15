package sorting;

public class QuickSort {
void quicksort(int[] arr, int low, int high) {
    if(low < high) {
        int pivot = partition(arr, low, high);
        quicksort(arr, low, pivot-1);
        quicksort(arr, pivot+1, high);
    }
}

int partition(int[] arr, int low, int high) {
   int pivot = arr[high]; //picking the rightmost to begin with
   int i = low - 1; // helps identify the position of pivot - which partitions the array as desired
   for(int j = low; j < high; j++) {
       if(arr[j] < pivot) {
           i++;
           swap(arr, i,j);
       }
   }
   swap(arr, i+1, high);
   return (i+1);
}

void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}

    public static void main(String[] args) {
    }
}
