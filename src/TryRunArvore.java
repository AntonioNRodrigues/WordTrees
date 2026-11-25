import java.util.Iterator;

/** Small manual smoke test for the word tree. */
public class TryRunArvore {

	public static void main(String[] args) {
		ArvorePalavras2 arvore = new ArvorePalavras2();
		arvore.inserePalavra("a");
		arvore.inserePalavra("a");
		arvore.inserePalavra("aa");
		arvore.inserePalavra("a");
		arvore.inserePalavra("aaaaaa");

		System.out.println(arvore);
		System.out.println("Frequency of 'a': " + arvore.contaFrequencia("a"));
		System.out.println("Words with prefix 'aa': " + arvore.prefixo("aa"));
		System.out.println("Words between 'a' and 'ab': " + arvore.entrePalavras("a", "ab"));

		arvore.removePrimeira();
		Iterator<String> iter = arvore.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		System.out.println("Most frequent: " + arvore.maxFrequencia());
	}
}
