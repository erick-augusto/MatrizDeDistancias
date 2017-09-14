
package matrizdedistancias;

import java.io.*;
import java.util.*;
/**
 * A classe <tt>Keyboard</tt> permite a leitura de dados de tipos nativos e de
 * intâncias da classe <tt>String</tt> a partir do teclado. Basicamente esta
 * classe encapsula o funcionamento do Stream <tt>System.in</tt>, que pode ser
 * usado como entrada padrão de dados.
 * <p>
 * Esta classe é baseada na classe <tt>EasyIn</tt>, de Peter van der Linden.
 * As principais modificações foram:
 * <ul>
 * <li>Os métodos são estáticos para facilitar o uso da classe.
 * <li>Criação de métodos sobrecarregados para que valores <i>default</i>
 * possam ser usados.
 * <li>Melhor tratamento da exceções.
 * <li>Reconhecimento de vários valores como booleanos e capacidade de leitura
 * de <tt>NaNs</tt> e infinitos para valores dos tipos <tt>float</tt> e 
 * <tt>double</tt>.
 * <li>Descrição dos detalhes de implementação com comentários.
 * </ul>
 * A classe permite o uso de arquivos de respostas (<i>"answer files"</i>),
 * que são arquivos contendo os dados que deveriam ser entrados pelo teclado.
 * A entrada destes dados pode ser simulada através de redirecionamento de
 * arquivos. Se, por exemplo, tivermos uma aplicação (classe)
 * <tt>UsaDataKeyboard</tt> e quisermos usar um arquivo de respostas chamado
 * <tt>dados.txt</tt> que já contém os dados que devem ser digitados, podemos
 * executar o comando <tt>java UsaDataKeyboard &lt; dados.txt </tt> para ler
 * os dados do arquivo em vez do teclado. O comando será o mesmo para todos os
 * sistemas operacionais que possuam terminal de linha de comando.
 * <p>
 * O suporte a arquivos de respostas é limitado, somente funcionará se todos
 * os dados a serem digitados forem entrados no arquivo de respostas, um em
 * cada linha. Exceções ocorrerão caso estas regras não sejam seguidas.
 * <p>
 * A mensagem original de copyright de Peter van der Linden é mostrada ao final
 * desta listagem. Vale a pena notar que a sintaxe proposta e mostrada na
 * mensagem é diferente da usada nesta classe.
 * <p>
 * O livro <b>"An Introduction to Computer Science Using Java"</b>, de Samuel
 * N. Kamin, M. Dennis Mickunas e Edward M. Reingold (editora McGraw-Hill,
 * ISBN 0-07-034224-5) também apresenta uma classe <tt>Keyboard</tt>, cujo
 * funcionamento e código são diferentes desta.
 *
 * @autor Rafael Santos
 * @version 1.3
 */
public class Keyboard  {
  // Declaração dos atributos privados desta classe.
  // O atributo is é uma instância da classe InputStreamReader que será
  // construída a partir do Stream System.in. Note que já que esta classe só
  // tem métodos estáticos, não terá construtor, mas podemos inicializar
  // instâncias de classes da forma mostrada a seguir.
  private static InputStreamReader is = new InputStreamReader(System.in);
  // O atributo br é uma instância da classe BufferedReader que usa is como
  // argumento para seu construtor.
  private static BufferedReader br = new BufferedReader(is);
  // O atributo st é uma instância da classe StringTokenizer que será usada em
  // vários métodos nesta classe.
  private static StringTokenizer st;
  // O atributo nt é uma instância da classe String que será usada em vários
  // métodos nesta classe.
  private static String nt;
  // O atributo debug estabelece se mensagens de erro de conversão e leitura
  // devem ser mostradas ou não. O atributo não pode ser modificado diretamente,
  // somente através de métodos específicos desta classe.
  private static boolean debug = false;
  
  /**
   * O método <tt>getToken</tt> lê uma String do <tt>BufferedReader</tt>
   * associado com a entrada padrão e devolve uma instância de
   * <tt>StringTokenizer</tt> contendo os <i>tokens</i> criados a partir da
   * linha lida. O método é declarado como <tt>private</tt> pois só deverá ser
   * chamado a partir de outros métodos da classe.
   * @return Uma instância de <tt>StringTokenizer</tt> contendo os
   *         <i>tokens</i> obtidos da linha lida.
   * @exception IOException se um erro de entrada e saída ocorrer.
   * @exception NullPointerException se uma String vazia for lida e tentarmos
   *            construir uma instância de <tt>StringTokenizer</tt> com ela.
   * @see java.util.StringTokenizer <tt>StringTokenizer</tt>
   */
  private static StringTokenizer getToken() throws IOException, 
      NullPointerException
    {
    String s = br.readLine();
    return new StringTokenizer(s);
    }

