package adisys.server.strumenti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DateFormatConverter {
	
	/**Cambia il formato di una stringa contenente una data
	 * 
	 * @param data - Stringa nel formato GG/MM/AAAA (in java dd/MM/yyyy)
	 * @param ora - Strigna nel formato HH.mm
	 * @return restituisce un Timestamp in caso di successo del parsing, 
	 * altrimenti <b>null</b> 
	 * @category utility
	 */
	public static String cambiaFormato(String dataOra, String formatoIngresso, String formatoUscita)
	{
		SimpleDateFormat dataOraInput = new SimpleDateFormat(formatoIngresso);
		SimpleDateFormat dataOraOutput = new SimpleDateFormat(formatoUscita);
		
		try {
			
			//trace
			System.out.format("\nFormattazione stringa '%s' da formato '%s' a '%s'" , dataOra, formatoIngresso, formatoUscita);
			
			java.util.Date data = dataOraInput.parse(dataOra);
			String stringaUscita = dataOraOutput.format(data);
			
			//trace
			System.out.format("\nStringa ottenuta: '%s'", stringaUscita);
			
			return stringaUscita;
			
		} catch (ParseException e) {
			
			//Avviso parsing fallito
			System.out.println("DateFormatConverter -> ERRORE nel parsing. Controllare i formati delle date");
			
			e.printStackTrace();
			return null;
		}
	}
	
	public static long dateString2long(String dataOra, String formatoIngresso)
	{
                
		SimpleDateFormat dataOraInput = new SimpleDateFormat(formatoIngresso);
		
		try {
                        //Trace
                        System.out.println();
                        System.out.println("Inserimento data/ora " + dataOra + " nel formato: " + formatoIngresso);
			
                        java.util.Date data = dataOraInput.parse(dataOra);
			
                        //Trace
                        System.out.println("Parsing effettuato, data ottenuta: " + data.toGMTString());
			
                        return data.getTime();
			
			
		} catch (ParseException e) {
			//Avviso parsing fallito
			System.out.println("DateFormatConverter -> ERRORE nel parsing. Controllare i formati delle date");
			
			e.printStackTrace();
			return 0;
		}
	}
	
	public static String long2dateString(long dataOra, String formatoUscita)
	{
		//Creazione formattatore
		SimpleDateFormat dataOraOutput = new SimpleDateFormat(formatoUscita);
		
		//Creazione oggetto data
		java.util.Date data= new java.util.Date(dataOra);
		
		//Formattazione e output
		String stringaUscita = dataOraOutput.format(data);
		
		return stringaUscita;
	}
	
	public static boolean parseable(String dataOra, String formato)
	{
		SimpleDateFormat dataOraInput = new SimpleDateFormat(formato);

		try {
			dataOraInput.parse(dataOra);
			return true;


		} catch (ParseException e) {
			//Avviso parsing fallito
			System.out.println("DateFormatConverter -> ERRORE nel parsing. Controllare i formati delle date");
			e.printStackTrace();
			return false;
		}
	}
	
	public static long oggi()
	{
		GregorianCalendar today= new GregorianCalendar();
		return today.getTimeInMillis();
	}
}
