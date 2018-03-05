/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yang.view;

import com.yang.model.Student;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 杨亚宸
 */
public class MyTableModel extends AbstractTableModel {

    private String columnName[] = {"学号", "姓名", "性别", "年龄"};

    private ArrayList<Student> listStudent = new ArrayList<Student>();

    public MyTableModel(ArrayList<Student> listStudent) {
        super();
        this.listStudent = listStudent;
    }

    @Override
    public int getRowCount() {//返回表格的列值
       if (listStudent == null || listStudent.size() == 0) {
            return 0;
        }
        return listStudent.size();
    }

    @Override
    public int getColumnCount() {//返回表格的行值
          return 4;
    }

    @Override
    public String getColumnName(int column) {//返回标题
        return columnName[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String temp = listStudent.get(rowIndex).returnValueByChoose(columnIndex);
        return temp != null ? temp : " ";
    }
}
