/*
 * Main.java
 *
 * Created on 20 de Outubro de 2007, 20:50
 * Sistemas Nebulosos - 2007
 */

package cmeans;
import java.util.*;
import java.io.*;

/**
 * Esta aplica��o implementa o m�todo C-Means de agrupamento fuzzy
 * As referencias utilizadas na implementa��o sao:
 *  - Slides da disciplina
 *  - http://home.dei.polimi.it/matteucc/Clustering/tutorial_html/cmeans.html
 * Est� � a Classe principal, a partir da qual o programa � iniciado
 * e os argumentos de linha de comando s�o passados.
 * @author Daniel Saraiva Leite
 */
public class Main 
{
    
    /** 
     * Construtor da classe principal 
     */
    public Main(LineNumberReader leitorBase, int numeroClusters) 
    {
        try
        {
            // constroi o objeto que encapsula a base
            BaseDados bd = new BaseDados(leitorBase); 
            // imprime a base
            //System.out.println("Base: ");
            //System.out.print(bd);          
            // aplica o metodo C-Means
            AgrupamentoCMeans cmeans = new AgrupamentoCMeans(bd, numeroClusters);
            cmeans.aplicarAlgoritmo();
            // imprime o resultado   
            System.out.print(cmeans);
        }
        catch(Exception exc)
        {
            System.out.println("Erro na execu��o do m�todo C-Means");
            System.out.println(exc.getLocalizedMessage());
            exc.printStackTrace();
            System.exit(1);
        }
    }
    
    
    /**
     * Fun��o de entrada da aplica��o
     * @param args os argumentos
     *   1o. - caminho para o arquivo texto da base de dados
     *   2o. - numero de clusters a utilizar
     */
    public static void main(String[] args) 
    {
        if(args.length != 2)
        {
            imprimeUso();
        }
        // Tenta carregar o arquivo e interpretar o numero de clusters
        try
        {
            // constroi o leitor do arquivo da base
            LineNumberReader leitor = new LineNumberReader(new FileReader(args[0]));
            // le o numero de clusters
            int nClusters = Integer.parseInt(args[1]);            
            Main main = new Main(leitor, nClusters);
        }
        catch(IOException exc)
        {
            System.out.printf("N�o foi possivel abrir o arquivo %s", args[0]);
            System.out.println();
            imprimeUso();
        }
        catch(NumberFormatException exc)
        {
            System.out.printf("%s � um n�mero inv�lidos de clusters.", args[1]);
            System.out.println();
            imprimeUso();
        }
        catch(Exception exc)
        {
            System.out.println(exc.getLocalizedMessage());
            System.out.println();
            imprimeUso();
        }
    }
    
    
    /*
     * Imprime a forma de uso do programa caso o usu�rio
     * entre com par�metros inv�lidos ou arquivo inexistente
     */
    public static void imprimeUso()
    {
        System.out.println("Parametros invalidos ou arquivo inexistente.\n");  
        System.out.println("Uso: CMeans [caminho] [k]\n");
        
        System.out.println("[caminho] localiza��o para o arquivo texto que " 
           + "cont�m a base de dados" +
           ", com os atributos separados com espa�os e uma inst�ncia por linha"); 
        System.out.println("[k] n�mero de clusters a utilizar"); 
        System.exit(1);
    }
    
}
