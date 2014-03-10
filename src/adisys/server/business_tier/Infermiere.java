/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adisys.server.business_tier;

/**
 *
 * @author Nicola
 */
public class Infermiere implements Infermiere_I {
	//Costanti di vincolo
	/**@category costanti */
	private static final int LUNGHEZZAMASSIMAID = 5;
	private static final int LUNGHEZZAMASSIMANOME = 20;
	private static final int LUNGHEZZAMASSIMACOGNOME = 20;
	
    private int ID;
    private String nome;
    private String cognome;

    public static String verificaCoerenzaID(String newID){
    	String errLog="";
    	
    	//ID NON VUOTO
    	if (newID.isEmpty()) errLog += "\nID Infermiere non selezionato";
    	
    	//Controllo lunghezza massima
    	if (newID.length()>LUNGHEZZAMASSIMAID) errLog += "\nL'ID dell'infermiere eccede la lunghezza massima consentita";
    	
    	//TODO controllo ID numerico - Saltato, operazione effettuata in automatico nell'interfaccia
    	return errLog;
    	}
    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param ID the ID to set
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
    
    /**Verifica coerenza del nome
     * 
     * @param nomeProva - il nome da controllare
     * @return una stringa vuota se la verifica è andata a buon fine, la stringa di errore nel caso opposto
     */
    public static String verificaNome(String nomeProva)
    {
    	String risultato=new String();
    	
    	//Verifica non vuoto
    	if (nomeProva.isEmpty()) risultato += "\n- il nome è vuoto";
    	
    	//Verifica lunghezza
    	if (nomeProva.length() > LUNGHEZZAMASSIMANOME) risultato += "\n- il nome eccede i " + LUNGHEZZAMASSIMANOME + " caratteri";
    	
    	//Restituzione risultato verifica
    	return risultato;
    	
    }
    
    /**Verifica coerenza del Cognome
     * 
     * @param cognomeProva - il cognome da controllare
     * @return una stringa vuota se la verifica è andata a buon fine, la stringa di errore nel caso opposto
     */
    public static String verificaCognome(String cognomeProva)
    {
    	String risultato=new String();
    	
    	//Verifica non vuoto
    	if (cognomeProva.isEmpty()) risultato += "\n- il cognome è vuoto";
    	
    	//Verifica lunghezza
    	if (cognomeProva.length() > LUNGHEZZAMASSIMACOGNOME) risultato += "\n- il cognome eccede i " + LUNGHEZZAMASSIMACOGNOME + " caratteri";
    	
    	//Restituzione risultato verifica
    	return risultato;
    	
    }
    @Override
    public String toString()
    {
    	String stringaInfermiere= new String();
    	stringaInfermiere += "ID Infermiere: " + getID();
    	stringaInfermiere += "\n- Nome: " + getNome();
    	stringaInfermiere += "\n- Cognome: " + getCognome();

    	return stringaInfermiere;
    }
}
