package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrm extends JFrame {

	private static final long serialVersionUID = 1L; // 序列化时为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性。两种生成方式，1L为默认
	private JPanel contentPane;
	private JDesktopPane desktopPane = new JDesktopPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrm() {
		setTitle("班次管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuItem = new JMenu("菜单");
		menuBar.add(menuItem);
		
		JMenu timetableMenu = new JMenu("班次管理");
		menuItem.add(timetableMenu);
		
		JMenuItem timetableSearchItem = new JMenuItem("班次查询");
		timetableSearchItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				planSearchActionPerformed(e);
			}
		});
		timetableMenu.add(timetableSearchItem);
		
		JMenuItem timetableManegeItem = new JMenuItem("鸭蛋山班次维护");
		timetableManegeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timetableSetActionPerformed(e);
			}
		});
		timetableMenu.add(timetableManegeItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("白峰班次维护");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bftimetableSetActionPerformed(e);
			}
		});
		timetableMenu.add(mntmNewMenuItem_2);
		
		
		JMenu mnNewMenu = new JMenu("船只管理");
		menuItem.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("船只添加");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShipAddInterFrm shipAddInterFrm = new ShipAddInterFrm();
				shipAddInterFrm.setVisible(true);
				desktopPane.add(shipAddInterFrm);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("船只维护");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShipManageInterFrm shipManageInterFrm = new ShipManageInterFrm();
				shipManageInterFrm.setVisible(true);
				desktopPane.add(shipManageInterFrm);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem ExitItem = new JMenuItem("退出");
		menuBar.add(ExitItem);
		ExitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitActionPerformed(e);
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	private void bftimetableSetActionPerformed(ActionEvent evt) {
		BFTimetableManageInterFrm timetableManageInterFrm = new BFTimetableManageInterFrm();
		timetableManageInterFrm.setVisible(true);
		desktopPane.add(timetableManageInterFrm);
		
	}

	private void planSearchActionPerformed(ActionEvent evt) {
		TimetableSearchInterFrm timetableSearchInterFrm = new TimetableSearchInterFrm();
		timetableSearchInterFrm.setVisible(true);
		desktopPane.add(timetableSearchInterFrm);
	}

	/**
	 * 班次维护menuItem事件处理
	 * @param evt
	 */
	private void timetableSetActionPerformed(ActionEvent evt) {
		TimetableManageInterFrm timetableManageInterFrm = new TimetableManageInterFrm();
		timetableManageInterFrm.setVisible(true);
		desktopPane.add(timetableManageInterFrm);
	}

	/**
	 * 安全退出menuItem事件处理
	 */
	private void exitActionPerformed(ActionEvent evt) {
		int exitResult = JOptionPane.showConfirmDialog(null, "确认退出？"); // yes=0; no=1; cancel=2
		//System.out.println("yes"+exitResult);
		if(exitResult == 0) {
			dispose();
		}
	}
}
