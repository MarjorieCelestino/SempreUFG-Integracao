package SempreUFG;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Hiago
 */
public class GravarRelatoImportacao {

    private Formatter gravador;

    public void criarArquivo(int num) {
        try {

            gravador = new Formatter("C:\\Users\\Hiago\\GitHub\\SempreUFG-Integracao\\SempreUFG\\Egressos-para-Importar.txt");
//            gravador = new Formatter("C:\\Users\\Juliano\\IntegrAplic\\Egressos-para-Importar.txt");

        } catch (FileNotFoundException w) {
            System.err.println("Erro ao criar o Relato de Importacao.txt!");
        }
    }

    public void addLembrete(String descricao) {
        try {
            gravador.format(descricao);
        } catch (NoSuchElementException e) {
            System.out.println("Erro ao gerar o arquivo .txt");
        }
    }

    public void fecharArquivo() {
        if (gravador != null) {
            gravador.close();
        }
    }
}
