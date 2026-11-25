import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Stack;

/**
 * Binary search tree of words with occurrence frequencies.
 *
 * Words are stored in upper case so that insertion and lookup are
 * case-insensitive. Public results are returned in lower case.
 *
 * @author antonio
 */
public class ArvorePalavras2 implements Iterable<String> {

	private Node raiz;

	public ArvorePalavras2() {
		this.raiz = new Node();
	}

	public ArvorePalavras2(String data, int freq) {
		this.raiz = new Node(data, freq);
	}

	public ArvorePalavras2(String data, int freq, ArvorePalavras2 esq,
			ArvorePalavras2 dta) {
		this.raiz = new Node(data, freq, esq.raiz, dta.raiz);
	}

	public void inserePalavra(String palavra) {
		if (palavra == null || palavra.isEmpty()) {
			return;
		}

		String upper = palavra.toUpperCase(Locale.ROOT);
		if (raiz.data == null) {
			raiz = new Node(upper, 1);
			return;
		}

		Node node = raiz;
		while (true) {
			int compare = node.data.compareTo(upper);
			if (compare == 0) {
				node.freq++;
				return;
			}
			if (compare > 0) {
				if (node.esq == null) {
					node.esq = new Node(upper, 1);
					return;
				}
				node = node.esq;
			} else {
				if (node.dta == null) {
					node.dta = new Node(upper, 1);
					return;
				}
				node = node.dta;
			}
		}
	}

	@Override
	public Iterator<String> iterator() {
		List<String> words = new ArrayList<String>();
		for (Node node : inOrderNodes()) {
			words.add(node.data.toLowerCase(Locale.ROOT));
		}
		return words.iterator();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node node : inOrderNodes()) {
			sb.append(node.data.toLowerCase(Locale.ROOT)).append(" [")
					.append(node.freq).append("]\n");
		}
		return sb.toString();
	}

	public int contaFrequencia(String palavra) {
		if (palavra == null || palavra.isEmpty() || raiz.data == null) {
			return 0;
		}

		String upper = palavra.toUpperCase(Locale.ROOT);
		Node node = raiz;
		while (node != null) {
			int compare = node.data.compareTo(upper);
			if (compare == 0) {
				return node.freq;
			}
			node = compare > 0 ? node.esq : node.dta;
		}
		return 0;
	}

	/**
	 * Counts distinct words strictly between the two given words in
	 * lexicographic order. Bounds are case-insensitive.
	 */
	public int entrePalavras(String palavra, String palavra2) {
		if (palavra == null || palavra2 == null || palavra.isEmpty()
				|| palavra2.isEmpty()) {
			return 0;
		}

		String first = palavra.toUpperCase(Locale.ROOT);
		String second = palavra2.toUpperCase(Locale.ROOT);
		String min = first.compareTo(second) <= 0 ? first : second;
		String max = first.compareTo(second) <= 0 ? second : first;

		int count = 0;
		for (Node node : inOrderNodes()) {
			if (node.data.compareTo(min) > 0 && node.data.compareTo(max) < 0) {
				count++;
			}
		}
		return count;
	}

	public ArrayList<String> prefixo(String palavra) {
		ArrayList<String> result = new ArrayList<String>();
		if (palavra == null) {
			return result;
		}

		String prefix = palavra.toLowerCase(Locale.ROOT);
		for (Node node : inOrderNodes()) {
			String word = node.data.toLowerCase(Locale.ROOT);
			if (word.startsWith(prefix)) {
				result.add(word);
			}
		}
		return result;
	}

	/** Removes the alphabetically first word from the tree. */
	public void removePrimeira() {
		if (raiz.data == null) {
			return;
		}
		if (raiz.esq == null) {
			raiz = raiz.dta == null ? new Node() : raiz.dta;
			return;
		}

		Node parent = raiz;
		Node node = raiz.esq;
		while (node.esq != null) {
			parent = node;
			node = node.esq;
		}
		parent.esq = node.dta;
	}

	/** Returns every word tied for the highest frequency. */
	public ArrayList<String> maxFrequencia() {
		ArrayList<String> result = new ArrayList<String>();
		List<Node> nodes = inOrderNodes();
		if (nodes.isEmpty()) {
			return result;
		}

		int max = 0;
		for (Node node : nodes) {
			max = Math.max(max, node.freq);
		}
		for (Node node : nodes) {
			if (node.freq == max) {
				result.add(node.data.toLowerCase(Locale.ROOT));
			}
		}
		return result;
	}

	private List<Node> inOrderNodes() {
		if (raiz == null || raiz.data == null) {
			return Collections.emptyList();
		}

		List<Node> nodes = new ArrayList<Node>();
		Stack<Node> stack = new Stack<Node>();
		Node current = raiz;
		while (current != null || !stack.isEmpty()) {
			while (current != null) {
				stack.push(current);
				current = current.esq;
			}
			current = stack.pop();
			nodes.add(current);
			current = current.dta;
		}
		return nodes;
	}

	private static class Node {
		private String data;
		private int freq;
		private Node esq;
		private Node dta;

		private Node() {
			this(null, 0, null, null);
		}

		private Node(String data, int freq) {
			this(data, freq, null, null);
		}

		private Node(String data, int freq, Node esq, Node dta) {
			this.data = data;
			this.freq = freq;
			this.esq = esq;
			this.dta = dta;
		}
	}
}
