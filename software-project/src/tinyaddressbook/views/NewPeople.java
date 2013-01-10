package tinyaddressbook.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewPeople extends JFrame {

	private JPanel contentPane;
	private JTextField first_name;
	private JTextField last_name;
	private JDateChooser  born;
	private JTextField phone;
	private JTextField email;
	private JTextField town;
	private JTextField street;
	private JTextField street_number;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPeople frame = new NewPeople();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewPeople() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewPeople.class.getResource("/tinyaddressbook/resources/user.png")));
		
		
		setTitle("Modifica Inserisci");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNome = new JLabel("Nome");
		
		first_name = new JTextField();
		first_name.setText("value");
		first_name.setColumns(10);
		
		JLabel lblCognome = new JLabel("Cognome");
		
		last_name = new JTextField();
		last_name.setText("value");
		last_name.setColumns(10);
		
		JLabel lblSesso = new JLabel("Sesso");
		
		JComboBox gender = new JComboBox();
		gender.setModel(new DefaultComboBoxModel(new String[] {"m", "f"}));
		
		JLabel lblDataDiNascita = new JLabel("Data di Nascita");
		
		born = new JDateChooser (new Date());
		
		JLabel lblTelefono = new JLabel("Telefono");
		
		phone = new JTextField();
		phone.setText("value");
		phone.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-Mail");
		
		email = new JTextField();
		email.setText("value");
		email.setColumns(10);
		
		JLabel lblCitt = new JLabel("Citt\u00E0");
		
		town = new JTextField();
		town.setText("value");
		town.setColumns(10);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		
		street = new JTextField();
		street.setText("value");
		street.setColumns(10);
		
		JLabel lblNumeroCivico = new JLabel("Numero Civico");
		
		street_number = new JTextField();
		street_number.setText("value");
		street_number.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JButton btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalva.setIcon(new ImageIcon(NewPeople.class.getResource("/tinyaddressbook/resources/accept.png")));
		
		JButton btnAnulla = new JButton("Anulla");
		btnAnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAnulla.setIcon(new ImageIcon(NewPeople.class.getResource("/tinyaddressbook/resources/action_stop.gif")));

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addComponent(lblSesso)
						.addComponent(lblTelefono)
						.addComponent(lblCitt)
						.addComponent(lblNumeroCivico)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(street_number, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
								.addComponent(town, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
								.addComponent(phone, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
								.addComponent(gender, Alignment.LEADING, 0, 189, Short.MAX_VALUE)
								.addComponent(first_name, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
								.addComponent(lblNome, Alignment.LEADING))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCognome)
								.addComponent(lblDataDiNascita)
								.addComponent(last_name, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
								.addComponent(lblEmail)
								.addComponent(born, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
								.addComponent(email, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
								.addComponent(lblIndirizzo)
								.addComponent(street, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnAnulla)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSalva)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCognome)
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(first_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(last_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSesso)
						.addComponent(lblDataDiNascita))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(gender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(born, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefono)
						.addComponent(lblEmail))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCitt)
						.addComponent(lblIndirizzo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(town, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(street, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNumeroCivico)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(street_number, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalva)
						.addComponent(btnAnulla))
					.addContainerGap(182, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
