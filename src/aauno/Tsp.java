package aauno;
import java.util.*;
import java.text.*;

class TSP {

    int weight[][], n, tour[], finalCost;
    final int INF = 1000;

    public TSP() {
        String f;
        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese numero de nodos:=>");
        n = s.nextInt();
        weight = new int[n][n];
        tour = new int[n - 1];
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (i != j) {
                    System.out.print("Ingrese peso de nodo " + (i + 1) + " a " + (j + 1) + ":=>");
                    weight[i][j] = s.nextInt();
                    weight[j][i] = weight[i][j];
                }
            }
        }
        f="1,";
        for(int l=1;l<n;l++)
        {
            f=f+Integer.toString(l+1)+",";
        }
        
        String[] jk = f.split(",");
        int t=n;
        System.out.println();
        System.out.println("Los caminos posibles son : ");
        Perm2(jk,"",t,t);
        System.out.println();
        System.out.println("comenzando en el nodo 1.");
        eval();
    }
     private static void Perm2(String[] elem, String act, int n, int r) {
        if (n == 0) {
            System.out.println(act);
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(elem[i])) { // Controla que no haya repeticiones
                    Perm2(elem, act + elem[i] + ", ", n - 1, r);
                }
            }
        }
    }

    public int COST(int currentNode, int inputSet[], int setSize) {
        if (setSize == 0) {
            return weight[currentNode][0];
        }
        int min = INF, minindex = 0;
        int setToBePassedOnToNextCallOfCOST[] = new int[n - 1];
        for (int i = 0; i < setSize; i++) {
            int k = 0;//initialise new set
            for (int j = 0; j < setSize; j++) {
                if (inputSet[i] != inputSet[j]) {
                    setToBePassedOnToNextCallOfCOST[k++] = inputSet[j];
                }
            }
            int temp = COST(inputSet[i], setToBePassedOnToNextCallOfCOST, setSize - 1);
            if ((weight[currentNode][inputSet[i]] + temp) < min) {
                min = weight[currentNode][inputSet[i]] + temp;
                minindex = inputSet[i];
            }
        }
        return min;
    }

    public int MIN(int currentNode, int inputSet[], int setSize) {
        if (setSize == 0) {
            return weight[currentNode][0];
        }
        int min = INF, minindex = 0;
        int setToBePassedOnToNextCallOfCOST[] = new int[n - 1];
        for (int i = 0; i < setSize; i++)//considers each node of inputSet
        {
            int k = 0;
            for (int j = 0; j < setSize; j++) {
                if (inputSet[i] != inputSet[j]) {
                    setToBePassedOnToNextCallOfCOST[k++] = inputSet[j];
                }
            }
            int temp = COST(inputSet[i], setToBePassedOnToNextCallOfCOST, setSize - 1);
            if ((weight[currentNode][inputSet[i]] + temp) < min) {
                min = weight[currentNode][inputSet[i]] + temp;
                minindex = inputSet[i];
            }
        }
        return minindex;
    }

    public void eval() {
        int dummySet[] = new int[n - 1];
        for (int i = 1; i < n; i++) {
            dummySet[i - 1] = i;
        }
        finalCost = COST(0, dummySet, n - 1);
        constructTour();
    }

    public void constructTour() {
        int previousSet[] = new int[n - 1];
        int nextSet[] = new int[n - 2];
        for (int i = 1; i < n; i++) {
            previousSet[i - 1] = i;
        }
        int setSize = n - 1;
        tour[0] = MIN(0, previousSet, setSize);
        for (int i = 1; i < n - 1; i++) {
            int k = 0;
            for (int j = 0; j < setSize; j++) {
                if (tour[i - 1] != previousSet[j]) {
                    nextSet[k++] = previousSet[j];
                }
            }
            --setSize;
            tour[i] = MIN(tour[i - 1], nextSet, setSize);
            for (int j = 0; j < setSize; j++) {
                previousSet[j] = nextSet[j];
            }
        }
        display();
    }

    public void display() {
        System.out.println();
        System.out.print("El camino mas corto es es 1-");
        for (int i = 0; i < n - 1; i++) {
            System.out.print((tour[i] + 1) + "-");
        }
        System.out.print("1");
        System.out.println();
        System.out.println("El costo menor es " + finalCost);
    }
}

class TSPExp {

    public static void main(String args[]) {
        TSP obj = new TSP();
    }
}
