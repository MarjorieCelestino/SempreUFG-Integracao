package SempreUFG;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class ImportarEgressos {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.printf("Informe o nome de arquivo:\n");
        String nome = ler.nextLine();

        LerArquivo.abrirArquivo(nome);
        LerArquivo.lerDados();
        LerArquivo.fecharArquivo();
    }
}
