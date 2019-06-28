package com.yh.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.yh.utils.AppInfo;
import com.yh.utils.BaseDao;
import com.yh.utils.BaseDao.CourseExistException;
import com.yh.utils.BaseDao.CourseNotFoundException;
import com.yh.utils.BaseDao.CourseSelectedException;
import com.yh.utils.BaseDao.StudentExistException;
import com.yh.utils.BaseDao.StudentNotFoundException;
import com.yh.utils.BaseDao.StudentSelectedCourseException;
import com.yh.utils.BaseDao.UserExistException;
import com.yh.dao.AdminDao;
import com.yh.service.AdminCourse;


/**
 * @Title: StudentView.java
 * @Description:
 * @Copyright: Copyright (c) 2018
 * @Company: FISH
 * @author yh
 * @date 2019年6月25日下午10:57:47
 * @version 1.0
 */


public class StudentView extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel container;
    private JTable stuMess;
    private static final String[] infocolumn =
            {AppInfo.SNO, AppInfo.SNAME, AppInfo.SEX, AppInfo.AGE,
                    AppInfo.SDEPT, AppInfo.USERNAME, AppInfo.PASSWORD};
    private JLabel totCount;

    public StudentView(AdminCourse frame) {
        super(frame, AppInfo.ADMIN_SUTDENTINFO, true);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(450, 300);
        setTitle(AppInfo.ADMIN_SUTDENTINFO);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        container = new JPanel();
        setContentPane(container);
        container.setLayout(new BorderLayout(5, 5));

        initBtn();
        initTable();

    }

    public void initBtn() {
        JPanel panel = new JPanel();
        container.add(panel, BorderLayout.EAST);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton addBtn = new JButton(AppInfo.ADMIN_SUTDENTINFO_ADD);
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton modBtn = new JButton(AppInfo.ADMIN_SUTDENTINFO_MOD);
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton delBtn = new JButton(AppInfo.ADMIN_SUTDENTINFO_DEL);
        delBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(100, 30)));
        panel.add(addBtn);
        panel.add(Box.createRigidArea(new Dimension(100, 30)));
        panel.add(modBtn);
        panel.add(Box.createRigidArea(new Dimension(100, 30)));
        panel.add(delBtn);

        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudent ac = new AddStudent(StudentView.this);
                ac.setVisible(true);
            }
        });
        modBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ModStudent ms = new ModStudent(StudentView.this);
				ms.setVisible(true);
				
			}
		});
        delBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DelStudent dc = new DelStudent(StudentView.this);
                dc.setVisible(true);
            }
        });
    }

    public void initTable() {
        JPanel panel = new JPanel();
        container.add(panel, BorderLayout.CENTER);
        stuMess = new JTable();
        stuMess.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        totCount = new JLabel();
        panel.add(totCount, BorderLayout.NORTH);
        stuMess.setEnabled(false);
        stuMess.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(stuMess);
        scrollPane.setPreferredSize(new Dimension(300, 180));
        panel.add(scrollPane);
        genTable();
    }

    public void genTable() {
        String[][] result = AdminDao.getInstance().getAllStudents();
        stuMess.setModel(new DefaultTableModel(result, infocolumn) {
            private static final long serialVersionUID = 1L;
        });
        totCount.setText(AppInfo.TOTAL_COUNT + String.valueOf(stuMess.getRowCount()));
    }

    private class AddStudent extends JDialog {

        private static final long serialVersionUID = 1L;
        private JPanel contPanel;
        private JTextField[] tFields;
        private final String[] checkregex = {AppInfo.REGEX_SNO, AppInfo.REGEX_SNAME,
                AppInfo.REGEX_SEX, AppInfo.REGEX_AGE, AppInfo.REGEX_SDEPT,
                AppInfo.REGEX_USERNAME, AppInfo.REGEX_PASSWORD};
        private final boolean checknull[] = {false, false, true, true, true, false, false};

        public AddStudent(StudentView frame) {
            super(frame, AppInfo.ADMIN_SUTDENTINFO_ADD, true);
            contPanel = new JPanel();
            setContentPane(contPanel);
            setLayout(new BorderLayout(5, 5));
            setResizable(false);
            setLocationRelativeTo(null);
            setSize(310, 330);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            initBtn();
            initTable();
        }

        public void initBtn() {
            JPanel panel = new JPanel();
            JButton jb = new JButton(AppInfo.VERIFY);
            panel.add(jb);
            contPanel.add(panel, BorderLayout.SOUTH);
            getRootPane().setDefaultButton(jb);
            jb.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] info = new String[7];
                    for (int i = 0; i < 7; i++) {
                        info[i] = tFields[i].getText();
                    }
                    try {
                        AdminDao.getInstance().AddStudent(info);
                    } catch (StudentExistException e1) {
                        JOptionPane.showMessageDialog(null, AppInfo.ADMIN_SNO_EXIST_ERROR,
                                AppInfo.ERROR, JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (UserExistException e2) {
                        JOptionPane.showMessageDialog(null, AppInfo.ADMIN_USER_EXIST_ERROR,
                                AppInfo.ERROR, JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    dispose();
                    StudentView.this.genTable();
                }
            });
        }

        public void initTable() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
            JPanel[] panels = new JPanel[7];
            JLabel[] labels = new JLabel[7];
            tFields = new JTextField[7];
            for (int i = 0; i < 7; i++) {
                panels[i] = new JPanel();
                panels[i].setLayout(new GridLayout(1, 2, 5, 5));
                labels[i] = new JLabel(infocolumn[i]);
                tFields[i] = new JTextField(10);
                panels[i].add(labels[i], Component.CENTER_ALIGNMENT);
                panels[i].add(tFields[i], Component.CENTER_ALIGNMENT);
                panel.add(panels[i]);
            }
            contPanel.add(panel, BorderLayout.CENTER);
        }
    }
    private class ModStudent extends JDialog {

        private static final long serialVersionUID = 1L;
        private JPanel contPanel;
        private JTextField[] tFields;
        private final String[] checkregex = {AppInfo.REGEX_SNAME,
                AppInfo.REGEX_SEX, AppInfo.REGEX_AGE, AppInfo.REGEX_SDEPT,
                AppInfo.REGEX_USERNAME, AppInfo.REGEX_PASSWORD,AppInfo.REGEX_SNO};
        private final boolean checknull[] = {false, true, true, true, false, false,false};

        public ModStudent(StudentView frame) {
            super(frame, AppInfo.ADMIN_SUTDENTINFO_MOD, true);
            contPanel = new JPanel();
            setContentPane(contPanel);
            setLayout(new BorderLayout(5, 5));
            setResizable(false);
            setLocationRelativeTo(null);
            setSize(310, 330);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            initBtn();
            initTable();
        }

        public void initBtn() {
            JPanel panel = new JPanel();
            JButton jb = new JButton(AppInfo.VERIFY);
            JButton m_jb = new JButton(AppInfo.ADMIN_QUERY);
            panel.add(m_jb);
            panel.add(jb);
            contPanel.add(panel, BorderLayout.SOUTH);
            getRootPane().setDefaultButton(jb);
            
            m_jb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String [][]rs = AdminDao.getInstance().queryStudent(tFields[0].getText());
					for (int i = 1; i < tFields.length; i++) {
						tFields[i].setText(rs[0][i]);
					}	
				}
			});
            jb.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] info = new String[7];
                    for (int i = 0; i < 7; i++) {
                    	 info[i] = tFields[(i+1)%7].getText();
                    }
                    AdminDao.getInstance().ModStudent(info);
                    dispose();
                    StudentView.this.genTable();
                }
            });
        }

        public void initTable() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
            JPanel[] panels = new JPanel[7];
            JLabel[] labels = new JLabel[7];
            tFields = new JTextField[7];
            for (int i = 0; i < 7; i++) {
                panels[i] = new JPanel();
                panels[i].setLayout(new GridLayout(1, 2, 5, 5));
                labels[i] = new JLabel(infocolumn[i]);
                tFields[i] = new JTextField(10);
                panels[i].add(labels[i], Component.CENTER_ALIGNMENT);
                panels[i].add(tFields[i], Component.CENTER_ALIGNMENT);
                panel.add(panels[i]);
            }
            contPanel.add(panel, BorderLayout.CENTER);
        }
    }
    private class DelStudent extends JDialog {

        private static final long serialVersionUID = 1L;
        private JPanel contPanel;
        private JTextField tField;

        public DelStudent(StudentView frame) {
            super(frame, AppInfo.ADMIN_SUTDENTINFO_DEL, true);
            contPanel = new JPanel();
            setContentPane(contPanel);
            setLayout(new BorderLayout(5, 5));
            setResizable(false);
            setLocationRelativeTo(null);
            setSize(280, 120);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            initBtn();
            initTable();
        }

        public void initBtn() {
            JPanel panel = new JPanel();
            JButton jb = new JButton(AppInfo.DELETE);
            panel.add(jb);
            contPanel.add(panel, BorderLayout.SOUTH);
            getRootPane().setDefaultButton(jb);
            jb.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String sno = tField.getText();
                    if (Pattern.matches(AppInfo.REGEX_SNO, sno) == false) {
                        tField.setBackground(Color.PINK);
                        return;
                    } else {
                        tField.setBackground(Color.WHITE);
                    }
                    try {
                        AdminDao.getInstance().DelStudent(sno);
                    } catch (StudentNotFoundException e1) {
                        JOptionPane.showMessageDialog(null, AppInfo.ADMIN_SNO_NOTEXIST_ERROR,
                                AppInfo.ERROR, JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (StudentSelectedCourseException e2) {
                        JOptionPane.showMessageDialog(null, AppInfo.ADMIN_SELECTEDCOURSE_ERROR,
                                AppInfo.ERROR, JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    dispose();
                    StudentView.this.genTable();
                }
            });
        }

        public void initTable() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
            JPanel panel2 = new JPanel();
            panel2.setLayout(new GridLayout(1, 2, 5, 5));
            JLabel label = new JLabel(AppInfo.SNO);
            tField = new JTextField(10);
            panel2.add(label, Component.CENTER_ALIGNMENT);
            panel2.add(tField, Component.LEFT_ALIGNMENT);
            panel.add(panel2);
            contPanel.add(panel, BorderLayout.CENTER);
        }
    }
}

