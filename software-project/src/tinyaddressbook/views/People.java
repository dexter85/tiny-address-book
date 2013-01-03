package tinyaddressbook.views;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import tinyaddressbook.model.Database;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;




public class People extends JFrame {

	private JPanel contentPane;
	private JTextField first_name;
	private JTextField last_name;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Integer	   id_people;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					People frame = new People();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	
	public Integer getId_people() {
		return id_people;
	}




	public void setId_people(Integer id_people) 
	{
		this.id_people = id_people;
	}




	/**
	 * Create the frame.
	 * @return 
	 */
	public void formGenerate() 
	{

		
		//Verifico se mi viene passatto un utente
		if(this.getId_people() != null)
		{
			Database prova = new Database();


			

		}
		
		
		setResizable(false);
		setTitle("Scheda Contatto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(People.class.getResource("/tinyaddressbook/resources/user.png")));
		setBounds(100, 100, 582, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		
		JButton btnSalva = new JButton("Salva");
		btnSalva.setIcon(new ImageIcon(People.class.getResource("/tinyaddressbook/resources/accept.png")));
		
		JButton btnAnulla = new JButton("Anulla");
		btnAnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		btnAnulla.setIcon(new ImageIcon(People.class.getResource("/tinyaddressbook/resources/action_stop.gif")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAnulla)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalva)
							.addGap(32)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAnulla)
						.addComponent(btnSalva))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		
		JLabel lblNome = new JLabel("Nome");
		
		first_name = new JTextField();
		first_name.setColumns(10);
		
		JLabel lblCognome = new JLabel("Cognome");
		
		last_name = new JTextField();
		last_name.setColumns(10);
		
		JLabel lblSesso = new JLabel("Sesso");
		
		
		
		JLabel lblNewLabel = new JLabel("Data di Nascita");
		
		textField_2 = new JTextField();
		textField_2.setText("__/__/____");
		textField_2.setColumns(10);
		
		JLabel lblCitt = new JLabel("Citt\u00E0");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblNCivico = new JLabel("N. Civico");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Maschio", "Femmina"}));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNCivico)
						.addComponent(lblSesso)
						.addComponent(lblCitt)
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, 0, 189, Short.MAX_VALUE)
						.addComponent(first_name, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
						.addComponent(textField_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
						.addComponent(textField_5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
					.addGap(13)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCognome)
						.addComponent(lblNewLabel)
						.addComponent(lblIndirizzo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_4)
						.addComponent(textField_2)
						.addComponent(last_name, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(last_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCognome)
						.addComponent(first_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSesso)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCitt)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIndirizzo)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNCivico)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(315, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
