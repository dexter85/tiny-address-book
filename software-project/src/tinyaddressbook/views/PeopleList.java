package tinyaddressbook.views;



import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import tinyaddressbook.model.ButtonColumn;
import tinyaddressbook.model.Database;

public class PeopleList 
{
	JPanel contentPane;
	Database mDatabase ;
	GroupLayout gl_contentPane;
	JScrollPane lastList;
	private JTextField textField;
	 Map<Integer, Integer> rowKey;
	
	/**
	 * Inizializzo l'interfaccia grafica
	 * 
	 * @return 
	 * @wbp.parser.entryPoint
	*/
	public PeopleList()
	{
		mDatabase = new Database();
		
		contentPane = new JPanel();
		
		JLabel lblRicerca = new JLabel("Ricerca");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent arg0) {
				updateList(textField.getText()	);
			}
		});
		textField.setColumns(10);
		lastList = getList("");
		JScrollPane scrollBar = lastList;
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(lblRicerca, Alignment.LEADING))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRicerca)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollBar, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		contentPane.setLayout(gl_contentPane);
		
//		contentPane.add(getList());

	}
	
	/**
	 * Restuisce il JPanel con dentro la tabella appena inizializzata
	 * @return
	 */
	public JPanel init()
	{
		return contentPane;
	}
	
	/**
	 * Metodo che esegue l'aggiornamento della tabella in base alla ricerca
	 * @param search
	 */
   public void updateList(String search)
   {
	   JScrollPane newList = getList(search);
	   

	   gl_contentPane.replace(lastList, newList);
	   contentPane.setLayout(gl_contentPane);
	   lastList = newList;
	   contentPane.validate();
	   contentPane.repaint();
   }
	
	
  public  JScrollPane getList(String search) 
  {
	  //Reseto l'hashMap mi serve in Modifica o elimina
	  rowKey = new HashMap<Integer, Integer>();
	  
	  
	  //Header della tabella
	  String[] columnTitles = { "Cognome", "Nome", "Sesso",  "Telefono", "E-Mail", "Città", "Indirizzo", "Civico", "Modifica", "Elimina" };
	  
	  //Inizializzo il contenuto della tabella
	  Object[][] rowData =null;
	  
	  //Istanzio la tabella
	  final JTable table;
	  
	  //Creao un DataModel 
	  DefaultTableModel model = new DefaultTableModel(rowData,columnTitles);
	  
	  //Passo il dataModel alla tabella
	  table = new JTable(model);
	  
	  //Prelevo i dati dal database
	  ResultSet rs = mDatabase.getPeople(search);
	  
	  
	  try 
	  {
		
		//Contatore
		int i = 0;  
		
		//Ciclo la query al db
		while(rs.next())
		{

				//Popolo l'hasMap
				rowKey.put(i, rs.getInt("id_people"));
				
				//Inserisco i dati nella tablla
				model.insertRow(table.getRowCount(),new Object[]{
				   	rs.getString("last_name"),
				   	rs.getString("first_name"), 
				   	rs.getString("gender"), 
				   	rs.getString("phone"), 
				   	rs.getString("email"),
				   	rs.getString("town"),
				   	rs.getString("street"),
				   	rs.getString("street_number"),
				   	"Modifica "+rs.getString("last_name"),
				   	"Elimina "+rs.getString("last_name"),
				});
				
				//Incremento il contatore
				i++;
		  }
		 System.out.println("Tot:"+i);
		 
	  } catch (SQLException e1) { e1.printStackTrace(); }
	   
	  //Spengo il doppio click nella tabella
	  table.setCellSelectionEnabled(false);

	  //Bind Aggiorna
	  Action update = new AbstractAction()
	  {
		  @Override
		  public void actionPerformed(ActionEvent e)
		  {
			  //Riga della tabella
			  int modelRow = Integer.valueOf( e.getActionCommand() );
            
			  //Chiamo la classe per il modifica
			  PeopleSet mPeopleSet = new PeopleSet();
			  
			  //Passo la chiave del db
			  mPeopleSet.setId_people(rowKey.get(modelRow));
			  
			  //Avvio l'interfaccia
			  mPeopleSet.init();
			  mPeopleSet.setVisible(true);
		  }
	  };     
    
    
    //Bind elimina
	Action delete = new AbstractAction()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			//Chiedo conferma
			JFrame frame = new JFrame();
		    int result = JOptionPane.showConfirmDialog(frame, "Sei sicuro di voler eliminare questo Contatto?");
		    
		    if(result == 0)
		    {
//				JTable table = (JTable)e.getSource();
				int modelRow = Integer.valueOf( e.getActionCommand());
				
				if(true == mDatabase.delPeople(rowKey.get(modelRow)))
				{
					//Aggiorno la tabella
					updateList(textField.getText());
//					((DefaultTableModel)table.getModel()).removeRow(modelRow);
				}
		    }
          
		}
	};
    
	//Associo il bottone aggiorna
    ButtonColumn buttonColumnUpd = new ButtonColumn(table, update, 8);
    buttonColumnUpd.setMnemonic(KeyEvent.VK_D);
    
    //Associo il bottone elimina
    ButtonColumn buttonColumnDel = new ButtonColumn(table, delete, 9);
    buttonColumnDel.setMnemonic(KeyEvent.VK_D);
    
    
    //Restituisco la tabella dentro uno scrollPanel
    JScrollPane mJScrollPane = new JScrollPane(table);
    return mJScrollPane;

  }
  
  
  
}