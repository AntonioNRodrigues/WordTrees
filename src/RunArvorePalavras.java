import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Classe que fornece uma interface com o utilizador
 * para poder utilizar a classe ArvorePalavras
 */
public class RunArvorePalavras {

	/*
	 * Funcao auxiliar que ler do ficheiro as palavras e cria��o da respectiva
	 * arvore bin�ria.
	 * 
	 * @param ficheiro nome do ficheiro
	 * 
	 * @return Uma inst�ncia da classe ArvorePalavras.
	 */
	private static ArvorePalavras2 lerFicheiro(String ficheiro)
			throws IOException {

		Scanner leitor = new Scanner(new FileReader(ficheiro));
		ArvorePalavras2 arvore = new ArvorePalavras2();
		String palavra;

		while (leitor.hasNext()) {
			palavra = leitor.next();
			palavra = palavra.replaceAll("[^\\p{L}]", "");
			palavra = palavra.toUpperCase().trim();
			arvore.inserePalavra(palavra);
		}

		leitor.close();

		return arvore;
	}

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		System.out.println("Introduza o Nome do ficheiro: ");
		BufferedWriter bWriter = new BufferedWriter(new FileWriter("resultado"));

		ArvorePalavras2 arvore = lerFicheiro(sc.next());

		bWriter.write(arvore.toString() + "\n");
		System.out.println("Introduza uma Palavra: ");
		String palavra = sc.next();
		int f1 = arvore.contaFrequencia(palavra);
		bWriter.write(Integer.toString(f1) + "\n\n");
		System.out.println("Introduza uma Palavra: ");
		String palavra2 = sc.next();
		int f2 = arvore.contaFrequencia(palavra2);
		bWriter.write(Integer.toString(f2) + "\n\n");
		if (f1 > 0 && f2 > 0)
			bWriter.write(Integer.toString(arvore.entrePalavras(palavra,
					palavra2)) + "\n\n");

		System.out.println("Introduza um Prefixo: ");
		palavra = sc.next();
		for (String s : arvore.prefixo(palavra)) {
			bWriter.write(s + "\n");
		
		}
		bWriter.write("\n");
		arvore.removePrimeira();

		bWriter.write(arvore.toString() + "\n");

		for (String s : arvore.maxFrequencia()) {
			bWriter.write(s + "\n");
		}

		bWriter.close();

	}

}
