/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adisys.server.business_tier;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import adisys.server.strumenti.DateFormatConverter;

/**
 *
 * @author Nicola
 */
public class Intervento {
	//TODO verificate codice - Costanti di formato
	private static final String formatoDataInput = "dd/MM/yyyy";
	private static final String formatoOraInput = "HH.mm";

	//TODO Dati disponibili per il parsing (decommentare in caso di utilizzo)
	//private static final String formatoDataOraPianificazione = "dd/MM/yyyy#HH.mm";
	//private static final String formatoTimestampLOG ="yyyy-MM-dd HH:mm";

	//Costanti di vincolo
	private static final int lunghezzaMassimaID = 6;
	private static final int lunghezzaMassimaNome = 20;
	private static final int lunghezzaMassimaCognome = 20;
	private static final int lunghezzaMassimaCitta = 30;
	private static final int lunghezzaMassimaCivico = 30;
	private static final int lunghezzaMassimaCap = 5; //Nuova vers 2
	private static final int lunghezzaMassimaTipo = 300;
	private static final int lunghezzaMassimaNote = 300;	

	//Campi
	private int ID;
	private int IDPaziente;
	private int IDInfermiere;

	private Date data;
	private Time oraInizio;
	private Time oraFine;

	private String citta;
	private String civico;
	private String cap;

	protected ArrayList<TipoIntervento> tipiIntervento;

	public Intervento()
	{
		//TODO Verifica aggiornamento costruttore
		data= new Date(0);
		oraInizio = new Time(0);
		oraFine = new Time(0); 
		tipiIntervento = new ArrayList<>();
	}

	public int getID() { 
		return ID; 
	}

	public int getIDPaziente() {
		return IDPaziente;
	}

	public int getIDInfermiere() {
		return IDInfermiere;
	}

	public String getData()
	{
		return data.toString();
	}

	public String getOraInizio()
	{
		return oraInizio.toString();
	}
	public String getOraFine()
	{
		return oraFine.toString();
	}

	public String getCitta() {
		return citta;
	}

	public String getCivico() {
		return civico;
	}

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	public String setID(String ID) {

		String errore="";

		//Caso stringa vuota
		if (ID.isEmpty()) errore+= "\nID intervento non specificato";
		else
		{
			this.ID = Integer.valueOf(ID);
		}

		//Restituzione stringa
		return errore;
	}

	public void setID(int newID)
	{
		this.ID = newID;
	}

	public void setIDPaziente(int IDPaziente) {
		this.IDPaziente = IDPaziente;
	}

	public String setIDPaziente(String newIDPaziente) {

		String errore="";

		//Caso stringa vuota
		if (newIDPaziente.isEmpty()) errore+= "\nID paziente non specificato";
		else
		{
			this.IDPaziente = Integer.valueOf(newIDPaziente);
		}

		//Restituzione stringa
		return errore;
	}

	public void setIDInfermiere(int IDInfermiere) {
		this.IDInfermiere = IDInfermiere;
	}

	public String setIDInfermiere(String newIDInfermiere) {

		String errore="";

		//Caso stringa vuota
		if (newIDInfermiere.isEmpty()) errore+= "\nID infermiere non specificato";
		else
		{
			this.IDInfermiere = Integer.valueOf(newIDInfermiere);
		}

		//Restituzione stringa
		return errore;
	}

	public boolean setData(String nuovaData) {
		try {
			//Tentativo di parsing
			SimpleDateFormat sdf = new SimpleDateFormat(formatoDataInput);
			data.setTime(sdf.parse(nuovaData).getTime());
			return true;
		} catch (ParseException e) {

			//Caso fallimento parsing
			e.printStackTrace();
			System.out.println("PARSING DATA FALLITO");
			return false;

		}
	}

	public boolean setOraInizio(String nuovaOraInizio) {
		try {
			//Tentativo di parsing
			SimpleDateFormat sdf = new SimpleDateFormat(formatoOraInput);
			oraInizio.setTime(sdf.parse(nuovaOraInizio).getTime());
			return true;
		} catch (ParseException e) {

			//Caso fallimento parsing
			e.printStackTrace();
			System.out.println("PARSING ORA INIZIO INTERVENTO FALLITO");
			return false;

		}
	}
	public boolean setOraFine(String nuovaOraInizio) {
		try {
			//Tentativo di parsing
			SimpleDateFormat sdf = new SimpleDateFormat(formatoOraInput);
			oraInizio.setTime(sdf.parse(nuovaOraInizio).getTime());
			return true;
		} catch (ParseException e) {

			//Caso fallimento parsing
			e.printStackTrace();
			System.out.println("PARSING ORA INIZIO INTERVENTO FALLITO");
			return false;

		}
	}
	public void setDataFmt(String strData, String formatoIngresso)
	{
		data.setTime(DateFormatConverter.dateString2long(strData, formatoIngresso) );
	}

