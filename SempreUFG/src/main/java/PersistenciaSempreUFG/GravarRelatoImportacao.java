package PersistenciaSempreUFG;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Hiago
 */
public class GravarRelatoImportacao {

    private static BufferedWriter out;
    private static boolean firstComment = true;

    public static void criarArquivo() throws IOException {
        try {

            out = new BufferedWriter(new FileWriter("C:\\Users\\Hiago\\GitHub\\SempreUFG-Integracao\\SempreUFG\\Relato de Importacao.txt"));
//            out = new BufferedWriter(new FileWriter("C:\\Users\\Juliano\\IntegrAplic\\Relato de Importacao.txt"));
            System.out.println("Arquivo foi gerado");
        } catch (FileNotFoundException w) {
            System.err.println("Erro ao criar o Relato de Importacao.txt!");
        }
    }

    public static void addRelato(String relato) {

        if (firstComment) {
            try {
                out.write(relato);
                firstComment = false;
            } catch (IOException e) {
            }
        } else {
            try {
                out.newLine();
                out.write(relato);
            } catch (IOException e) {
            }
        }
    }

    public static void fecharArquivo() throws IOException {
        if (out != null) {
            out.close();
            System.out.println("arquivo foi fechado");
        }
    }
}
