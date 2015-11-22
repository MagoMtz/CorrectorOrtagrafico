package com.mago.grammar.gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridLayout;

import javax.swing.JTextPane;

import org.languagetool.JLanguageTool;
import org.languagetool.language.Spanish;








import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.itextpdf.text.DocumentException;
import com.mago.grammar.objeto.App;
import com.mago.grammar.spelling.Ortografia;

public class Main {
	
	private JButton btnDisplay;
	private JButton btnAsm;
	private JButton btnRevisar;
	private JButton btnCorregir;
	private JButton btnLimpiar;

	private JFrame frmCorrectorDeOrtografa;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblArchivo;
	private JTextField txtRuta;
	private JButton btnAbrir;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblTextoDelArchivo;
	private JTextPane txtTexto;
	private JLabel lblErroresOrtogrficosYo;
	private JTextPane txtErrores;
	private JLabel lblTextoCorregido;
	private JTextPane txtCorregido;
	
	private JScrollPane scrollPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmCorrectorDeOrtografa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCorrectorDeOrtografa = new JFrame();
		frmCorrectorDeOrtografa.setTitle("Corrector de ortografía");
		frmCorrectorDeOrtografa.setBounds(100, 100, 650, 550);
		frmCorrectorDeOrtografa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon iRevisar = new ImageIcon("ico/ico_revisar.png");
		ImageIcon iCorregir = new ImageIcon("ico/ico_corregir.png");
		ImageIcon iLimpiar = new ImageIcon("ico/ico_limpiar.png");
		ImageIcon iAsm = new ImageIcon("ico/ico_asm.png");
		ImageIcon iDisplay = new ImageIcon("ico/ico_display.png");
		
		btnRevisar = new JButton();
		btnRevisar.setIcon(iRevisar);
		btnRevisar.setToolTipText("Revisar ortografía");
		btnCorregir= new JButton();
		btnCorregir.setIcon(iCorregir);
		btnCorregir.setToolTipText("Corregir texto");
		btnLimpiar= new JButton();
		btnLimpiar.setIcon(iLimpiar);
		btnLimpiar.setToolTipText("LimpiarCaja");
		btnAsm= new JButton();
		btnAsm.setIcon(iAsm);
		btnAsm.setToolTipText("Generar .asm");
		btnDisplay= new JButton();
		btnDisplay.setIcon(iDisplay);
		btnDisplay.setToolTipText("Reproducir en display");
		
		JToolBar toolBar = new JToolBar();
		toolBar.add(btnRevisar);
		toolBar.add(btnCorregir);
		toolBar.add(btnLimpiar);
		toolBar.addSeparator();
		toolBar.add(btnAsm);
		toolBar.add(btnDisplay);
		toolBar.setFloatable(false);
		frmCorrectorDeOrtografa.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		panel = new JPanel();
		frmCorrectorDeOrtografa.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		lblArchivo = new JLabel("Archivo:");
		panel_1.add(lblArchivo);
		
		txtRuta = new JTextField();
		panel_1.add(txtRuta);
		txtRuta.setColumns(35);
		
		btnAbrir = new JButton("Abrir");
		
		btnAbrir.setHorizontalAlignment(SwingConstants.LEADING);
		panel_1.add(btnAbrir);
		
		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		lblTextoDelArchivo = new JLabel("Texto del archivo");
		panel_3.add(lblTextoDelArchivo, BorderLayout.NORTH);
		
		txtTexto = new JTextPane();
		scrollPanel=new JScrollPane(txtTexto);
		panel_3.add(scrollPanel, BorderLayout.CENTER);
		
		panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		lblErroresOrtogrficosYo = new JLabel("Errores ortográficos y/o gramaticales");
		panel_4.add(lblErroresOrtogrficosYo, BorderLayout.NORTH);
		
		
		txtErrores = new JTextPane();
		scrollPanel=new JScrollPane(txtErrores);
		panel_4.add(scrollPanel, BorderLayout.CENTER);
		
		panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		lblTextoCorregido = new JLabel("Texto corregido");
		panel_5.add(lblTextoCorregido, BorderLayout.NORTH);
		
		txtCorregido = new JTextPane();
		scrollPanel=new JScrollPane(txtCorregido);
		panel_5.add(scrollPanel, BorderLayout.CENTER);
		
		btnAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtTexto.setText(abrirArchivo());
			}
		});
		
		btnRevisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Ortografia spelling = new Ortografia();
				txtErrores.setText(spelling.checkOrtografia(txtTexto.getText()));
			}
		});
		
		btnCorregir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Ortografia spelling = new Ortografia();
				txtCorregido.setText(spelling.editOrtografia(txtTexto.getText(), new JLanguageTool(new Spanish())));
			}
		});
		
		btnLimpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtTexto.setText("");
				txtErrores.setText("");
				txtCorregido.setText("");
			}
		});
		
		btnAsm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				App codObj= new App();
				codObj.codigoObj(txtCorregido.getText());
				JOptionPane.showMessageDialog(null,"Código objeto generado.");
			}
		});
		
		btnDisplay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				App codObj= new App();
				try {
					codObj.exeASM();
					codObj.generatePDF(txtCorregido.getText());
				} catch (IOException | InterruptedException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}

	private String abrirArchivo() {
		  String aux="";   
		  String texto="";
		  try{
		   JFileChooser file=new JFileChooser();
		   file.showOpenDialog(frmCorrectorDeOrtografa);
		   File abre=file.getSelectedFile();
		 
		   if(abre!=null){
			  txtRuta.setText(abre.getParent()+"\\"+abre.getName());
		      FileReader archivos=new FileReader(abre);
		      BufferedReader lee=new BufferedReader(archivos);
		      while((aux=lee.readLine())!=null){
		         texto+= aux+ " ";
		      }
		         lee.close();
		    }    
		   }
		   catch(IOException ex)
		   {
		     JOptionPane.showMessageDialog(null,ex+"" +
		           "\nNo se ha encontrado el archivo",
		                 "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
		    }
		  return texto;
		}
	
}
