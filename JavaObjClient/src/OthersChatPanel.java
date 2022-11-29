import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OthersChatPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public JButton profileBtn;
	public JLabel username;
	
	public OthersChatPanel() {
		setPreferredSize(new Dimension(400, 50));
		setBackground(new Color(186, 206, 224));
		setLayout(null);
		
		profileBtn = new JButton();
		profileBtn.setBounds(0, 4, 46, 46);
		profileBtn.setBorderPainted(false);
		profileBtn.setContentAreaFilled(false);
		profileBtn.setFocusPainted(false);
		profileBtn.getCursor();
		profileBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		add(profileBtn);
		
		username = new JLabel();
		username.setLocation(60, 6);
		username.setSize(100, 20);
		add(username);

	}
	
}
