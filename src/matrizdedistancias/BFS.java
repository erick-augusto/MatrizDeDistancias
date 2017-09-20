
package matrizdedistancias;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    
    public BFS(){
        
    }
    
    protected final int INFINITO = 999999;
    
    public int[][] matDistancia;
    
    public int[] vet;
    
    public void buscaEmLargura(Grafo g, Vertice s){              
        //Vetor de Vértices com distância e cor acrescentados aos atributos originais
        Vertice[] vertices = g.getVertices();
        Vertice[] distancias = new Vertice[vertices.length];
        
        for(int i = 0; i < vertices.length; i++){
            int id = vertices[i].getIndice();
            String nome = vertices[i].getNome();
            Vertice v = new Vertice(id,nome,Constantes.INFINITO);
            v.cor = Cores.BRANCO;
            distancias[i] = v;
        }
        
        //Get do total de vértices
        int totaldevertices = g.getTotalDeVertices();
        
        //Get da matriz de adjacência
        Arco[][] matAdj = g.getMatrizDeAdjacencia();
        
        //Iniciando a matriz de distâncias com zero
        matDistancia = new int[totaldevertices][totaldevertices];
        int i = 0, j = 0;
        for (i = 0; i < totaldevertices; i++) {
            for (j = 0; j < totaldevertices; j++) {
                matDistancia[i][j] = 0;
            }
        }     
    }
    
    public int[][] getMatDistancia(){
        return matDistancia;
    }
    
    public int[] getVet(){
        return vet;
    }
}
