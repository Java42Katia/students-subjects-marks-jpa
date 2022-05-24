package telran.college.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.college.entities.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
	 
	@Query("select s from SubjectEntity s where s.id in "
			+ "(select m.subject.id from MarkEntity m group by m.subject.id having avg(m.mark) > cast(:avgMark as double))")
	List<SubjectEntity> findSubjectsHavingAvgMarkGreater(Number avgMark);
}
