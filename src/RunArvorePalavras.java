import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Command line interface for the word frequency tree.
 *
 * Usage: RunArvorePalavras [input-file] [output-file]
 * Defaults: input-file=texto, output-file=resultado
 */
public class RunArvorePalavras {

	private static ArvorePalavras2 lerFicheiro(String ficheiro) throws IOException {
		ArvorePalavras2 arvore = new ArvorePalavras2();
		try (Scanner leitor = new Scanner(new BufferedReader(new FileReader(ficheiro)))) {
			while (leitor.hasNext()) {
				String palavra = leitor.next().replaceAll("[^\\p{L}]", "");
				if (!palavra.isEmpty()) {
					arvore.inserePalavra(palavra.toUpperCase(Locale.ROOT));
				}
			}
		}
		return arvore;
	}

	public static void main(String[] args) throws IOException {
		String input = args.length > 0 ? args[0] : "texto";
		String output = args.length > 1 ? args[1] : "resultado";

		try (Scanner sc = new Scanner(System.in);
				BufferedWriter bWriter = new BufferedWriter(new FileWriter(output))) {

			ArvorePalavras2 arvore = lerFicheiro(input);
			bWriter.write(arvore.toString() + "\n");

			System.out.print("Enter a word: ");
			String palavra = sc.hasNext() ? sc.next() : "";
			int f1 = arvore.contaFrequencia(palavra);
			bWriter.write(Integer.toString(f1) + "\n\n");

			System.out.print("Enter another word: ");
			String palavra2 = sc.hasNext() ? sc.next() : "";
			int f2 = arvore.contaFrequencia(palavra2);
			bWriter.write(Integer.toString(f2) + "\n\n");

			if (f1 > 0 && f2 > 0) {
				bWriter.write(Integer.toString(arvore.entrePalavras(palavra, palavra2)) + "\n\n");
			}

			System.out.print("Enter a prefix: ");
			String prefixo = sc.hasNext() ? sc.next() : "";
			for (String s : arvore.prefixo(prefixo)) {
				bWriter.write(s + "\n");
			}
			bWriter.write("\n");

			arvore.removePrimeira();
			bWriter.write(arvore.toString() + "\n");

			for (String s : arvore.maxFrequencia()) {
				bWriter.write(s + "\n");
			}
		}
	}
}
