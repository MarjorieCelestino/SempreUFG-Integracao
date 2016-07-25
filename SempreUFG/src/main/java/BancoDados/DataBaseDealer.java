/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BancoDados;

/**
 *
 * @author Hiago
 */
import SempreUFG.Egresso;
import SempreUFG.HistoricoNaUFG;
import java.sql.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseDealer {

    private static DataBaseDealer INSTANCE;
    public Connection conn;

    public static DataBaseDealer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataBaseDealer();
        }
        return INSTANCE;
    }

    private DataBaseDealer() {
        this.conn = connectToDatabaseOrDie();
    }

    private Connection connectToDatabaseOrDie() {
        String url = "jdbc:postgresql://localhost/SempreUFG", username = "postgres", password = "33697741";
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return conn;
    }

    public PreparedStatement statmentEgresso(Egresso egr) throws ParseException {

        String sqlEgresso = "INSERT INTO Egresso (ID_EGRESSO, NOME, TIPO_DOCUMENTO, NUMERO_DOCUMENTO, DATA_NASCIMENTO) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement inserirEgresso = null;
        try {
            inserirEgresso = conn.prepareStatement(sqlEgresso);
            inserirEgresso.setString(1, egr.getIdEgresso());
            inserirEgresso.setString(2, egr.getNome());
            inserirEgresso.setString(3, egr.getTipoDocumento());
            inserirEgresso.setString(4, egr.getNumeroDocumento());
            inserirEgresso.setDate(5, egr.getDataNascimento());

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseDealer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inserirEgresso;
    }

    public PreparedStatement statmentHistorico(HistoricoNaUFG his) {

        String sqlInserirHistorico = "INSERT INTO HISTORICO_NA_UFG (ID_HISTORICO, ID_EGRESSO, CURSO_UFG, MES_ANO_DE_INICIO, MES_ANO_DE_FIM, NUMERO_MATRICULA_CURSO, TITULO_DO_TRABALHO_FINAL) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement inserirHistorico = null;
        try {

            inserirHistorico = conn.prepareStatement(sqlInserirHistorico);
            inserirHistorico.setString(1, his.getIdHistorico());
            inserirHistorico.setString(2, his.getIdEgresso());
            inserirHistorico.setString(3, his.getCursoUFG());
            inserirHistorico.setInt(4, his.getMesAnoInicio());
            inserirHistorico.setInt(5, his.getMesAnoFim());
            inserirHistorico.setInt(6, his.getNumeroMatriculaCurso());
            inserirHistorico.setString(7, his.getTituloTrabalhoFinal());

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseDealer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inserirHistorico;
    }

}
