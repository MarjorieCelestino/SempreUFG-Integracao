package SempreUFG;

import BancoDados.DataBaseDealer;
import PersistenciaSempreUFG.LerArquivo;
import PersistenciaSempreUFG.GravarRelatoImportacao;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static void main(String[] args) throws IOException, ParseException {

        GravarRelatoImportacao.criarArquivo();
        GravarRelatoImportacao.addRelato("Relatorio de Importacao: ");
        LerArquivo.lerDados();
        GravarRelatoImportacao.fecharArquivo();
        LerArquivo.fecharArquivo();

        if (ImportarEgressos.temInconsistencia) {
            GravarRelatoImportacao.addRelato("Foram constatadas inconsistencias. Devido a estas inconsistencias, o Bando de Dados nao sera alterado.");
            System.exit(0);
        } else {
            GravarRelatoImportacao.addRelato("Nao foram encontradas inconsistencias. As alteracoes serao salvas no Banco de Dados.");
        }

        //A partir daqui sera realizada a persistencia dos dados no BD
        DataBaseDealer db = DataBaseDealer.getInstance();
        try {
            db.conn.setAutoCommit(false);

            //Enquanto le no arquivoo
//            while (true) {
            //Se for um reg1
            if (true) {
                //Cria o Egresso
                Egresso egr;
                //Existe um egresso por arquivo
                //O egresso sempre estara na posicao 0 do HashMap
                //metodo ".getInstancia(n)" retorna um egresso, no caso o unico egresso a ser retornado 
                egr = Egresso.getInstancia(0);
                //Testa se o Egresso Ã© valido
                if (egr != null) {
                    PreparedStatement statmentEgresso = db.statmentEgresso(egr);
                    statmentEgresso.executeUpdate();
                } else {
                    //Cancela a transaÃ§Ã£o
                    db.conn.rollback();
                    //Adiciona no relatÃ³rio de erro

                    //Encerra o ciclo
//                        break;
                }

                //Cria o HistÃ³rico
                HistoricoNaUFG his;
                his = HistoricoNaUFG.getInstancia(0);
                //Testa se o histÃ³rico Ã© valido
                if (his != null) {
                    PreparedStatement statmentHistorico = db.statmentHistorico(his);
                    statmentHistorico.executeUpdate();
                } else {
                    //Cancela a transaÃ§Ã£o
                    db.conn.rollback();
                    //Adiciona no relatÃ³rio de erro

                    //Encerra o ciclo
//                        break;
                }
                //Se for um Reg 2
            } else if (true) {

            }

            db.conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ImportarEgressos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                db.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImportarEgressos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
