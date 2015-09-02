import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * @author antonio
 * 
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

		if (raiz.data == null) {
			raiz = new Node(palavra, 1);

		} else {
			Node anterior = null;
			Node nodeRaiz = raiz;
			int compare = 0;
			boolean goEsquerda = false;
			boolean goDireita = false;

			do {
				compare = nodeRaiz.data.compareTo(palavra);
				if (compare == 0) {
					nodeRaiz.freq++;
					return;
				}
				if (compare > 0) {
					anterior = nodeRaiz;
					nodeRaiz = nodeRaiz.esq;
					goEsquerda = true;
					goDireita = false;
				}
				if (compare < 0) {
					anterior = nodeRaiz;
					nodeRaiz = nodeRaiz.dta;
					goEsquerda = false;
					goDireita = true;
				}

			} while (nodeRaiz != null);
			if (goDireita) {
				anterior.dta = new Node(palavra, 1);
			} else if (goEsquerda) {
				anterior.esq = new Node(palavra, 1);
			}
		}
	}

	public Iterator<String> iterator() {
		return new Iterador(raiz);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Iterator it = new Iterador(raiz);
		Iterador it2= new Iterador(raiz);
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			sb.append(it.next()).append(" [").append(it2.next2()).append("]");
			sb.append("\n");
		}
		return sb.toString().toLowerCase();
	}

	private class Node<String extends Comparable<String>, Integer> {
		private String data;
		private int freq;
		private Node esq;
		private Node dta;

		private Node() {
			this.freq = 0;
			this.data = null;
			this.dta = null;
			this.esq = null;
		}

		private Node(String data, int freq, Node esq, Node dta) {
			this.freq = freq;
			this.data = data;
			this.dta = dta;
			this.esq = esq;
		}

		private Node(String data, int freq) {
			this.freq = freq;
			this.data = data;
			this.dta = null;
			this.esq = null;
		}

		public int compareTo(String o) {
			return this.data.compareTo(o);
		}
		
	}

	private class Iterador implements Iterator {
		private Stack<Node> stack;
		private Node current;

		public Iterador(Node copyRoot) {
			this.stack = new Stack<>();
			this.current = copyRoot;
			pushEsq(current);
		}

		private void pushEsq(Node current) {
			while (current != null) {
				stack.push(current);
				current = current.esq;
			}
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public String next() {
			Node resultado = null;
			if (hasNext()) {
				resultado = stack.pop();
				pushEsq(resultado.dta);
			}
			return resultado.data.toString();
		}
		public Integer next2() {
			Node resultado = null;
			if (hasNext()) {
				resultado = stack.pop();
				pushEsq(resultado.dta);
			}
			return resultado.freq;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}

	public int contaFrequencia(String palavra) {
		boolean encontrouPalavra = false;
		int compare = 0;
		Node node = raiz;

		while (!encontrouPalavra) {
			String p = (String) node.data;
			compare = p.compareTo(palavra.toUpperCase());
			if (compare == 0) {
				encontrouPalavra = true;
				return node.freq;
			}
			if (compare > 0) {
				node = node.esq;
			}
			if (compare < 0) {
				node = node.dta;
			}
			if (node == null) {
				return 0;
			}
		}
		return 0;
	}

	public int entrePalavras(String palavra, String palavra2) {
		
		
		return 0;
	}

	public ArrayList<String> prefixo(String palavra) {
		ArrayList<String> lista = getLista();
		ArrayList<String> listaPrefixo = new ArrayList<String>();
		
		for (String s : lista){
			if(s.toLowerCase().startsWith(palavra)){
				listaPrefixo.add(s.toLowerCase());
			}
		}
		
		return listaPrefixo;
	}

	public void removePrimeira() {
		Node node = raiz;
		Node pai = node;
		while (node.esq != null) {
			pai = node;
			node = node.esq;

		}
		if (node.dta != null) {
			pai.esq = node.dta;
			node = null;
		} else {
			pai.esq = null;
		}

	}

	public ArrayList<String> maxFrequencia() {
		ArrayList<Integer> lista = getListaInt();
		ArrayList<String> lista3 = getLista();
		ArrayList<String> lista2 = new ArrayList<String>();
		int max = 0;
		int indice = 0;
		for (int i : lista){
			if (i > max){
				max = i;
				indice = lista.indexOf(i);
			}
		}
		lista2.add(lista3.get(indice));
		
		return lista2;
	}

	private ArrayList<String> getLista() {
		ArrayList<String> lista = new ArrayList<String>();
		Iterator it = new Iterador(raiz);
		while (it.hasNext()) {
			lista.add((String) it.next());
		}
		return lista;
	}
	private ArrayList<Integer> getListaInt() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		Iterador it = new Iterador(raiz);
		while (it.hasNext()) {
			lista.add((Integer) it.next2());
		}
		return lista;
	}
}