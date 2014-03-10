
package adisys.server.business_tier;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;

import adisys.server.strumenti.DateFormatConverter;

/**Struttura per il passaggio dei dati di un singolo paziente
 *
 * @author Francesco Colella
 */
public class Paziente {
	//Costanti di vincolo (statiche)
	private static final int LUNGHEZZA_MASSIMA_ID = 5;
	private static final int LUNGHEZZA_MASSIMA_NOME = 20;
	private static final int LUNGHEZZA_MASSIMA_COGNOME = 20;
	private static final int LUNGHEZZA_MASSIMA_CELLULARE = 10;
	
	//Variabili di istanza
    private int ID;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private ArrayList<String> cellulari;    //Modifica Ver 2: sostituisce "cellulare"
    private String patologia;


    public Paziente()
    {
    	cellulari=new ArrayList<String>();
    }
    
    
    public static String verificaCoerenzaID(String newID){
    	String errLog="";
    	
    	//ID NON VUOTO
    	if (newID.isEmpty()) errLog += "\nID Paziente vuoto";
    	
    	//Controllo lunghezza massima
    	if (newID.length()>LUNGHEZZA_MASSIMA_ID) errLog += "\nL'ID del paziente eccede la lunghezza massima consentita";
    	
    	//TODO controllo ID numerico - Saltato, operazione effettuata in automatico nell'interfaccia
    	return errLog;
    	}

    public static String verificaNome(String nome)
    {
    	String errLog="";
    	
    	//NON VUOTO
    	if (nome.isEmpty()) errLog += "\nNome Paziente vuoto";
    	
    	//Controllo lunghezza massima
    	if (nome.length()>LUNGHEZZA_MASSIMA_NOME) errLog += "\nIl nome del paziente eccede la lunghezza massima consentita (" + LUNGHEZZA_MASSIMA_NOME + ")";
    	
    	return errLog;
    }
    
    public static String verificaCognome(String cognome)
    {
    	String errLog="";
    	
    	//NON VUOTO
    	if (cognome.isEmpty()) errLog += "\nCognome Paziente vuoto";
    	
    	//Controllo lunghezza massima
    	if (cognome.length()>LUNGHEZZA_MASSIMA_COGNOME) errLog += "\nIl cognome del paziente eccede la lunghezza massima consentita (" + LUNGHEZZA_MASSIMA_COGNOME + ")";
    	
    	return errLog;
    }
    public static String verificaDataNascita(String dataNascita, String formato)
    {
    	String errLog="";
    	
    	
    	if (dataNascita.isEmpty()) 
    		errLog += "\nData di nascita non specificata";
    	else if(!DateFormatConverter.parseable(dataNascita, formato))
    		errLog += "\nFormato data di nascita non valido"; 
    	
    	return errLog;
    }
    
    public static String verificaCellulare(String cellulare)
    {
    	String errLog="";
    	
    	//NON VUOTO
    	if (cellulare.isEmpty()) errLog += "\nNumero di cellulare vuoto";
    	//Controllo lunghezza massima
    	if (cellulare.length()>LUNGHEZZA_MASSIMA_CELLULARE) errLog += "\nIl nomero di cellulare del paziente eccede la lunghezza massima consentita (" + LUNGHEZZA_MASSIMA_CELLULARE + ")";
    	
    	return errLog;
    	  	
    }
    
    public static String verificaPatologia(String patologia){
		
		String errLog="";
    	
    	//NON VUOTO
    	if (patologia.isEmpty()) errLog += "\nNessuna patologia";
    	
    	return errLog;
		
	}
    
    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @return Il nome del paziente
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return il cognome del paziente
     */
    public String getCognome() {
        return cognome;
    }

    public String getDataNascita(String formato) {
		if (dataNascita!=null)
			return DateFormatConverter.long2dateString(dataNascita.getTime(), formato);
		else return "";
	}

	/**Modificato in versione 2 da getCellulare a getCellulari
	 * Metodo che restituisce un array di numeri di telefono (Qbject[] che contiene stringhe)
     * @return il numero di cellulare del paziente
     */
    public Object[] getCellulari() {
        return (cellulari.toArray());
    }
    
    public String getPatologia(){
    	return patologia;
    }

    /**Aggiunta in versione 2: Cancellazione numeri di telefono del paziente
     * 
     */
    public void clearCellulari()
    {
    	cellulari.clear();
    }
    
    /**
     * @param ID il nuovo ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param cognome the cognome to set
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    public void setPatologia(String patologia){
    	this.patologia = patologia;
    }

    /**Aggiunta numero cellulare (sostituisce setCellulare nella vers.2)
     * @param cellulare the cellulare to set
     */
    public void addCellulare(String cellulare) {
        this.cellulari.add(cellulare);
    }

    public void setDataNascita(String newDataNascita , String formato) {

        dataNascita = new Date(DateFormatConverter.dateString2long(newDataNascita, formato));
    }

    public String calcolaAnniMesi()
    {
    	GregorianCalendar today = new GregorianCalendar();
    	GregorianCalendar nascita = new GregorianCalendar();
    	nascita.setTimeInMillis(dataNascita.getTime());
    	
    	int yearDiff  = today.get(Calendar.YEAR) - nascita.get(Calendar.YEAR); 
		int monthDiff = (yearDiff * 12 + today.get(Calendar.MONTH) - nascita.get(Calendar.MONTH)) %12;
    	
    	String formatoprovvisorio = "%02d/%02d";
    	Formatter formatter = new Formatter();
    	formatter.format(formatoprovvisorio, yearDiff,monthDiff);
    	
    	System.out.println("Pazinte -> Elaborazione Anni/mesi: " + formatter.toString());
    	return formatter.toString();

    }

    @Override
    public String toString()
    {
    	String stringaPaziente= new String();
    	stringaPaziente += "ID Paziente: " + getID();
    	stringaPaziente += "\n- Nome: " + getNome();
    	stringaPaziente += "\n- Cognome: " + getCognome();
    	stringaPaziente += "\n- Data di nascita: " + getDataNascita("dd/MM/yyyy");
    	for(String c:cellulari)
    		stringaPaziente += "\n- Numero di cellulare: " + c;
    	return stringaPaziente;
    }
}
