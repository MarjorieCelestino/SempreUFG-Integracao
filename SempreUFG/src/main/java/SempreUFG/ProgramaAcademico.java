package SempreUFG;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProgramaAcademico {

    private String idHistorico, tipo, data_incio, data_fim, descricao;

    public ProgramaAcademico(String idHistorico, String tipo, String data_incio, String data_fim, String descricao) {
        this.idHistorico = idHistorico;
        this.tipo = tipo;
        this.data_incio = data_incio;
        this.data_fim = data_fim;
        this.descricao = descricao;
    }

    public boolean validaProgramaAcademico() {
        boolean testDescricao, testInicio, testFim, testHistorico, testTipo;

        testDescricao = (this.descricao != null) && (this.descricao.length() <= 100);
        if (!testDescricao) {
            ImportarEgressos.setRelatorio("Erro: A descricao do Programa Academico está vazia ou maior que o tamanho permitido.");
        }
        testInicio = (this.data_incio != null) && (this.data_incio.length() == 10);
        if (!testInicio) {
            ImportarEgressos.setRelatorio("Erro: A data de inicio do Programa Academico está vazia ou no formato errado.");
        }
        testFim = (this.data_fim != null) && (this.data_fim.length() == 10);
        if (!testFim) {
            ImportarEgressos.setRelatorio("Erro: A data final do Programa Academico está vazia ou no formato errado.");
        }
        testHistorico = (this.idHistorico != null);
        if (!testHistorico) {
            ImportarEgressos.setRelatorio("Erro: O identificador do Histórico do aluno está vazio.");
        }
        testTipo = (this.tipo != null) && (this.data_fim.length() <= 50);
        if (!testTipo) {
            ImportarEgressos.setRelatorio("Erro: O tipo de Programa Academico nao foi definido ou esta fora do tamanho permitido.");
        }

        return (testDescricao && testInicio && testFim && testHistorico && testTipo);
    }

    private boolean testDataInicio() throws ParseException {
        int ano, mes, dia;
        boolean testeAnoInicio, testeMesInicio, testeDiaInicio;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicio = sdf.parse(this.data_incio);

        Calendar cal = Calendar.getInstance();
        cal.setTime(dataInicio);
        ano = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);

        testeAnoInicio = (ano < 9999) && (ano > 0);
        testeMesInicio = (mes > 0) && (mes < 13);
        testeDiaInicio = (dia > 0) && (dia < 32);

        return (testeAnoInicio && testeMesInicio && testeDiaInicio);
    }

    private boolean testDataFim() throws ParseException {
        int ano, mes, dia;
        boolean testeAnoFim, testeMesFim, testeDiaFim;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFim = sdf.parse(this.data_fim);

        Calendar cal = Calendar.getInstance();
        cal.setTime(dataFim);
        ano = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);

        testeAnoFim = (ano < 9999) && (ano > 0);
        testeMesFim = (mes > 0) && (mes < 13);
        testeDiaFim = (dia > 0) && (dia < 32);

        return (testeAnoFim && testeMesFim && testeDiaFim);
    }

    //---------- GETs ----------
    public String getData_incio() {
        return data_incio;
    }

    public String getData_fim() {
        return data_fim;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getIdHistorico() {
        return idHistorico;
    }

    public String getTipo() {
        return tipo;
    }

}
