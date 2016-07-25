package SempreUFG;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Egresso {

    private String idEgresso, nome, tipoDocumento, numeroDocumento;
    private List<String> tipoDocLista = new ArrayList<String>();
    private List<String> numDocLista = new ArrayList<String>();
    private static Map<Integer, Egresso> mapa = new HashMap<Integer, Egresso>();
    private static int numEgresso = 0, numEgressoObject, listaTotal = 0;
    private int listaObjt;
    private String dataNascimento;

    public static Egresso getInstancia(int num) {
        if (Egresso.mapa.get(num) != null) {
            return Egresso.mapa.get(num);
        } else {
            return null;
        }
    }

    public void addTipoDocLista(String tipoDoc) {
        this.tipoDocLista.add(tipoDoc);
    }

    public void addNumDocLista(String numeroDoc) {
        this.numDocLista.add(numeroDoc);
    }

    public int getTipoDocListaSize() {
        return tipoDocLista.size();
    }

    public int getNumDocListaSize() {
        return numDocLista.size();
    }

    public Egresso(String nome, String tipoDocumento, String numeroDocumento, String dataNascimento) {
        this.nome = nome;
        this.tipoDocLista.add(tipoDocumento);
        this.tipoDocumento = tipoDocumento;
        this.numDocLista.add(numeroDocumento);
        this.numeroDocumento = numeroDocumento;
        this.dataNascimento = dataNascimento;

        listaObjt = listaTotal;
        setNumEgresso();     //Atribuindo o identificador unico

        this.idEgresso = this.tipoDocumento + this.numeroDocumento;
        mapa.put(Egresso.numEgressoObject, this);    //Adicionando o objeto dentro da mapa;
        listaTotal++;
    }

    public boolean validaListas() {
        boolean testTipoDocLista = true, testNumDocLista = true;

        for (int i = 0; i < tipoDocLista.size(); i++) {
            if (this.tipoDocLista.get(i) == null) {
                ImportarEgressos.setRelatorio("Erro: o tipo de documento sobre o numero: " + (i + 1) + ", do Egresso no registro Req.2 esta nulo.");
                ImportarEgressos.setTemInconsistencia(true);
                testTipoDocLista = false;
            }
            if (this.tipoDocLista.get(i).length() > 50) {
                ImportarEgressos.setRelatorio("Erro: o tipo de documento sobre o numero: " + (i + 1) + ", do Egresso no registro Req.2 esta com mais de 50 caracteres.");
                ImportarEgressos.setTemInconsistencia(true);
                testTipoDocLista = false;
            }
        }

        for (int i = 0; i < numDocLista.size(); i++) {
            if (this.numDocLista.get(i) == null) {
                ImportarEgressos.setRelatorio("Erro: o numero do documento sobre o numero: " + (i + 1) + ", do Egresso no registro Req.2 esta nulo.");
                ImportarEgressos.setTemInconsistencia(true);
                testNumDocLista = false;
            }
            if (this.numDocLista.get(i).length() > 50) {
                ImportarEgressos.setRelatorio("Erro: o numero do documento sobre o numero: " + (i + 1) + ", do Egresso no registro Req.2 esta com mais de 50 caracteres.");
                ImportarEgressos.setTemInconsistencia(true);
                testNumDocLista = false;
            }
        }

        return testTipoDocLista && testNumDocLista;
    }

    public boolean validaEgresso() {
        boolean testNome, testTipoDoc, testNumDoc, testNumNascimento;

        testNome = (this.nome != null) && (this.nome.length() <= 100);

        if (!testNome) {
            ImportarEgressos.setTemInconsistencia(true);
            if (this.nome == null) {
                ImportarEgressos.setRelatorio("Erro: o nome do Egresso no registro Req.1 esta nulo.");
            } else if (this.nome.length() > 100) {
                ImportarEgressos.setRelatorio("Erro: o nome do Egresso no registro Req.1 esta com mais de 100 caracteres.");
            }
        }

        testTipoDoc = (this.tipoDocumento != null) && (this.tipoDocumento.length() <= 50);

        if (!testTipoDoc) {
            ImportarEgressos.setTemInconsistencia(true);
            if (this.tipoDocumento == null) {
                ImportarEgressos.setRelatorio("Erro: o tipo do documento do Egresso no registro Req.1 esta nulo.");
            } else if (this.nome.length() > 50) {
                ImportarEgressos.setRelatorio("Erro: o tipo do documento do Egresso no registro Req.1 esta com mais de 50 caracteres.");
            }
        }

        testNumDoc = (this.numeroDocumento != null) && (this.numeroDocumento.length() <= 50) && testNumeroDoc();

        if (!testNumDoc) {
            ImportarEgressos.setTemInconsistencia(true);
            if (this.numeroDocumento == null) {
                ImportarEgressos.setRelatorio("Erro: o numero do documento do Egresso no registro Req.1 esta nulo.");
            } else if (this.nome.length() > 50) {
                ImportarEgressos.setRelatorio("Erro: o numero do documento do Egresso no registro Req.1 esta com mais de 50 caracteres.");
            }
        }

        testNumNascimento = (this.dataNascimento != null) && (this.dataNascimento.length() <= 50) && testNumeroNascimento();

        if (!testNumNascimento) {
            ImportarEgressos.setTemInconsistencia(true);
            if (this.numeroDocumento == null) {
                ImportarEgressos.setRelatorio("Erro: a data de nascimento do Egresso no registro Req.1 esta nulo.");

            } else if (this.nome.length() > 50) {
                ImportarEgressos.setRelatorio("Erro: a data de nascimento do Egresso no registro Req.1 esta com mais de 50 caracteres.");
            }
        }
        return (testNome && testTipoDoc && testNumDoc && testNumNascimento);
    }

    public int getNumDocAux() {
        return listaObjt;
    }

    public java.sql.Date getDataNascimento() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date data = new java.sql.Date(format.parse(dataNascimento).getDate());

        return data;
    }

    public static int getNumEgresso() {
        return numEgresso;
    }

    public int getNumEgressoObject() {
        return numEgressoObject;
    }

    public int getNumLista() {
        return Egresso.mapa.size();
    }

    private void setNumEgresso() {
        this.numEgressoObject = Egresso.numEgresso;
        Egresso.numEgresso = Egresso.numEgresso + 1;
    }

    private boolean testNumeroDoc() {
        char[] numDoc = this.numeroDocumento.toCharArray();
        boolean resultado = true;

        for (int i = 0; i < numDoc.length; i++) // verifica se o char não é um dígito
        {
            if (!Character.isDigit(numDoc[i])) {
                ImportarEgressos.setTemInconsistencia(true);
                resultado = false;
                ImportarEgressos.setRelatorio("Erro: o numero do documento do Egresso no registro Req.1 possui campos que nao sao numerais.");
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
            if ((!Character.isDigit(numDoc[i])) && (!aux.equals("/") && (!aux.equals("-")))) {
                ImportarEgressos.setTemInconsistencia(true);
                resultado = false;
                ImportarEgressos.setRelatorio("Erro: a data de nascimento do Egresso no registro Req.1 possui campos que nao sao numerais ou que nao eh '/' ou '-'.");
                resultado = false;
                break;
            }
        }

        if (numDoc.length != 10) {
            ImportarEgressos.setRelatorio("Erro: a data de nascimento do Egresso no registro Req.1 nao possui 10 caracteres.");
            ImportarEgressos.setTemInconsistencia(true);
            resultado = false;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }
}
