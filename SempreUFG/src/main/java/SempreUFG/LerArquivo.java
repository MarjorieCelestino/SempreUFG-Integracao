package SempreUFG;

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

    public static void abrirArquivo() {
        try {
            leitor = new Scanner(new File("C:\\Users\\Hiago\\GitHub\\SempreUFG-Integracao\\SempreUFG\\Egressos-para-Importar.txt"));
//            leitor = new Scanner(new File("C:\\Users\\Juliano\\IntegrAplic\\Egressos-para-Importar.txt"));
            System.out.println("Arquivo Aberto com sucesso!");
        } catch (FileNotFoundException file) {
            System.out.println("Erro ao abrir o arquivo!");
        }
    }

    public static void lerDados() {

        System.out.printf("\nConteudo do arquivo texto:\n\n");
        try {
            FileReader arq = new FileReader("C:\\Users\\Hiago\\GitHub\\SempreUFG-Integracao\\SempreUFG\\Egressos-para-Importar.txt");
//            FileReader arq = new FileReader("C:\\Users\\Juliano\\IntegrAplic\\Egressos-para-Importar.txt"));

            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            char[] numDoc = linha.toCharArray();
            String ident = "";

            while (linha != null) {
                ident = "";
                numDoc = linha.toCharArray();
                System.out.printf("Conteudo da linha: " + "%s\n", linha);
                for (int i = 0; i < 5; i++) {
                    ident = ident + numDoc[i];
                }

                //Registro tipo 1: Valor fixo “Reg.1”, os quatro primeiros 
                //campos de Egresso, o nome do Curso da UFG cursado pelo egresso, 
                //e todos os campos de Histórico na UFG. 
                if (ident.equals("Reg.1")) {
                    System.out.println("Identificador da linha: " + ident);
                    
                    
                    
                    
                    
                    //Registro tipo 2: Valor fixo “Reg.2”, o segundo e terceiro 
                    //campos do Egresso, o identificador de um Curso da UFG 
                    //cursado pelo egresso, e todos os campos de Realização de 
                    //Programa Acadêmico do egresso nesse curso.
                } else if (ident.equals("Reg.2")) {
                    
                    System.out.println("Identificador da linha: " + ident);
                    
                    
                    
                    
                    
                } else {
                    ImportarEgressos.setTemInconsistencia(true);
                    System.out.println("Identificador invalido. Identificador encontrado: " + ident);
                }

                linha = lerArq.readLine();
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    public static void fecharArquivo() {
        if (leitor != null) {
            leitor.close();
        }
    }
}
