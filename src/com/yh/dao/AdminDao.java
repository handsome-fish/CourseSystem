package com.yh.dao;

import com.mysql.fabric.xmlrpc.base.Param;
import com.yh.utils.BaseDao;

/**
 * @Title: AdminDao.java
 * @Description:
 * @Copyright: Copyright (c) 2018
 * @Company: FISH
 * @author yh
 * @date 2019年6月25日下午10:18:46
 * @version 1.0
 */
public class AdminDao extends BaseDao {

	private static AdminDao ad = null;

	public static synchronized AdminDao getInstance() {
		if (ad == null) {
			ad = new AdminDao();
		}
		return ad;
	}

	/**
	 * 
	 * @Description: query students who have selected a specific course.
	 */
	public String[][] queryStuWhoSeleCou(String cno) {
		String sql = "select sno,grade from course as A, stu_course as B where A.cno=B.cno and A.cno=?";
		String[] param = { cno };
		rs = db.executeQuery(sql, param);
		return buildResult();
	}

	/**
	 *
	 * @Description: get all courses.
	 */
	public String[][] getAllCourses() {
		String sql = "select cno,cname,hour,grade,term,max from COURSEXX";
		rs = db.executeQuery(sql);
		return buildResult();
	}

	/**
	 *
	 * @Description: get all students.
	 */
	public String[][] getAllStudents() {
		String sql = "select * from REGISTERXU";
		rs = db.executeQuery(sql);
		return buildResult();
	}

	/**
	 *
	 * @Description: query the course for a student.
	 */
	public String[][] queryCourses(String sno) {
		String sql = "select cno from stu_course where sno=?";
		String[] param = { sno };
		rs = db.executeQuery(sql, param);
		return buildResult();
	}

	/**
	 * 
	 * @Description: update a student's grade.
	 */
	public int updateCourseGrade(String sno, String cno, String grade) {
		String sql = "update stu_course set grade=? where sno=? and cno=?";
		String[] prarm = { grade, sno, cno };
		return db.executeUpdate(sql, prarm);
	}

	/**
	 *
	 * @throws CourseExistException
	 * @Description: AddCourse
	 */
	public void AddCourse(String[] prarm) throws CourseExistException {
		if (queryCourse(prarm[0]).length != 0) {
			// check if the course exist
			throw new CourseExistException();
		}
		String sql = "insert into coursexx values(?,?,?,?,?,'NoNecessary',0,?)";
		db.executeUpdate(sql, prarm);
	}

	public void ModCourse(String[] prarm)
			throws CourseExistException, CourseNotFoundException, CourseSelectedException {

		String sql = "update coursexx set cname=?,hour=?,grade=?,term=?,max=? where cno=?";
		for (int i = 0; i < prarm.length; i++) {
			System.out.println(prarm[i]);
		}
		db.executeUpdate(sql, prarm);
	}

	/**
	 *
	 * @throws CourseNotFoundException
	 * @throws CourseSelectedException
	 * @Description: DelCourse
	 */
	public void DelCourse(String cno) throws CourseNotFoundException, CourseSelectedException {
		if (queryCourse(cno).length == 0) {
			// check if the course exist
			throw new CourseNotFoundException();
		}
//		if (queryStuWhoSeleCou(cno).length != 0) {
//			// check if some student selected the course
//			throw new CourseSelectedException();
//		}
		String sql = "delete from coursexx where cno=?";
		String[] prarm = { cno };
		db.executeUpdate(sql, prarm);
	}

	/**
	 *
	 * @throws StudentExistException
	 * @throws UserExistException
	 * @Description: AddStudent
	 */
	public void AddStudent(String[] prarm) throws StudentExistException, UserExistException {
		if (queryStudent(prarm[0]).length != 0) {
			// check if the student exist
			throw new StudentExistException();
		}
		if (queryUser(prarm[6]).length != 0) {
			// check if the username exist
			throw new UserExistException();
		}
		String sql = "insert into student values(?,?,?,?,?,?,?)";
		prarm[6] = prarm[6] + prarm[5];
		db.executeUpdate(sql, prarm);

	}

	public void ModStudent(String[] prarm) {

		String sql = "update student set sname=?,sex=?,age=?,sdept=?,username=?,password=? where sno=?";
		for (int i = 0; i < prarm.length; i++) {
			System.out.println(prarm[i]);
		}
		db.executeUpdate(sql, prarm);
	}

	/**
	 *
	 * @throws StudentNotFoundException
	 * @throws StudentSelectedCourseException
	 * @Description: DelStudent
	 */
	public void DelStudent(String sno) throws StudentNotFoundException, StudentSelectedCourseException {
		if (queryStudent(sno).length == 0) {
			// check if the student exist
			throw new StudentNotFoundException();
		}
		if (queryCourses(sno).length != 0) {
			// check if the student selected some course
			throw new StudentSelectedCourseException();
		}
		String sql = "delete from student where sno=?";
		String[] prarm = { sno };
		db.executeUpdate(sql, prarm);
	}
}
