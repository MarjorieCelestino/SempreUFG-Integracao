package SempreUFG;

import PersistenciaSempreUFG.LerArquivo;
import PersistenciaSempreUFG.GravarRelatoImportacao;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class ImportarEgressos {

    private static boolean temInconsistencia = false;

    public static boolean isTemInconsistencia() {
        return temInconsistencia;
    }

    public static void setTemInconsistencia(boolean temInconsistencia) {
        ImportarEgressos.temInconsistencia = temInconsistencia;
    }

    public static void main(String[] args) throws IOException {

        LerArquivo.abrirArquivo();
        LerArquivo.lerDados();
        LerArquivo.fecharArquivo();

        GravarRelatoImportacao.criarArquivo();
        GravarRelatoImportacao.addRelato("Teste 01");
        GravarRelatoImportacao.addRelato("Teste 02");
        GravarRelatoImportacao.addRelato("Teste 03");
        GravarRelatoImportacao.addRelato("Teste 04");
        GravarRelatoImportacao.fecharArquivo();

    }
}
