package PersistenciaSempreUFG;

import SempreUFG.Egresso;
import SempreUFG.ImportarEgressos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
     * Classe responsavel por receber o arquivo.
     */
    public static void abrirArquivo() throws IOException {
        try {
            leitor = new Scanner(new File("C:\\Users\\Hiago\\GitHub\\SempreUFG-Integracao\\SempreUFG\\Egressos-para-Importar.txt"));
//            leitor = new Scanner(new File("C:\\Users\\Juliano\\IntegrAplic\\Egressos-para-Importar.txt"));
            System.out.println("Arquivo Aberto com sucesso!");
        } catch (FileNotFoundException file) {
            System.out.println("Erro ao abrir o arquivo!");
        }
    }

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

            //O while le todo o arquivo, linha por linha
            while (linha != null) {
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
                    boolean ok = egresso.validaEgresso();

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

                    egresso = Egresso.getInstancia(0);
                    egresso.addTipoDocLista(tipoDocEgresso);
                    egresso.addNumDocLista(numDocEgresso);
//                    identificadorCurso

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

            if (controlReg1 < 4) {
                int result = 4 - controlReg1;

                if (result == 1) {
                    ImportarEgressos.setRelatorio("Erro: esta faltando um campo no registro do tipo Req.1.");
                } else {
                    ImportarEgressos.setRelatorio("Erro: estao faltando 2 ou mais campos no registro do tipo Req.1.");
                }
                System.out.println(result);
                ImportarEgressos.setTemInconsistencia(true);
            }

            if (controlReg2 < 8) {
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
