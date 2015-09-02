import java.util.Iterator;


public class TryRunArvore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ConjuntoPalavraFrequencia p = new ConjuntoPalavraFrequencia("a", 0);
		ConjuntoPalavraFrequencia pp = new ConjuntoPalavraFrequencia("ac", 1);
		ArvorePalavras2 arvore = new ArvorePalavras2();
		arvore.inserePalavra("a");
		arvore.inserePalavra("a");
		arvore.inserePalavra("aa");
		arvore.inserePalavra("a");
		arvore.inserePalavra("aaaaaa");
		arvore.contaFrequencia("d");
		System.out.println(arvore);
		System.out.println(arvore.contaFrequencia("a"));
		System.out.println("qew"+ arvore.prefixo("aa"));
		System.out.println(arvore.entrePalavras("swq", "wq"));
		
		arvore.entrePalavras("a", "ab");
//		System.out.println("Antes " + arvore);
		arvore.removePrimeira();
	//	System.out.println("Depois " + arvore);
		Iterator iter = arvore.iterator();

		//while (iter.hasNext()) {
		//System.out.println(iter.next());
		//}
		//System.out.println(arvore.maxFrequencia());

	}

}
