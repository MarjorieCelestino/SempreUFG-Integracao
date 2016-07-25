package SempreUFG;

public class CursoUFG {

    private String nome;

    private enum nivel {
        BACHARELADO, LICENCIATURA, APERFEICOAMENTO, ESPECIALIZACAO, MESTRADO, DOUTORADO;
    }

    public CursoUFG(String nome) {
        this.nome = nome;
    }

    public boolean validaCurso() {
        boolean testNome;

         testNome = (this.nome != null) && (this.nome.length() <= 50);
        if(!testNome){
           ImportarEgressos.setRelatorio("Erro: Nome do Curso vaizo ou fora do tamanho permitido.");
        }

        return testNome;
    }

    //---------- GET e SET ----------
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
