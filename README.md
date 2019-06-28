# CourseSystem
数据库改为MySQL只要修改JDBCUtil连接方式即可

数据库表有registerxu、coursexx、studentxx、choicesxx

表结构分别为


 	名称                                      是否为空? 类型
 	----------------------------------------- -------- ----------------------------

	 ID                                                 VARCHAR2(20)
 	USERNAME                                           VARCHAR2(20)
 	PASSWORD                                           VARCHAR2(20)
 	IDENTIFY                                           VARCHAR2(20)

----------
	
  	名称                                      是否为空? 类型
 	----------------------------------------- -------- ----------------------------

 	CNO                                       NOT NULL VARCHAR2(20)
 	CNAME                                              VARCHAR2(20)
 	HOUR                                               NUMBER
 	GRADE                                              NUMBER
 	TERM                                               VARCHAR2(20)
 	ISNEED                                             VARCHAR2(20)
 	SELECTEDCOUNT                                      NUMBER
 	MAX                                                NUMBER

----------
	名称                                      是否为空? 类型
	----------------------------------------- -------- ----------------------------

	SID                                                VARCHAR2(20)
	SNAME                                     NOT NULL VARCHAR2(20)
	SEX                                                VARCHAR2(20)
	BIRTHDAY                                           VARCHAR2(20)
	CLASSNAME                                          VARCHAR2(20)
	IMAGE                                              VARCHAR2(20)

----------
	名称                                      是否为空? 类型
	----------------------------------------- -------- ----------------------------

	SNAME                                              VARCHAR2(20)
	CNO                                                VARCHAR2(20)