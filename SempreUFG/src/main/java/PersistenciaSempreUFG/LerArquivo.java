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

    public static void abrirArquivo() throws IOException {
        try {
            leitor = new Scanner(new File("C:\\Users\\Hiago\\GitHub\\SempreUFG-Integracao\\SempreUFG\\Egressos-para-Importar.txt"));
//            leitor = new Scanner(new File("C:\\Users\\Juliano\\IntegrAplic\\Egressos-para-Importar.txt"));
            System.out.println("Arquivo Aberto com sucesso!");
        } catch (FileNotFoundException file) {
            System.out.println("Erro ao abrir o arquivo!");
        }
    }

    public static void lerDados() throws IOException {
        try {
            FileReader arq = new FileReader("C:\\Users\\Hiago\\GitHub\\SempreUFG-Integracao\\SempreUFG\\Egressos-para-Importar.txt");
//            FileReader arq = new FileReader("C:\\Users\\Juliano\\IntegrAplic\\Egressos-para-Importar.txt"));

            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            char[] numDoc = linha.toCharArray();
            String ident = "";
            int controlReg1 = 1; //Verifica qual campo do Egresso será salvo
            String aux; // Converte o char[i] em uma String para ser analisada no if

            while (linha != null) {
                ident = "";
                numDoc = linha.toCharArray();
                System.out.printf("Conteudo da linha: " + "%s\n", linha);
                if (linha != null && !"".equals(linha)) {
                    for (int i = 0; i < 5; i++) {
                        ident = ident + numDoc[i];
                    }
                } else {
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
                    Egresso egresso;
                    String nome = "", tipoDocumento = "", numeroDocumento = "", dataNascimento = "";
                    boolean passou = false;

                    for (int i = 5; i < numDoc.length; i++) {
                        aux = String.valueOf(numDoc[i]);

                        if (i == 5) {
                            if (aux.equals("\\")) {
                                ImportarEgressos.setRelatorio("Erro: foi constatado a inexistencia do primeiro campo do Req.1.");
                                ImportarEgressos.setTemInconsistencia(true);
                                controlReg1 = controlReg1 + 1;
                            }
                        }

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

                    boolean ok = egresso.validaEgresso();
//
//                    if (ok) {
//                        System.out.println("Não foram encontradas inconsist");
//                    } else {
//                        System.out.println("Opps...");
//                    }
                    //Registro tipo 2: Valor fixo “Reg.2”, o segundo e terceiro 
                    //campos do Egresso, o identificador de um Curso da UFG 
                    //cursado pelo egresso, e todos os campos de Realização de 
                    //Programa Acadêmico do egresso nesse curso.
                } else if (ident.equals("Reg.2")) {

                    System.out.println("Identificador da linha: " + ident);

                } else {
                    ImportarEgressos.setTemInconsistencia(true);
                    ImportarEgressos.setRelatorio("Erro: identificador invalido. Identificador encontrado: " + ident + ".");
                }

                linha = lerArq.readLine();
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public static void fecharArquivo() throws IOException {
        if (leitor != null) {
            leitor.close();
        }
    }
}
