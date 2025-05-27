package com.mycompany.algoritmopractica5_2025;

import java.util.Arrays;

public class test{
      public static void main(String[] args) {
        int[] data = {5, 3, 8, 1, 2, 7};
        int[] arr;
        System.out.println("Datos iniciales: " + Arrays.toString(data));
        arr = data.clone();
        System.out.println("QuickSort antes:  " + Arrays.toString(arr));
        algoritmosSorts.quickSort(arr);
        System.out.println("QuickSort después:" + Arrays.toString(arr));

        arr = data.clone();
        System.out.println("SelectionSort antes:  " + Arrays.toString(arr));
        algoritmosSorts.selectionSort(arr);
        System.out.println("SelectionSort después:" + Arrays.toString(arr));

        arr = data.clone();
        System.out.println("ShellSort antes:  " + Arrays.toString(arr));
        algoritmosSorts.shellSort(arr);
        System.out.println("ShellSort después:" + Arrays.toString(arr));

        arr = data.clone();
        System.out.println("MergeSort antes:  " + Arrays.toString(arr));
        algoritmosSorts.mergeSort(arr);
        System.out.println("MergeSort después:" + Arrays.toString(arr));

        arr = data.clone();
        System.out.println("RadixSort antes:  " + Arrays.toString(arr));
        algoritmosSorts.radixSort(arr);
        System.out.println("RadixSort después:" + Arrays.toString(arr));
    }
}
