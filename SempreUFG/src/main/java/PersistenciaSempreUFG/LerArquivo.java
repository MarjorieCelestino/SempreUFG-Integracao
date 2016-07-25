package PersistenciaSempreUFG;

import SempreUFG.Egresso;
import SempreUFG.HistoricoNaUFG;
import SempreUFG.ImportarEgressos;
import SempreUFG.ProgramaAcademico;
import SempreUFG.Reg1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Hiago
 */
public class LerArquivo {

    private static Scanner leitor;
    private static int quantEgresso = 0;

    /**
     * Classe responsavel por realizar a leitura do arquivo
     * "Egresso-para-importar".
     */
    public static void lerDados() throws IOException {
        try {
            FileReader arq = new FileReader("C:\\Users\\Hiago\\GitHub\\SempreUFG-Integracao\\SempreUFG\\Egressos-para-Importar.txt");
//            FileReader arq = new FileReader("C:\\Users\\Juliano\\IntegrAplic\\Egressos-para-Importar.txt"));

            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            char[] numDoc = linha.toCharArray();
            String ident = "";
            int controlReg1 = 1; //Verifica qual campo do Egresso será salvo
            int controlReg2 = 1; //Verifica qual campo do Egresso será salvo
            String aux; // Converte o char[i] em uma String para ser analisada no if
            Egresso egresso;
            HistoricoNaUFG historicoUfg;
            ProgramaAcademico progAcad;
            int reg1 = 0;

            //O while le todo o arquivo, linha por linha
            while (linha != null) {
                if (controlReg1 > reg1) {
                    reg1 = controlReg1;
                }
                controlReg1 = 1;
                controlReg2 = 1;
                ident = "";
                numDoc = linha.toCharArray();
                System.out.printf("Conteudo da linha: " + "%s\n", linha);

                if (linha != null && !"".equals(linha)) {
                    //variavel ident recebe o identificador da linha, podendo este ser Reg.1 ou Reg.2
                    for (int i = 0; i < 5; i++) {
                        ident = ident + numDoc[i];
                    }
                } else {
                    //Erro lancado em caso da linha estar em branco
                    ImportarEgressos.setRelatorio("Erro: foi constatado uma linha em branco no registro.");
                    ImportarEgressos.setTemInconsistencia(true);
                }

                //Registro tipo 1: Valor fixo “Reg.1”, os quatro primeiros 
                //campos de Egresso, o nome do Curso da UFG cursado pelo egresso, 
                //e todos os campos de Histórico na UFG. 
                if (ident.equals("Reg.1")) {
                    quantEgresso = quantEgresso + 1;

                    if (quantEgresso > 1) {
                        ImportarEgressos.setRelatorio("Erro: foi constatado a existencia de mais de um registro do tipo Req.1.");
                        ImportarEgressos.setTemInconsistencia(true);
                    }

                    System.out.println("Identificador da linha: " + ident);
                    String nome = "", tipoDocumento = "", numeroDocumento = "", dataNascimento = "";
                    String curso = "", idHistorico = "", cursoUFG = "";
                    String mesAnoInicio = "", mesAnoFim = "", numeroMatriculaCurso = "";
                    String tituloTrabalhoFinal = "";
                    boolean passou = false;

                    for (int i = 5; i < numDoc.length; i++) {
                        aux = String.valueOf(numDoc[i]);

                        //Verifica se logo apos o identificador houve o '\'.
                        //Caso positivo, esta faltando o primeiro campo e eh lancado uma inconsistencia
                        if (i == 5) {
                            if (aux.equals("\\")) {
                                ImportarEgressos.setRelatorio("Erro: foi constatado a inexistencia do primeiro campo do Req.1.");
                                ImportarEgressos.setTemInconsistencia(true);
                                controlReg1 = controlReg1 + 1;
                            }
                        }

                        //switch para os campos do registro Req.1
                        switch (controlReg1) {
                            case 1:
                                if (aux.equals("\\")) {
                                    controlReg1 = controlReg1 + 1;
                                } else {
                                    nome = nome + aux;
                                }
                                break;
                            case 2:
                                if (aux.equals("\\")) {
                                    controlReg1 = controlReg1 + 1;
                                } else {
                                    tipoDocumento = tipoDocumento + aux;
                                }
                                break;
                            case 3:
                                if (aux.equals("\\")) {
                                    controlReg1 = controlReg1 + 1;
                                } else {
                                    numeroDocumento = numeroDocumento + aux;
                                }
                                break;
                            case 4:
                                if (aux.equals("\\")) {
                                    controlReg1 = controlReg1 + 1;
                                } else {
                                    dataNascimento = dataNascimento + aux;
                                }
                                break;

                            case 5:
                                if (aux.equals("\\")) {
                                    controlReg1 = controlReg1 + 1;
                                } else {
                                    curso = curso + aux;
                                }
                                break;
                            case 6:
                                if (aux.equals("\\")) {
                                    controlReg1 = controlReg1 + 1;
                                } else {
                                    idHistorico = idHistorico + aux;
                                }
                                break;
                            case 7:
                                if (aux.equals("\\")) {
                                    controlReg1 = controlReg1 + 1;
                                } else {
                                    cursoUFG = cursoUFG + aux;
                                }
                                break;
                            case 8:
                                if (aux.equals("\\")) {
                                    controlReg1 = controlReg1 + 1;
                                } else {
                                    mesAnoInicio = mesAnoInicio + aux;
                                }
                                break;
                            case 9:
                                if (aux.equals("\\")) {
                                    controlReg1 = controlReg1 + 1;
                                } else {
                                    mesAnoFim = mesAnoFim + aux;
                                }
                                break;
                            case 10:
                                if (aux.equals("\\")) {
                                    controlReg1 = controlReg1 + 1;
                                } else {
                                    numeroMatriculaCurso = numeroMatriculaCurso + aux;
                                }
                                break;
                            case 11:
                                if (aux.equals("\\")) {
                                    controlReg1 = controlReg1 + 1;
                                } else {
                                    tituloTrabalhoFinal = tituloTrabalhoFinal + aux;
                                }
                                break;
                            default:
                                if (!passou) {
                                    passou = true;
                                    ImportarEgressos.setRelatorio("Erro: Foi constatado a existencia de um campo a mais no registro Req.1.");
                                    ImportarEgressos.setTemInconsistencia(true);
                                }
                                break;
                        }
                    }

                    egresso = new Egresso(nome, tipoDocumento, numeroDocumento, dataNascimento);

                    //Chama o metodo responsavel por validar as informacoes do egresso
                    boolean reg1OK = egresso.validaEgresso();
                    boolean testaCurso = true;

                    if (curso == null) {
                        testaCurso = false;
                        ImportarEgressos.setRelatorio("Erro: o identificador do curso da UfG cursado pelo egresso no registro Req.1 esta nulo.");
                        ImportarEgressos.setTemInconsistencia(true);
                    }
                    if (curso.length() > 50) {
                        testaCurso = false;
                        ImportarEgressos.setRelatorio("Erro: o identificador do curso da UfG cursado pelo egresso no registro Req.1 esta com mais de 50 caracteres.");
                        ImportarEgressos.setTemInconsistencia(true);
                    }

                    int inicio, fim, matriculaCurso;

                    inicio = Integer.parseInt(mesAnoInicio);
                    fim = Integer.parseInt(mesAnoFim);
                    matriculaCurso = Integer.parseInt(numeroMatriculaCurso);

                    historicoUfg = new HistoricoNaUFG(idHistorico, cursoUFG, inicio, fim, matriculaCurso, tituloTrabalhoFinal);

                    boolean historicoOK = historicoUfg.validaHistoricoNaUFG();

                    //public Reg1(Egresso egresso, String curso, HistoricoNaUFG historico) {
                    if (reg1OK && testaCurso && historicoOK) {
                        Reg1 regra = new Reg1(egresso, curso, historicoUfg);
                    }

                    //Registro tipo 2: Valor fixo “Reg.2”, o segundo e terceiro 
                    //campos do Egresso, o identificador de um Curso da UFG 
                    //cursado pelo egresso, e todos os campos de Realização de 
                    //Programa Acadêmico do egresso nesse curso.
                } else if (ident.equals("Reg.2")) {

                    System.out.println("Identificador da linha: " + ident);

                    String tipoDocEgresso = "", numDocEgresso = "";
                    String identificadorCurso = "";
                    String idHistorico = "", tipoEnum = "", dataInicio = "", dataFim = "", descricao = "";

                    boolean passou = false;

                    for (int i = 5; i < numDoc.length; i++) {
                        aux = String.valueOf(numDoc[i]);

                        //Verifica se logo apos o identificador houve o '\'.
                        //Caso positivo, esta faltando o primeiro campo e eh lancado uma inconsistencia
                        if (i == 5) {
                            if (aux.equals("\\")) {
                                ImportarEgressos.setRelatorio("Erro: foi constatado a inexistencia do primeiro campo do Req.2.");
                                ImportarEgressos.setTemInconsistencia(true);
                                controlReg2 = controlReg2 + 1;
                            }
                        }

                        //switch para os campos do registro Req.2
                        switch (controlReg2) {
                            case 1:
                                if (aux.equals("\\")) {
                                    controlReg2 = controlReg2 + 1;
                                } else {
                                    tipoDocEgresso = tipoDocEgresso + aux;
                                }
                                break;
                            case 2:
                                if (aux.equals("\\")) {
                                    controlReg2 = controlReg2 + 1;
                                } else {
                                    numDocEgresso = numDocEgresso + aux;
                                }
                                break;
                            case 3:
                                if (aux.equals("\\")) {
                                    controlReg2 = controlReg2 + 1;
                                } else {

                                    identificadorCurso = identificadorCurso + aux;
                                }
                                break;
                            case 4:
                                if (aux.equals("\\")) {
                                    controlReg2 = controlReg2 + 1;
                                } else {
                                    idHistorico = idHistorico + aux;
                                }
                                break;
                            case 5:
                                if (aux.equals("\\")) {
                                    controlReg2 = controlReg2 + 1;
                                } else {
                                    tipoEnum = tipoEnum + aux;
                                }
                                break;
                            case 6:
                                if (aux.equals("\\")) {
                                    controlReg2 = controlReg2 + 1;
                                } else {
                                    dataInicio = dataInicio + aux;
                                }
                                break;
                            case 7:
                                if (aux.equals("\\")) {
                                    controlReg2 = controlReg2 + 1;
                                } else {
                                    dataFim = dataFim + aux;
                                }
                                break;
                            case 8:
                                if (aux.equals("\\")) {
                                    controlReg2 = controlReg2 + 1;
                                } else {
                                    descricao = descricao + aux;
                                }
                                break;
                            default:
                                if (!passou) {
                                    passou = true;
                                    ImportarEgressos.setRelatorio("Erro: Foi constatado a existencia de um campo a mais no registro Req.2.");
                                    ImportarEgressos.setTemInconsistencia(true);
                                }
                                break;
                        }
                    }

                    //Realizando a persistencia dos atributos e verificacao de inconsistencias (chamando os metodos responsaveis).
                    //Cases 1 e 2:
                    egresso = Egresso.getInstancia(0);
                    egresso.addTipoDocLista(tipoDocEgresso);
                    egresso.addNumDocLista(numDocEgresso);
                    egresso.validaListas();

                    //Case 3:
                    if (identificadorCurso == null) {
                        ImportarEgressos.setRelatorio("Erro: o identificador do curso da UfG cursado pelo egresso no registro Req.2 esta nulo.");
                        ImportarEgressos.setTemInconsistencia(true);
                    }
                    if (identificadorCurso.length() > 50) {
                        ImportarEgressos.setRelatorio("Erro: o identificador do curso da UfG cursado pelo egresso no registro Req.2 esta com mais de 50 caracteres.");
                        ImportarEgressos.setTemInconsistencia(true);
                    }

                    //Case 4, 5, 6, 7 e 8:
                    progAcad = new ProgramaAcademico(idHistorico, tipoEnum, dataInicio, dataFim, descricao);
                    boolean ok = progAcad.validaProgramaAcademico();

                } else {
                    ImportarEgressos.setTemInconsistencia(true);
                    ImportarEgressos.setRelatorio("Erro: identificador invalido. Identificador encontrado: " + ident + ".");
                }

                linha = lerArq.readLine();
            }

            if (quantEgresso == 0) {
                ImportarEgressos.setRelatorio("Erro: nao foi constatado nenhum registro do tipo Req.1.");
                ImportarEgressos.setTemInconsistencia(true);
            }

            if (reg1 != 11) {
                int result = 11 - reg1;

                if (result == 1) {
                    ImportarEgressos.setRelatorio("Erro: esta faltando um campo no registro do tipo Req.1.");
                } else {
                    ImportarEgressos.setRelatorio("Erro: estao faltando 2 ou mais campos no registro do tipo Req.1.");
                }
                ImportarEgressos.setTemInconsistencia(true);
            }

            if (controlReg2 != 8) {
                int result = 8 - controlReg1;

                if (result == 1) {
                    ImportarEgressos.setRelatorio("Erro: esta faltando um campo no registro do tipo Req.2.");
                } else {
                    ImportarEgressos.setRelatorio("Erro: estao faltando 2 ou mais campos no registro do tipo Req.2.");
                }
                System.out.println(result);
                ImportarEgressos.setTemInconsistencia(true);
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    //Fechando o leitor
    public static void fecharArquivo() throws IOException {
        if (leitor != null) {
            leitor.close();
        }
    }
}
