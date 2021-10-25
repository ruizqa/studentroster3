package studentRoster3.mvc.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

//..
@Entity
@Table(name="classes")
public class Class {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 @Column(updatable=false)
 private Date createdAt;
 private Date updatedAt;
 @ManyToMany(fetch = FetchType.LAZY)
 @JoinTable(
     name = "students_classes", 
     joinColumns = @JoinColumn(name = "class_id"), 
     inverseJoinColumns = @JoinColumn(name = "student_id")
 )
 private List<Student> students;
 
 public Class() {
     
 }

 public Class(String name) {
     this.name= name;
 }
 
 public Class(Long id, String name) {
     this.id=id;
     this.name= name;
 }
 
 public Class(Long id, String name, List<Student> students) {
     this.id=id;
     this.name= name;
 }
 
public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}

public Date getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}

public List<Student> getStudents() {
	return students;
}

public void setStudents(List<Student> students) {
	this.students = students;
}


 

}
