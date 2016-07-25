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
                ImportarEgressos.setRelatorio("Erro: O registro não foi encontrado.");
                ImportarEgressos.setTemInconsistencia(true);
            }

            String linha = lerArq.readLine();
            if (linha != null && !linha.isEmpty()) {
                //Enquanto le no arquivoo
                while (linha != null) {
                    //Se for um reg1
                    if (linha.startsWith("Reg1")) {
                        //Cria o Reg1
                        Reg1 reg1 = LerArquivo.lerReg1(linha);

                        //Testa se o Egresso Ã© valido
                        if (reg1.getEgresso() != null) {
                            PreparedStatement statmentEgresso = db.statmentEgresso(reg1.getEgresso());
                            statmentEgresso.executeUpdate();
                        } else {
                            db.conn.rollback();
                            ImportarEgressos.setRelatorio("Erro: O Egresso (nome = +" + reg1.getEgresso().getNome()
                                    + ", tipo de documento = " + reg1.getEgresso().getTipoDocumento()
                                    + ", numero de documento = " + reg1.getEgresso().getNumeroDocumento()
                                    + ", data de nascimento = " + reg1.getEgresso().getDataNascimento().getDate()
                                    + ") já existe no Banco de Dados ou já foi adicionado em uma linha anterior.");
                            ImportarEgressos.setTemInconsistencia(true);
                            break;
                        }
                        
                        //Histórico
                        
                        //Testa se o histÃ³rico Ã© valido
                        if (reg1.getHistorico() != null) {
                            PreparedStatement statmentHistorico = db.statmentHistorico(reg1.getHistorico());
                            statmentHistorico.executeUpdate();
                        } else {
                            db.conn.rollback();
                            ImportarEgressos.setRelatorio("Erro: O Histórico na UFG (egresso = +" + reg1.getHistorico().getIdEgresso()
                                    + ", curso = " + reg1.getHistorico().getCursoUFG()
                                    + ", mes e ano de inicio = " + reg1.getHistorico().getMesAnoInicio()
                                    + ", mes e ano de fim = " + reg1.getHistorico().getMesAnoFim()
                                    + ", matricula = " + reg1.getHistorico().getNumeroMatriculaCurso()
                                    + ", titulo do trabalho final = " + reg1.getHistorico().getMesAnoFim()
                                    + ") já existe no Banco de Dados ou já foi adicionado em uma linha anterior.");
                            ImportarEgressos.setTemInconsistencia(true);
                            break;
                        }
                        //Se for um Reg 2
                    } else if (linha.startsWith("Reg2")) {

                    }
                    linha = lerArq.readLine();
                }
                db.conn.commit();
            } else {
                ImportarEgressos.setRelatorio("Erro: o registro começa com uma linha em branco ou está vazio.");
                ImportarEgressos.setTemInconsistencia(true);
            }
        } catch (SQLException ex) {
            
        } finally {
            db.conn.close();
        }

    }
}
