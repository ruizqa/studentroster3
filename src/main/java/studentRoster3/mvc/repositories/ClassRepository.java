package studentRoster3.mvc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import studentRoster3.mvc.models.Class;
import studentRoster3.mvc.models.Student;

@Repository
public interface ClassRepository extends CrudRepository<Class, Long> {
	List<Class> findAll();
	Optional <Class> findById(Long id);
	List<Class> findByStudentsNotContains(Student student);
}
