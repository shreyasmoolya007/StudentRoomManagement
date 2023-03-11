package com.xadmin.studentroommanagement.web;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.studentroommanagement.dao.RoomDAO;
import com.xadmin.studentroommanagement.model.SRoom;


@WebServlet("/")
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomDAO roomDAO;
	
	public void init() {
		roomDAO = new RoomDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertRoom(request, response);
				break;
			case "/delete":
				deleteRoom(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateRoom(request, response);
				break;
			default:
				listRoom(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listRoom(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<SRoom> listRoom = roomDAO.selectAllRooms();
		request.setAttribute("listRoom", listRoom);
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentroom-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentroom-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		SRoom existingRoom = roomDAO.selectRoom(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentroom-form.jsp");
		request.setAttribute("user", existingRoom);
		dispatcher.forward(request, response);

	}

	private void insertRoom(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String usn = request.getParameter("usn");
		String room = request.getParameter("room");
		SRoom newRoom = new SRoom(name, usn, room);
		roomDAO.insertRoom(newRoom);
		response.sendRedirect("list");
	}

	private void updateRoom(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String usn = request.getParameter("usn");
		String room = request.getParameter("room");

		SRoom book = new SRoom(id, name, usn, room);
		roomDAO.updateRoom(book);
		response.sendRedirect("list");
	}

	private void deleteRoom(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		roomDAO.deleteRoom(id);
		response.sendRedirect("list");

	}

}