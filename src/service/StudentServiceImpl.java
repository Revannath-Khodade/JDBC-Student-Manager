package service;

import dao.StudentDAO;
import dao.StudentDAOImpl;
import model.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDAO dao = new StudentDAOImpl();

    @Override
    public void addStudent(Student student) {
        dao.insert(student);
    }

    @Override
    public void updateName(int id, String name) {
        dao.updateName(id, name);
    }

    @Override
    public void updateAge(int id, int age) {
        dao.updateAge(id, age);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        dao.deleteByName(name);
    }

    @Override
    public List<Student> getAllStudents() {
        return dao.getAll();
    }
}