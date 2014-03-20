package aauno;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class AAuno {

    public static void main(String[] args) throws IOException {
        
        
        String f;
        BufferedReader entrada =new BufferedReader(new InputStreamReader(System.in));   
        f = entrada.readLine();
        String[] elementos = f.split(",");
        int n = 10;                  //Tipos para escoger
        int r = elementos.length;   //Elementos elegidos
        Perm2(elementos, "", n, r);
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
    
}
