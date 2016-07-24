package SempreUFG;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProgramaAcademico {

    private String idHistorico, tipo, data_incio, data_fim, descricao;
    private List<String> idHistoricoLista = new ArrayList<String>();
    private List<String> tipoLista = new ArrayList<String>();
    private List<String> dataInicioLista = new ArrayList<String>();
    private List<String> dataFimLista = new ArrayList<String>();
    private List<String> descricaoLista = new ArrayList<String>();

    private enum tipoDePrograma {
        INICIACAO_CIENTIFICA, MONITORIA, EXTENSAO, INTERCAMBIO;
    }

    public ProgramaAcademico(String idHistorico, String tipo, String data_incio, String data_fim, String descricao) {
        this.idHistorico = idHistorico;
        this.idHistoricoLista.add(idHistorico);
        this.tipo = tipo;
        this.tipoLista.add(tipo);
        this.data_incio = data_incio;
        this.dataInicioLista.add(data_incio);
        this.data_fim = data_fim;
        this.dataFimLista.add(data_fim);
        this.descricao = descricao;
        this.descricaoLista.add(descricao);
    }

    public boolean validaProgramaAcademico() {
        boolean testId = true, testTipo = true, testInicio = true, testFim = true, testDescricao = true;

        for (int i = 0; i < idHistoricoLista.size(); i++) {
            if (this.idHistoricoLista.get(i) == null) {
                ImportarEgressos.setRelatorio("Erro: o id do historico sobre o numero: " + (i + 1) + ", do Programa Academico no registro Req.2 esta nulo.");
                ImportarEgressos.setTemInconsistencia(true);
                testId = false;
            }
            if (this.idHistoricoLista.get(i).length() > 600) {
                ImportarEgressos.setRelatorio("Erro: o id do historico sobre o numero: " + (i + 1) + ", do Programa Academico no registro Req.2 esta com mais de 600 caracteres.");
                ImportarEgressos.setTemInconsistencia(true);
                testId = false;
            }
        }

        for (int i = 0; i < tipoLista.size(); i++) {
            if ((!"INICIACAO_CIENTIFICA".equals(this.tipoLista.get(i))) || (!"MONITORIA".equals(this.tipoLista.get(i)))
                    || (!"EXTENSAO".equals(this.tipoLista.get(i))) || (!"INTERCAMBIO".equals(this.tipoLista.get(i)))) {
                ImportarEgressos.setRelatorio("Erro: o tipo de programa sobre o numero: " + (i + 1) + ", do Programa Academico no registro Req.2 se difere do esperado.");
                ImportarEgressos.setTemInconsistencia(true);
                testTipo = false;
            }
            if (this.tipoLista.get(i) == null) {
                ImportarEgressos.setRelatorio("Erro: o tipo de programa sobre o numero: " + (i + 1) + ", do Programa Academico no registro Req.2 se encontra nulo.");
                ImportarEgressos.setTemInconsistencia(true);
                testTipo = false;
            }
        }

        for (int i = 0; i < dataInicioLista.size(); i++) {
            if (this.dataInicioLista.get(i) == null) {
                ImportarEgressos.setRelatorio("Erro: a data de inicio do historico sobre o numero: " + (i + 1) + ", do Programa Academico no registro Req.2 esta nulo.");
                ImportarEgressos.setTemInconsistencia(true);
                testInicio = false;
            }
            if (this.dataInicioLista.get(i).length() != 10) {
                ImportarEgressos.setRelatorio("Erro: a data de inicio do historico sobre o numero: " + (i + 1) + ", do Programa Academico no registro Req.2 nao possui 10 caracteres.");
                ImportarEgressos.setTemInconsistencia(true);
                testInicio = false;
            }

            if (this.dataFimLista.get(i) == null) {
                ImportarEgressos.setRelatorio("Erro: a data de fim do historico sobre o numero: " + (i + 1) + ", do Programa Academico no registro Req.2 esta nulo.");
                ImportarEgressos.setTemInconsistencia(true);
                testFim = false;
            }
            if (this.dataFimLista.get(i).length() != 10) {
                ImportarEgressos.setRelatorio("Erro: a data de fim do historico sobre o numero: " + (i + 1) + ", do Programa Academico no registro Req.2 nao possui 10 caracteres.");
                ImportarEgressos.setTemInconsistencia(true);
                testFim = false;
            }
        }

        for (int i = 0; i < descricaoLista.size(); i++) {
            if (this.descricaoLista.get(i) == null) {
                ImportarEgressos.setRelatorio("Erro: a descricao do historico sobre o numero: " + (i + 1) + ", do Programa Academico no registro Req.2 esta nulo.");
                ImportarEgressos.setTemInconsistencia(true);
                testDescricao = false;
            }
            if (this.descricaoLista.get(i).length() > 100) {
                ImportarEgressos.setRelatorio("Erro: a descricao do historico sobre o numero: " + (i + 1) + ", do Programa Academico no registro Req.2 esta com mais de 100 caracteres.");
                ImportarEgressos.setTemInconsistencia(true);
                testDescricao = false;
            }
        }

        return (testId && testTipo && testInicio && testFim && testDescricao);
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

    //---------- GETs e SETs ----------
    public String getData_incio() {
        return data_incio;
    }

    public void setData_incio(String data_incio) {
        this.data_incio = data_incio;
    }

    public String getData_fim() {
        return data_fim;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdHistorico() {
        return idHistorico;
    }

    public String getTipo() {
        return tipo;
    }
}
