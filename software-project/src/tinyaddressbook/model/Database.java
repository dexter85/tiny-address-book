package tinyaddressbook.model;


import java.io.File;
import java.sql.*;

public class Database 
{
	public String fileName = "D:\\database.sqlite";
	Connection conn;
	
	public Database() 
	{
		
		boolean firstInser;
		
		//Verifico se è presente il file del database
		if(false == new File(fileName).isFile())
			firstInser = true;
		else
			firstInser = false;
		
		//Verifico il driver
		if(checkDriver())
		{
	        try
	        {
	        	//Connessione
	            conn = DriverManager.getConnection("jdbc:sqlite:"+fileName);
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
            stat.executeUpdate("CREATE TABLE calendar__info (id_calendar INTEGER PRIMARY KEY, id_tag NUMERIC, start NUMERIC, end NUMERIC, title TEXT, description TEXT);");
            stat.executeUpdate("CREATE TABLE calendar__people (id_calendar_people INTEGER PRIMARY KEY, id_calendar NUMERIC, id_people NUMERIC);");
            stat.executeUpdate("CREATE TABLE people__info (id_people INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT, gender TEXT, born TEXT, town TEXT, street TEXT, street_number TEXT);");
            stat.executeUpdate("CREATE TABLE people__phones (id_phone INTEGER PRIMARY KEY, id_people NUMERIC, id_tag NUMERIC, phone TEXT);");
            stat.executeUpdate("CREATE TABLE people__emails (id_email INTEGER PRIMARY KEY, id_people NUMERIC, id_tag NUMERIC, email TEXT);");
            stat.executeUpdate("CREATE TABLE system__tags (id_tag INTEGER PRIMARY KEY, tag TEXT);");
            
            conn.close();
            stat.close();
    }
    catch(SQLException e){
        e.getErrorCode();
        }
    }
    
    
    private void setDeveloperInser()
    {
//    	PreparedStatement preparedStatement = null;
//    	String insertTableSQL = "INSERT INTO people__info (first_name,last_name,gender,town,street,street_number) VALUES (?,?,?,?,?,?)";
//    	
//    	try {
//
//			preparedStatement = conn.prepareStatement(insertTableSQL);
// 
//
//			preparedStatement.setString(1, "mkyong");
//			preparedStatement.setString(2, "system");
//			preparedStatement.setString(3, "system");
//			preparedStatement.setString(4, "system");
//			preparedStatement.setString(5, "system");
//			preparedStatement.setString(6, "system");
//
// 
//			// execute insert SQL stetement
//			preparedStatement.executeUpdate();
// 
//			System.out.println("Record is inserted into DBUSER table!");
// 
//		} catch (SQLException e) {
// 
//			System.out.println(e.getMessage());
// 
//		} 
    	

    }

}
