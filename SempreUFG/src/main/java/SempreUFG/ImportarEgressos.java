package SempreUFG;

import PersistenciaSempreUFG.LerArquivo;
import PersistenciaSempreUFG.GravarRelatoImportacao;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class ImportarEgressos {

    public static void setRelatorioR1(String relatorioR1) {
        GravarRelatoImportacao.addRelato(relatorioR1);
        System.out.println(relatorioR1);
    }

    private static boolean temInconsistencia = false;

    public static boolean isTemInconsistencia() {
        return temInconsistencia;
    }

    public static void setTemInconsistencia(boolean temInconsistencia) {
        ImportarEgressos.temInconsistencia = temInconsistencia;
    }

    public static void main(String[] args) throws IOException {

        GravarRelatoImportacao.criarArquivo();
        LerArquivo.abrirArquivo();
        GravarRelatoImportacao.addRelato("Relatorio de Importacao: ");
        LerArquivo.lerDados();
        GravarRelatoImportacao.fecharArquivo();
        LerArquivo.fecharArquivo();
    }
}
