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
import com.yh.dao.AdminDao;
import com.yh.service.AdminCourse;
import com.yh.service.Register;

/**
 * @Title: CourseView.java
 * @Description:
 * @Copyright: Copyright (c) 2018
 * @Company: FISH
 * @author yh
 * @date 2019年6月25日下午10:34:48
 * @version 1.0
 */

public class CourseView extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel container;
    private JTable courseMess;
    private static final String[] infocolumn = {AppInfo.CNO, AppInfo.CNAME,
            AppInfo.HOUR, AppInfo.GRADE, AppInfo.TERM, AppInfo.MAX};
    private JLabel totCount;
    private AdminCourse frame;

    public CourseView(AdminCourse frame) {
        super(frame, AppInfo.ADMIN_COURSEINFO, true);
        this.frame = frame;
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(600, 300);
        setTitle(AppInfo.ADMIN_COURSEINFO);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        container = new JPanel();
        setContentPane(container);
        container.setLayout(new BorderLayout(5, 5));

        initBtn();
        initTable();

    }

    public void initBtn() {
        JPanel panel = new JPanel();
        container.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JButton addBtn = new JButton(AppInfo.ADMIN_COURSEINFO_ADD);
        addBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        JButton modBtn = new JButton(AppInfo.ADMIN_COURSEINFO_MOD);
        modBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        JButton delBtn = new JButton(AppInfo.ADMIN_COURSEINFO_DEL);
        delBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        JButton quitBtn = new JButton(AppInfo.ADMIN_COURSEINFO_QUIT);
        quitBtn.setAlignmentY(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(70, 30)));
        panel.add(addBtn);
        panel.add(Box.createRigidArea(new Dimension(70, 30)));
        panel.add(modBtn);
        panel.add(Box.createRigidArea(new Dimension(70, 30)));
        panel.add(delBtn);
        panel.add(Box.createRigidArea(new Dimension(70, 30)));
        panel.add(quitBtn);

        
        
        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddCourse ac = new AddCourse(CourseView.this);
                ac.setVisible(true);
            }
        });
        modBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ModCourse mc = new ModCourse(CourseView.this);
				mc.setVisible(true);
				
			}
		});
        delBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DelCourse dc = new DelCourse(CourseView.this);
                dc.setVisible(true);
            }
        });
        quitBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
//                frame.genChoice();
                new Register();
            }
        });
    }

    public void initTable() {
        JPanel panel = new JPanel();
        container.add(panel, BorderLayout.CENTER);
        courseMess = new JTable();
        courseMess.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        totCount = new JLabel();
        panel.add(totCount, BorderLayout.NORTH);
        courseMess.setEnabled(false);
        courseMess.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(courseMess);
        scrollPane.setPreferredSize(new Dimension(550, 180));
        panel.add(scrollPane);
        genTable();
    }

    public void genTable() {
        String[][] result = AdminDao.getInstance().getAllCourses();
        courseMess.setModel(new DefaultTableModel(result, infocolumn) {
            private static final long serialVersionUID = 1L;
        });
        totCount.setText(AppInfo.TOTAL_COUNT + String.valueOf(courseMess.getRowCount()));
    }

    private class AddCourse extends JDialog {

        private static final long serialVersionUID = 1L;

        private JPanel contPanel;
        private JTextField[] tFields;
//        private final String[] checkregex = {AppInfo.REGEX_CNO, AppInfo.REGEX_CNAME,
//                AppInfo.REGEX_CREDIT, AppInfo.REGEX_CDEPT, AppInfo.REGEX_TNAME};
//        private final boolean[] checknull = {false, false, true, true, true};

        public AddCourse(CourseView frame) {
            super(frame, AppInfo.ADMIN_COURSEINFO_ADD, true);
            contPanel = new JPanel();
            setContentPane(contPanel);
            setLayout(new BorderLayout(5, 5));
            setResizable(false);
            setLocationRelativeTo(null);
            setSize(310, 310);
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
                    String[] info = new String[6];
                    for (int i = 0; i < 6; i++) {
                        info[i] = tFields[i].getText();
                    }
//                    boolean isVaild = true;
//                    for (int i = 0; i < 5; i++) {
//                        if (Pattern.matches(checkregex[i], info[i]) == false) {
//                            isVaild = false;
//                            tFields[i].setBackground(Color.PINK);
//                        } else {
//                            tFields[i].setBackground(Color.WHITE);
//                        }
//                        if (checknull[i] && info[i].equals("")) {
//                            info[i] = null;
//                        }
//                    }
//                    if (!isVaild) {
//                        return;
//                    }
                    try {
                        AdminDao.getInstance().AddCourse(info);
                    } catch (CourseExistException e1) {
                        JOptionPane.showMessageDialog(null, AppInfo.ADMIN_CNO_EXIST_ERROR,
                                AppInfo.ERROR, JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    dispose();
                    CourseView.this.genTable();
                }
            });
        }

        public void initTable() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
            JPanel[] panels = new JPanel[6];
            JLabel[] labels = new JLabel[6];
            tFields = new JTextField[6];
            for (int i = 0; i < 6; i++) {
                panels[i] = new JPanel();
                panels[i].setLayout(new GridLayout(1, 2, 6, 6));
                labels[i] = new JLabel(infocolumn[i]);
                tFields[i] = new JTextField(10);
                panels[i].add(labels[i], Component.CENTER_ALIGNMENT);
                panels[i].add(tFields[i], Component.CENTER_ALIGNMENT);
                panel.add(panels[i]);
            }
            contPanel.add(panel, BorderLayout.CENTER);
        }
    }

    private class ModCourse extends JDialog{

        private static final long serialVersionUID = 1L;

        private JPanel contPanel;
        private JTextField[] tFields;
//        private final String[] checkregex = {AppInfo.REGEX_CNAME,
//                AppInfo.REGEX_CREDIT, AppInfo.REGEX_CDEPT, AppInfo.REGEX_TNAME,AppInfo.REGEX_CNO};
//        private final boolean[] checknull = {false, false, true, true, true};

        public ModCourse(CourseView frame) {
            super(frame, AppInfo.ADMIN_COURSEINFO_MOD, true);
            contPanel = new JPanel();
            setContentPane(contPanel);
            setLayout(new BorderLayout(5, 5));
            setResizable(false);
            setLocationRelativeTo(null);
            setSize(310, 310);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            initBtn();
            initTable();
        }

        public void initBtn() {
            JPanel panel = new JPanel();
            JButton jb = new JButton(AppInfo.VERIFY);
            JButton s_jb = new JButton(AppInfo.ADMIN_QUERY);
            panel.add(s_jb);
            panel.add(jb);
            contPanel.add(panel, BorderLayout.SOUTH);
            getRootPane().setDefaultButton(jb);
            
            s_jb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String [][]rs = AdminDao.getInstance().queryCourse(tFields[0].getText());
					for (int i = 1; i < tFields.length; i++) {
						tFields[i].setText(rs[0][i]);
					}
				}
			});
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String[] info = new String[6];
                    for (int i = 0; i < 6; i++) {
                        info[i] = tFields[(i+1)%6].getText();
                    }
