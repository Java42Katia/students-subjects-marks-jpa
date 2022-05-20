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
		//TODO add additional marks
		collegeService.addMark(new Mark(2, 1, 50));
		collegeService.addMark(new Mark(3, 2, 70));
		collegeService.addMark(new Mark(4, 5, 99));
		collegeService.addMark(new Mark(5, 4, 78));
		collegeService.addMark(new Mark(7, 4, 60));
		collegeService.addMark(new Mark(10, 5,71));
	}
	@Test
	void getMarksStudentSubjectTest() {
		List<Integer> expected = Arrays.asList(70, 80, 90);
		List<Integer> actual = collegeService.getStudentMarksSubject("student1", "subject1");
		assertIterableEquals(expected, actual);
	}
	@Test
	void getStudentsSubjectMarks() {
		//test of the method getStudentsSubjectMark
		//TODO 
		List<String> expexted = Arrays.asList("student5", "student7");
		List<String> actual = collegeService.getStudentsSubjectMark("subject4", 60);
		assertIterableEquals(expexted, actual);
	}

}
