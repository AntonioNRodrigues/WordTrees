import java.util.Comparator;

/**
 * 
 */

/**
 * @author antonio
 * 
 */
public class ConjuntoPalavraFrequencia implements Comparable<String> {
	private String palavra;
	private int frequencia;

	/**
	 * @param palavra
	 * @param frequencia
	 */
	public ConjuntoPalavraFrequencia(String palavra, int frequencia) {
		this.palavra = palavra;
		this.frequencia = frequencia;
	}

	/**
	 * @return the palavra
	 */

	public String getPalavra() {
		return palavra;
	}

	/**
	 * @param palavra
	 *            the palavra to set
	 */
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	/**
	 * @return the frequencia
	 */
	public int getFrequencia() {
		return frequencia;
	}

	/**
	 * @param frequencia
	 *            the frequencia to set
	 */
	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return palavra +" ["+ frequencia;
	}

	@Override
	public int compareTo(String o) {
		return this.getPalavra().compareTo(o);
	}

}
