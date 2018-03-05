/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yang.view;

import com.yang.model.Student;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import utils.StudentFactory;

/**
 *
 * @author 杨亚宸
 */
class MyDialog extends JDialog implements ActionListener{
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldID; // 学生ID输入框
	private JTextField textFieldName; // 学生姓名输入框
	private JTextField textFieldAge; // 学生年龄输入框
	private JRadioButton rdbtnNewRadioButtonMale, rdbtnNewRadioButtonFemale;// 性别单选按钮
	private MainFrame mainFrame; 
        
        
	private int operationType;

	public static final int OPERATION_ADD_TYPE = 1; // 插入数据操作类型
	public static final int OPERATION_UPDATE_TYPE = 2; // 修改数据操作类型

        	public MyDialog(MainFrame mainFrame, int operationType) {
		this.mainFrame = mainFrame;
		this.operationType = operationType;
		// 对学生信息进行操作
		setTitle("\u5BF9\u5B66\u751F\u4FE1\u606F\u8FDB\u884C\u64CD\u4F5C");
		setModal(true);
		setSize(443, 378);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			// 学号
			JLabel lblNewLabel_1 = new JLabel("学号：");
			lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 20));
			lblNewLabel_1.setBounds(65, 34, 85, 35);
			contentPanel.add(lblNewLabel_1);
		}
		{
			// 姓名
			JLabel lblNewLabel_2 = new JLabel("姓名：");
			lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 20));
			lblNewLabel_2.setBounds(65, 90, 85, 35);
			contentPanel.add(lblNewLabel_2);
		}
		{
			//年龄
			JLabel lblNewLabel_3 = new JLabel("年龄：");
			lblNewLabel_3.setFont(new Font("宋体", Font.BOLD, 20));
			lblNewLabel_3.setBounds(65, 145, 85, 35);
			contentPanel.add(lblNewLabel_3);
		}
		{
			// 性别
			JLabel label = new JLabel("性别 ：");
			label.setFont(new Font("宋体", Font.BOLD, 20));
			label.setBounds(65, 203, 85, 35);
			contentPanel.add(label);
		}

		textFieldID = new JTextField();
		textFieldID.setBounds(185, 43, 121, 21);
		contentPanel.add(textFieldID);
		textFieldID.setColumns(10);

		textFieldName = new JTextField();
		textFieldName.setBounds(185, 99, 121, 21);
		contentPanel.add(textFieldName);
		textFieldName.setColumns(18);

		textFieldAge = new JTextField();
		textFieldAge.setBounds(185, 154, 121, 21);
		contentPanel.add(textFieldAge);
		textFieldAge.setColumns(10);

		// 单选按钮 男
		rdbtnNewRadioButtonMale = new JRadioButton("男");
		rdbtnNewRadioButtonMale.setSelected(true);
		rdbtnNewRadioButtonMale.setFont(new Font("宋体", Font.BOLD, 20));
		rdbtnNewRadioButtonMale.setBounds(185, 211, 62, 23);
		contentPanel.add(rdbtnNewRadioButtonMale);
		// 单选按钮 女
		rdbtnNewRadioButtonFemale = new JRadioButton("女");
		rdbtnNewRadioButtonFemale.setFont(new Font("宋体", Font.BOLD, 20));
		rdbtnNewRadioButtonFemale.setBounds(249, 211, 62, 23);
		// 将两个单选按钮放入同一个组中

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButtonFemale);
		group.add(rdbtnNewRadioButtonMale);

		contentPanel.add(rdbtnNewRadioButtonFemale);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// 确定按钮
				JButton okButton = new JButton("确定");
				okButton.addActionListener(this);
				okButton.setFont(new Font("宋体", Font.BOLD, 20));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{// 下面这个Lable是用来调整布局，没有实际作用
				JLabel lblNewLabel = new JLabel("                ");
				buttonPane.add(lblNewLabel);
			}
			{
				// 取消按钮
				JButton cancelButton = new JButton("取消");
				cancelButton.setFont(new Font("宋体", Font.BOLD, 20));
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		 // 如果以更新模式启动对话框，需要向界面中填入数据
		if(operationType==MyDialog.OPERATION_UPDATE_TYPE)  
			initData();
	}
    @Override
    public void actionPerformed(ActionEvent e) {
       String actionCommand = e.getActionCommand();
		if ("OK".equals(actionCommand)) {
			switch (operationType) {
			case MyDialog.OPERATION_ADD_TYPE: // 表示执行的 添加学生操作
				insertStudent();
                                this.dispose(); // 关闭当前的对话框
				break;
			case MyDialog.OPERATION_UPDATE_TYPE: // 表示执行 修改学生操作
				updateStudent();
                                this.dispose(); // 关闭当前的对话框
				break;
			default:
				break;
			}
			return;
		}
		if ("Cancel".equals(actionCommand)) {
			this.dispose(); // 关闭当前的对话框
			return;
		}
    }

    private void initData() {
		Student student =mainFrame.getSelectRowData();  //获取主界面中所选行 的数据
		textFieldID.setText(student.getStuid());
		textFieldID.setEditable(false);	
		textFieldName.setText(student.getName());
		textFieldAge.setText(student.getAge());
		if("男".equals(student.getGender()))
			rdbtnNewRadioButtonMale.setSelected(true);
		else
			rdbtnNewRadioButtonFemale.setSelected(true);
		
		
	}

    private void insertStudent() {
      		String stuid = textFieldID.getText();
		String name = textFieldName.getText();
		String age = textFieldAge.getText();
		String gender = rdbtnNewRadioButtonMale.isSelected() ? "男" : "女";
		StudentFactory.getStudentDao().addUser(stuid, name, gender, age);
                
    }

    private void updateStudent() {
              	String stuid = textFieldID.getText();
		String name = textFieldName.getText();
		String age = textFieldAge.getText();
		String gender = rdbtnNewRadioButtonMale.isSelected() ? "男" : "女";
                StudentFactory.getStudentDao().updateUser(stuid, name, gender, age);
    }
}
