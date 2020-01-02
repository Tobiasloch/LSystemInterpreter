package Gui;

import javax.swing.JToolBar;

import Interpreter.Minus;
import Interpreter.Plus;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SettingsBar extends JToolBar {
	
	public Viewer v;
	public JTextField plusAngleTextField;
	public JTextField minusAngleTextfield;
	public JTextField depthTextField;
	public JComboBox<String> comboBox;
	
	public double getRotation() {
		String orientation = (String) comboBox.getSelectedItem();
		switch (orientation) {
		case "NORTH":
			return Math.toRadians(90);
		case "SOUTH":
			return Math.toRadians(270);
		case "WEST":
			return Math.toRadians(0);
		case "EAST":
			return Math.toRadians(180);
		}
		
		return Math.toRadians(0);
	}
	
	public SettingsBar(Viewer v) {
		this.v = v;
		
		JPanel panel = new JPanel();
		add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[]{1.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblPlusAngle = new JLabel("Plus Angle:");
		GridBagConstraints gbc_lblPlusAngle = new GridBagConstraints();
		gbc_lblPlusAngle.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblPlusAngle.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlusAngle.gridx = 0;
		gbc_lblPlusAngle.gridy = 0;
		panel.add(lblPlusAngle, gbc_lblPlusAngle);
		
		plusAngleTextField = new JTextField(Double.toString(Math.toDegrees(Plus.defaultAngle)));
		GridBagConstraints gbc_plusAngleTextField = new GridBagConstraints();
		gbc_plusAngleTextField.anchor = GridBagConstraints.NORTH;
		gbc_plusAngleTextField.insets = new Insets(0, 0, 5, 0);
		gbc_plusAngleTextField.weightx = 1.0;
		gbc_plusAngleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_plusAngleTextField.gridx = 1;
		gbc_plusAngleTextField.gridy = 0;
		panel.add(plusAngleTextField, gbc_plusAngleTextField);
		plusAngleTextField.setColumns(10);
		
		JLabel lblMinusAngle = new JLabel("Minus Angle:");
		GridBagConstraints gbc_lblMinusAngle = new GridBagConstraints();
		gbc_lblMinusAngle.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblMinusAngle.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinusAngle.gridx = 0;
		gbc_lblMinusAngle.gridy = 1;
		panel.add(lblMinusAngle, gbc_lblMinusAngle);
		
		minusAngleTextfield = new JTextField(Double.toString(Math.toDegrees(Minus.defaultAngle)));
		GridBagConstraints gbc_minusAngleTextfield = new GridBagConstraints();
		gbc_minusAngleTextfield.insets = new Insets(0, 0, 5, 0);
		gbc_minusAngleTextfield.anchor = GridBagConstraints.NORTH;
		gbc_minusAngleTextfield.weightx = 1.0;
		gbc_minusAngleTextfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_minusAngleTextfield.gridx = 1;
		gbc_minusAngleTextfield.gridy = 1;
		panel.add(minusAngleTextfield, gbc_minusAngleTextfield);
		minusAngleTextfield.setColumns(10);
		
		JLabel lblDepth = new JLabel("Depth:");
		GridBagConstraints gbc_lblDepth = new GridBagConstraints();
		gbc_lblDepth.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDepth.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepth.gridx = 0;
		gbc_lblDepth.gridy = 2;
		panel.add(lblDepth, gbc_lblDepth);
		
		depthTextField = new JTextField(Integer.toString(v.depth));
		GridBagConstraints gbc_depthTextField = new GridBagConstraints();
		gbc_depthTextField.insets = new Insets(0, 0, 5, 0);
		gbc_depthTextField.anchor = GridBagConstraints.NORTH;
		gbc_depthTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_depthTextField.gridx = 1;
		gbc_depthTextField.gridy = 2;
		panel.add(depthTextField, gbc_depthTextField);
		depthTextField.setColumns(10);
		
		JLabel lblRotation = new JLabel("Rotation:");
		GridBagConstraints gbc_lblRotation = new GridBagConstraints();
		gbc_lblRotation.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblRotation.weighty = 1.0;
		gbc_lblRotation.insets = new Insets(0, 0, 0, 5);
		gbc_lblRotation.gridx = 0;
		gbc_lblRotation.gridy = 3;
		panel.add(lblRotation, gbc_lblRotation);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"WEST", "EAST", "SOUTH", "NORTH"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 3;
		panel.add(comboBox, gbc_comboBox);
	}

}
