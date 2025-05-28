package com.mycompany.algoritmopractica5_2025;

public class algoritmosSorts {

    
    public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
        if (a == null || a.length < 2) return;
        mergeSortRec(a, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void mergeSortRec(T[] a, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSortRec(a, l, m);
            mergeSortRec(a, m + 1, r);
            merge(a, l, m, r);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<? super T>> void merge(T[] a, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        T[] L = (T[]) new Comparable[n1];
        T[] R = (T[]) new Comparable[n2];
        System.arraycopy(a, l, L, 0, n1);
        System.arraycopy(a, m + 1, R, 0, n2);
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                a[k++] = L[i++];
            } else {
                a[k++] = R[j++];
            }
        }
        while (i < n1) a[k++] = L[i++];
        while (j < n2) a[k++] = R[j++];
    }

    
    public static <T extends Comparable<? super T>> void quickSort(T[] A) {
        if (A == null || A.length < 2) return;
        quickSortRec(A, 0, A.length - 1);
    }

    private static <T extends Comparable<? super T>> void quickSortRec(T[] A, int ini, int fin) {
        int izq = ini, der = fin;
        T pivot = A[ini + (fin - ini) / 2];
        while (izq <= der) {
            while (A[izq].compareTo(pivot) < 0) izq++;
            while (A[der].compareTo(pivot) > 0) der--;
            if (izq <= der) {
                T aux = A[izq]; A[izq] = A[der]; A[der] = aux;
                izq++; der--;
            }
        }
        if (ini < der) quickSortRec(A, ini, der);
        if (izq < fin) quickSortRec(A, izq, fin);
    }

    
    public static <T extends Comparable<? super T>> void selectionSort(T[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j].compareTo(a[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            T temp = a[minIdx];
            a[minIdx] = a[i];
            a[i] = temp;
        }
    }

    
    public static <T extends Comparable<? super T>> void shellSort(T[] a) {
        int n = a.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                T temp = a[i];
                int j;
                for (j = i; j >= gap && a[j - gap].compareTo(temp) > 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = temp;
            }
        }
    }

    
    public static void radixSort(Integer[] a) {
        if (a == null || a.length < 2) return;
        // handle negatives by offsetting
        int min = a[0], max = a[0];
        for (int v : a) {
            if (v < min) min = v;
            if (v > max) max = v;
        }
        int offset = -min;
        int[] aux = new int[a.length];
        for (int i = 0; i < a.length; i++) aux[i] = a[i] + offset;
        int m = getMax(aux);
        for (int exp = 1; m / exp > 0; exp *= 10) {
            countingSortByDigit(aux, exp);
        }
        for (int i = 0; i < a.length; i++) a[i] = aux[i] - offset;
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
        for (int v : a) count[(v / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            int d = (a[i] / exp) % 10;
            output[--count[d]] = a[i];
        }
        System.arraycopy(output, 0, a, 0, n);
    }
}
