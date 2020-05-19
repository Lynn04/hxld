package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import dao.BFPlanDao;
import dao.PlanDao;
import dao.ShipDao;
import util.JTxtFldUtil;
import util.MySqlUtil;
import util.StringUtil;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TimetableSearchInterFrm extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField validDateTxt;
	private JTextField searchDateTxt;
	private JRadioButton ydsRdButton;
	private JRadioButton bfRdButton;
	private MySqlUtil mySqlUtil = new MySqlUtil();
	private PlanDao ydsplanDao = new PlanDao();
	private BFPlanDao bfplanDao = new BFPlanDao();
	private ShipDao shipDao = new ShipDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimetableSearchInterFrm frame = new TimetableSearchInterFrm();
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
	public TimetableSearchInterFrm() {
		setTitle("班次查询");
		setClosable(true);
		setBounds(100, 100, 511, 399);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("生效日期：");
		
		validDateTxt = new JTextField();
		validDateTxt.addFocusListener(new JTxtFldUtil(validDateTxt, "2020-01-01"));
		validDateTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("查询日期：");
		
		
		searchDateTxt = new JTextField();
		searchDateTxt.setColumns(10);
		searchDateTxt.addFocusListener(new JTxtFldUtil(searchDateTxt, "2020-01-01"));
		
		JLabel lblNewLabel_2 = new JLabel("查询站点：");
		
		ydsRdButton = new JRadioButton("鸭蛋山");
		bfRdButton = new JRadioButton("白峰");
		// 创建按钮组
		ButtonGroup group = new ButtonGroup();
		group.add(ydsRdButton);
		group.add(bfRdButton);
		// 设置默认选择
		ydsRdButton.setSelected(true);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(validDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(searchDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(bfRdButton)
										.addComponent(ydsRdButton)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addComponent(btnNewButton)))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(validDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(searchDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(ydsRdButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bfRdButton)
					.addGap(40)
					.addComponent(btnNewButton)
					.addContainerGap(31, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(28, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		
		table = new JTable();
		String[] headers = {"船只名称", "计划时间"}; 
		Object[][] cellData = null;
		// 以表头和表数据创建表格,并且让表单元格不可改.
		tableModel = new DefaultTableModel(cellData, headers);
		table = new JTable(tableModel); 
		
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

	}
	
	/**
	 * 修改Button事件处理
	 * @param evt
	 */
	private void searchActionPerformed(ActionEvent evt) {
		if(StringUtil.isEmpty(validDateTxt.getText())) {
			JOptionPane.showMessageDialog(null, "生效时间不能为空！");
		}
		if(StringUtil.isEmpty(searchDateTxt.getText())) {
			JOptionPane.showMessageDialog(null, "查询时间不能为空！");
		}
		//判断格式
		// 创建SimpleDateFormat类型对象、 "yyyy-MM-dd HH:mm:ss.SSS"是正则式，分别表示年月日时分秒毫秒
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);
		try {
			Date vDate = df.parse(validDateTxt.getText());
			Date sDate = df.parse(searchDateTxt.getText());
			filltable(vDate, sDate);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "日期输入格式无效，请改为yyyy-MM-dd");
			e.printStackTrace();
		}
	}

	/**
	 * 表格初始化
	 */
	private void filltable(Date vDate, Date sDate) {
		DefaultTableModel dtm= (DefaultTableModel) table.getModel();
		dtm.setRowCount(0); // 设置成第0行
		Connection con = null;
		try {
			con = mySqlUtil.getCon();
			String[] ydsplanArr = ydsplanDao.planArr(con);
			String[] bfplanArr = bfplanDao.planArr(con);
			String[] shipArr = shipDao.shipArr(con);
			int shipNum = shipArr.length;
			int ydsplanNum = ydsplanArr.length;
			int bfplanNum = bfplanArr.length;
			
			// 船只轮换
			String[] shipCopyArr = new String[shipNum];
			int changeNum = this.getChangeNum(vDate, sDate, shipNum);
			for(int i =0; i<shipNum-changeNum; i++) {
				shipCopyArr[i+changeNum] = shipArr[i]; 
			}
			if(changeNum != 0) {
				for(int i = 0; i<changeNum; i++) {
					shipCopyArr[i] = shipArr[i+(shipNum-changeNum)];
				}
			}
			
			// 站点选择
			if(ydsRdButton.isSelected()) {
				// 填充表格
				for(int j=0; j < ydsplanNum; j+=shipNum) {
					for(int i = 0; i < shipNum; i++) {
						if(j+i<ydsplanNum) {
							Vector<String> v = new Vector<String>();
							v.add(shipCopyArr[i]);
							v.add(ydsplanArr[j+i]);
							dtm.addRow(v);
						}
					}
				}
			}else {
				// 填充表格
				for(int j=0; j < bfplanNum; j+=shipNum) {
					for(int i = 0; i < shipNum; i++) {
						if(j+i<bfplanNum) {
							Vector<String> v = new Vector<String>();
							v.add(shipCopyArr[i]);
							v.add(bfplanArr[j+i]);
							dtm.addRow(v);
						}
					}
				}
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
	 * 获取需改动的船只数
	 * @return
	 */
	private int getChangeNum(Date vDate, Date sDate, int shipNum ) {
		// 得到毫秒数/(24*60*60*1000)=天数, dataType为long
		int dateDiff = (int) (sDate.getTime() - vDate.getTime()) / (24 * 60 * 60 * 1000);
		int changeNum = dateDiff % shipNum;
		
		return changeNum;
	}
}
