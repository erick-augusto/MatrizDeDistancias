
package matrizdedistancias;

public class MatrizDeDistancias {

    public static void main(String[] args) {
        //Leitura do teclado
        Grafo g = new Grafo();
        g.leDoTeclado();
        
        int n = g.totalDeVertices;
        int distancias[][] = new int[n][n];
        int[] vet = new int[n];
        int i, j;
    }
    
}
