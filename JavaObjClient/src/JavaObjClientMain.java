
// JavaObjClientView.java ObjecStram 기반 Client
//실질적인 채팅 창
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;


public class JavaObjClientMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public String UserName;
	
	/* test code */
	private Socket socket; // 연결소켓
	public JavaObjClientMain testview; // view 상속을 위해 view 선언
	public List<JavaObjClientChatRoom> testchatviews = new ArrayList<JavaObjClientChatRoom>(); // 클라이언트의 채팅방을 담아두는 리스트
	private JTextPane chatRoomArea; // scrollpane 내부에 하위의 chatRoomBox를 담아줄 친구
	private JTextPane friendListArea;
	private String test_roomid = ""; // 고유한 룸 아이디
	private List<FriendListPanel> friend_lists = new ArrayList<FriendListPanel>(); // 친구 리스트 패널을 담을 리스트
	private List<ChatRoomBoxTest> chatRoomlists = new ArrayList<ChatRoomBoxTest>(); // 채팅방 리스트를 담을 리스트
	
	public String[] user_list;
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public JButton btnfriend;
	public JButton btnchat;
	//test//
	public ImageIcon profileBasic = new ImageIcon("src/profileIMG_basic.png");
	public String[] usertemp;
	public JTextPane myprofile;
	public int count=0;
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
		
		/* test code */
		chatRoomArea = new JTextPane();
		chatRoomArea.setEditable(true);
		chatRoomArea.setFont(new Font("굴림체", Font.PLAIN, 2));
		chatRoomArea.setOpaque(false);
		chat_scrollPane.setViewportView(chatRoomArea); // scrollpane에 chatRoomArea 추가

		JScrollPane myprofileArea = new JScrollPane();
		myprofileArea.setBounds(64, 65, 320, 94);
		myprofileArea.getViewport().setOpaque(false);
		myprofileArea.setOpaque(false);
		myprofileArea.setBorder(null);		
		contentPane.add(myprofileArea);

		myprofile = new JTextPane();
		myprofile.setEditable(true);
		myprofile.setBackground(Color.white);
		myprofile.setBounds(64, 156, 320, -90);
		myprofile.setFont(new Font("굴림체", Font.PLAIN, 14));
		myprofile.setOpaque(false);
		myprofile.setLayout(null);
		myprofileArea.setViewportView(myprofile);

		myprofileArea.setVisible(false);

		JLabel myprofilename = new JLabel(username);
		myprofilename.setBounds(120, -70, 65, 18);
		myprofilename.setFont(new Font("굴림체", Font.PLAIN, 14));
		myprofile.add(myprofilename);

		JButton myprofileBtn = new JButton(profileBasic);
		myprofileBtn.setBounds(30, -50, 65, 18);
		myprofileBtn.setBorderPainted(false);
		myprofileBtn.setContentAreaFilled(false);
		myprofileBtn.setFocusPainted(false);
		myprofile.add(myprofileBtn);
		
		myprofile = new JTextPane();
		myprofile.setEditable(true);
		myprofile.setBackground(Color.white);
		myprofile.setBounds(64, 156, 320, -90);
		myprofile.setFont(new Font("굴림체", Font.PLAIN, 14));
		myprofile.setOpaque(false);
		myprofile.setLayout(null);
		myprofileArea.setViewportView(myprofile);
		
		myprofileArea.setVisible(false);
		
		JScrollPane friend_scrollPane = new JScrollPane();
		friend_scrollPane.setBounds(64, 143, 320, 450);
		friend_scrollPane.getViewport().setOpaque(false);
		friend_scrollPane.setOpaque(false);
		friend_scrollPane.setBorder(null);		
		contentPane.add(friend_scrollPane);

		friendListArea = new JTextPane();
		friendListArea.setEditable(true);
		friendListArea.setFont(new Font("굴림체", Font.PLAIN, 14));
		friendListArea.setOpaque(false);
		friend_scrollPane.setViewportView(friendListArea); // scrollpane에 chatRoomArea 추가
				
		friendListArea = new JTextPane();
		friendListArea.setEditable(true);
		friendListArea.setFont(new Font("굴림체", Font.PLAIN, 14));
		friendListArea.setOpaque(false);
		friend_scrollPane.setViewportView(friendListArea); // scrollpane에 chatRoomArea 추가

		JLabel lblNewLabel_1 = new JLabel("친구"); // 테스트 라벨
		friend_scrollPane.setColumnHeaderView(lblNewLabel_1);
		friend_scrollPane.setVisible(false);
				
		JLabel chatLabel = new JLabel("채팅"); // 디폴트 : 채팅
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
		btnfriend.addActionListener(new ActionListener() { // 친구 버튼 을 누르면
			public void actionPerformed(ActionEvent e) {
				chat_scrollPane.setVisible(false);
				friend_scrollPane.setVisible(true);
				myprofileArea.setVisible(true);
				chatLabel.setText("친구"); // 친구로 라벨 변경
				btnfriend.setIcon(friend_icon_c);
				btnchat.setIcon(chat_icon_n);
				// test code, 친구 버튼 클릭 시 서버에게 접속자 리스트 요청
//				ChatMsg obcm = new ChatMsg(UserName, "600", "LIST");
//				SendObject(obcm);
			}
		});
		btnfriend.addMouseListener(new MouseListener() { // 버튼에 커서 이벤트 적용
			
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
				if(btnfriend.getIcon() != null && btnfriend.getIcon().toString() != "src/friendbtn_c.png")
					btnfriend.setIcon(friend_icon_n);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				if(btnfriend.getIcon() != null && btnfriend.getIcon().toString() != "src/friendbtn_c.png")
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
		btnchat.addActionListener(new ActionListener() { // 채팅 버튼을 누르면
			public void actionPerformed(ActionEvent e) {
				chat_scrollPane.setVisible(true);
				friend_scrollPane.setVisible(false);
				myprofileArea.setVisible(false);
				chatLabel.setText("채팅"); // 채팅으로 라벨 변경
				btnfriend.setIcon(friend_icon_n);
				btnchat.setIcon(chat_icon_c);
			}
		});
		btnchat.addMouseListener(new MouseListener() { // 버튼에 커서 이벤트 적용
			
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
		btnchatplus.addActionListener(new ActionListener() { // 채팅방 추가 버튼 클릭 리스너
			@Override
			public void actionPerformed(ActionEvent e) { // 버튼이 눌리면
				ChatMsg obcm = new ChatMsg(UserName, "601", "LIST");
				SendObject(obcm);
				
				// 추후 사용 예정 로직
//				Date now = new Date(); // 현재 날짜 및 시간을 계산해서
//				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd/HH-mm-ss초"); // 형식을 정하고
//				test_roomid = formatter.format(now); // 계산된 시간을 형식에 적용
//				SendRoomId(test_roomid); // 서버로 채팅방 이름 보냄
//				testchatviews.add(new JavaObjClientChatRoom(username, test_roomid, testview)); // 채팅방에는 유저 이름과 채팅방 이름, 현재 유저의 Mainview 전달, 추후 채팅방 유저 리스트 추가 요망
//				for(JavaObjClientChatRoom testchatview : testchatviews) {
////					System.out.println(testchatview.getRoomId());
//				}
			}
		});
		contentPane.add(btnchatplus);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 65, 593);
		panel.setBackground(new Color(236, 236, 237));
		contentPane.add(panel);
		
		setVisible(true);
		
		UserName = username;
		testview = this; // 뷰 상속을 위해
		
		try {
			socket = new Socket(ip_addr, Integer.parseInt(port_no));

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

			ChatMsg obcm = new ChatMsg(UserName, "100", "Hello");
			SendObject(obcm);
			
			ListenNetwork net = new ListenNetwork();
			net.start();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			AppendText("connect error");
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
							for(JavaObjClientChatRoom testchatview : testchatviews) { // 유저의 채팅방들을 돌며
								if(testchatview.getRoomId().equals(cm.getRoomId())) { // 채팅방 이름을 검색해서 
									testchatview.AppendText(msg); // 해당하는 채팅방에 AppendText 호출
								}
							}
							break;
						case "300": // Image 첨부
							for(JavaObjClientChatRoom testchatview : testchatviews) { // 유저의 채팅방들을 돌며
								if(testchatview.getRoomId().equals(cm.getRoomId())) { // 채팅방 이름을 검색해서 
										testchatview.AppendText("[" + cm.getId() + "]");
										testchatview.AppendImage(cm.img);
								}
							}
							break;
						case "301": // 더블클릭
							for(JavaObjClientChatRoom testchatview : testchatviews) { // 유저의 채팅방들을 돌며
								if(testchatview.getRoomId().equals(cm.getRoomId())) { // 채팅방 이름을 검색해서 
										testchatview.AppendText("[" + cm.getId() + "]");
										testchatview.AppendImage(cm.img);
								}
							}
							break;
						case "302": // 한번 클릭
							for(JavaObjClientChatRoom testchatview : testchatviews) { // 유저의 채팅방들을 돌며
								if(testchatview.getRoomId().equals(cm.getRoomId())) { // 채팅방 이름을 검색해서 
									testchatview.panelIMG=cm.img;
									testchatview.EmoLabel.setVisible(true);
									testchatview.EmoLabel.setIcon(cm.img);
									testchatview.EmoLabel.repaint();
								}
							}
							break;
						case "500":
							for(JavaObjClientChatRoom testchatview : testchatviews) { // 유저의 채팅방들을 돌며
								if(testchatview.getRoomId().equals(cm.getRoomId())) { // 채팅방 이름을 검색해서 
									testchatview.AppendText("[" + cm.getId() + "] " + cm.filename);
									testchatview.AppendFile(cm.file, cm.filename);
								}
							}
							break;
						case "601": // 현재 접속한 유저 리스트를 받음
//							System.out.println(UserName + cm.con_user_list);
							new ChatRoomPlus(UserName, cm.con_user_list, testview);
							break;
						case "777": // 클라에서 유저 Login 인식하면
							String uls[] = cm.getData().split(","); // uls[n] = username OFF, 유저 분리
							myprofile.setText(""); // JTextPane 초기화
							friendListArea.setText(""); // JTextPane 초기화
							friend_lists.clear(); // 리스트 초기화
 							for(int i = 0; i < uls.length; i++) {
								String uis[] = uls[i].split(" "); // uis[0] = username, uis[1] = userstatus
								FriendListPanel f = new FriendListPanel(profileBasic, uis[0], testview, uis[1]); // 패널 추가 시 상태도 전달
								if(uis[0].equals(UserName)) { // 이름이 같으면 마이프로필에 추가
									myprofile.insertComponent(f);
								}
								else {
									friendListArea.insertComponent(f);											
								}
								friend_lists.add(f); // 리스트에 추가
							}							
							break;
						case "888": // 888을 수신받으면
							for(FriendListPanel fl : friend_lists) { // 리스트 루프를 돌며
								if(fl.getFriendList_username().equals(cm.getId())) { // 패널의 이름과 이름이 같은 친구를 찾아서 ( 즉 변경 요청 본인 )
									fl.profileBtn.setIcon(cm.img); // 이미지 설정
//									System.out.println(UserName + " 님 방 " + cm.getId());
								}
							}
							for(ChatRoomBoxTest chatRoomlist: chatRoomlists) {
//								System.out.println(UserName + " " + chatRoomlist.getChatroombox_title());
								String[] ul = chatRoomlist.getChatroombox_title().trim().split(", ");
								if(ul.length == 1) {
									for(int i=0; i<ul.length; i++) {
										if(cm.getId().equals(ul[i])) {
//											System.out.println(cm.getId());
											chatRoomlist.usersPfImgOne.setIcon(cm.img);
										}
									}									
								} else if(ul.length == 2) {
									for(int i=0; i<ul.length; i++) {
										if(cm.getId().equals(ul[i])) {
//											System.out.println(cm.getId());
											if(cm.getId().equals(chatRoomlist.usersPfImgTwo_1.getText()))
												chatRoomlist.usersPfImgTwo_1.setIcon(cm.img);
											else
												chatRoomlist.usersPfImgTwo_2.setIcon(cm.img);
										}
									}									
								} else if(ul.length == 3) {
									for(int i=0; i<ul.length; i++) {
										if(cm.getId().equals(ul[i])) {
//											System.out.println(cm.getId());
											if(cm.getId().equals(chatRoomlist.usersPfImgTh_1.getText()))
												chatRoomlist.usersPfImgTh_1.setIcon(cm.img);
											else if(cm.getId().equals(chatRoomlist.usersPfImgTh_2.getText()))
												chatRoomlist.usersPfImgTh_2.setIcon(cm.img);
											else
												chatRoomlist.usersPfImgTh_3.setIcon(cm.img);
										}
									}									
								} else {
									for(int i=0; i<ul.length; i++) {
										if(cm.getId().equals(ul[i])) {
//											System.out.println(cm.getId());
											if(cm.getId().equals(chatRoomlist.usersPfImgF_1.getText()))
												chatRoomlist.usersPfImgF_1.setIcon(cm.img);
											else if(cm.getId().equals(chatRoomlist.usersPfImgF_2.getText()))
												chatRoomlist.usersPfImgF_2.setIcon(cm.img);
											else if(cm.getId().equals(chatRoomlist.usersPfImgF_3.getText()))
												chatRoomlist.usersPfImgF_3.setIcon(cm.img);
											else
												chatRoomlist.usersPfImgF_4.setIcon(cm.img);
										}
									}									
								}
							}
							for(JavaObjClientChatRoom testchatview : testchatviews) {
								String[] ul = testchatview.roomUserlist.trim().split(" ");
								if(ul.length == 1) {
									for(int i=0; i<ul.length; i++) {
										if(cm.getId().equals(ul[i])) {
//											System.out.println(cm.getId());
											testchatview.usersPfImgOne.setIcon(cm.img);
										}
									}									
								} else if(ul.length == 2) {
									for(int i=0; i<ul.length; i++) {
										if(cm.getId().equals(ul[i])) {
//											System.out.println(cm.getId());
											if(cm.getId().equals(testchatview.usersPfImgTwo_1.getText()))
												testchatview.usersPfImgTwo_1.setIcon(cm.img);
											else
												testchatview.usersPfImgTwo_2.setIcon(cm.img);
										}
									}									
								} else if(ul.length == 3) {
									for(int i=0; i<ul.length; i++) {
										if(cm.getId().equals(ul[i])) {
//											System.out.println(cm.getId());
											if(cm.getId().equals(testchatview.usersPfImgTh_1.getText()))
												testchatview.usersPfImgTh_1.setIcon(cm.img);
											else if(cm.getId().equals(testchatview.usersPfImgTh_2.getText()))
												testchatview.usersPfImgTh_2.setIcon(cm.img);
											else
												testchatview.usersPfImgTh_3.setIcon(cm.img);
										}
									}									
								} else {
									for(int i=0; i<ul.length; i++) {
										if(cm.getId().equals(ul[i])) {
//											System.out.println(cm.getId());
											if(cm.getId().equals(testchatview.usersPfImgF_1.getText()))
												testchatview.usersPfImgF_1.setIcon(cm.img);
											else if(cm.getId().equals(testchatview.usersPfImgF_2.getText()))
												testchatview.usersPfImgF_2.setIcon(cm.img);
											else if(cm.getId().equals(testchatview.usersPfImgF_3.getText()))
												testchatview.usersPfImgF_3.setIcon(cm.img);
											else
												testchatview.usersPfImgF_4.setIcon(cm.img);
										}
									}									
								}
							}
							break;
						case "999": // 코드가 999라면 채팅방 정보를 담고 있는 패널을 채팅방 목록에 추가함
							int len = chatRoomArea.getDocument().getLength();
							chatRoomArea.setCaretPosition(len); // place caret at the end (with no selection)
							String room_title = cm.selected_userlist.trim();
							room_title = room_title.replace(" ", ", ");
							ChatRoomBoxTest chatroombox_test = new ChatRoomBoxTest(room_title, profileBasic);
							chatRoomlists.add(chatroombox_test);
//							System.out.println(cm.getData());
							chatroombox_test.addMouseListener(new MouseListener() { // 채팅방 클릭 리스너
								@Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void mousePressed(MouseEvent e) { 
									// TODO Auto-generated method stub
									if (e.getClickCount()==2){ // 두번 클릭하면
										testchatviews.add(new JavaObjClientChatRoom(UserName, cm.getData(), testview, cm.selected_userlist)); // cm.getData()에는 채팅방 이름이 담겨 있고 해당 채팅방 띄우기
									}
								}

								@Override
								public void mouseReleased(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void mouseEntered(MouseEvent e) {
									// TODO Auto-generated method stub
								}

								@Override
								public void mouseExited(MouseEvent e) {
									// TODO Auto-generated method stub
								}			
							});

							chatRoomArea.insertComponent(chatroombox_test);
							chatRoomArea.replaceSelection("\n");
							String username = cm.getId();
							if(UserName.equals(username)) {
									testchatviews.add(new JavaObjClientChatRoom(username, test_roomid, testview, cm.selected_userlist));
							}
							break;
						}
					}
				 catch (IOException e) {
//					AppendText("ois.readObject() error");
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
	public void SendObject(Object ob) { // 서버로 메세지를 보내는 메소드
		try {
			oos.writeObject(ob);
		} catch (IOException e) {
//			AppendText("SendObject Error");
		}
	}
	
	/* test code */
	public void SendRoomId(String userlist) { // 버튼 클릭 시 
		try {
			Date now = new Date(); // 현재 날짜 및 시간을 계산해서
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd/HH-mm-ss초"); // 형식을 정하고
			test_roomid = formatter.format(now); // 계산된 시간을 형식에 적용
			ChatMsg obcm = new ChatMsg(UserName, "999", test_roomid);
			obcm.selected_userlist = userlist;
			oos.writeObject(obcm); // 여기서부터 하면됨 서버에서 수신하는지?
		} catch (IOException e) {
//			AppendText("SendObject Error");
		}
	}


}
