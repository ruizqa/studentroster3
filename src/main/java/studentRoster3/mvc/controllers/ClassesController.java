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

import studentRoster3.mvc.models.Class;
import studentRoster3.mvc.models.Student;
import studentRoster3.mvc.services.ClassService;
import studentRoster3.mvc.services.StudentService;

@Controller
public class ClassesController {
	private final ClassService serv;
	private final StudentService studserv;
	 
	 public ClassesController(ClassService serv, StudentService studserv) {
	     this.serv = serv;
	     this.studserv = studserv;
	     }
	 
	 @RequestMapping("/classes/new")
	 public String newcategory(@ModelAttribute("class") Class clase,Model model) {
	     return "/classes/new.jsp";

	 }
	 
	 @RequestMapping(value="/classes/create")
	 public String create(@RequestParam(value="name") String name) {
	     Class clase = new Class(name);
	     serv.createClass(clase);
	     Long id = clase.getId();
	     
	     return String.format("redirect:/classes/%d",id);
	 }
	 
	 @RequestMapping("/classes/{id}")
	 public String getcategory(@PathVariable("id") Long id, 
			 Model model) {
		 Class clase = serv.findClass(id);
		 model.addAttribute("clase",clase);
	     return "/classes/show.jsp";

	 } 
	 
	 @RequestMapping(value="/classes/{id}/remove")
	 public String remove(@PathVariable (value="id") Long id,
			 @RequestParam(value="student") Long studentID) {
		 Student s = studserv.findStudent(studentID);
		 Class c = serv.findClass(id);
		 c.getStudents().remove(s);
		 serv.createClass(c);
		 return String.format("redirect:/classes/%d",id);
		 
	 }
	 
	 
}
