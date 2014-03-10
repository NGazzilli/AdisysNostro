package adisys.server.business_tier;

import java.util.ArrayList;
import java.util.Date;

import adisys.server.strumenti.DateFormatConverter;

public class InterventoCompleto extends Intervento {
	
	//Enumerazioni per verifiche
	public enum StatoVerifica{nonVerificato, verificaOK, anomalia}
	
	//Oggetti membro
	private Paziente paziente;
	private Infermiere infermiere;

	private String misuraRilevata;
	private ArrayList<Rilevazione> log;
	private StatoVerifica statoVerificaGPS;
	private StatoVerifica statoVerificaAccelerometro;
	
	private String note;
	
	public InterventoCompleto() {

		log = new ArrayList<Rilevazione>();
		statoVerificaGPS=StatoVerifica.nonVerificato;
		statoVerificaAccelerometro=StatoVerifica.nonVerificato;
	}

	public Paziente getPaziente()
	{
		return paziente;
	}
	
	public Infermiere getInfermiere()
	{
		return infermiere;
	}

	public Rilevazione getLog(int indice)
	{
		if ( indice >= 0 && indice < contaLog())
			return log.get(indice);
		else
		{
			System.out.println("InterventoCompleto -> Tentativo di prelievo di UnitaLog fuori dai limiti della lista.");
			return null;
		}
	}

	public String getMisuraRilevata() {
		return misuraRilevata;
	}

	public StatoVerifica getStatoVerificaGPS() {
		return statoVerificaGPS;
	}

	public StatoVerifica getStatoVerificaAccelerometro() {
		return statoVerificaAccelerometro;
	}

	public boolean addLog(Rilevazione u)
	{
		return log.add(u);
	}

	public int contaLog()
	{
		return log.size();
	}

	public boolean setPaziente(Paziente newPaziente)
	{
		paziente=newPaziente;
		return true;
	}

	public boolean setInfermiere(Infermiere newInfermiere)
	{
		infermiere=newInfermiere;
		return true;
	}

	public boolean setMisuraRilevata(String newMisuraRilevata)
	{
		misuraRilevata=newMisuraRilevata;
		return true;
	}

	public void setStatoVerificaGPS(StatoVerifica statoVerificaGPS) {
		this.statoVerificaGPS = statoVerificaGPS;
	}

	public void setStatoVerificaAccelerometro(
			StatoVerifica statoVerificaAccelerometro) {
		this.statoVerificaAccelerometro = statoVerificaAccelerometro;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString()
	{
		String stringaIntervento= "Intervento numero " + getID();
		stringaIntervento += "\nInfermiere n." + getIDInfermiere();
		stringaIntervento += "\n" + getPaziente().toString();
                
		stringaIntervento += "\n- Data intervento: " +getDataDaFormato("dd/MM/yyyy");
		stringaIntervento += "\n- Ora inizio intervento: " + getOraInizioDaFormato("HH:mm");
		//stringaIntervento += "\n- Ora fine intervento: " + getOraFineDaFormato("HH:mm");
		stringaIntervento += "\n- Indirizzo : " + getCivico();
		stringaIntervento += "\n- Città: " + getCitta() + " - CAP " + getCap();
		stringaIntervento += "\nTipi di intervento: ";
		stringaIntervento += "\n";
		for(TipoIntervento t: tipiIntervento)
			stringaIntervento += "\n" + t.toString();
        stringaIntervento += "\nNote rilevazioni: " + getNote();
		
		return stringaIntervento;
	}
}
