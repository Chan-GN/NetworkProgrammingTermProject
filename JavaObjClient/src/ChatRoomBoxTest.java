import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChatRoomBoxTest extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7410115339163008109L;
	private JLabel chatroombox_title;
	/**
	 * Create the panel.
	 */
	public ChatRoomBoxTest(String title) {
		setLayout(null); // absolute layout
		setPreferredSize(new Dimension(320,70)); // 높이 설정만 가능한듯 ?
		JLabel chatroombox_title = new JLabel(title);
		chatroombox_title.setBounds(69, 10, 169, 30);
		chatroombox_title.setFont(new Font("굴림체", Font.PLAIN, 14));
		add(chatroombox_title);
	}
	public JLabel getChatroombox_title() {
		return chatroombox_title;
	}
}
