package SempreUFG;

class Egresso {

    private String idEgresso, nome, tipoDocumento, numeroDocumento;

    public Egresso(String nome, String tipoDocumento, String documentoId) {
        this.nome = nome;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = documentoId;
        this.idEgresso = this.tipoDocumento + this.numeroDocumento;
    }

    public boolean validaEgresso() {
        boolean testNome, testTipoDoc, testNumDoc;

        testNome = (this.nome != null) && (this.nome.length() <= 100);
        testTipoDoc = (this.tipoDocumento != null) && (this.tipoDocumento.length() <= 50);
        testNumDoc = (this.numeroDocumento != null) && (this.numeroDocumento.length() <= 50) && testNumeroDoc();

        return (testNome && testTipoDoc && testNumDoc);
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
