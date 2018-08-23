package interfaz;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class MainForm 
{
	private JFrame frmPrueba;
	private JComboBox<String> comboBox;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnFemenino;
	private JRadioButton rdbtnMasculino;
	private JTextArea textArea;
	private int cantidadVocales;
	private JTextArea textAreaReplica;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textFieldColor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					MainForm window = new MainForm();
					window.frmPrueba.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() 
	{
		try 
		{
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		initialize(); 
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() 
	{
		inicializarFrame();
		ponerComboBox();		
		ponerBotonOk();
		ponerBotonAgarrame();
		ponerRadioBottons();
		ponerAreaDeTexto();
		losLabels();	
		ponerAreaReplica();
		inicializarTabla();
	}

	private void inicializarFrame() 
	{
		frmPrueba = new JFrame();
		frmPrueba.setResizable(false);
		frmPrueba.setTitle("Prueba Complementos WB");
		frmPrueba.setBounds(100, 100, 450, 443);
		frmPrueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrueba.setLocationRelativeTo(null); //centra la ventana
		frmPrueba.getContentPane().setLayout(null);
	}
	
	private void ponerComboBox() 
	{
		comboBox = new JComboBox<String>();
		comboBox.addItem("Seleccionar un color...");
		comboBox.addItem("Azul");
		comboBox.addItem("Blanco");
		comboBox.addItem("Rojo");
		comboBox.addItem("Otro...");
		comboBox.setBounds(71, 12, 173, 20);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent it) 
			{
				if (comboBox.getSelectedIndex()==4)
					textFieldColor.setEditable(true);
				else
				{
					textFieldColor.setEditable(false);
					textFieldColor.setText("");
				}
			}
		});
		frmPrueba.getContentPane().add(comboBox);	
	}
	
	private void ponerBotonOk() 
	{
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int indice  = comboBox.getSelectedIndex();
				String elegido = (String) comboBox.getSelectedItem();
				frmPrueba.setTitle("vocales: "+ cantidadVocales + " "+elegido + " " + indice);
				String sexo= "Femenino"; 
				if (rdbtnMasculino.isSelected())
					sexo = "Masculino";
				frmPrueba.setTitle(frmPrueba.getTitle()+" "+sexo);
			}
		});
		btnOk.setBounds(319, 11, 89, 23);
		frmPrueba.getContentPane().add(btnOk);
	}
	
	private void ponerBotonAgarrame() 
	{
		JButton btnAgarrame = new JButton("Agarrame");
		btnAgarrame.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				btnAgarrame.setBounds(btnAgarrame.getX()+15 ,btnAgarrame.getY()+15, btnAgarrame.getWidth(), btnAgarrame.getHeight());
			}

			@Override
			public void mouseExited(MouseEvent arg0) 
			{
				btnAgarrame.setBounds(btnAgarrame.getX()-15,btnAgarrame.getY()-15, btnAgarrame.getWidth(), btnAgarrame.getHeight());
			}
		});

		btnAgarrame.setBounds(66, 168, 89, 23);
		frmPrueba.getContentPane().add(btnAgarrame);
	}
	
	private void ponerRadioBottons() 
	{
		rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.setSelected(true);
		buttonGroup.add(rdbtnFemenino);
		rdbtnFemenino.setBounds(66, 103, 109, 23);
		frmPrueba.getContentPane().add(rdbtnFemenino);

		rdbtnMasculino = new JRadioButton("Masculino");
		buttonGroup.add(rdbtnMasculino);
		rdbtnMasculino.setBounds(66, 129, 109, 23);
		frmPrueba.getContentPane().add(rdbtnMasculino);
	}
	
	private void ponerAreaDeTexto() 
	{
		textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				textAreaReplica.setText(textAreaReplica.getText()+e.getKeyCode()+"\n");
				if (KeyEvent.VK_A==e.getKeyCode())					
					cantidadVocales++;
				if (KeyEvent.VK_E==e.getKeyCode())
					cantidadVocales++;
				if (KeyEvent.VK_I==e.getKeyCode())					
					cantidadVocales++;
				if (KeyEvent.VK_O==e.getKeyCode())
					cantidadVocales++;
				if (KeyEvent.VK_U==e.getKeyCode())
					cantidadVocales++;
			}
		});				
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(213, 107, 207, 118);
		frmPrueba.getContentPane().add(scrollPane);
		scrollPane.setViewportView(textArea);
	}


	private void losLabels() 
	{
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(66, 82, 46, 14);
		frmPrueba.getContentPane().add(lblSexo);

		JLabel lblEscriboTexto = new JLabel("Escribo Texto");
		lblEscriboTexto.setBounds(213, 82, 137, 14);
		frmPrueba.getContentPane().add(lblEscriboTexto);

		JLabel lblMuestroCapturaTeclado = new JLabel("Muestro captura teclado");
		lblMuestroCapturaTeclado.setBounds(213, 258, 137, 14);
		frmPrueba.getContentPane().add(lblMuestroCapturaTeclado);

		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(10, 15, 46, 14);
		frmPrueba.getContentPane().add(lblColor);
	}

	private void ponerAreaReplica() 
	{
		textAreaReplica = new JTextArea();
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(213, 283, 209, 121);
		frmPrueba.getContentPane().add(scrollPane_1);

		scrollPane_1.setViewportView(textAreaReplica);
	}

	private void inicializarTabla() 
	{
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		lasColumnas(model);
		cargarValores(model);
		table.setModel(model);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(22, 283, 178, 121);
		scrollPane_2.setViewportView(table);
		frmPrueba.getContentPane().add(scrollPane_2);
		
		textFieldColor = new JTextField();
		textFieldColor.setEditable(false);
		textFieldColor.setBounds(71, 40, 173, 20);
		frmPrueba.getContentPane().add(textFieldColor);
		textFieldColor.setColumns(10);
	}
	
	private void lasColumnas(DefaultTableModel model) 
	{
		model.addColumn("Nombre");
		model.addColumn("Apellido");
		model.addColumn("Edad");
	}

	private void cargarValores(DefaultTableModel model) 
	{
		model.setRowCount(6);		
		model.setValueAt("Alejandro", 0, 0);
		model.setValueAt("Nelis", 0, 1);
		model.setValueAt("41", 0, 2);
	}	
}
