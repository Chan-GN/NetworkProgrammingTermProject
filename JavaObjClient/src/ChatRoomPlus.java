import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ChatRoomPlus extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JavaObjClientMain mainview;
	public ChatRoomPlus view;
	public JTextPane ulArea;
	public List<ChatRoomPlusUser> userslist = new ArrayList<ChatRoomPlusUser>();

	/**
	 * Create the frame.
	 * @param testview 
	 * @param con_user_list 
	 */
	@SuppressWarnings("null")
	public ChatRoomPlus(String myname, String con_user_list, JavaObjClientMain testview) {
		mainview = testview;
		setBounds(100, 100, 350, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JScrollPane ul_scrollPane = new JScrollPane();
		ul_scrollPane.setBounds(27, 65, 284, 379);
		ul_scrollPane.getViewport().setOpaque(false);
		ul_scrollPane.setOpaque(false);
		ul_scrollPane.setBorder(null);
		contentPane.add(ul_scrollPane);
		
		ulArea = new JTextPane();
		ulArea.setEditable(true);
		ulArea.setFont(new Font("굴림체", Font.PLAIN, 14));
		ulArea.setOpaque(false);
		ul_scrollPane.setViewportView(ulArea); // scrollpane에 ulArea 추가
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel selectUsers = new JLabel("대화상대 선택");
		selectUsers.setBounds(27, 23, 114, 30);
		contentPane.add(selectUsers);
		
		JButton confirmBtn = new JButton("확인");
		confirmBtn.setBounds(117, 454, 91, 36);
		contentPane.add(confirmBtn);
		
		JButton cancelBtn = new JButton("취소");
		cancelBtn.setBounds(220, 454, 91, 36);
		contentPane.add(cancelBtn);
		
		con_user_list.trim();
		String[] user = con_user_list.split(" ");
		for(int i=0; i<user.length; i++) {
			ChatRoomPlusUser users = new ChatRoomPlusUser(user[i]);
			if(user[i].equals(myname)) {
				users.checkBox.setSelected(true);
				users.checkBox.setEnabled(false);
			}
			userslist.add(users);
			ulArea.insertComponent(users);
		}
		
		confirmBtn.addActionListener(new ActionListener(){
			// 여기서부터 진행
			@Override
			public void actionPerformed(ActionEvent e) {
				String userlist = "";
				for(ChatRoomPlusUser username : userslist) {
					if(username.checkBox.isSelected()) {
//						System.out.println(username.getUserName());
						userlist += username.getUserName() + " ";
					}
				}
				mainview.SendRoomId(userlist);
				dispose();
			}
		});
		
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		setVisible(true);
	}
}
