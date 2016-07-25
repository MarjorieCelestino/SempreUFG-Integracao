package SempreUFG;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hiago
 */
public class HistoricoNaUFG {

    private String idHistorico, idEgresso, cursoUFG;
    private int mesAnoInicio, mesAnoFim, numeroMatriculaCurso;
    private String tituloTrabalhoFinal;
    private Egresso egresso;
    private static Map<Integer, HistoricoNaUFG> mapa = new HashMap<Integer, HistoricoNaUFG>();
    private int numHistoricoObject = 0;
    private static int numEgresso;

    public HistoricoNaUFG(String idHistorico, String cursoUFG,
            int mesAnoInicio, int mesAnoFim, int numeroMatriculaCurso, String tituloTrabalhoFinal) {

        this.idHistorico = idHistorico;
        egresso = Egresso.getInstancia(0);
        this.idEgresso = egresso.getIdEgresso();
        this.cursoUFG = cursoUFG;
        this.mesAnoInicio = mesAnoInicio;
        this.mesAnoFim = mesAnoFim;
        this.numeroMatriculaCurso = numeroMatriculaCurso;
        this.tituloTrabalhoFinal = tituloTrabalhoFinal;

        setNumHistorico();

        mapa.put(this.numHistoricoObject, this);    //Adicionando o objeto dentro da mapa;        
    }

    private void setNumHistorico() {
        this.numHistoricoObject = HistoricoNaUFG.numEgresso;
        HistoricoNaUFG.numEgresso = HistoricoNaUFG.numEgresso + 1;
    }

    public static HistoricoNaUFG getInstancia(int num) {
        if (HistoricoNaUFG.mapa.get(num) != null) {
            return HistoricoNaUFG.mapa.get(num);
        } else {
            return null;
        }
    }

    public boolean validaHistoricoNaUFG() {
        boolean testIdEgresso, testCursoUfg, testInicio, testFim,
                testNumeroMatricula, testTituloTrabalho;

        testIdEgresso = (this.idEgresso != null) && (this.idEgresso.length() <= 100) && testString(this.idEgresso);

        if (!testIdEgresso) {
            ImportarEgressos.setTemInconsistencia(true);
            if (this.idEgresso == null) {
                ImportarEgressos.setRelatorio("Erro: o id do Egresso do Historico na UFG no registro Req.1 esta nulo.");
            }
            if (this.idEgresso.length() > 100) {
                ImportarEgressos.setRelatorio("Erro: o id do Egresso do Historico na UFG no registro Req.1 esta com mais de 100 caracteres.");
            }
            if (!testString(this.idEgresso)) {
                ImportarEgressos.setRelatorio("Erro: o id do Egresso do Historico na UFG no registro Req.1 possui campos que nao sao validos.");
            }
        }

        testCursoUfg = (this.cursoUFG != null) && (this.cursoUFG.length() <= 500) && testString(this.cursoUFG);

        if (!testCursoUfg) {
            ImportarEgressos.setTemInconsistencia(true);
            if (this.cursoUFG == null) {
                ImportarEgressos.setRelatorio("Erro: o curso do Egresso do Historico na UFG no registro Req.1 esta nulo.");
            }
            if (this.cursoUFG.length() > 500) {
                ImportarEgressos.setRelatorio("Erro: o curso do Egresso do Historico na UFG no registro Req.1 esta com mais de 100 caracteres.");
            }
            if (!testString(this.cursoUFG)) {
                ImportarEgressos.setRelatorio("Erro: o curso do Egresso do Historico na UFG no registro Req.1 possui campos que nao sao numerais.");
            }
        }

        testInicio = (this.mesAnoInicio > 0);

        if (!testInicio) {
            ImportarEgressos.setTemInconsistencia(true);
            ImportarEgressos.setRelatorio("Erro: a data de inicio do Egresso no Historico na UFG esta invalida.");
        }

        testFim = (this.mesAnoFim > 0);

        if (!testFim) {
            ImportarEgressos.setTemInconsistencia(true);
            ImportarEgressos.setRelatorio("Erro: a data de fim do Egresso no Historico na UFG esta invalida.");
        }

        testNumeroMatricula = (this.numeroMatriculaCurso > 0);

        if (!testNumeroMatricula) {
            ImportarEgressos.setTemInconsistencia(true);
            ImportarEgressos.setRelatorio("Erro: a matricula do Egresso no Historico na UFG possui um valor negativo.");
        }

        testTituloTrabalho = (this.tituloTrabalhoFinal != null) && (this.tituloTrabalhoFinal.length() <= 500) && testString(this.tituloTrabalhoFinal);

        if (!testTituloTrabalho) {
            ImportarEgressos.setTemInconsistencia(true);
            if (this.tituloTrabalhoFinal == null) {
                ImportarEgressos.setRelatorio("Erro: o titulo do trabalho final do Egresso do Historico na UFG no registro Req.1 esta nulo.");
            }
            if (this.tituloTrabalhoFinal.length() > 500) {
                ImportarEgressos.setRelatorio("Erro: o titulo do trabalho final do Egresso do Historico na UFG no registro Req.1 esta com mais de 500 caracteres.");
            }
            if (!testString(this.tituloTrabalhoFinal)) {
                ImportarEgressos.setRelatorio("Erro: o titulo do trabalho final do Egresso do Historico na UFG no registro Req.1 possui campos que nao sao numerais.");
            }
        }

        return (testIdEgresso && testCursoUfg && testInicio && testFim && testNumeroMatricula && testTituloTrabalho);
    }

    private boolean testString(String doc) {
        char[] numDoc = doc.toCharArray();
        boolean resultado = true;
        for (int i = 0; i < numDoc.length; i++) // verifica se o char não é um dígito
        {
            if (!Character.isDigit(numDoc[i])) {
                resultado = false;
                break;
            }
        }
        return resultado;
    }

    //---------- GETs e SEts ----------
    public String getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(String idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getIdEgresso() {
        return idEgresso;
    }

    public void setIdEgresso(String idEgresso) {
        this.idEgresso = idEgresso;
    }

    public String getCursoUFG() {
        return cursoUFG;
    }

    public void setCursoUFG(String cursoUFG) {
        this.cursoUFG = cursoUFG;
    }

    public int getMesAnoInicio() {
        return mesAnoInicio;
    }

    public void setMesAnoInicio(int mesAnoInicio) {
        this.mesAnoInicio = mesAnoInicio;
    }

    public int getMesAnoFim() {
        return mesAnoFim;
    }

    public void setMesAnoFim(int mesAnoFim) {
        this.mesAnoFim = mesAnoFim;
    }

    public int getNumeroMatriculaCurso() {
        return numeroMatriculaCurso;
    }

    public void setNumeroMatriculaCurso(int numeroMatriculaCurso) {
        this.numeroMatriculaCurso = numeroMatriculaCurso;
    }

    public String getTituloTrabalhoFinal() {
        return tituloTrabalhoFinal;
    }

    public void setTituloTrabalhoFinal(String tituloTrabalhoFinal) {
        this.tituloTrabalhoFinal = tituloTrabalhoFinal;
    }
}
