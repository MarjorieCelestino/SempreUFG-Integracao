package SempreUFG;

public class Reg1 {
    private Egresso egresso;
    private String curso;
    private HistoricoNaUFG historico;

    public Reg1(Egresso egresso, String curso, HistoricoNaUFG historico) {
        this.egresso = egresso;
        this.curso = curso;
        this.historico = historico;
    }

    public Egresso getEgresso() {
        return egresso;
    }

    public String getCurso() {
        return curso;
    }

    public HistoricoNaUFG getHistorico() {
        return historico;
    }
}
