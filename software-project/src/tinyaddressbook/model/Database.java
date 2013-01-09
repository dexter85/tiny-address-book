package tinyaddressbook.model;


import java.io.File;
import java.sql.*;

import org.sqlite.SQLiteConfig;

public class Database 
{
	public static String fileName = "database.sqlite";
	Connection conn;
	
	public Database() 
	{
		
		boolean firstInser;
		
		//Verifico se è presente il file del database
		if(false == new File(fileName).isFile())
			firstInser = true;
		else
			firstInser = false;
		
		SQLiteConfig config = new SQLiteConfig();
		config.enableRecursiveTriggers(true);

		
		//Verifico il driver
		if(checkDriver())
		{
	        try
	        {
	        	//Connessione
	            conn = DriverManager.getConnection("jdbc:sqlite:"+fileName, config.toProperties());
	        } 
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	            System.exit(1);
	        }
		}
		
		if(true == firstInser)
		{
			setStructure();
			setDeveloperInser();
		}
	}
	
    
	/**
	 * 
	 * Verifica se è  presente il driver per sqlite all'interno del progetto
	 * 
	 * @return {@link Boolean}
	 */
    private boolean checkDriver()
    {
        try{
            Class.forName("org.sqlite.JDBC");}
        catch(ClassNotFoundException e){
            System.out.println("Driver sqlite non caricato");
            return false;
        }
        return true;
    }
    
    
    private void setStructure()
    {
    	System.out.println("Creazione Struttura");

        try{
            Statement stat = conn.createStatement();
            
            //Creazione delle tabelle nel database
            stat.executeUpdate("CREATE TABLE calendar__info (id_calendar INTEGER PRIMARY KEY, id_tag NUMERIC, start NUMERIC, end NUMERIC, title TEXT, description TEXT);");
            stat.executeUpdate("CREATE TABLE calendar__people (id_calendar_people INTEGER PRIMARY KEY, id_calendar NUMERIC, id_people NUMERIC);");
            stat.executeUpdate("CREATE TABLE people__info (id_people INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT, gender TEXT, born TEXT, town TEXT, street TEXT, street_number TEXT);");
            stat.executeUpdate("CREATE TABLE people__phones (id_phone INTEGER PRIMARY KEY, id_people NUMERIC, id_tag NUMERIC, phone TEXT);");
            stat.executeUpdate("CREATE TABLE people__emails (id_email INTEGER PRIMARY KEY, id_people NUMERIC, id_tag NUMERIC, email TEXT);");
            stat.executeUpdate("CREATE TABLE system__tags (id_tag INTEGER PRIMARY KEY, tag TEXT);");

            //Creazione delle view
            stat.executeUpdate("CREATE VIEW people__phones_view AS"+
								"SELECT"+ 
									"people__phones.id_phone, people__phones.id_people, (people__phones.id_tag) AS id_tag_phone, (system__tags.tag) AS tag_phone, phone"+
								"FROM"+ 
									"people__phones"+
								"LEFT OUTER JOIN system__tags ON people__phones.id_tag = system__tags.id_tag ;");
            
            //Creazione degli indici
            stat.executeUpdate("CREATE TRIGGER delete_people__info"+
								"BEFORE DELETE ON people__info"+
								"FOR EACH ROW BEGIN"+
									"DELETE FROM  people__emails WHERE people__emails.id_people = OLD.id_people;"+
									"DELETE FROM  people__phones WHERE people__phones.id_people = OLD.id_people;"+
									"DELETE FROM  calendar__people WHERE calendar__people.id_people = OLD.id_people;");


            

            //Creazione delle triggers
//            stat.executeUpdate("");
            
            
            
            conn.close();
            stat.close();
    }
    catch(SQLException e){
        e.getErrorCode();
        }
    }
    
    /**
     * Inserisci un po' di dati per lo sviluppo
     */
    private void setDeveloperInser()
    {

        try
        {
            Statement stat = conn.createStatement();
            for(int i = 0;i<50;i++)
            {
            stat.executeUpdate("INSERT INTO people__info " +
            											"(first_name, last_name, gender, born, town, street, street_number)"+
            									"VALUES " +
            											"('name "+i+"','surname "+i+"','m','1','city','address','nn')");
            }
        }
        catch(SQLException e){
            e.getErrorCode();
         }
    }

}
