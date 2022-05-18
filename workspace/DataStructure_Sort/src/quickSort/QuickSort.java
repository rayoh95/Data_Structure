package quickSort;

import bubbleSort.ISort;

public class QuickSort implements ISort {

	@Override
	public void sort(int[] arr) {
		
		quickSort(arr, 0, arr.length - 1);
	}
	
	private void quickSort(int[] arr, int low, int high) {
		
		if (low >= high) {
			return;
		}
		
		int pivot = low + ((high - low) / 2);
		int pivotValue = arr[pivot];
		
		int left = low;
		int right = high;
		
		while (left <= right) {
			while (arr[left] < pivotValue) {
				left++;
			}
			
			while (arr[right] > pivotValue) {
				right--;
			}
			
			if (left <= right) {
				int temp = arr[right];
				arr[right] = arr[left];
				arr[left] = temp;
				left++;
				right--;
			}
		}
		
		quickSort(arr, low, right);	// 왼쪽의 서브 리스트
		quickSort(arr, low, high);	// 오른쪽의 서브 리스트
		
	}

}