//                    boolean isVaild = true;
//                    for (int i = 0; i < 5; i++) {
//                        if (Pattern.matches(checkregex[i], info[i]) == false) {
//                            isVaild = false;
//                            tFields[i].setBackground(Color.PINK);
//                        } else {
//                            tFields[i].setBackground(Color.WHITE);
//                        }
//                        if (checknull[i] && info[i].equals("")) {
//                            info[i] = null;
//                        }
//                    }
//                    if (!isVaild) {
//                        return;
//                    }
                    try {
                        AdminDao.getInstance().ModCourse(info);
                    } catch (CourseExistException e1) {
                        JOptionPane.showMessageDialog(null, AppInfo.ADMIN_CNO_EXIST_ERROR,
                                AppInfo.ERROR, JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (CourseNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (CourseSelectedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    dispose();
                    CourseView.this.genTable();
                }
            });
        }

        public void initTable() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
            JPanel[] panels = new JPanel[6];
            JLabel[] labels = new JLabel[6];
            tFields = new JTextField[6];
            for (int i = 0; i < 6; i++) {
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
    
    
    private class DelCourse extends JDialog {

        private static final long serialVersionUID = 1L;
        private JPanel contPanel;
        private JTextField tField;

        public DelCourse(CourseView frame) {
            super(frame, AppInfo.ADMIN_COURSEINFO_DEL, true);
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
                    String cno = tField.getText();
//                    if (Pattern.matches(AppInfo.REGEX_SNO, cno) == false) {
//                        tField.setBackground(Color.PINK);
//                        return;
//                    } else {
//                        tField.setBackground(Color.WHITE);
//                    }
                    try {
                        AdminDao.getInstance().DelCourse(cno);
                    } catch (CourseNotFoundException e1) {
                        JOptionPane.showMessageDialog(null, AppInfo.ADMIN_CNO_NOTEXIST_ERROR,
                                AppInfo.ERROR, JOptionPane.ERROR_MESSAGE);
                        return;
                    } catch (CourseSelectedException e2) {
                        JOptionPane.showMessageDialog(null, AppInfo.ADMIN_COURSESELECTED_ERROR,
                                AppInfo.ERROR, JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    dispose();
                    CourseView.this.genTable();
                }
            });
        }

        public void initTable() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
            JPanel panel2 = new JPanel();
            panel2.setLayout(new GridLayout(1, 2, 5, 5));
            JLabel label = new JLabel(AppInfo.CNO);
            tField = new JTextField(10);
            panel2.add(label, Component.CENTER_ALIGNMENT);
            panel2.add(tField, Component.LEFT_ALIGNMENT);
            panel.add(panel2);
            contPanel.add(panel, BorderLayout.CENTER);
        }
    }
}

