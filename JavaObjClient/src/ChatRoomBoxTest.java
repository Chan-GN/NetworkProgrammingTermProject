import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ChatRoomBoxTest extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7410115339163008109L;
	private JLabel chatroombox_title;
	public JButton usersPfImgOne;
	public JButton usersPfImgTwo_1;
	public JButton usersPfImgTwo_2;
	public JButton usersPfImgTh_1;
	public JButton usersPfImgTh_2;
	public JButton usersPfImgTh_3;
	public JButton usersPfImgF_1;
	public JButton usersPfImgF_2;
	public JButton usersPfImgF_3;
	public JButton usersPfImgF_4;
	/**
	 * Create the panel.
	 */
	public ChatRoomBoxTest(String title, JavaObjClientMain testview) { // ¿ÃπÃ¡ˆ ¥ÎΩ≈ ∫‰ ªÛº”
		String[] ul = title.trim().split(", ");
		
		setLayout(null); // absolute layout
		setBackground(Color.white);
		setPreferredSize(new Dimension(320,70)); // ≥Ù¿Ã º≥¡§∏∏ ∞°¥…«—µÌ ?
		chatroombox_title = new JLabel(title);
		chatroombox_title.setBounds(69, 10, 169, 30);
		chatroombox_title.setFont(new Font("≥™¥Æ∞ÌµÒ", Font.BOLD, 12));
		add(chatroombox_title);

		usersPfImgOne = new JButton(); // ±‚∫ª ¿ÃπÃ¡ˆ ¥ÎΩ≈
		usersPfImgOne.setBounds(9, 10, 48, 46);
		usersPfImgOne.setBorderPainted(false);
		usersPfImgOne.setContentAreaFilled(false);
		usersPfImgOne.setFocusPainted(false);
		
		usersPfImgTwo_1 = new JButton();
		usersPfImgTwo_1.setBounds(9, 10, 30, 30);
		usersPfImgTwo_1.setBorderPainted(false);
		usersPfImgTwo_1.setContentAreaFilled(false);
		usersPfImgTwo_1.setFocusPainted(false);

		usersPfImgTwo_2 = new JButton();
		usersPfImgTwo_2.setBounds(25, 25, 30, 30);
		usersPfImgTwo_2.setBorderPainted(false);
		usersPfImgTwo_2.setContentAreaFilled(false);
		usersPfImgTwo_2.setFocusPainted(false);
		
		usersPfImgTh_1 = new JButton();
		usersPfImgTh_1.setBounds(20, 10, 25, 25);
		usersPfImgTh_1.setBorderPainted(false);
		usersPfImgTh_1.setContentAreaFilled(false);
		usersPfImgTh_1.setFocusPainted(false);
		
		usersPfImgTh_2 = new JButton();
		usersPfImgTh_2.setBounds(30, 25, 25, 25);
		usersPfImgTh_2.setBorderPainted(false);
		usersPfImgTh_2.setContentAreaFilled(false);
		usersPfImgTh_2.setFocusPainted(false);
		
		usersPfImgTh_3 = new JButton();
		usersPfImgTh_3.setBounds(9, 25, 25, 25);
		usersPfImgTh_3.setBorderPainted(false);
		usersPfImgTh_3.setContentAreaFilled(false);
		usersPfImgTh_3.setFocusPainted(false);
		
		usersPfImgF_1 = new JButton();
		usersPfImgF_1.setBounds(9, 10, 20, 20);
		usersPfImgF_1.setBorderPainted(false);
		usersPfImgF_1.setContentAreaFilled(false);
		usersPfImgF_1.setFocusPainted(false);
		
		usersPfImgF_2 = new JButton();
		usersPfImgF_2.setBounds(35, 10, 20, 20);
		usersPfImgF_2.setBorderPainted(false);
		usersPfImgF_2.setContentAreaFilled(false);
		usersPfImgF_2.setFocusPainted(false);

		usersPfImgF_3 = new JButton();
		usersPfImgF_3.setBounds(9, 38, 20, 20);
		usersPfImgF_3.setBorderPainted(false);
		usersPfImgF_3.setContentAreaFilled(false);
		usersPfImgF_3.setFocusPainted(false);

		usersPfImgF_4 = new JButton();
		usersPfImgF_4.setBounds(35, 38, 20, 20);
		usersPfImgF_4.setBorderPainted(false);
		usersPfImgF_4.setContentAreaFilled(false);
		usersPfImgF_4.setFocusPainted(false);
				
		if(ul.length == 1) {
			usersPfImgOne.setIcon(testview.getUserProfile(ul[0])); // ∏ﬁ¿Œø°º≠ ¿ÃπÃ¡ˆ πﬁæ∆ø»
			add(usersPfImgOne);
		} else if (ul.length == 2) {
			usersPfImgTwo_1.setText(ul[0]);
			usersPfImgTwo_1.setIcon(testview.getUserProfile(ul[0]));
			usersPfImgTwo_1.setFont(new Font("±º∏≤√º", Font.PLAIN, 0));
			usersPfImgTwo_2.setText(ul[1]);
			usersPfImgTwo_2.setIcon(testview.getUserProfile(ul[1]));
			usersPfImgTwo_2.setFont(new Font("±º∏≤√º", Font.PLAIN, 0));
			add(usersPfImgTwo_1);
			add(usersPfImgTwo_2);
		} else if (ul.length == 3) {
			usersPfImgTh_1.setText(ul[0]);
			usersPfImgTh_1.setIcon(testview.getUserProfile(ul[0]));
			usersPfImgTh_1.setFont(new Font("±º∏≤√º", Font.PLAIN, 0));
			usersPfImgTh_2.setText(ul[1]);
			usersPfImgTh_2.setIcon(testview.getUserProfile(ul[1]));
			usersPfImgTh_2.setFont(new Font("±º∏≤√º", Font.PLAIN, 0));
			usersPfImgTh_3.setText(ul[2]);
			usersPfImgTh_3.setIcon(testview.getUserProfile(ul[2]));
			usersPfImgTh_3.setFont(new Font("±º∏≤√º", Font.PLAIN, 0));
			
			add(usersPfImgTh_1);
			add(usersPfImgTh_2);
			add(usersPfImgTh_3);
		} else {
			usersPfImgF_1.setText(ul[0]);
			usersPfImgF_1.setIcon(testview.getUserProfile(ul[0]));
			usersPfImgF_1.setFont(new Font("±º∏≤√º", Font.PLAIN, 0));
			usersPfImgF_2.setText(ul[1]);
			usersPfImgF_2.setIcon(testview.getUserProfile(ul[1]));
			usersPfImgF_2.setFont(new Font("±º∏≤√º", Font.PLAIN, 0));
			usersPfImgF_3.setText(ul[2]);
			usersPfImgF_3.setIcon(testview.getUserProfile(ul[2]));
			usersPfImgF_3.setFont(new Font("±º∏≤√º", Font.PLAIN, 0));
			usersPfImgF_4.setText(ul[3]);
			usersPfImgF_4.setIcon(testview.getUserProfile(ul[3]));
			usersPfImgF_4.setFont(new Font("±º∏≤√º", Font.PLAIN, 0));
			
			add(usersPfImgF_1);
			add(usersPfImgF_2);
			add(usersPfImgF_3);
			add(usersPfImgF_4);
		}
		addMouseListener(new MouseListener() {			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setBackground(Color.white);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
	public String getChatroombox_title() {
		return chatroombox_title.getText();
	}
}
