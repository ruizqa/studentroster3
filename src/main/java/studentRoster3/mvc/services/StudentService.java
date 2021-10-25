package studentRoster3.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import studentRoster3.mvc.models.Class;
import studentRoster3.mvc.models.Student;
import studentRoster3.mvc.repositories.StudentRepository;




@Service
public class StudentService {
    private final StudentRepository repo;
    
    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }
 
    public List<Student> allStudents() {
        return repo.findAll();
    }
    
    public List<Student> allStudentsNotInClass(Class clase) {
        return repo.findByClassesNotContains(clase);
    }
    
    
    public Student createStudent(Student s) {
        return repo.save(s);
    }
    
    public Student findStudent(Long id) {
        Optional<Student> opStudent = repo.findById(id);
        if(opStudent.isPresent()) {
            return opStudent.get();
        } else {
            return null;
        }
    }
    
    public Student updateStudent(Long id, String firstName, String lastName, int age, List<Class> clases) {    	
    	Student s = new Student (id, firstName, lastName, age, clases);
    	
        return repo.save(s);
    }
    
 
    public Student updateStudent(Student s) {
        return repo.save(s);
    }
    
 
    public void deleteStudent(Long id) {
        Optional<Student> optionalStudent = repo.findById(id);
        if(optionalStudent.isPresent()) {
            repo.deleteById(id);
        } 
    }
    
    
    
}
