package studentRoster3.mvc.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import studentRoster3.mvc.models.Class;
import studentRoster3.mvc.models.Student;
import studentRoster3.mvc.repositories.ClassRepository;



@Service 
public class ClassService {
	private final ClassRepository repo;

    public ClassService(ClassRepository repo) {
        this.repo = repo;
    }
 
    public List<Class> allClasses() {
        return repo.findAll();
    }
    
    public Class createClass(Class c) {
        return repo.save(c);
    }
    
    public Class findClass(Long id) {
        Optional<Class> opClass = repo.findById(id);
        if(opClass.isPresent()) {
            return opClass.get();
        } else {
            return null;
        }
    }
    
    public Class updateClass(Long id, String name, List<Student> students) {    	
    	Class c = new Class (id,name, students);
        return repo.save(c);
    }
    
  
    public void deleteClass(Long id) {
        Optional<Class> optionalClass = repo.findById(id);
        if(optionalClass.isPresent()) {
            repo.deleteById(id);
        } 
    }
    
    public List<Class> findOtherClass(Student s){
    	return repo.findByStudentsNotContains(s);
    }
    
}
