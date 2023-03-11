package com.xadmin.studentroommanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.studentroommanagement.model.SRoom;


public class RoomDAO {
	private int count=26;
	private String jdbcURL = "jdbc:mysql://localhost:3306/room";
	private String jdbcRoomname = "root";
	private String jdbcPassword = "root";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STUDENTS_SQL = "INSERT INTO room" + "  (name, usn, room) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_STUDENT_BY_ID = "select id,name,usn,room from room where id =?";
	private static final String SELECT_ALL_STUDENTS = "select * from room";
	private static final String DELETE_STUDENTS_SQL = "delete from room where id = ?;";
	private static final String UPDATE_STUDENTS_SQL = "update room set name = ?,usn= ?, room =? where id = ?;";

	public RoomDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcRoomname, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public int getCount() {
		return count;
	}
	public void insertRoom(SRoom sRoom) throws SQLException {
		System.out.println(INSERT_STUDENTS_SQL);
		// try-with-resource statement will auto close the connection.
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)) {
			preparedStatement.setString(1, sRoom.getName());
			preparedStatement.setString(2, sRoom.getUsn());
			preparedStatement.setString(3, sRoom.getRoom());
			count=count-1;
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public SRoom selectRoom(int id) {
		SRoom sRoom = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String usn = rs.getString("usn");
				String room = rs.getString("room");
				sRoom = new SRoom(id, name, usn, room);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return sRoom;
	}

	public List<SRoom> selectAllRooms() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<SRoom> sRooms = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String usn = rs.getString("usn");
				String room = rs.getString("room");
				sRooms.add(new SRoom(id, name, usn, room));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return sRooms;
	}

	public boolean deleteRoom(int id) throws SQLException {
		
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS_SQL);) {
			statement.setInt(1, id);
			count=count+1;
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateRoom(SRoom sRoom) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENTS_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, sRoom.getName());
			statement.setString(2, sRoom.getUsn());
			statement.setString(3, sRoom.getRoom());
			statement.setInt(4, sRoom.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	


}