
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

        //Início do cálculo do BFS 
        BFS bfs = new BFS();
        for (i = 0; i < g.vertices.length; i++) {
            bfs.buscaEmLargura(g, g.vertices[i]);
            vet = bfs.getVet();
            for (j = 0; j < n; j++){
                distancias[g.vertices[i].getIndice()][j] = vet[j];
            }
        }

        //Impressão da matriz de distâncias
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if(distancias[i][j] == Constantes.INFINITO){
                    System.out.print(". ");
                } else{
                    System.out.print(distancias[i][j]+" ");                
                }
            }
            System.out.println("");
        }
    }
    
}
