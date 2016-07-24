package SempreUFG;

public class Reg2 {
    private String idEgresso, curso;
    private ProgramaAcademico progAcad;

    public Reg2(String idEgresso, String curso, ProgramaAcademico progAcad) {
        this.idEgresso = idEgresso;
        this.curso = curso;
        this.progAcad = progAcad;
    }

    public String getIdEgresso() {
        return idEgresso;
    }

    public String getCurso() {
        return curso;
    }

    public ProgramaAcademico getProgAcad() {
        return progAcad;
    }
    
}