	public void setOraInizioFmt(String strOra, String formatoIngresso)
	{
		oraInizio.setTime(DateFormatConverter.dateString2long(strOra, formatoIngresso) );
	}

	public void setOraFineFmt(String strOra, String formatoIngresso)
	{
		oraFine.setTime(DateFormatConverter.dateString2long(strOra, formatoIngresso) );
	}



	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	/*
	public String setTimestampDaStringhe(String data, String ora)
	{
		String errore="";
		//Controllo stringhe vuote
		if (data.isEmpty()) errore+="Data vuota";
		if (ora.isEmpty()) errore+=",Ora vuota";

		//Se non ci sono stati errori tenta il parsing e la scrittura del timestamp
		if (errore.isEmpty())
		{

			try {
				//Tentativo di parsing
				String stringaTimeStamp = data + " " + ora;
				SimpleDateFormat sdf = new SimpleDateFormat(formatoDataOraInput);
				dataOra.setTime(sdf.parse(stringaTimeStamp).getTime() );

			} catch (ParseException e) {

				//Caso fallimento parsing
				e.printStackTrace();

				//Aggiunge informazioni alla stringa di errore
				errore+="Parsing data fallito. Formato data non valido.";
				return errore;
			}

		}
		return null;
	}
	 */

	public static String verificaCoerenzaID(String newID){
		String errLog="";

		//ID NON VUOTO
		if (newID.isEmpty()) errLog += "\nIntervento non selezionato";

		//Controllo lunghezza massima
		if (newID.length()>lunghezzaMassimaID) errLog += "\nL'ID dell'intervento eccede la lunghezza massima consentita";

		//TODO controllo ID numerico - Saltato, operazione effettuata in automatico nell'interfaccia
		return errLog;


	}
	public static String verificaValiditaNome(String newNome){
		String errLog="";

		//NON VUOTO
		if (newNome.isEmpty()) errLog += "\nNome vuoto";

		//Controllo lunghezza massima
		if (newNome.length()>lunghezzaMassimaNome) errLog += "\nNome troppo lungo (Max "+ lunghezzaMassimaNome+" caratteri)";

		//TODO controllo caratteri speciali


		return errLog;

	}

	public static String verificaValiditaCognome(String newCognome){
		String errLog="";

		//NON VUOTO
		if (newCognome.isEmpty()) errLog += "\nCognome vuoto";

		//Controllo lunghezza massima
		if (newCognome.length()>lunghezzaMassimaCognome) errLog += "\nCognome troppo lungo (Max "+ lunghezzaMassimaCognome+" caratteri)";

		//TODO controllo caratteri speciali

		return errLog;

	}

	public static String verificaValiditaCitta(String newCitta){
		String errLog="";

		//NON VUOTO
		if (newCitta.isEmpty()) errLog += "\nNessuna citta' specificata";

		//Controllo lunghezza massima
		if (newCitta.length()>lunghezzaMassimaCitta) errLog += "\nNome citta' troppo lungo (Max "+ lunghezzaMassimaCitta+" caratteri)";

		//TODO controllo caratteri speciali

		return errLog;
	}
	public static String verificaValiditaCivico(String newCivico){

		String errLog="";

		//NON VUOTO
		if (newCivico.isEmpty()) errLog += "\nIndirizzo mancante";

		//Controllo lunghezza massima
		if (newCivico.length()>lunghezzaMassimaCivico) errLog += "\nIndirizzo troppo lungo (Max "+ lunghezzaMassimaCivico+" caratteri)";

		//TODO controllo caratteri speciali

		return errLog;
	}

	public static String  verificaValiditaCap(String newCap)
	{
		String errLog="";

		//NON VUOTO
		if (newCap.isEmpty()) errLog += "\nCAP mancante";

		//Controllo lunghezza massima
		if (newCap.length()>lunghezzaMassimaCap) errLog += "\nCAP troppo lungo (Max "+ lunghezzaMassimaCap+" caratteri)";

		//controllo caratteri (già fatto nell'interfaccia


		return errLog;

	}
	public static String verificaCoerenzaTipi(ArrayList<TipoIntervento> array)
	{
		String errLog="";

		//Controllo array non vuoto
		if(array.size()<1) errLog += "\nNessun tipo di intervento specificato";

		for(int i=0; i<array.size(); i++ )
		{
			//Lettura elemento corrente
			TipoIntervento t= array.get(i);
			
			//TODO Controllo nome SQL non nullo
			if (t.getNome() == "null") errLog += "\nTipo intervento n." + String.valueOf(i+1) + ", non valido, nome vuoto o con valore \"null\".";

			//TODO Controllo assenza duplicati
			for(int j=i+1;j<array.size();j++) 
				if(t.getNome().equals(array.get(j).getNome()))
					errLog += "\nTipo di intervento "+ t.getNome() + " duplicato, correggere.";

			//Controllo nome e tipo validi
			errLog += Intervento.verificaValiditaTipo(t.getNome());
			errLog += Intervento.verificaValiditaNote(t.getNote());
		}
		return errLog;
	}

