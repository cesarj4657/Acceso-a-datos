import java.util.Objects;

public class Ordenar implements Comparable<Ordenar>{
	String palabra=" ";
	int repetidas=0;
	
	Ordenar (String palabra,int repetidas){
		this.palabra=palabra;
		this.repetidas=repetidas;
	}

	public String getPalabra() {
		return palabra;
	}

	@Override
	public int hashCode() {
		return Objects.hash(palabra, repetidas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordenar other = (Ordenar) obj;
		return Objects.equals(palabra, other.palabra) && repetidas == other.repetidas;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	public int getRepetidas() {
		return repetidas;
	}

	public void setRepetidas(int repetidas) {
		this.repetidas = repetidas;
	}


	public int compareTo(Ordenar o) {
		return o.getRepetidas()-this.repetidas;
	}
}
