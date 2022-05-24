package telran.college.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.college.entities.MarkEntity;
import telran.college.entities.projection.MarkProj;
import telran.college.entities.projection.StudentNameProj;
import telran.college.entities.projection.StudentProj;

public interface MarkRepository extends JpaRepository<MarkEntity, Long> {
//	@Query("select m.mark as mark from MarkEntity m where m.student.name = ?1"
//			+ " and m.subject.subjectName = ?2")
List<MarkProj> findByStudentNameAndSubjectSubjectName(String name, String subjectName);
/************************************************************************************/
 @Query("select distinct m.student.name as studentName from MarkEntity m"
 		+ " where m.subject.subjectName = :subjectName and m.mark >= :mark")
List<StudentNameProj> findDistinctBySubjectSubjectNameAndMarkGreaterThanEqual(String subjectName, int mark);
 /*************************************/
 @Query("select m.student.id as id, m.student.name as name from MarkEntity m "
 		+ "group by m.student.id, m.student.name having avg(m.mark) >= "
 		+ "(select avg(m1.mark) from MarkEntity m1)")
 List<StudentProj> findGoodStudents() ;

 
 
}
