package entites;

public class GeradorDeLinhas {
	private StringBuilder sb = new StringBuilder();
	public GeradorDeLinhas(String sb) {
		this.sb.append(sb);
	}
	public void setAdicionar(String sb) {
		this.sb.append(sb);
	}
	public String getLinha() {
		return this.sb.toString();
		
	}
}
