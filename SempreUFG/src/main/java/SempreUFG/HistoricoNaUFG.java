package SempreUFG;

/**
 *
 * @author Hiago
 */
public class HistoricoNaUFG {

    private int idHistorico;
    private String idEgresso, cursoUFG;
    private int mesAnoInicio, mesAnoFim, numeroMatriculaCurso;
    private String tituloTrabalhoFinal;
    private Egresso egresso;

    public HistoricoNaUFG(int idHistorico, String cursoUFG,
            int mesAnoInicio, int mesAnoFim, int numeroMatriculaCurso, String tituloTrabalhoFinal) {

        this.idHistorico = idHistorico;
        this.idEgresso = egresso.getIdEgresso();
        this.cursoUFG = cursoUFG;
        this.mesAnoInicio = mesAnoInicio;
        this.mesAnoFim = mesAnoFim;
        this.numeroMatriculaCurso = numeroMatriculaCurso;
        this.tituloTrabalhoFinal = tituloTrabalhoFinal;
    }

    public boolean validaHistoricoNaUFG() {
        boolean testIdEgresso, testCursoUfg, testInicio, testFim,
                testNumeroMatricula, testTituloTrabalho;

        testIdEgresso = (this.idEgresso != null) && (this.idEgresso.length() <= 100) && testString(this.idEgresso);
        testCursoUfg = (this.cursoUFG != null) && (this.cursoUFG.length() <= 500) && testString(this.cursoUFG);
        testInicio = (this.mesAnoInicio > 0);
        testFim = (this.mesAnoFim > 0);
        testNumeroMatricula = (this.numeroMatriculaCurso > 0);
        testTituloTrabalho = (this.tituloTrabalhoFinal != null) && (this.tituloTrabalhoFinal.length() <= 500) && testString(this.tituloTrabalhoFinal);

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
    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
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
