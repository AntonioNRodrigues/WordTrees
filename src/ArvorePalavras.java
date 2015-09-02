import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * 
 */

/**
 * @author antonio
 * 
 */
public class ArvorePalavras<ConjuntoPalavrasFrequencia> implements
		Iterable<String> {
	
	private Node<ConjuntoPalavraFrequencia> raiz;

	public ArvorePalavras() {
		this.raiz = new Node<>();
	}

	public ArvorePalavras(ConjuntoPalavraFrequencia data) {
		this.raiz = new Node<>(data);
	}

	public ArvorePalavras(ConjuntoPalavraFrequencia data,
			ArvorePalavras<ConjuntoPalavraFrequencia> esq,
			ArvorePalavras<ConjuntoPalavraFrequencia> dta) {
		this.raiz = new Node<ConjuntoPalavraFrequencia>(data, esq.raiz,
				dta.raiz);
	}

	public void inserePalavra(String palavra) {

		if (raiz.data == null) {
			raiz = new Node<ConjuntoPalavraFrequencia>(
					new ConjuntoPalavraFrequencia(palavra, 1));

		} else {
			Node<ConjuntoPalavraFrequencia> anterior = null;
			Node<ConjuntoPalavraFrequencia> nodeRaiz = raiz;
			int compare = 0;
			boolean goEsquerda = false;
			boolean goDireita = false;

			do {
				compare = nodeRaiz.data.compareTo(palavra);
				if (compare == 0) {
					nodeRaiz.data
							.setFrequencia(nodeRaiz.data.getFrequencia() + 1);
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
				anterior.dta = new Node<ConjuntoPalavraFrequencia>(
						new ConjuntoPalavraFrequencia(palavra, 1));
			} else if (goEsquerda) {
				anterior.esq = new Node<ConjuntoPalavraFrequencia>(
						new ConjuntoPalavraFrequencia(palavra, 1));
			}
		}
	}

	public int contaFrequencia(String palavra) {
		boolean encontrouPalavra = false;
		int compare = 0;
		Node<ConjuntoPalavraFrequencia> node = raiz;

		while (!encontrouPalavra) {
			compare = node.data.getPalavra().compareTo(palavra);
			if (compare == 0) {
				encontrouPalavra = true;
				return node.data.getFrequencia();
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
		ArrayList<String> lista = new ArrayList<>();
		Iterator it = new Iterador(raiz);
		System.out.println("swaeqq "+ it.next());
		
		while (it.hasNext()) {
			lista.add((String) it.next());
		}
		String aux = "";
		int index = 0;
		;
		if (palavra.compareTo(palavra2) < 0) {
			aux = palavra2;
		}
		

		return 0;
	}

	public ArrayList prefixo(String p) {
		ArrayList<String> lista = new ArrayList<>();
		ArrayList<String> lista2 = new ArrayList<>();
		Iterator it = new Iterador(raiz);

		while (it.hasNext()) {
			lista.add((String) it.next());
			
			
		}
		for (String s : lista) {
			if (s.startsWith(p)) {
				lista2.add(s);
			}
		}

		return lista2;
	}

	public void removePrimeira() {
		Node<ConjuntoPalavraFrequencia> node = raiz;
		Node<ConjuntoPalavraFrequencia> pai = node;
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
		ArrayList<String> newlista = new ArrayList<>();
		ArrayList<String> newlista2 = new ArrayList<String>();
		Iterator it = new Iterador(raiz);
		int max = 0;
		while (it.hasNext()) {
			newlista.add((String) (it.next()));
		}
		
		
		return newlista2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Iterator it = new Iterador(raiz);
		StringBuilder sb = new StringBuilder();
		while (it.hasNext()) {
			sb.append(it.next());
			sb.append("\n");
		}
		return sb.toString();
	}

	private class Node<E> {
		private E data;
		private Node esq;
		private Node dta;

		private Node() {
			this.data = null;
			this.dta = null;
			this.esq = null;
		}

		private Node(E data, Node esq, Node dta) {
			this.data = data;
			this.dta = dta;
			this.esq = esq;
		}

		private Node(E data) {
			this.data = data;
			this.dta = null;
			this.esq = null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */

		public String toString() {
			return data + "]";
		}

	}

	@Override
	public Iterator<String> iterator() {
		return new Iterador(raiz);

	}

	private class Iterador implements Iterator<String> {
		private Stack<Node<ConjuntoPalavraFrequencia>> stack;
		private Node<ConjuntoPalavraFrequencia> current;

		public Iterador(Node<ConjuntoPalavraFrequencia> copyRoot) {
			this.stack = new Stack<>();
			this.current = copyRoot;
			pushEsq(current);
		}

		private void pushEsq(Node<ConjuntoPalavraFrequencia> current) {
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
			Node<ConjuntoPalavraFrequencia> resultado = null;
			if (hasNext()) {
				resultado = stack.pop();
				pushEsq(resultado.dta);
			}
			return resultado.data.getPalavra().toString();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}

	}

}
