package com.example.calculator.sorting;

public class Merge extends Sort {

    @Override
    public void sort() {
        displayBefore(input);
        merge(input,0,input.length-1);
        displayAfter(input);
    }

    //[5, 3, 8, 2]
    //s = 0 , e = 3 , m = 1
    public void  merge(int[] input, int start, int end){
        if(start>=end) {
            return;
        }
        int mid = (end+start)/2;
        merge(input , start,mid); //0,1
        merge(input , mid+1,end);//1+1 , 3
        mergeSort(input , start,mid,end);//[5, 3, 8, 2]-0-1-3
    }

    public void mergeSort(
            int[] arr,
            int left,
            int mid,
            int right
    ){
       int leftLength = mid -left + 1;
       int rightLength = right - mid;

       int[] leftArr = new int[leftLength];
       int[] rightArr = new int[rightLength];

       for(int i = 0;i<leftLength;i++){
           leftArr[i] = arr[i+left];
       }

        for(int j = 0;j<rightLength;j++){
            rightArr[j] = arr[j+mid+1];
        }

        int i=0,j=0,k=left; //[5, 3, 8, 2]
        while (i<leftLength && j<rightLength){
            if(leftArr[i]<rightArr[j]){
                input[k++] = leftArr[i++];
            }else{
                input[k++] = rightArr[j++];
            }
        }
        while (i<leftLength){
            input[k++] = leftArr[i++];
        }

        while (j<rightLength){
            input[k++] = rightArr[j++];
        }

    }
}

/**
 * MERGE SORT ANALYSIS:
 *
 * 1. Time Complexity:
 *    - Best Case:  O(n log n)
 *    - Worst Case: O(n log n)
 *    (Analysis: Always divides the array and merges regardless of initial order).
 *
 * 2. Space Complexity:
 *    - Best/Worst Case: O(n)
 *    (Analysis: Requires auxiliary arrays 'leftArr' and 'rightArr' to store elements).
 *
 * 3. Maintaining Element Order (Stability):
 *    - Stable: Yes (It preserves the relative order of duplicate elements).
 *
 * 4. Data Volume Performance:
 *    - Small Data: Not ideal due to memory allocation and recursion overhead.
 *    - Large Data: Very efficient; one of the best for large datasets.
 *
 * 5. Overall Best For: Large datasets where stability is required and extra memory is available.
 */
