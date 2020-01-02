package Gui;

import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class FormulaInputBar extends JToolBar {
	private JTextArea txtFFf;
	private Viewer viewer;
	private JTextField txtF;
	
	
	public FormulaInputBar(Viewer v) {
		this.viewer = v;
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		txtFFf = new JTextArea(5,20);
		txtFFf.setText(v.axiom.generateRepresentation(true));
		JScrollPane scrollPane = new JScrollPane(txtFFf);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnParse = new JButton("Parse");
		panel_1.add(btnParse);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {40, 40};
		gbl_panel_2.rowHeights = new int[]{20, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblAxiom = new JLabel("Axiom:");
		GridBagConstraints gbc_lblAxiom = new GridBagConstraints();
		gbc_lblAxiom.insets = new Insets(0, 5, 0, 5);
		gbc_lblAxiom.fill = GridBagConstraints.BOTH;
		gbc_lblAxiom.gridx = 0;
		gbc_lblAxiom.gridy = 0;
		panel_2.add(lblAxiom, gbc_lblAxiom);
		
		txtF = new JTextField();
		GridBagConstraints gbc_txtF = new GridBagConstraints();
		gbc_txtF.fill = GridBagConstraints.BOTH;
		gbc_txtF.gridx = 1;
		gbc_txtF.gridy = 0;
		panel_2.add(txtF, gbc_txtF);
		txtF.setText(v.axiom.representation);
		txtF.setColumns(10);
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewer.view(txtFFf.getText(), txtF.getText());
			}
		});
	}
}
