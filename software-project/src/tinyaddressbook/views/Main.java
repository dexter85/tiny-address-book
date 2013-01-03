package tinyaddressbook.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/tinyaddressbook/resources/book.png")));
		setTitle("Tiny Address Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRubrica = new JMenu("Rubrica");
		menuBar.add(mnRubrica);
		
		JMenuItem mntmTuttiIContatti = new JMenuItem("Tutti i Contatti");
		mntmTuttiIContatti.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/book_open.png")));
		mnRubrica.add(mntmTuttiIContatti);
		
		JMenuItem mntmNuovoContantto = new JMenuItem("Nuovo Contatto");
		mntmNuovoContantto.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/add.png")));
		mnRubrica.add(mntmNuovoContantto);
		
		JMenu mnCalendario = new JMenu("Calendario");
		menuBar.add(mnCalendario);
		
		JMenuItem mntmTuttiGliEventi = new JMenuItem("Tutti gli eventi");
		mntmTuttiGliEventi.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/calendar_view_day.png")));
		mnCalendario.add(mntmTuttiGliEventi);
		
		JMenuItem mntmNuovoEvento = new JMenuItem("Nuovo Evento");
		mntmNuovoEvento.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/add.png")));
		mnCalendario.add(mntmNuovoEvento);
		
		JMenu mnNewMenu = new JMenu("?");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmInformazioni = new JMenuItem("Informazioni su Tiny Adrress Book");
		mntmInformazioni.setIcon(new ImageIcon(Main.class.getResource("/tinyaddressbook/resources/icon_info.gif")));
		mnNewMenu.add(mntmInformazioni);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
