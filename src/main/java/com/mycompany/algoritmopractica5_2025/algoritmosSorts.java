package com.mycompany.algoritmopractica5_2025;
public class algoritmosSorts{

    // existing methods (quickSort, selectionSort, shellSort)…

    public static void mergeSort(int[] a) {
        if (a == null || a.length < 2) return;
        mergeSortRec(a, 0, a.length - 1);
    }

    private static void mergeSortRec(int[] a, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSortRec(a, l, m);
            mergeSortRec(a, m + 1, r);
            merge(a, l, m, r);
        }
    }

    private static void merge(int[] a, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = a[l + i];
        for (int j = 0; j < n2; j++) R[j] = a[m + 1 + j];
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k++] = L[i++];
            } else {
                a[k++] = R[j++];
            }
        }
        while (i < n1) a[k++] = L[i++];
        while (j < n2) a[k++] = R[j++];
    }

    public static void radixSort(int[] a) {
        if (a == null || a.length < 2) return;
        int max = getMax(a);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(a, exp);
        }
    }

    private static int getMax(int[] a) {
        int mx = a[0];
        for (int v : a) if (v > mx) mx = v;
        return mx;
    }

    private static void countingSortByDigit(int[] a, int exp) {
        int n = a.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            count[(a[i] / exp) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int d = (a[i] / exp) % 10;
            output[--count[d]] = a[i];
        }
        System.arraycopy(output, 0, a, 0, n);
    }


    public static void quickSort(int[] A) {
        if (A == null || A.length < 2) return;
        quickSortRec(A, 0, A.length - 1);
    }

    private static void quickSortRec(int[] A, int ini, int fin) {
        int izq = ini, der = fin, pos = ini;
        boolean band = true;
        while (band) {
            band = false;
            while (A[pos] <= A[der] && pos != der) der--;
            if (pos != der) {
                int aux = A[pos]; A[pos] = A[der]; A[der] = aux;
                pos = der;
                while (A[pos] >= A[izq] && pos != izq) izq++;
                if (pos != izq) {
                    band = true;
                    aux = A[pos]; A[pos] = A[izq]; A[izq] = aux;
                    pos = izq;
                }
            }
        }
        if (pos - 1 > ini) quickSortRec(A, ini, pos - 1);
        if (fin > pos + 1) quickSortRec(A, pos + 1, fin);
    }

    public static void selectionSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int menor = a[i], k = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < menor) {
                    menor = a[j];
                    k = j;
                }
            }
            a[k] = a[i];
            a[i] = menor;
        }
    }

    public static void shellSort(int[] a) {
        int n = a.length;
        int gap = n;
        while (gap > 1) {
            gap = gap / 2;
            boolean band = true;
            while (band) {
                band = false;
                for (int i = 0; i + gap < n; i++) {
                    if (a[i] > a[i + gap]) {
                        int aux = a[i];
                        a[i] = a[i + gap];
                        a[i + gap] = aux;
                        band = true;
                    }
                }
            }
        }
    }

    
}
