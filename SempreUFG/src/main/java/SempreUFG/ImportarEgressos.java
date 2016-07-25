package SempreUFG;

import BancoDados.DataBaseDealer;
import PersistenciaSempreUFG.LerArquivo;
import PersistenciaSempreUFG.GravarRelatoImportacao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.*;
import sun.applet.Main;

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

    public static void main(String[] args) throws IOException, ParseException, SQLException {

        GravarRelatoImportacao.criarArquivo();
        GravarRelatoImportacao.addRelato("Relatorio de Importacao: ");
//        LerArquivo.lerDados();
        if (ImportarEgressos.temInconsistencia) {
            GravarRelatoImportacao.addRelato("Foram constatadas inconsistencias. Devido a estas inconsistencias, o Bando de Dados nao sera alterado.");
            System.exit(0);
        } else {
            GravarRelatoImportacao.addRelato("Nao foram encontradas inconsistencias. As alteracoes serao salvas no Banco de Dados.");
        }
        GravarRelatoImportacao.fecharArquivo();
        LerArquivo.fecharArquivo();

        DataBaseDealer db = DataBaseDealer.getInstance();
        try {
            db.conn.setAutoCommit(false);
            db.conn.setTransactionIsolation(TRANSACTION_READ_UNCOMMITTED);

            FileReader arq;
            BufferedReader lerArq = null;
            try {
                if (args[0] != null) {
                    if (args[0].startsWith(".\\")) {
                        args[0].replaceFirst(".", new File("").getAbsolutePath());
                    }
                    arq = new FileReader(args[0] + "\\Egressos-para-Importar.txt");
                } else {
                    arq = new FileReader("Egressos-para-Importar.txt");
                }
                lerArq = new BufferedReader(arq);
            } catch (FileNotFoundException e) {
                //Adicionar o ERRO de relat´rorio inexistente
                //------------------------------------------------
            }

            String linha = lerArq.readLine();
            if (linha != null && !linha.isEmpty()) {
                //Enquanto le no arquivoo
                while (linha != null) {
                    //Se for um reg1
                    if (linha.startsWith("Reg1")) {
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
                    } else if (linha.startsWith("Reg2")) {

                    }
                    linha = lerArq.readLine();
                }
                db.conn.commit();
            }
            else{
                //ADICIONAR ERRO DE Q O ARQUIVO ESTA VAZIO;
                //----------------------------------------
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                db.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
