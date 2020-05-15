package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.ShipDao;
import model.Ship;
import util.MySqlUtil;
import util.StringUtil;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class ShipAddInterFrm extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTextField shipNameTxt;
	private MySqlUtil mySqlUtil = new MySqlUtil();
	private ShipDao shipDao = new ShipDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShipAddInterFrm frame = new ShipAddInterFrm();
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
	public ShipAddInterFrm() {
		setClosable(true);
		setTitle("船只添加");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("名称：");
		
		shipNameTxt = new JTextField();
		shipNameTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shipAddActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(63)
							.addComponent(lblNewLabel)
							.addGap(35)
							.addComponent(shipNameTxt, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(95)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_1)))
					.addContainerGap(104, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(74)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(shipNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addGap(37))
		);
		getContentPane().setLayout(groupLayout);

	}

	/**
	 * 添加船只Button事件处理
	 * @param evt
	 */
	private void shipAddActionPerformed(ActionEvent evt) {
		String shipName = this.shipNameTxt.getText();
		if(StringUtil.isEmpty(shipName)) {
			JOptionPane.showMessageDialog(null, "船只名称不能为空！");
			return;
		}
		Ship ship = new Ship(shipName);
		Connection con = null;
		try {
			con = mySqlUtil.getCon();
			int n = shipDao.add(con, ship);
			if(n == 1) {
				JOptionPane.showMessageDialog(null, "船只添加成功！");
				this.shipNameTxt.setText("");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "船只添加失败！");
		}finally{
			try {
				mySqlUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 重置Button事件处理
	 * @param evt
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		this.shipNameTxt.setText("");
	}
}