	public static String verificaValiditaTipo(String newTipo){
		String errLog="";

		//NON VUOTO
		if (newTipo.isEmpty()) errLog += "\nTipo intervento non specificato";

		//Controllo lunghezza massima
		if (newTipo.length()>lunghezzaMassimaTipo) errLog += "\nTipo intervento troppo lungo (Max "+ lunghezzaMassimaTipo+" caratteri)";

		//TODO controllo caratteri speciali

		return errLog;

	}
	public static String verificaValiditaNote(String newNote){

		String errLog="";

		//Controllo lunghezza massima
		if (newNote.length()>lunghezzaMassimaNote) errLog += "\nNote troppo lunghe (Max "+ lunghezzaMassimaNote+" caratteri)";

		//TODO controllo caratteri speciali
		return errLog;
	}

	/*
	public static String verificaValiditaDataOra(String newData,String newOra){
		SimpleDateFormat formato= new SimpleDateFormat(formatoDataOraInput);

		String newDataOra = newData + " "+ newOra +".00";

		String errLog="";

		try {
			formato.parse(newDataOra);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
			errLog += "\nImpossibile effettuare il parsing di data e ora (formato GG/MM/AAAA e HH.MM)";
		}
		return errLog;
	}
	 */

	public static String verificaValiditaData(String data)
	{
		SimpleDateFormat formato= new SimpleDateFormat(formatoDataInput);

		String errLog="";

		try {
			formato.parse(data);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
			errLog += "\nImpossibile effettuare il parsing della data <<" + data + ">> (non è nel formato " + formatoDataInput + ")";
		}
		return errLog;		
	}

	public static String verificaValiditaOra(String ora)
	{
		SimpleDateFormat formato= new SimpleDateFormat(formatoOraInput);

		String errLog="";

		try {
			formato.parse(ora);
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
			errLog += "\nImpossibile effettuare il parsing dell'ora <<" + ora + ">> (non è nel formato " + formatoOraInput + ")";
		}
		return errLog;
	}

	/**Restituisce data dell'intervento in un formato specifico (es. "dd/MM/yyyy")
	 *
	 */
	public String getDataDaFormato(String formatoData) {
		return DateFormatConverter.long2dateString(data.getTime(), formatoData);
	}

	/**Restituisce l'ora di inizio dell'intervento in un formato specifico (es. "hh:mm:ss")
	 *
	 */
	public String getOraInizioDaFormato(String formatoOra) {
		return DateFormatConverter.long2dateString(oraInizio.getTime(), formatoOra);
	}

	/**Restituisce l'ora di fine dell'intervento in un formato specifico (es. "hh:mm:ss")
	 *
	 */
	public String getOraFineDaFormato(String formatoOra) {
		return DateFormatConverter.long2dateString(oraFine.getTime(), formatoOra);
	}


	public void addTipoIntervento (TipoIntervento nuovoTipoIntervento)
	{
		tipiIntervento.add(nuovoTipoIntervento);
	}

	public void removeTipoIntervento(int indice)
	{
		tipiIntervento.remove(indice);
	}

	public void cancellaTipiIntervento()
	{
		tipiIntervento.clear();
	}

	public int countTipInterventi()
	{
		return tipiIntervento.size();
	}

	public TipoIntervento getTipoIntervento(int n)
	{
		try{
			return tipiIntervento.get(n);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public String toString()
	{
		String stringaIntervento= "Intervento numero " + getID();
		stringaIntervento += "\n- Infermiere assegnato n." + getIDInfermiere();
		stringaIntervento += "\n- Paziente n." + getIDPaziente();
		stringaIntervento += "\n- Data intervento: " +getDataDaFormato("dd/MM/yyyy");
		stringaIntervento += "\n- Ora inizio intervento: " + getOraInizioDaFormato("HH:mm");
		//stringaIntervento += "\n- Ora fine intervento: " + getOraFineDaFormato("HH:mm");
		stringaIntervento += "\n- Indirizzo : " + getCivico();
		stringaIntervento += "\n- Città: " + getCitta() + " - CAP " + getCap();
		stringaIntervento += "\nTipi di intervento: ";
		stringaIntervento += "\n";
		for(TipoIntervento t: tipiIntervento)
			stringaIntervento += "\n" + t.toString();
		return stringaIntervento;
	}
}
