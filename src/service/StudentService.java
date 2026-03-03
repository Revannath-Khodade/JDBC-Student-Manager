package service;

import model.Student;
import java.util.List;

public interface StudentService {

    void addStudent(Student student);
    void updateName(int id, String name);
    void updateAge(int id, int age);
    void deleteById(int id);
    void deleteByName(String name);
    List<Student> getAllStudents();
}