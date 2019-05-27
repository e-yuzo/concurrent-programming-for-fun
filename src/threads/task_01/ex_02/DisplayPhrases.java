package threads.task_01.ex_02;

/**
 *
 * @author yuzo
 * Description: faça uma thread Java que realize a leitura de um arquivo texto com
 * frases e exiba as frases a cada 10 segundos.
 */
public class DisplayPhrases {
    
    public static void main(String[] args) {
        new Thread(new ThreadReader()).start();
    }
}
