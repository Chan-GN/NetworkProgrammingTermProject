
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.PublicKey;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.color.CMMException;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class JavaObjClientChatRoom extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel EmoLabel;
	private JTextField txtInput;
	private String UserName;
	private JButton btnSend;
	private static final int BUF_LEN = 128; // Windows 처럼 BUF_LEN 을 정의
	private Socket socket; // 연결소켓
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private JLabel lblUserName;
	// private JTextArea textArea;
	private JTextPane textArea;
	
	public EmojiView emoji;
	public JavaObjClientChatRoom view;
	//frame위치
	public int frameX;
	public int frameY;
	public ImageIcon panelIMG;
		
	private Frame frame;
	private FileDialog fd;
	private JButton imgBtn;
	private JButton filebtn;
	private JButton emobtn;
	private JButton listbtn;

	/**
	 * Create the frame.
	 */
	public JavaObjClientChatRoom(String username, String ip_addr, String port_no) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 394, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(186, 206, 224));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 352, 467);
		scrollPane.getViewport().setOpaque(false);
		

		ImageIcon close_img = new ImageIcon("src/closebtn.png");

		//이모티콘 패널
		EmoLabel = new JLabel() {
		    protected void paintComponent(Graphics g)
		    {
		        g.setColor(new Color(255, 255, 255, 90));
		        g.fillRect(0, 0, getWidth(), getHeight());
		        super.paintComponent(g);
		    }
		};
		EmoLabel.setOpaque(false); 
		contentPane.add(EmoLabel);
		
		EmoLabel.setBounds(0, 393, 378, 90);
		
		
		
		EmoLabel.setOpaque(false);
		EmoLabel.setBackground(new Color(192, 192, 192, 80));		
				EmoLabel.setHorizontalAlignment(JLabel.RIGHT);
				contentPane.add(EmoLabel);
				EmoLabel.setVisible(false);
				JButton closebtn = new JButton(close_img);
				closebtn.setBounds(350, 0, 20, 20);
				closebtn.setBorder(BorderFactory.createEmptyBorder());
				//closebtn.setContentAreaFilled(false);
				closebtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EmoLabel.setVisible(false);
						EmoLabel.repaint();
					}
				});
				EmoLabel.add(closebtn);
		scrollPane.setOpaque(false);
		scrollPane.setBorder(null);
		contentPane.add(scrollPane);

		textArea = new JTextPane();
		textArea.setEditable(true);
		textArea.setFont(new Font("굴림체", Font.PLAIN, 14));
		textArea.setOpaque(false);
		scrollPane.setViewportView(textArea);

		txtInput = new JTextField();
		txtInput.setBounds(12, 490, 366, 40);
		txtInput.setBorder(null);
		contentPane.add(txtInput);
		txtInput.setColumns(10);

		btnSend = new JButton("\uC804\uC1A1");
		btnSend.setFont(new Font("굴림", Font.PLAIN, 12));
		btnSend.setBounds(305, 542, 61, 34);
		btnSend.setBackground(new Color(250, 218, 10));
		btnSend.setBorder(null);
		setHandCursor(btnSend);
		contentPane.add(btnSend);

		lblUserName = new JLabel("Name");
		lblUserName.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblUserName.setBackground(Color.WHITE);
		lblUserName.setFont(new Font("굴림", Font.BOLD, 14));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(12, 539, 62, 40);
		// contentPane.add(lblUserName);
		setVisible(true);

		// AppendText("User " + username + " connecting " + ip_addr + " " + port_no);
		UserName = username;
		lblUserName.setText(username);

		
		JButton btnNewButton = new JButton("종 료");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatMsg msg = new ChatMsg(UserName, "400", "Bye");
				SendObject(msg);
				System.exit(0);
			}
		});
		btnNewButton.setBounds(220, 540, 69, 40);
		setHandCursor(btnNewButton);
		// contentPane.add(btnNewButton);
				
		
		// 이미지 버튼 생성
		ImageIcon emobtn_img = new ImageIcon("src/emoticon_btn.png");
		emobtn = new JButton(emobtn_img);
		emobtn.setBounds(10, 550, 25, 25);
		emobtn.setBorder(BorderFactory.createEmptyBorder());
		emobtn.setContentAreaFilled(false);		
		setHandCursor(emobtn);
		contentPane.add(emobtn);

		
		ImageIcon imgbtn_img = new ImageIcon("src/image_btn.png");
		imgBtn = new JButton(imgbtn_img);
		imgBtn.setBounds(45, 550, 25, 25);
		imgBtn.setBorder(BorderFactory.createEmptyBorder());
		imgBtn.setContentAreaFilled(false);
		setHandCursor(imgBtn);
		contentPane.add(imgBtn);
		
		ImageIcon filebtn_img = new ImageIcon("src/file_btn.png");
		filebtn = new JButton(filebtn_img);
		filebtn.setBounds(80, 550, 25, 25);
		filebtn.setBorder(BorderFactory.createEmptyBorder());
		filebtn.setContentAreaFilled(false);
		setHandCursor(filebtn);
		contentPane.add(filebtn);
		
		ImageIcon listbtn_img = new ImageIcon("src/list_btn.png");
		listbtn = new JButton(listbtn_img);
		listbtn.setBounds(115, 550, 25, 25);
		listbtn.setBorder(BorderFactory.createEmptyBorder());
		listbtn.setContentAreaFilled(false);
		setHandCursor(listbtn);
		contentPane.add(listbtn);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 479, 390, 123);
		panel.setBackground(Color.white);
		contentPane.add(panel);
		
		view=this;
				
		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");
			SendObject(obcm);
			
			ListenNetwork net = new ListenNetwork();
			net.start();
			TextSendAction action = new TextSendAction();
			btnSend.addActionListener(action);
			txtInput.addActionListener(action);
			txtInput.requestFocus();
			ImageSendAction action2 = new ImageSendAction();
			imgBtn.addActionListener(action2);
			ListSendAction action3 = new ListSendAction(); 
			listbtn.addActionListener(action3);
			FileSendAction action4 = new FileSendAction();
			filebtn.addActionListener(action4);
			EmoticonSendAction action5 = new EmoticonSendAction();
			emobtn.addActionListener(action5);
			


		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AppendText("connect error");
		}

	}
	
	// Server Message를 수신해서 화면에 표시
	class ListenNetwork extends Thread {
		public void run() {
			while (true) {
				try {
					Object obcm = null;
					String msg = null;
					ChatMsg cm;
										
					try {
						obcm = ois.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
					if (obcm == null)
						break;
					if (obcm instanceof ChatMsg) {
						cm = (ChatMsg) obcm;
						// if(cm.getId().equals(UserName))
							// 오른쪽에 출력
						msg = String.format("[%s] %s", cm.getId(), cm.getData());
					} else
						continue;
					switch (cm.getCode()) {
					case "200": // chat message
						AppendText(msg);
						break;				
					case "300": // Image 첨부
						AppendText("[" + cm.getId() + "]");
						AppendImage(cm.img);
						break;
					case "301": // 더블클릭
						AppendText("[" + cm.getId() + "]");
						AppendImage(cm.img);
						break;
					case "302": // 한번 클릭
						panelIMG=cm.img;
						EmoLabel.setVisible(true);
						EmoLabel.setIcon(cm.img);
						EmoLabel.repaint();
						break;
					case "500":
						AppendText("[" + cm.getId() + "] " + cm.filename);
						AppendFile(cm.file, cm.filename);
						break;					
					}
				} catch (IOException e) {
					AppendText("ois.readObject() error");
					try {
						ois.close();
						oos.close();
						socket.close();

						break;
					} catch (Exception ee) {
						break;
					} // catch문 끝
				} // 바깥 catch문끝

			}
		}
	}

	// keyboard enter key 치면 서버로 전송
	class TextSendAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Send button을 누르거나 메시지 입력하고 Enter key 치면
			if (e.getSource() == btnSend || e.getSource() == txtInput) {
				String msg = null;
				// msg = String.format("[%s] %s\n", UserName, txtInput.getText());
				msg = txtInput.getText();
				if(msg.equals("(하이)")) { // 임시
					ChatMsg obcm = new ChatMsg(UserName, "300", "EMOTICON");
					obcm.setImg(icon1);
					SendObject(obcm);
				} else if(msg.equals("(히히)")) {
					ChatMsg obcm = new ChatMsg(UserName, "300", "EMOTICON");
					obcm.setImg(icon2);
					SendObject(obcm);
				} else if(EmoLabel.isVisible()) {
					ChatMsg obcm = new ChatMsg(UserName, "300", "Emoji");
					obcm.setImg(panelIMG);
					SendObject(obcm);
					SendMessage(msg);
				}
				else {				
					SendMessage(msg);
				}
				txtInput.setText(""); // 메세지를 보내고 나면 메세지 쓰는창을 비운다.
				txtInput.requestFocus(); // 메세지를 보내고 커서를 다시 텍스트 필드로 위치시킨다
				if (msg.contains("/exit")) // 종료 처리
					System.exit(0);
			}
		}
	}

	class ImageSendAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에서 Enter key 치면
			if (e.getSource() == imgBtn) {
				frame = new Frame("이미지첨부");
				fd = new FileDialog(frame, "이미지 선택", FileDialog.LOAD);
				fd.setVisible(true);
				
				if(fd.getFile() == null) {
//					System.out.println("취소됨");
				} else {
					ChatMsg obcm = new ChatMsg(UserName, "300", "IMG");
					ImageIcon img = new ImageIcon(fd.getDirectory() + fd.getFile());
					obcm.setImg(img);
					SendObject(obcm);
				}
				
			}
		}
	}
	
	
	
	class FileSendAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// 액션 이벤트가 sendBtn일때 또는 textField 에서 Enter key 치면
			if (e.getSource() == filebtn) {
				frame = new Frame("파일첨부");
				fd = new FileDialog(frame, "파일 선택", FileDialog.LOAD);
				fd.setVisible(true);
				/* 파일 전송 로직 */
				if(fd.getFile() == null) {
//					System.out.println("취소됨");
				} else {
					ChatMsg obcm = new ChatMsg(UserName, "500", "FILE");
					File file = new File(fd.getDirectory() + fd.getFile()); // 파일 경로 및 이름
					try {
						FileInputStream fis = new FileInputStream(file);
						byte b[] = new byte[fis.available()]; 
						fis.read(b); // 실제 파일 내용 읽기
						obcm.setFile(b); // 실제 파일 내용 저장
						obcm.setFilename(fd.getFile()); // 파일 이름 저장
						SendObject(obcm);
						fis.close();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
	class ListSendAction implements ActionListener { // 리스트 버튼 클릭 리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == listbtn) {
				ChatMsg obcm = new ChatMsg(UserName, "600", "LIST"); // 600 전송
				SendObject(obcm);
			}
		}
	}
	
	class EmoticonSendAction implements ActionListener { // 이모티콘 버튼 클릭 리스너
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == emobtn) {
				frameX=getBounds().x;
				frameY=getBounds().y;
				if(emoji==null) {
				emoji = new EmojiView(UserName, view);
				}
				else {
					emoji.dispose();
					emoji=null;
				}
			}
		}
	}
	
	ImageIcon icon1 = new ImageIcon("src/icon1.jpg");
	ImageIcon icon2 = new ImageIcon("src/icon2.jpg");
	private JPanel panel_1;


	public void AppendIcon(ImageIcon icon) {
		int len = textArea.getDocument().getLength();
		// 끝으로 이동
		textArea.setCaretPosition(len);
		textArea.insertIcon(icon);
	}



	// 화면에 출력
	public void AppendText(String msg) {
		msg = msg.trim(); // 앞뒤 blank와 \n을 제거한다.
		int len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len);
		textArea.replaceSelection(msg + "\n");
		
		
		// 받은 문자열에서 이름 분리
		String[] msg_c = msg.split(" ");
		String username = msg_c[0];
		if(("["+this.UserName+"]").equals(username)) { // 보낸 사람이 자신이라면 오른쪽 정렬
			alignLeft();
		} else { // 보낸 사람이 타인이라면 왼쪽 정렬
			alignRight();
		}
	}

	public void alignLeft() {
		StyledDocument doc = textArea.getStyledDocument();
		SimpleAttributeSet right = new SimpleAttributeSet();
		StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setForeground(right, Color.blue);
		doc.setParagraphAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd(), right, false);
	}
	
	public void alignRight() {
		StyledDocument doc = textArea.getStyledDocument();
		SimpleAttributeSet left = new SimpleAttributeSet();
		StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
		StyleConstants.setForeground(left, Color.black);
		doc.setParagraphAttributes(textArea.getSelectionStart(), textArea.getSelectionEnd(), left, false);		
	}
	
	public void setHandCursor(JButton btn) { // 버튼에 커서 올릴 시 커서 변경하는 메소드
		btn.getCursor();
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public void AppendFile(byte[] file, String filename) { // 파일 내용, 파일 전송 유저, 파일 이름
		int len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len); // place caret at the end (with no selection)
		ImageIcon filebtn_img = new ImageIcon("src/filedown_btn.png"); 
		JButton file_yes = new JButton(filebtn_img); // 파일 수신 패널의 이미지 버튼
		setHandCursor(file_yes);
		file_yes.setBorder(BorderFactory.createEmptyBorder());
		file_yes.setContentAreaFilled(false);

		JLabel file_name = new JLabel(filename);
		file_name.setFont(new Font("굴림체", Font.BOLD, 14)); // 파일 수신 패널의 파일 이름
			
		JPanel file_panel = new JPanel();
		file_panel.setBackground(Color.white);
		file_panel.add(file_name); // 파일 이름 추가
		file_panel.add(file_yes); // 파일 다운 버튼 추가

		String ext = filename.substring(filename.lastIndexOf(".")); // 파일 확장자 획득
			
		file_yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 버튼 클릭시 파일 다운 Dialog 실행
				try {
					frame = new Frame("파일 저장");
					fd = new FileDialog(frame, "파일 저장할 디렉토리", FileDialog.LOAD);
					fd.setVisible(true);
					// 선택된 디렉터리에 입력된 파일 이름으로 파일 저장 ( 확장자 추가 )
					if(fd.getFile() == null) {
//						System.out.println("취소됨");
					} else {
						File recvfile = new File(fd.getDirectory() +"[" + UserName + "] " + fd.getFile() + ext);
						FileOutputStream fos = new FileOutputStream(recvfile);
						fos.write(file);
						fos.flush();
						fos.close();						
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}						
			}
		});
			
		textArea.insertComponent(file_panel);
			
		len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len);
		textArea.replaceSelection("\n");
	}
	
	public void AppendImage(ImageIcon ori_icon) {
		int len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len); // place caret at the end (with no selection)
		Image ori_img = ori_icon.getImage();
		int width, height;
		double ratio;
		width = ori_icon.getIconWidth();
		height = ori_icon.getIconHeight();
		// Image가 너무 크면 최대 가로 또는 세로 250 기준으로 축소시킨다.
		if (width > 250 || height > 250) {
			if (width > height) { // 가로 사진
				ratio = (double) height / width;
				width = 200;
				height = (int) (width * ratio);
			} else { // 세로 사진
				ratio = (double) width / height;
				height = 200;
				width = (int) (height * ratio);
			}
			Image new_img = ori_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon new_icon = new ImageIcon(new_img);
			textArea.insertIcon(new_icon);
		} else
			textArea.insertIcon(ori_icon);
		len = textArea.getDocument().getLength();
		textArea.setCaretPosition(len);
		textArea.replaceSelection("\n");
	}

	// Windows 처럼 message 제외한 나머지 부분은 NULL 로 만들기 위한 함수
	public byte[] MakePacket(String msg) {
		byte[] packet = new byte[BUF_LEN];
		byte[] bb = null;
		int i;
		for (i = 0; i < BUF_LEN; i++)
			packet[i] = 0;
		try {
			bb = msg.getBytes("euc-kr");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		for (i = 0; i < bb.length; i++)
			packet[i] = bb[i];
		return packet;
	}

	// Server에게 network으로 전송
	public void SendMessage(String msg) {
		EmoLabel.setVisible(false);
		try {
			ChatMsg obcm = new ChatMsg(UserName, "200", msg);
			oos.writeObject(obcm);
		} catch (IOException e) {
			AppendText("oos.writeObject() error");
			try {
				ois.close();
				oos.close();
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.exit(0);
			}
		}
	}


	public void SendObject(Object ob) { // 서버로 메세지를 보내는 메소드
		EmoLabel.setVisible(false);
		try {
			oos.writeObject(ob);
		} catch (IOException e) {
			AppendText("SendObject Error");
		}
	}
}
