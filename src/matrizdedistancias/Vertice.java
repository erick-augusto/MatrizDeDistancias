
package matrizdedistancias;

public class Vertice {
    private int indice; // Indice do vertice no grafo.
    private String nome; // Nome do vertice.

    // Publicos intencionalmente para serem acessados diretamente pelos algoritmos.
    public int d;     // Guarda uma distancia ou um instante de descoberta 
                       // de uma busca em profundidade.
    public int chave; // Uma chave para FilaDePrioridade.
    public int f;   // Um instante de finalizacao de uma busca em profundidade.
    public Cores cor; // Guarda uma Cores.
    
    public Vertice PI;  // Guarda o vertice predecessor em um caminho.
    public Vertice pai; // Guarda o pai do vertice em uma arvore.
    
    // O construtor recebe o indice e o nome do vertice.
    public Vertice(int i, String n) {
        indice = i; nome = n;
        d = 0; chave = 0; cor = Cores.SEMCOR; PI = null; pai = null;
    }

    // O construtor recebe o indice, o nome e o valor para o atributo d.
    public Vertice(int i, String n, int dOuChave) {
        this(i, n);
        this.d = dOuChave;       // Distancia no EP1 e no EP4.
        this.chave = dOuChave;   // Chave no EP2.
    }
    
    // Devolve o indice do vertice.
    public int getIndice() {
        return indice;
    }

    // Devolve o nome do vertice.
    public String getNome() {
        return nome;
    }
}
