
package matrizdedistancias;

import java.util.*;

public class Fila {
    // Declaracao dos atributos.
    // Implementa a fila usando lista ligada.
    protected LinkedList q;

    // O construtor inicializa a lista ligada, inicialmente vazia,
    // sem nenhum elemento na lista.
    public Fila() {
        q = new LinkedList();
    }
    
    // Insere um elemento no fim da fila.
    // Note que todas as classe sao derivadas da classe Object.
    // Desta forma, podemos inserir qualquer objeto, por exemplo,
    // vértices na fila.
    public void insereNaFila(Object x) {
        q.addLast(x);
    }
    
    // Remove e devolve o primeiro da fila.
    public Object removeDaFila() {
        return q.removeFirst();
    }
    
    // Fila vazia?
    public boolean filaVazia() {
        if (q.size() <= 0)
            return true;
        return false;
    }
    
}
