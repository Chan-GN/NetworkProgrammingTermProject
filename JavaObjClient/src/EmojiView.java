
// JavaObjClientView.java ObjecStram 기반 Client
//실질적인 채팅 창
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.ImageObserver;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.PublicKey;

import javax.management.loading.PrivateClassLoader;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ScrollPaneConstants;

public class EmojiView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextPane textArea;
	
	private String UserName;
	
	GridBagLayout Gbag = new GridBagLayout();

	
	public JavaObjClientChatRoom mainview;
	public boolean PanelOn=false;


	
	public EmojiView(String username, JavaObjClientChatRoom view)  {
		mainview=view;
		UserName = username;
		setVisible(true);
		//이모티콘 창 아이콘
		Toolkit tollkit=Toolkit.getDefaultToolkit();
		Image icon= tollkit.getImage("src/emoframicon.png");
		
		ImageIcon emoji1=new ImageIcon("src/emoji/emoji1.png");
		ImageIcon emoji2=new ImageIcon("src/emoji/emoji2.png");
		ImageIcon emoji3=new ImageIcon("src/emoji/emoji3.png");
		ImageIcon emoji4=new ImageIcon("src/emoji/emoji4.png");
		ImageIcon emoji5=new ImageIcon("src/emoji/emoji5.png");
		ImageIcon emoji6=new ImageIcon("src/emoji/emoji6.png");
		ImageIcon emoji7=new ImageIcon("src/emoji/emoji7.png");
		ImageIcon emoji8=new ImageIcon("src/emoji/emoji8.png");
		ImageIcon emoji9=new ImageIcon("src/emoji/emoji9.png");
		ImageIcon emoji10=new ImageIcon("src/emoji/emoji10.png");

		setIconImage(icon);
		setResizable(false);
		getContentPane().setLayout(null);	
		setBounds(mainview.frameX-354, mainview.frameY+103, 369, 527);
				
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel(new GridLayout(6,3));
		panel.setBackground(Color.white);


		scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(0, 0, 355, 490);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);
		scrollPane.setBorder(null);
		//scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);
		

		
		
		//이모티콘 추가
		
		JLabel emo_1 = new JLabel(emoji1);
		panel.add(emo_1);
		emo_1.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e)  
		    {  	
				if(e.getClickCount()==2) {
					ChatMsg cm = new ChatMsg(UserName, "301", "image");
					cm.img=emoji1;
					mainview.SendObject(cm);
				}
				else {
					ChatMsg cm = new ChatMsg(UserName, "302", "panelOn");
					cm.img=emoji1;
					mainview.SendObject(cm);
				}
		    }  
		});
		JLabel emo_2 = new JLabel(emoji2);
		panel.add(emo_2);
		emo_2.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e)  
		    {     		
				if(e.getClickCount()==2) {
					ChatMsg cm = new ChatMsg(UserName, "301", "image");
					cm.img=emoji2;
					mainview.SendObject(cm);
				}
				else {
					ChatMsg cm = new ChatMsg(UserName, "302", "panelOn");
					cm.img=emoji2;
					mainview.SendObject(cm);		
				}
		    }  
		});	
		JLabel emo_3 = new JLabel(emoji3);
		panel.add(emo_3);
		emo_3.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e)  
		    {     
				if(e.getClickCount()==2) {
					ChatMsg cm = new ChatMsg(UserName, "301", "image");
					cm.img=emoji3;
					mainview.SendObject(cm);
				}
				else {
					ChatMsg cm = new ChatMsg(UserName, "302", "panelOn");
					cm.img=emoji3;
					mainview.SendObject(cm);		
				}
		    }  
		});	
		JLabel emo_4 = new JLabel(emoji4);
		panel.add(emo_4);		
		emo_4.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e)  
		    {     
				if(e.getClickCount()==2) {
					ChatMsg cm = new ChatMsg(UserName, "301", "image");
					cm.img=emoji4;
					mainview.SendObject(cm);
				}
				else {
					ChatMsg cm = new ChatMsg(UserName, "302", "panelOn");
					cm.img=emoji4;
					mainview.SendObject(cm);		
				}
		    }  
		});		
		JLabel emo_5 = new JLabel(emoji5);
		panel.add(emo_5);		
		emo_5.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e)  
		    {     
				if(e.getClickCount()==2) {
					ChatMsg cm = new ChatMsg(UserName, "301", "image");
					cm.img=emoji5;
					mainview.SendObject(cm);
				}
				else {
					ChatMsg cm = new ChatMsg(UserName, "302", "panelOn");
					cm.img=emoji5;
					mainview.SendObject(cm);		
				}
		    }  
		});	
		JLabel emo_6 = new JLabel(emoji6);
		panel.add(emo_6);		
		emo_6.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e)  
		    {     
				if(e.getClickCount()==2) {
					ChatMsg cm = new ChatMsg(UserName, "301", "image");
					cm.img=emoji6;
					mainview.SendObject(cm);
				}
				else {
					ChatMsg cm = new ChatMsg(UserName, "302", "panelOn");
					cm.img=emoji6;
					mainview.SendObject(cm);		
				}
		    }  
		});
		JLabel emo_7 = new JLabel(emoji7);
		panel.add(emo_7);		
		emo_7.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e)  
		    {     
				if(e.getClickCount()==2) {
					ChatMsg cm = new ChatMsg(UserName, "301", "image");
					cm.img=emoji7;
					mainview.SendObject(cm);
				}
				else {
					ChatMsg cm = new ChatMsg(UserName, "302", "panelOn");
					cm.img=emoji7;
					mainview.SendObject(cm);		
				}
		    }  
		});
		JLabel emo_8 = new JLabel(emoji8);
		panel.add(emo_8);		
		emo_8.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e)  
		    {     
				if(e.getClickCount()==2) {
					ChatMsg cm = new ChatMsg(UserName, "301", "image");
					cm.img=emoji8;
					mainview.SendObject(cm);
				}
				else {
					ChatMsg cm = new ChatMsg(UserName, "302", "panelOn");
					cm.img=emoji8;
					mainview.SendObject(cm);		
				}
		    }  
		});
		JLabel emo_9 = new JLabel(emoji9);
		panel.add(emo_9);		
		emo_9.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e)  
		    {     
				if(e.getClickCount()==2) {
					ChatMsg cm = new ChatMsg(UserName, "301", "image");
					cm.img=emoji9;
					mainview.SendObject(cm);
				}
				else {
					ChatMsg cm = new ChatMsg(UserName, "302", "panelOn");
					cm.img=emoji9;
					mainview.SendObject(cm);		
				}
		    }  
		});	
		JLabel emo_10 = new JLabel(emoji10);
		panel.add(emo_10);		
		emo_10.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e)  
		    {     
				if(e.getClickCount()==2) {
					ChatMsg cm = new ChatMsg(UserName, "301", "image");
					cm.img=emoji10;
					mainview.SendObject(cm);
				}
				else {
					ChatMsg cm = new ChatMsg(UserName, "302", "panelOn");
					cm.img=emoji10;
					mainview.SendObject(cm);		
				}
		    }  
		});	
	}
}


