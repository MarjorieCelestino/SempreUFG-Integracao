package SempreUFG;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class ImportarEgressos {

    public static void main(String[] args) throws IOException {

//        LerArquivo.abrirArquivo();
//        LerArquivo.lerDados();
//        LerArquivo.fecharArquivo();
        
        GravarRelatoImportacao.criarArquivo();
        GravarRelatoImportacao.addLembrete("Teste 01");
        GravarRelatoImportacao.addLembrete("Teste 02");
        GravarRelatoImportacao.addLembrete("Teste 03");
        GravarRelatoImportacao.addLembrete("Teste 04");
        GravarRelatoImportacao.fecharArquivo();

    }
}
