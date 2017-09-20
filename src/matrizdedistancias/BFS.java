
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
        
        //Começo do BFS pelo vértice dado
        s.d = 0;
        s.cor = Cores.CINZA;
        distancias[s.getIndice()].d = 0;
        distancias[s.getIndice()].cor = Cores.CINZA;
        Fila Q = new Fila();
        Q.insereNaFila(s);
        //Queue<Vertice> Q = new LinkedList();
        //Q.add(s);
        
        //Laço para realizar a busca de caminhos BRANCOS em todos os nós a partir de s
        while (!Q.filaVazia()) {
            Vertice u = (Vertice) Q.removeDaFila();           
            for (i = 0; i < totaldevertices; i++) {
                Arco a = matAdj[u.getIndice()][i];
                if (a != null) {
                    int ind = a.getDestino().getIndice();
                    if (distancias[ind].cor.equals(Cores.BRANCO)) {
                        Vertice v = a.getDestino();
                        distancias[v.getIndice()].cor = Cores.CINZA;
                        distancias[v.getIndice()].d = u.d + 1;
                        //matDistancia[s.indice][i] = distancias[v.indice].d;
                        Q.insereNaFila(distancias[v.getIndice()]);
                    }
                }
            }
            distancias[u.getIndice()].cor = Cores.PRETO;
        }  
        
        vet = new int[totaldevertices];
        //System.out.println("Impriminto vet:");
        for(i = 0; i < totaldevertices; i ++){
            vet[i] = distancias[i].d;
            //vet[i] = matDistancia[s.indice][i];
            //System.out.print(vet[i]+" ");
        }
        
        //Fim do BFS para o vértice dado e impressão da matriz de distâncias referente ao vértice
        /*for (i = 0; i < totaldevertices; i++) {
            if(matDistancia[s.indice][i] == 0 && s.indice!=i){
                System.out.print(". ");
            } else{
                System.out.print(matDistancia[s.indice][i]+" ");
            }            
        }
        System.out.println("");*/      
    }
    
    public int[][] getMatDistancia(){
        return matDistancia;
    }
    
    public int[] getVet(){
        return vet;
    }
}
