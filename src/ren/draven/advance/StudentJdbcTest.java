package ren.draven.advance;

import ren.draven.pojo.Student;

public class StudentJdbcTest {
	public static void main(String[] args) {
		StudentJdbc jdbc = new StudentJdbc();

		Student student = new Student();
		student.setName("zhangsan");
		student.setNickname("zsan");
		student.setAddress("changzhou");
		student.setGender("male");
		student.setAge(20);
		
		jdbc.save(student);
	}
}
