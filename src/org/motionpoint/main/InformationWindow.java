package org.motionpoint.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.motionpoint.main.Node.ConnectionType;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InformationWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtCmd;
	private JCheckBox chckbxCurve;
	private JLabel lblStep;
	
	private Node focusedNode;
	private World world;
	private int index;
	
	public void display(World world, int index) {
		lblStep.setText("Step: " + String.valueOf(index));
		txtX.setText(String.valueOf(world.nodes.get(index).getX()));
		txtY.setText(String.valueOf(world.nodes.get(index).getY()));
		focusedNode = world.nodes.get(index);
		
		chckbxCurve.setSelected((focusedNode.getType() == ConnectionType.CURVED));
		
		this.world = world;
		this.index = index;
	}

	public InformationWindow() {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblX = new JLabel("X:");
		lblX.setBounds(10, 11, 22, 14);
		contentPane.add(lblX);
		
		txtX = new JTextField();
		txtX.setBounds(98, 8, 86, 20);
		contentPane.add(txtX);
		txtX.setColumns(10);
		
		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(10, 50, 22, 14);
		contentPane.add(lblY);
		
		txtY = new JTextField();
		txtY.setBounds(98, 47, 86, 20);
		contentPane.add(txtY);
		txtY.setColumns(10);
		
		JLabel lblCmd = new JLabel("Cmd:");
		lblCmd.setBounds(10, 93, 46, 14);
		contentPane.add(lblCmd);
		
		txtCmd = new JTextField();
		txtCmd.setBounds(98, 90, 86, 20);
		contentPane.add(txtCmd);
		txtCmd.setColumns(10);
		
		chckbxCurve = new JCheckBox("Curve");
		chckbxCurve.setBounds(98, 125, 77, 23);
		contentPane.add(chckbxCurve);
		
		lblStep = new JLabel("Step: ");
		lblStep.setBounds(10, 186, 46, 14);
		contentPane.add(lblStep);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index > 0) {
					focusedNode.setX(Integer.parseInt(txtX.getText()));
					focusedNode.setY(Integer.parseInt(txtY.getText()));
					
					if (chckbxCurve.isSelected()) {
						focusedNode.setType(ConnectionType.CURVED);
					} else {
						focusedNode.setType(ConnectionType.STRAIGHT);
					}
				} else {
					txtX.setText("0");
					txtY.setText("0");
				}
			}
		});
		btnUpdate.setBounds(52, 257, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (index > 0) {
					world.setPrime(index - 1);
					world.nodes.remove(index);
				}
			}
		});
		btnDelete.setBounds(52, 223, 89, 23);
		contentPane.add(btnDelete);
		setVisible(true);
	}
}
