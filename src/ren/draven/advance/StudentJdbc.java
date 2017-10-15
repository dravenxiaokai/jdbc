package ren.draven.advance;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import ren.draven.common.ConnectionFactory;
import ren.draven.common.DBUtils;
import ren.draven.pojo.Student;

public class StudentJdbc {
	public void save(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rSet = null;

		try {
			connection = ConnectionFactory.getConnection();
//			String selectSQL = "SELECT * FROM student";
//			preparedStatement = (PreparedStatement) connection.prepareStatement(selectSQL);
//			rSet = (ResultSet) preparedStatement.executeQuery();
//			Long id = 0L;
//			if (rSet.next()) {
//				id = rSet.getLong(1);// 获取第一列的值
//			}
			String insertSQL = "INSERT INTO student(name,nickname,address,gender,age) values(?,?,?,?,?)";
			preparedStatement = (PreparedStatement) connection.prepareStatement(insertSQL);
//			preparedStatement.setLong(1, id);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getNickname());
			preparedStatement.setString(3, student.getAddress());
			preparedStatement.setString(4, student.getGender());
			preparedStatement.setInt(5, student.getAge());

			// 执行操作影响的记录数
			int rows = preparedStatement.executeUpdate();
			System.out.println("成功插入记录：" + rows + "条");

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
			}
		} finally {
			DBUtils.close(rSet, preparedStatement, connection);
		}
	}
}
