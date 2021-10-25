package studentRoster3.mvc.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;



@Entity
@Table(name="students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Min(1)
    private int age;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    	     name = "students_classes", 
    	     joinColumns = @JoinColumn(name = "student_id"), 
    	     inverseJoinColumns = @JoinColumn(name = "class_id")
    	 )
    private List<Class> classes;
    
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    
    public Student() {
        
    }
    
    public Student(String firstName, String lastName, int age) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.age = age;
    }
    

    
    public Student(Long id, String firstName, String lastName, int age, List<Class> classes) {
    	this.id = id;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.age = age;
    	this.classes= classes;
    }
  


	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}






	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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


	
	public List<Class> getClasses() {
		return classes;
	}

	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
  
}
