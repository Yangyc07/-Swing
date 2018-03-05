package com.yang.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import utils.StudentFactory;

import com.yang.model.Student;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame implements ActionListener {

    JFrame jf = new JFrame("学生管理");

    public JTable table;

    private JPanel jp1 = new JPanel();
    private JPanel jp2 = new JPanel();
    private JPanel jp3 = new JPanel();
    private JPanel jp4 = new JPanel();

    JLabel lid = new JLabel("学号");
    JLabel lname = new JLabel("姓名");
    JLabel lgender = new JLabel("性别");
    JLabel lage = new JLabel("年龄");

    JTextField jname;
    JTextField jid;
    JTextField jgender;
    JTextField jage;
    JTextArea message;

    JButton add = new JButton("增加");
    JButton delete = new JButton("删除");
    JButton queryAll = new JButton("查询所有");
    JButton query = new JButton("按学号查询");
    JButton modify = new JButton("修改");
    private JScrollPane jsp = null;

    public MainFrame() {

        jid = new JTextField("", 8);
        ;

        jp1.setLayout(new FlowLayout());
        jp1.add(lid);
        jp1.add(jid);
        jp1.add(query);

        jp2.setLayout(new FlowLayout());
        jp2.add(add);
        jp2.add(delete);
        jp2.add(modify);
        jp2.add(queryAll);

        jp3.setLayout(new BorderLayout());
        jp3.add(jp1, BorderLayout.NORTH);
        jp3.add(jp2, BorderLayout.SOUTH);

        jp4.setLayout(new BorderLayout());
        jp4.add(jp3, BorderLayout.NORTH);

        table = new JTable();
        jsp = new JScrollPane(table);
        jp4.add(jsp);

        query.addActionListener(this);
        queryAll.addActionListener(this);
        add.addActionListener(this);
        delete.addActionListener(this);
        modify.addActionListener(this);

        jf.add(jp4);
        jf.setLocation(200, 200);
        jf.setSize(400, 470);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == queryAll) {//查询所有学生信息
            jid.setText("");
            query();
        } else if (event.getSource() == query) {//按学号查询
            query();
        } else if (event.getSource() == add) {//添加学生
            showDialog(MyDialog.OPERATION_ADD_TYPE);
            query();
        } else if (event.getSource() == modify) {//修改学生信息
            int count = table.getSelectedRowCount();//获取选择的行数
            if (count == 0) {
                JOptionPane.showMessageDialog(this, "没有选择修改的数据!");
                return;
            }
            showDialog(MyDialog.OPERATION_UPDATE_TYPE);
            query();
        } else if (event.getSource() == delete) {//删除学生信息
            int count = table.getSelectedRowCount();//获取选择的行数
            String stuid = (String) table.getValueAt(table.getSelectedRow(), 0);
            StudentFactory.getStudentDao().deleteUser(stuid);
            JOptionPane.showMessageDialog(this, "删除数据成功!");
            query();
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    Student getSelectRowData() {
        int rowId = table.getSelectedRow();
        String stuid = (String) table.getValueAt(rowId, 0);
        String name = (String) table.getValueAt(rowId, 1);
        String gender = (String) table.getValueAt(rowId, 2);
        String age = (String) table.getValueAt(rowId, 3);
        return new Student(stuid, name, gender, age);
    }

    private void showDialog(int type) {
        Dialog dialog = new MyDialog(this, type);
        Point point = this.getLocation(); //获取当前窗口左上角的坐标
        Dimension frameDimension = this.getSize(); //获取当前窗口的大小
        Dimension dialogDimension = dialog.getSize();//获取对话框的大小
        dialog.setLocation(400, 200);
        dialog.setVisible(true);
    }

    private void query() {
        String stuid = jid.getText();
        ArrayList<Student> studentList = new ArrayList<Student>();//返回一个list
        if (stuid.equals("")) {
            studentList = StudentFactory.getStudentDao().queryAllUser();
        } else {
            Student student = StudentFactory.getStudentDao().query(stuid);
            studentList.add(student);
        }
        MyTableModel tableModel = new MyTableModel(studentList);
        table.setModel(tableModel);
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumn("学号").setCellRenderer(render);
        table.getColumn("姓名").setCellRenderer(render);
        table.getColumn("性别").setCellRenderer(render);
        table.getColumn("年龄").setCellRenderer(render);
    }
}