  /**
   * O método <tt>readBoolean</tt> lê e devolve um valor do tipo
   * <tt>boolean</tt>. Este método simplesmente chama o método
   * <tt>readBoolean</tt> com argumentos, descrito a seguir, considerando o
   * valor <i>default</i> como sendo <tt>true</tt>.
   * @return O valor do tipo <tt>boolean</tt> lido (ou, em caso de erro 
   *         de leitura, <tt>true</tt>).
   */
  public static boolean readBoolean() 
    {
    return readBoolean(true);
    }

  /**
   * O método <tt>readBoolean</tt> lê e devolve um valor do tipo
   * <tt>boolean</tt>. Este método tenta pegar o próximo <i>token</i> a ser
   * lido da entrada padrão e o compara com algumas Strings constantes para
   * verificar a igualdade, devolvendo <tt>true</tt> ou <tt>false</tt>
   * dependendo do resultado da comparação.
   * @param defaultValue O valor <i>default</i> caso não seja possível ler e
   *        decodificar um valor booleano.
   * @return O valor do tipo <tt>boolean</tt> lido (ou, em caso de erro 
   *         de leitura, igual ao argumento passado para o método).
   */
  public static boolean readBoolean(boolean defaultValue) 
    {
    try
      {
      st = getToken();  // Lê os tokens da linha,
      nt = st.nextToken(); // e usa a primeira String dos tokens.
      // Devolve true se a String for igual a...
      if (nt.equalsIgnoreCase("true") ||  
          nt.equalsIgnoreCase("t") ||  
          nt.equalsIgnoreCase("yes") ||  
          nt.equalsIgnoreCase("y") ||  
          nt.equalsIgnoreCase("on") ||  
          nt.equalsIgnoreCase("v") ||  
          nt.equalsIgnoreCase("s") ||  
          nt.equalsIgnoreCase("sim") ||  
          nt.equalsIgnoreCase("verdadeiro"))
        return true;
      // Devolve false se a String for igual a...
      else if (nt.equalsIgnoreCase("false") ||  
                nt.equalsIgnoreCase("f") ||  
                nt.equalsIgnoreCase("no") ||  
                nt.equalsIgnoreCase("n") ||  
                nt.equalsIgnoreCase("off") ||  
                nt.equalsIgnoreCase("nao") ||  
                nt.equalsIgnoreCase("não") ||  
                nt.equalsIgnoreCase("falso"))
        return false;
      // (Alteração: 2016/04/19) 0: false; 1: true
      if (nt.startsWith("0"))
        return false;
      else if (nt.startsWith("1") || nt.startsWith("2") || nt.startsWith("3") || nt.startsWith("4") || nt.startsWith("5") || nt.startsWith("6") || nt.startsWith("7") || nt.startsWith("8") || nt.startsWith("9"))
        return true;
      // Se não for nenhum dos valores reconhecidos, devolve o default.
      else
        return defaultValue;
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e saída lendo um "+
                           "boolean. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se não houver tokens,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada não contém um boolean. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O método <tt>readByte</tt> lê e devolve um valor do tipo <tt>byte</tt>.
   * Este método simplesmente chama o método <tt>readByte</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>(byte)0</tt>.
   * @return O valor do tipo <tt>byte</tt> lido (ou, em caso de erro de
   *         leitura, <tt>(byte)0</tt>).
   */
  public static byte readByte() 
    {
    return readByte((byte) 0);
    }

  /**
   * O método <tt>readByte</tt> lê e devolve um valor do tipo <tt>byte</tt>.
   * Este método tenta pegar o próximo <i>token</i> a ser lido da entrada
   * padrão e o passa como argumentos para o método <tt>parseByte</tt> da
   * classe <tt>Byte</tt>, que tenta convertê-lo. Se a leitura e conversão
   * puderem ser feitas, um valor do tipo <tt>byte</tt> é devolvido, caso
   * contrário o valor <i>default</i> (passado como argumento) é devolvido.
   * @param defaultValue O valor <i>default</i> caso não seja possível ler e
   *        decodificar um valor do tipo <tt>byte</tt>.
   * @return O valor do tipo <tt>byte</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o método).
   */
  public static byte readByte(byte defaultValue) 
    {
    try
      {
      st = getToken();  // Lê os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      // Devolve o valor processado pela classe Byte.
      return Byte.parseByte(nt);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e saída lendo um "+
                           "byte. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NumberFormatException nfe)  // Se houver erro de conversão,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro convertendo "+nt+" para "+
                           "um byte. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se não houver tokens,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada não contém um byte. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O método <tt>readShort</tt> lê e devolve um valor do tipo <tt>short</tt>.
   * Este método simplesmente chama o método <tt>readShort</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>(short)0</tt>.
   * @return O valor do tipo <tt>short</tt> lido (ou, em caso de erro de
   *         leitura, <tt>(short)0</tt>).
   */
  public static short readShort() 
    {
    return readShort((short) 0);
    }

  /**
   * O método <tt>readShort</tt> lê e devolve um valor do tipo <tt>short</tt>.
   * Este método tenta pegar o próximo <i>token</i> a ser lido da entrada
   * padrão e o passa como argumentos para o método <tt>parseShort</tt> da
   * classe <tt>Short</tt>, que tenta convertê-lo. Se a leitura e conversão
   * puderem ser feitas, um valor do tipo <tt>short</tt> é devolvido, caso
   * contrário o valor <i>default</i> (passado como argumento) é devolvido.
   * @param defaultValue O valor <i>default</i> caso não seja possível ler e
   *        decodificar um valor do tipo <tt>short</tt>.
   * @return O valor do tipo <tt>short</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o método).
   */
  public static short readShort(short defaultValue) 
    {
    try
      {
      st = getToken();  // Lê os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      // Devolve o valor processado pela classe Short.
      return Short.parseShort(nt);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e saída lendo um "+
                           "short. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NumberFormatException nfe)  // Se houver erro de conversão,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro convertendo "+nt+" para "+
                           "um short. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se não houver tokens,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada não contém um short. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O método <tt>readInt</tt> lê e devolve um valor do tipo <tt>int</tt>.
   * Este método simplesmente chama o método <tt>readInt</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>0</tt>.
   * @return O valor do tipo <tt>int</tt> lido (ou, em caso de erro de
   *         leitura, <tt>0</tt>).
   */
  public static int readInt() 
    {
    return readInt(0);
    }

  /**
   * O método <tt>readInt</tt> lê e devolve um valor do tipo <tt>int</tt>.
   * Este método tenta pegar o próximo <i>token</i> a ser lido da entrada
   * padrão e o passa como argumentos para o método <tt>parseInt</tt> da
   * classe <tt>Integer</tt>, que tenta convertê-lo. Se a leitura e conversão
   * puderem ser feitas, um valor do tipo <tt>int</tt> é devolvido, caso
   * contrário o valor <i>default</i> (passado como argumento) é devolvido.
   * @param defaultValue O valor <i>default</i> caso não seja possível ler e
   *        decodificar um valor do tipo <tt>int</tt>.
   * @return O valor do tipo <tt>int</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o método).
   */
  public static int readInt(int defaultValue) 
    {
    try
      {
      st = getToken();  // Lê os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      // Devolve o valor processado pela classe Integer.
      return Integer.parseInt(nt);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e saída lendo um "+
                           "int. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NumberFormatException nfe)  // Se houver erro de conversão,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro convertendo "+nt+" para "+
                           "um int. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se não houver tokens,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada não contém um int. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O método <tt>readLong</tt> lê e devolve um valor do tipo <tt>long</tt>.
   * Este método simplesmente chama o método <tt>readLong</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>0l</tt>.
   * @return O valor do tipo <tt>long</tt> lido (ou, em caso de erro de
   *         leitura, <tt>0l</tt>).
   */
  public static long readLong() 
    {
    return readLong(0l);
    }

  /**
   * O método <tt>readLong</tt> lê e devolve um valor do tipo <tt>long</tt>.
   * Este método tenta pegar o próximo <i>token</i> a ser lido da entrada
   * padrão e o passa como argumentos para o método <tt>parseLong</tt> da
   * classe <tt>Long</tt>, que tenta convertê-lo. Se a leitura e conversão
   * puderem ser feitas, um valor do tipo <tt>long</tt> é devolvido, caso
   * contrário o valor <i>default</i> (passado como argumento) é devolvido.
   * @param defaultValue O valor <i>default</i> caso não seja possível ler e
   *        decodificar um valor do tipo <tt>long</tt>.
   * @return O valor do tipo <tt>long</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o método).
   */
  public static long readLong(long defaultValue) 
    {
    try
      {
      st = getToken();  // Lê os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      // Devolve o valor processado pela classe Long.
      return Long.parseLong(nt);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e saída lendo um "+
                           "long. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NumberFormatException nfe)  // Se houver erro de conversão,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro convertendo "+nt+" para "+
                           "um long. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se não houver tokens,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada não contém um long. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O método <tt>readFloat</tt> lê e devolve um valor do tipo <tt>float</tt>.
   * Este método simplesmente chama o método <tt>readFloat</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>0f</tt>.
   * @return O valor do tipo <tt>float</tt> lido (ou, em caso de erro de
   *         leitura, <tt>0f</tt>).
   */
  public static float readFloat() 
    {
    return readFloat(0f);
    }

  /**
   * O método <tt>readFloat</tt> lê e devolve um valor do tipo <tt>float</tt>.
   * Este método tenta pegar o próximo <i>token</i> a ser lido da entrada
   * padrão e o analisa, verificando se aparentemente é <tt>NaN</tt> ou
   * infinito. Se não for, passa o valor lido como argumento para o método
   * <tt>parseFloat</tt> da classe <tt>Float</tt>, que tenta convertê-lo.
   * Se a leitura e conversão puderem ser feitas, um valor do tipo <tt>float</tt>
   * é devolvido, caso contrário o valor <i>default</i> (passado como argumento)
   * é devolvido.
   * @param defaultValue O valor <i>default</i> caso não seja possível ler e
   *        decodificar um valor do tipo <tt>float</tt>.
   * @return O valor do tipo <tt>float</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o método).
   */
  public static float readFloat(float defaultValue) 
    {
    try
      {
      st = getToken();  // Lê os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      if (nt.toLowerCase().startsWith("nan"))
        return Float.NaN;
      else if (nt.toLowerCase().startsWith("inf"))
        return Float.POSITIVE_INFINITY;
      else if (nt.toLowerCase().startsWith("+inf"))
        return Float.POSITIVE_INFINITY;
      else if (nt.toLowerCase().startsWith("-inf"))
        return Float.NEGATIVE_INFINITY;
      else  // Devolve o valor processado pela classe Float.
        return Float.parseFloat(nt);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e saída lendo um "+
                           "float. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NumberFormatException nfe)  // Se houver erro de conversão,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro convertendo "+nt+" para "+
                           "um float. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se não houver tokens,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada não contém um float. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O método <tt>readDouble</tt> lê e devolve um valor do tipo <tt>double</tt>.
   * Este método simplesmente chama o método <tt>readDouble</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>0.0</tt>.
   * @return O valor do tipo <tt>double</tt> lido (ou, em caso de erro de
   *         leitura, <tt>0.0</tt>).
   */
  public static double readDouble() 
    {
    return readDouble(0.0);
    }

  /**
   * O método <tt>readDouble</tt> lê e devolve um valor do tipo <tt>double</tt>.
   * Este método tenta pegar o próximo <i>token</i> a ser lido da entrada
   * padrão e o analisa, verificando se aparentemente é <tt>NaN</tt> ou
   * infinito. Se não for, passa o valor lido como argumento para o método
   * <tt>parseDouble</tt> da classe <tt>Double</tt>, que tenta convertê-lo.
   * Se a leitura e conversão puderem ser feitas, um valor do tipo <tt>double</tt>
   * é devolvido, caso contrário o valor <i>default</i> (passado como argumento)
   * é devolvido.
   * @param defaultValue O valor <i>default</i> caso não seja possível ler e
   *        decodificar um valor do tipo <tt>double</tt>.
   * @return O valor do tipo <tt>double</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o método).
   */
  public static double readDouble(double defaultValue) 
    {
    try
      {
      st = getToken();  // Lê os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      if (nt.toLowerCase().startsWith("nan"))
        return Double.NaN;
      else if (nt.toLowerCase().startsWith("inf"))
        return Double.POSITIVE_INFINITY;
      else if (nt.toLowerCase().startsWith("+inf"))
        return Double.POSITIVE_INFINITY;
      else if (nt.toLowerCase().startsWith("-inf"))
        return Double.NEGATIVE_INFINITY;
      else  // Devolve o valor processado pela classe Double.
      return Double.parseDouble(nt);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e saída lendo um "+
                           "double. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NumberFormatException nfe)  // Se houver erro de conversão,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro convertendo "+nt+" para "+
                           "um double. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se não houver tokens,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada não contém um double. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O método <tt>readChar</tt> lê e devolve um valor do tipo <tt>char</tt>.
   * Este método simplesmente chama o método <tt>readChar</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>' '</tt> (espaço em branco).
   * @return O valor do tipo <tt>char</tt> lido (ou, em caso de erro de
   *         leitura, um espaço em branco).
   */
  public static char readChar() 
    {
    return readChar(' ');
    }

  /**
   * O método <tt>readChar</tt> lê e devolve um valor do tipo <tt>char</tt>.
   * Este método tenta pegar o próximo <i>token</i> a ser lido da entrada
   * padrão e devolve o primeiro caractere deste <i>token</i>, ignorando os
   * outros caracteres. Se a leitura puder ser feita, um valor do tipo
   * <tt>char</tt> é devolvido, caso contrário o valor <i>default</i>
   * (passado como argumento) é devolvido.
   * @param defaultValue O valor <i>default</i> caso não seja possível ler e
   *        decodificar um valor do tipo <tt>char</tt>.
   * @return O valor do tipo <tt>char</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o método).
   */
  public static char readChar(char defaultValue) 
    {
    try
      {
      st = getToken();  // Lê os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      // Devolve o primeiro caractere da String lida.
      return nt.charAt(0);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e saída lendo um "+
                           "char. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se não houver tokens,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada não contém um char. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O método <tt>readString</tt> lê e devolve uma instância da classe <tt>String</tt>.
   * Este método simplesmente chama o método <tt>readString</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>""</tt> (String vazia).
   * @return O valor da <tt>String</tt> lida (ou, em caso de erro de
   *         leitura, uma String vazia).
   */
  public static String readString() 
    {
    return readString("");
    }

  /**
   * O método <tt>readString</tt> lê e devolve uma instância da classe <tt>String</tt>.
   * Este método devolve uma linha inteira lida da entrada
   * padrão, sem processá-la como <i>tokens</i>. Se a leitura 
   * puder ser feita, a <tt>String</tt> lida é devolvida, caso
   * contrário o valor <i>default</i> (passado como argumento) é devolvido.
   * O valor <i>default</i> também é devolvido se a String digitada for vazia.
   * @param defaultValue O valor <i>default</i> caso não seja possível ler 
   *        uma instância da classe <tt>String</tt>.
   * @return O valor da <tt>String</tt> lida (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o método).
   */
  public static String readString(String defaultValue) 
    {
    try
      {
      nt = br.readLine();  // Uma String diretamente do teclado.
      // Se nada foi digitado, então devolve o valor default.
      if (nt.trim().length() == 0)
        return defaultValue;
      else
        return nt;  // Devolve o que foi lido do teclado.
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impressão de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e saída lendo um "+
                           "String. Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O método <tt>setDebut</tt> modifica o atributo que indica que mensagens de
   * erro deverão ser mostradas na tela.
   * @param show Se for true mostra mensagens, caso contrário não as mostra.
   */
  public static void setDebug(boolean show) 
    {
    debug = show;
    if (debug)
        System.err.println("KEYBOARD:: Mostrando mensagens de erro e avisos.");
    }

/*

Segue a mensagem original da classe EasyIn de Peter van der Linden

Simple input from the keyboard for all primitive types. ver 1.0
Copyright (c) Peter van der Linden, May 5 1997.
corrected error message 11/21/97

The creator of this software hereby gives you permission to:

1. copy the work without changing it
2. modify the work providing you send me a copy which I can use in any way I want, including incorporating into this work.
3. distribute copies of the work to the public by sale, lease, rental, or lending
4. perform the work
5. display the work
6. fold the work into a funny hat and wear it on your head.

This is not thread safe, not high performance, and doesn't tell EOF.
It's intended for low-volume easy keyboard input.
An example of use is:

EasyIn easy = new EasyIn();
int i = easy. readInt();  // reads an int from System.in
float f = easy.readFloat();  // reads a float from System.in


*/

}
