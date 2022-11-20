import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FriendListPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7410115339163008109L;
	private JLabel FriendList_username;
	public JButton profileBtn;
	private Frame frame;
	private FileDialog fd;
	public JavaObjClientMain mainview; // 뷰의 SendObject 활용을 위해
	public FriendListPanel view;
	/**
	 * Create the panel.
	 */
	public FriendListPanel(ImageIcon profile, String username, JavaObjClientMain testview) { // 뷰를 인자로 얻어옴
		mainview = testview;
		setLayout(null); // absolute layout
		setBackground(Color.white);
		setPreferredSize(new Dimension(320,70)); // 높이 설정만 가능한듯 ?
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
		FriendList_username = new JLabel(username);
		FriendList_username.setBounds(111, 14, 169, 30);
		FriendList_username.setFont(new Font("굴림체", Font.PLAIN, 14));
		add(FriendList_username);

		profileBtn = new JButton(profile);
		profileBtn.setBounds(12, 14, 77, 46);
		profileBtn.setBorderPainted(false);
		profileBtn.setContentAreaFilled(false);
		profileBtn.setFocusPainted(false);
		add(profileBtn);

		profileBtn.addMouseListener(new MouseListener() {			
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

		profileBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(username.equals(mainview.UserName)) { // 패널의 이름과 메인뷰의 이름이 같은 경우, 즉 본인일 경우에만 변경 가능케
					if (e.getSource() == profileBtn) {
						frame = new Frame("프로필 사진 변경");
						fd = new FileDialog(frame, "프로필 사진 선택", FileDialog.LOAD);
						fd.setVisible(true);
						
						if(fd.getFile() == null) {
//							System.out.println("취소됨");
						} else {
							ChatMsg obcm = new ChatMsg(username, "888", "PROFILEIMG"); // 사진이 선택되면 888 코드와
							ImageIcon img = new ImageIcon(fd.getDirectory() + fd.getFile()); // 변경하고자 하는 이미지 아이콘을
							obcm.setImg(img); // 객체에 담아
							mainview.SendObject(obcm); // 메인뷰의 SendObject 호출
						}	
					}
				}
			}
			
		});
	}
	public String getFriendList_username() {
		return FriendList_username.getText();
	}
}