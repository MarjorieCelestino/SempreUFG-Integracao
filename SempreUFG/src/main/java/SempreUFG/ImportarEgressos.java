package SempreUFG;

import PersistenciaSempreUFG.LerArquivo;
import PersistenciaSempreUFG.GravarRelatoImportacao;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class ImportarEgressos {

    public static void setRelatorio(String relatorio) {
        GravarRelatoImportacao.addRelato(relatorio);
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
        GravarRelatoImportacao.addRelato("Relatorio de Importacao: ");
        LerArquivo.lerDados();
        if (ImportarEgressos.temInconsistencia) {
            GravarRelatoImportacao.addRelato("Foram constatadas inconsistencias. Devido a estas inconsistencias, o Bando de Dados nao sera alterado.");
        } else {
            GravarRelatoImportacao.addRelato("Nao foram encontradas inconsistencias. As alteracoes serao salvas no Banco de Dados.");
        }
        GravarRelatoImportacao.fecharArquivo();
        LerArquivo.fecharArquivo();
    }
}
