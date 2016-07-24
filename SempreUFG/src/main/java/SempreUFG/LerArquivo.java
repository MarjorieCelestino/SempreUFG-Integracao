/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    static String nome;

    public static void abrirArquivo(String nome) {
        LerArquivo.nome = nome;
        try {
            leitor = new Scanner(new File("C:\\Users\\Hiago\\GitHub\\SempreUFG-Integracao\\SempreUFG\\" + nome + ".txt"));
//            leitor = new Scanner(new File("C:\\Users\\Juliano\\IntegrAplic\\" + nome + ".txt"));
            System.out.println("Arquivo Aberto com sucesso!");
        } catch (FileNotFoundException file) {
            System.out.println("Erro ao abrir o arquivo!");
        }
    }

    public static void lerDados() {

        System.out.printf("\nConteúdo do arquivo texto:\n");
        try {
            FileReader arq = new FileReader("C:\\Users\\Hiago\\GitHub\\SempreUFG-Integracao\\SempreUFG\\" + nome + ".txt");
//            FileReader arq = new FileReader("C:\\Users\\Juliano\\IntegrAplic\\" + nome + ".txt"));

            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            while (linha != null) {
                System.out.printf("%s\n", linha);

                linha = lerArq.readLine();
            }
            arq.close();

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
    }

    public static void fecharArquivo() {
        if (leitor != null) {
            leitor.close();
        }
    }
}
