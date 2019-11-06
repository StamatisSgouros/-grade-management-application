package com.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StudentDao;
import com.model.Student;

@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT = "/addStudent.jsp";
    private static String EDIT = "/editStudent.jsp";
    private static String LIST_Student = "/listStudent.jsp";
    private StudentDao dao;

    public StudentController() {
        super();
        dao = new StudentDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

        if (action.equalsIgnoreCase("delete")){
            int RegistrationNumber = Integer.parseInt(request.getParameter("RegistrationNumber"));
            dao.deleteStudent(RegistrationNumber);
            forward = LIST_Student;
            request.setAttribute("Students", dao.getAllStudents());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = EDIT;
            int RegistrationNumber = Integer.parseInt(request.getParameter("RegistrationNumber"));
            Student Student = dao.getStudentByRegNumber(RegistrationNumber);
            request.setAttribute("Student", Student);
        } else if (action.equalsIgnoreCase("listStudent")){
            forward = LIST_Student;
            request.setAttribute("Students", dao.getAllStudents());
        } else {
            forward = INSERT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
    	String action = request.getParameter("action");
    	Student Student = new Student();
        Student.setName(request.getParameter("Name"));
        Student.setSurname(request.getParameter("SurName"));
        Student.setDepartment(request.getParameter("Department"));
        String RegistrationNumber = request.getParameter("RegistrationNumber");
        Student.setId(Integer.parseInt(RegistrationNumber));
        if (action.equalsIgnoreCase("edit")){
        	dao.updateStudent(Student);
        } else if (action.equalsIgnoreCase("insert")){
        	dao.addStudent(Student);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_Student);
        request.setAttribute("Students", dao.getAllStudents());
        view.forward(request, response);
    }
}