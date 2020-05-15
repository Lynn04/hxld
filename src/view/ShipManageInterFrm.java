package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ShipDao;
import model.Ship;
import util.MySqlUtil;
import util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class ShipManageInterFrm extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTable shipTable;
	private DefaultTableModel tableModel;
	private MySqlUtil mySqlUtil = new MySqlUtil();
	private ShipDao shipDao = new ShipDao();
	private JTextField searchShipNameTxt;
	private JTextField idTxt;
	private JTextField updateShipNameTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShipManageInterFrm frame = new ShipManageInterFrm();
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
	public ShipManageInterFrm() {
		setClosable(true);
		setTitle("船只管理");
		setBounds(100, 100, 450, 424);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("船只名称：");
		
		searchShipNameTxt = new JTextField();
		searchShipNameTxt.setColumns(10);
		
		JButton searchButton = new JButton("查询");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shipNameSearchActionPerformed(e);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(56, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(searchShipNameTxt, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(searchButton))
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
					.addGap(37))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(searchShipNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchButton))
					.addGap(28)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel_1 = new JLabel("id：");
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setBackground(Color.LIGHT_GRAY);
		idTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("船只名称：");
		
		updateShipNameTxt = new JTextField();
		updateShipNameTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shipUpdateActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shipDeleteActionPerformed(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
					.addGap(32)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1)
						.addComponent(updateShipNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(updateShipNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		String[] headers = {"编号","id", "船只名称"}; 
		Object[][] cellData = null;
		// 以表头和表数据创建表格,并且让表单元格不可改.
		tableModel = new DefaultTableModel(cellData, headers);
		shipTable = new JTable(tableModel); 
		shipTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				shipTableMousePressed(e);
			}
		});
		scrollPane.setViewportView(shipTable);
		getContentPane().setLayout(groupLayout);
		
		this.filltable(new Ship());

	}
	
	/**
	 * 船只删除Button事件处理
	 * @param evt
	 */
	private void shipDeleteActionPerformed(ActionEvent evt) {
		String shipname = updateShipNameTxt.getText();
		if(StringUtil.isEmpty(shipname)) {
			JOptionPane.showMessageDialog(null, "请选择需要修改的记录!");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除这条记录吗？"); // yes==0, no==1, cancel==2
		if(n == 0) {
			Connection con =null;
			try {
				con = mySqlUtil.getCon();
				int deleteNum = shipDao.delete(con, shipname);
				if(deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					this.resetUpdateValue();
					this.filltable(new Ship());
				}else {
					JOptionPane.showMessageDialog(null, "删除失败！");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					mySqlUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 船只修改Button事件处理
	 * @param evt
	 */
	private void shipUpdateActionPerformed(ActionEvent evt) {
		String id = idTxt.getText();
		String shipName = updateShipNameTxt.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择需要修改的记录!");
			return;
		}
		if(StringUtil.isEmpty(shipName)) {
			JOptionPane.showMessageDialog(null, "船只名称不能为空！");
			return;
		}
		Ship ship = new Ship(Integer.parseInt(id),shipName);
		Connection con = null;
		try {
			con = mySqlUtil.getCon();
			int updateNum = shipDao.update(con, ship);
			if(updateNum == 1) {
				JOptionPane.showMessageDialog(null, "修改成功！");
				this.resetUpdateValue();
				this.filltable(new Ship());
			}else {
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败！");
		}finally {
			try {
				mySqlUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 重制表单
	 */
	private void resetUpdateValue() {
		idTxt.setText("");
		updateShipNameTxt.setText("");
	}

	/**
	 * 表格行点击事件处理
	 * @param evt
	 */
	private void shipTableMousePressed(MouseEvent evt) {
		int row = shipTable.getSelectedRow(); // 获取选中行数据
		idTxt.setText((String)shipTable.getValueAt(row, 1));
		updateShipNameTxt.setText((String)shipTable.getValueAt(row, 2));
	}

	/**
	 * 船只名称查询Button事件处理
	 * @param evt
	 */
	private void shipNameSearchActionPerformed(ActionEvent evt) {
		String shipName = searchShipNameTxt.getText();
		Ship ship = new Ship(shipName);
		this.filltable(ship); // 查询实际上就是重复初始化表格，将内容写进表格里
	}

	/**
	 * 初始化表格
	 * @param ship
	 */
	private void filltable(Ship ship) {
		DefaultTableModel dtm= (DefaultTableModel) shipTable.getModel();
		dtm.setRowCount(0); // 设置成第0行
		Connection con = null;
		try {
			con = mySqlUtil.getCon();
			ResultSet rs = shipDao.shiplist(con, ship);
			while(rs.next()) {
				Vector<String> v = new Vector<String>();
				v.add(rs.getInt("rownum")+"");
				v.add(rs.getString("id"));
				v.add(rs.getString("shipname"));
				dtm.addRow(v);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				mySqlUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
