package adisys.server.business_tier;

import java.util.Date;

public class TipoIntervento 
{
	private String nome;
	private String valoreRilevato;
	private String tempoIntervento;
	private String note;
	
	public TipoIntervento()
	{
		nome="";
		valoreRilevato="";
		tempoIntervento="";
		note="";
	}
	
	public TipoIntervento(String newNome, String newNote)
	{
		nome=newNome;
		valoreRilevato="";
		tempoIntervento="";
		note=newNote;
	}
	
                
	public String getNome() {
		return nome;
	}
	
	public String getValoreRilevato() {
		return valoreRilevato;
	}
	
	public String getTempoIntervento() {
		return tempoIntervento;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setValoreRilevato(String valoreRilevato) {
		this.valoreRilevato = valoreRilevato;
	}
	
	public void setTempoIntervento(String tempoIntervento) {
		this.tempoIntervento = tempoIntervento;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public String toString()
	{
		String stringaTipo = "Tipo intervento: "+ getNome();
		stringaTipo += "\n Valore rilevato: "+ getValoreRilevato(); 
		stringaTipo += "\n Tempo intervento: "+ getTempoIntervento();
		stringaTipo += "\n Note: "+ getNote();
		
		return stringaTipo;
	}
}
