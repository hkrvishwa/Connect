package com.example.calculator.sorting;

public class Insertion extends Sort {

    @Override
    public void sort() {
        displayBefore(input);
        for(int i=1;i<input.length;i++){
           int key = input[i];
           int j = i-1;
           while(j>=0 && input[j]>key){
               input[j+1] = input[j];
               j--;
            }
           input[j+1] = key;
        }
        displayAfter(input);
    }
}

/**
 * INSERTION SORT ANALYSIS:
 * 1. Time Complexity: Best O(n), Worst O(n²)
 * 2. Space Complexity: O(1)
 * 3. Stable: Yes
 * 4. Data: Excellent for small data or nearly sorted data.
 * 5. Overall: Best for small datasets (N < 50).
 */
