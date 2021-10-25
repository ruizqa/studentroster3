package studentRoster3.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import studentRoster3.mvc.models.Student;
import studentRoster3.mvc.models.Class;
import studentRoster3.mvc.services.ClassService;
import studentRoster3.mvc.services.StudentService;





@Controller
public class StudentController {
	private final StudentService serv;
	private final ClassService classServ;
	 
	 public StudentController(StudentService serv, ClassService classServ) {
	     this.serv = serv;
	     this.classServ = classServ;
	     }

		 
	 
	 @RequestMapping("/students/new")
	 public String index() {
	     return "/students/new.jsp";

	 }
	 
	 @RequestMapping(value="/students/create")
	 public String create(@RequestParam(value="firstName") String firstName,
			 @RequestParam(value="lastName") String lastName,
			 @RequestParam(value="age") int age) {
	     
		 Student s = new Student (firstName, lastName, age);
		 serv.createStudent(s);
		 Long id = s.getId();
		 return String.format("redirect:/students/%d",id);
		 
		  
	 }
	 
	 @RequestMapping(value="/students/{id}")
	 public String create(@PathVariable (value="id") Long id,
			 Model model) {
		  
		 Student s = serv.findStudent(id);
		 List<Class> classes = classServ.findOtherClass(s);
		 model.addAttribute("student",s);
		 model.addAttribute("classes",classes);
		 return "/students/show.jsp";
		 
	 }
	 
	 
	 
	 @RequestMapping(value="/students/{id}/add")
	 public String create(@PathVariable (value="id") Long id,
			 @RequestParam(value="classID") Long classID) {
		 Class c = classServ.findClass(classID);
		 Student s = serv.findStudent(id);
		 s.getClasses().add(c);
		 serv.updateStudent(s);
		 return String.format("redirect:/students/%d",id);
		 
	 }
	 

	 

	 
}
