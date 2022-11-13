
// JavaObjClientView.java ObjecStram 기반 Client
//실질적인 채팅 창
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.nio.Buffer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class JavaObjClientMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String UserName;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private Frame frame;
	private FileDialog fd;
	
	public JButton btnfriend;
	public JButton btnchat;
	
	/**
	 * Create the frame.
	 */
	public JavaObjClientMain(String username, String ip_addr, String port_no) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 394, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane chat_scrollPane = new JScrollPane();
		chat_scrollPane.setBounds(64, 65, 320, 528);
		chat_scrollPane.getViewport().setOpaque(false);
		chat_scrollPane.setOpaque(false);
		chat_scrollPane.setBorder(null);
		contentPane.add(chat_scrollPane);
		
		JScrollPane friend_scrollPane = new JScrollPane();
		friend_scrollPane.setBounds(64, 65, 320, 528);
		friend_scrollPane.getViewport().setOpaque(false);
		friend_scrollPane.setOpaque(false);
		friend_scrollPane.setBorder(null);		
		contentPane.add(friend_scrollPane);
				
		JLabel lblNewLabel_1 = new JLabel("친구"); // 테스트 라벨
		friend_scrollPane.setColumnHeaderView(lblNewLabel_1);
		friend_scrollPane.setVisible(false);
		

		JLabel lblNewLabel = new JLabel("채팅"); // 테스트 라벨
		chat_scrollPane.setColumnHeaderView(lblNewLabel);
		
		JLabel chatLabel = new JLabel("채팅");
		chatLabel.setFont(new Font("굴림", Font.BOLD, 18));
		chatLabel.setBounds(80, 25, 50, 32);
		contentPane.add(chatLabel);
		
		// 이미지 버튼 생성
		ImageIcon friend_icon_c = new ImageIcon("src/friendbtn_c.png");
		ImageIcon friend_icon_n = new ImageIcon("src/friendbtn_n.png");
		ImageIcon friend_icon_o = new ImageIcon("src/friendbtn_o.png");
		ImageIcon chat_icon_n = new ImageIcon("src/chatbtn_n.png");
		ImageIcon chat_icon_o = new ImageIcon("src/chatbtn_o.png");
		ImageIcon chat_icon_c = new ImageIcon("src/chatbtn_c.png");
		
		btnfriend = new JButton(friend_icon_n);
		btnfriend.setBounds(8, 21, 45, 45);
		btnfriend.setBorder(BorderFactory.createEmptyBorder());
		btnfriend.setFocusPainted(false);
		btnfriend.setContentAreaFilled(false);
		btnfriend.getCursor();
		btnfriend.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnfriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chat_scrollPane.setVisible(false);
				friend_scrollPane.setVisible(true);
				chatLabel.setText("친구");
				btnfriend.setIcon(friend_icon_c);
				btnchat.setIcon(chat_icon_n);
			}
		});
		btnfriend.addMouseListener(new MouseListener() {
			
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
				if(btnfriend.getIcon() != null && btnfriend.getIcon().toString() != "src/friend_test.png")
					btnfriend.setIcon(friend_icon_n);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(btnfriend.getIcon() != null && btnfriend.getIcon().toString() != "src/friend_test.png")
					btnfriend.setIcon(friend_icon_o);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.add(btnfriend);

		btnchat = new JButton(chat_icon_c);
		btnchat.setBounds(8, 85, 45, 45);
		btnchat.setBorder(BorderFactory.createEmptyBorder());
		btnchat.setContentAreaFilled(false);
		btnchat.setFocusPainted(false);
		btnchat.getCursor();
		btnchat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));		
		btnchat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chat_scrollPane.setVisible(true);
				friend_scrollPane.setVisible(false);
				chatLabel.setText("채팅");
				btnfriend.setIcon(friend_icon_n);
				btnchat.setIcon(chat_icon_c);
			}
		});
		btnchat.addMouseListener(new MouseListener() {
			
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
				if(btnchat.getIcon() != null && btnchat.getIcon().toString() != "src/chatbtn_c.png")
					btnchat.setIcon(chat_icon_n);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(btnchat.getIcon() != null && btnchat.getIcon().toString() != "src/chatbtn_c.png")
					btnchat.setIcon(chat_icon_o);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.add(btnchat);
		
		ImageIcon chatplus_icon = new ImageIcon("src/chatplus_test.png");
		JButton btnchatplus= new JButton(chatplus_icon);
		btnchatplus.setBounds(323, 14, 45, 45);
		btnchatplus.setBorder(BorderFactory.createEmptyBorder());
		btnchatplus.setFocusPainted(false);
		btnchatplus.setContentAreaFilled(false);
		btnchatplus.getCursor();
		btnchatplus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnchatplus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JavaObjClientChatRoom view = new JavaObjClientChatRoom(username, ip_addr, port_no);				
			}
		});
		contentPane.add(btnchatplus);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 65, 593);
		panel.setBackground(new Color(236, 236, 237));
		contentPane.add(panel);
		
		setVisible(true);
	}
}
