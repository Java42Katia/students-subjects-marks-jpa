package telran.college;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import telran.college.dto.Mark;
import telran.college.dto.Student;
import telran.college.service.CollegeService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsSubjectsMarksJpaApplicationTests {
@Autowired
	CollegeService collegeService;
	@Test
	@Order(1)
	void addMarks() {
		collegeService.addMark(new Mark(1, 1, 70));
		collegeService.addMark(new Mark(1, 1, 80));
		collegeService.addMark(new Mark(1, 1, 90));
		collegeService.addMark(new Mark(2, 1, 70));
		collegeService.addMark(new Mark(2, 1, 80));
		collegeService.addMark(new Mark(3, 1, 60));
		//TODO add additional marks
		collegeService.addMark(new Mark(2, 1, 50));
		collegeService.addMark(new Mark(3, 2, 70));
		collegeService.addMark(new Mark(4, 5, 99));
		collegeService.addMark(new Mark(5, 4, 78));
		collegeService.addMark(new Mark(7, 4, 60));
		collegeService.addMark(new Mark(10, 5,71));
	}
	@Test
	@Order(2)
	void getMarksStudentSubjectTest() {
		List<Integer> expected = Arrays.asList(70, 80, 90);
		List<Integer> actual = collegeService.getStudentMarksSubject("student1", "subject1");
		assertIterableEquals(expected, actual);
	}
	@Test
	@Order(3)
	void getStudentsSubjectMarks() {
		List<String> expected = Arrays.asList("student1", "student2");
		List<String> actual = collegeService.getStudentsSubjectMark("subject1", 70);
		assertIterableEquals(expected, actual);
	}
	@Test
	@Order(4)
	void getGoodStudents() {
		List<Student> expected = Arrays.asList(new Student(1, "student1"), new Student(2, "student2"));
		List<Student> actual = collegeService.goodCollegeStudents();
		assertIterableEquals(expected, actual);
	}
	@Test
	@Order(5)
	void deleteStudents() {
		collegeService.deleteStudentsAvgMarkLess(70);
		List<String> actual = collegeService.getStudentsSubjectMark("subject1", 30);
		assertEquals(2, actual.size());	
	}
	

}
