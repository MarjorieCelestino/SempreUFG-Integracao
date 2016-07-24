package SempreUFG;

import PersistenciaSempreUFG.GravarRelatoImportacao;
import java.util.HashMap;
import java.util.Map;

public class Egresso {

    private String idEgresso, nome, tipoDocumento, numeroDocumento, dataNascimento;
    private static Map<Integer, Egresso> mapa = new HashMap<Integer, Egresso>();
    private static int numEgresso = 0;
    private int numEgressoObject;

    public String getDataNascimento() {
        return dataNascimento;
    }

    public static int getNumEgresso() {
        return numEgresso;
    }

    private void setNumEgresso() {
        Egresso.numEgresso = Egresso.numEgresso + 1;
        this.numEgressoObject = Egresso.numEgresso;
    }

    public int getNumEgressoObject() {
        return numEgressoObject;
    }

    public int getNumLista() {
        return Egresso.mapa.size();
    }

    public Egresso(String nome, String tipoDocumento, String numeroDocumento, String dataNascimento) {
        this.nome = nome;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.dataNascimento = dataNascimento;
        this.idEgresso = this.tipoDocumento + this.numeroDocumento;
        setNumEgresso();     //Atribuindo o identificador unico
        mapa.put(this.numEgressoObject, this);    //Adicionando o objeto dentro da mapa;
    }

    public boolean validaEgresso() {
        boolean testNome, testTipoDoc, testNumDoc, testNumNascimento;

        testNome = (this.nome != null) && (this.nome.length() <= 100);

        if (!testNome) {
            ImportarEgressos.setTemInconsistencia(true);
            if (this.nome == null) {
                GravarRelatoImportacao.addRelato("Erro: o nome do Egresso no registro Req.1 esta nulo.");
            } else if (this.nome.length() > 100) {
                GravarRelatoImportacao.addRelato("Erro: o nome do Egresso no registro Req.1 esta com mais de 100 caracteres.");
            }
        }

        testTipoDoc = (this.tipoDocumento != null) && (this.tipoDocumento.length() <= 50);

        if (!testTipoDoc) {
            ImportarEgressos.setTemInconsistencia(true);
            if (this.tipoDocumento == null) {
                GravarRelatoImportacao.addRelato("Erro: o tipo do documento do Egresso no registro Req.1 esta nulo.");
            } else if (this.nome.length() > 50) {
                GravarRelatoImportacao.addRelato("Erro: o tipo do documento do Egresso no registro Req.1 esta com mais de 50 caracteres.");
            }
        }

        testNumDoc = (this.numeroDocumento != null) && (this.numeroDocumento.length() <= 50) && testNumeroDoc();
        
        if (!testNumDoc) {
            ImportarEgressos.setTemInconsistencia(true);
            if (this.numeroDocumento == null) {
                GravarRelatoImportacao.addRelato("Erro: o numero do documento do Egresso no registro Req.1 esta nulo.");
            } else if (this.nome.length() > 50) {
                GravarRelatoImportacao.addRelato("Erro: o numero do documento do Egresso no registro Req.1 esta com mais de 50 caracteres.");
            }
        }
        
        testNumNascimento = (this.dataNascimento != null) && (this.dataNascimento.length() <= 50) && testNumeroNascimento();

        return (testNome && testTipoDoc && testNumDoc && testNumNascimento);
    }

    private boolean testNumeroDoc() {
        char[] numDoc = this.numeroDocumento.toCharArray();
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

    private boolean testNumeroNascimento() {
        char[] numDoc = this.dataNascimento.toCharArray();
        boolean resultado = true;
        String aux;
        for (int i = 0; i < numDoc.length; i++) // verifica se o char não é um dígito
        {
            aux = String.valueOf(numDoc[i]);
            if ((!Character.isDigit(numDoc[i])) && (!aux.equals("-"))) {
                resultado = false;
                break;
            }
        }
        return resultado;
    }

    //---------- GETs e SEts ----------
    public String getIdEgresso() {
        return idEgresso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}
