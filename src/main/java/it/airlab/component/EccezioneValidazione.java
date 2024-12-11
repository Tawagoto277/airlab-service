package it.airlab.component;

public class EccezioneValidazione extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private final String CAMPO;
	private final String MESSAGGIO;
	
	public EccezioneValidazione(String c, String m) {
		CAMPO = c;
		MESSAGGIO = m;
	}

	public String getCAMPO() {
		return CAMPO;
	}

	public String getMESSAGGIO() {
		return MESSAGGIO;
	}
}
