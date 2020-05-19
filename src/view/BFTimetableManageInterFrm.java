package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.BFPlanDao;
import model.Plan;
import util.MySqlUtil;
import util.StringUtil;

public class BFTimetableManageInterFrm extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField addTimeTxt;
	private JTextField idTxt;
	private JTextField updateTimeTxt;
	private JTable table;
	private DefaultTableModel tableModel;
	private MySqlUtil mySqlUtil = new MySqlUtil();
	private BFPlanDao planDao = new BFPlanDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BFTimetableManageInterFrm frame = new BFTimetableManageInterFrm();
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
	public BFTimetableManageInterFrm() {
		
		setClosable(true);
		setTitle("班次管理");
		setBounds(100, 100, 450, 481);

JLabel lblNewLabel = new JLabel("计划时间：");
		
		addTimeTxt = new JTextField();
		addTimeTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeAddActionPerformed(e);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(72, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(addTimeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(54))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addTimeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(btnNewButton))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		String[] headers = {"班次","id", "计划时间"}; 
		Object[][] cellData = null;
		table = new JTable();
		tableModel = new DefaultTableModel(cellData, headers);
		// 以表头和表数据创建表格,并且让表单元格不可改.
		table = new JTable(tableModel); 
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				tableMousePressed(e);
			}
		});
		this.filltable(new Plan());
		scrollPane.setViewportView(table);
		
		
		JLabel lblNewLabel_1 = new JLabel("id：");
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setBackground(Color.LIGHT_GRAY);
		idTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("时间：");
		
		updateTimeTxt = new JTextField();
		updateTimeTxt.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateActionPerformed(e);
			}
		});
		
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTimeActionPerformed(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(updateTimeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(10, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(btnNewButton_2)
					.addGap(47))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(updateTimeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);

	}

	/**
	 * 删除Button时间处理
	 * @param evt
	 */
	private void deleteTimeActionPerformed(ActionEvent evt) {
		String id = idTxt.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择需要修改的记录!");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除这条记录吗？"); // yes==0, no==1, cancel==2
		if(n == 0) {
			Connection con =null;
			try {
				con = mySqlUtil.getCon();
				int deleteNum = planDao.delete(con, id);
				if(deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "删除成功！");
					this.resetUpdateValue();
					this.filltable(new Plan());
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
	 * 修改Button时间处理
	 * @param evt
	 */
	private void updateActionPerformed(ActionEvent evt) {
		String id = idTxt.getText();
		String time = updateTimeTxt.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择需要修改的记录!");
			return;
		}
		if(StringUtil.isEmpty(time)) {
			JOptionPane.showMessageDialog(null, "计划时间不能为空！");
			return;
		}
		Plan plan = new Plan(Integer.parseInt(id), time);
		Connection con = null;
		try {
			con = mySqlUtil.getCon();
			int updateNum = planDao.update(con, plan);
			if(updateNum == 1) {
				JOptionPane.showMessageDialog(null, "修改成功！");
				this.resetUpdateValue();
				this.filltable(new Plan());
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
		updateTimeTxt.setText("");
	}

	/**
	 * 计划时间添加Button事件处理
	 * @param evt
	 */
	private void timeAddActionPerformed(ActionEvent evt) {
		String time = this.addTimeTxt.getText();
		if(StringUtil.isEmpty(time)) {
			JOptionPane.showMessageDialog(null, "计划时间不能为空！");
			return;
		}
		Plan plan = new Plan(time);
		Connection con = null;
		try {
			con = mySqlUtil.getCon();
			int n = planDao.add(con, plan);
			if(n == 1) {
				JOptionPane.showMessageDialog(null, "计划时间添加成功！");
				this.addTimeTxt.setText("");
				filltable(new Plan());
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "计划时间添加失败！");
		}finally{
			try {
				mySqlUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 初始化表格
	 * @param plan
	 */
	private void filltable(Plan plan) {
		DefaultTableModel dtm= (DefaultTableModel) table.getModel();
		dtm.setRowCount(0); // 设置成第0行
		Connection con = null;
		try {
			con = mySqlUtil.getCon();
			ResultSet rs = planDao.planlist(con, plan);
			while(rs.next()) {
				Vector<String> v = new Vector<String>();
				v.add(rs.getInt("rownum")+"");
				v.add(rs.getString("id"));
				v.add(rs.getString("time"));
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
	
	/**
	 * 表格行点击事件处理
	 * @param evt
	 */
	private void tableMousePressed(MouseEvent evt) {
		int row = table.getSelectedRow(); // 获取选中行数据
		idTxt.setText((String)table.getValueAt(row, 1));
		updateTimeTxt.setText((String)table.getValueAt(row, 2));
	}
}
