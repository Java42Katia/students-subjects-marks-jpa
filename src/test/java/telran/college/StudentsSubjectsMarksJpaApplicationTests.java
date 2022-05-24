package telran.college;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import telran.college.dto.Mark;
import telran.college.dto.Student;
import telran.college.dto.Subject;
import telran.college.service.CollegeService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsSubjectsMarksJpaApplicationTests {
	@Autowired
	ApplicationContext appCtx;
	
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
	
	@Test
	@Order(6)
	void subjectsAvgMarkGreaterTest() {
		// SubjectRepository contains only one subject "subject1"
		List<Subject> actual = collegeService.subjectsAvgMarkGreater(0);
		assertEquals(1, actual.size());
		// all subjects out of condition
		actual = collegeService.subjectsAvgMarkGreater(100);
		assertTrue(actual.isEmpty());
		// add subject5 with avg > 90
		IntStream.rangeClosed(1, 10).forEach(i -> collegeService.addMark(new Mark(1, 5, 100)));
		actual = collegeService.subjectsAvgMarkGreater(90);
		assertEquals("subject5", actual.get(0).subjectName);
		// add subject4 with avg > 90
		IntStream.rangeClosed(1, 10).forEach(i -> collegeService.addMark(new Mark(2, 4, 100)));
		actual = collegeService.subjectsAvgMarkGreater(90);
		assertTrue(actual.stream().anyMatch(s -> s.subjectName.equals("subject4")));
		assertEquals(2, actual.size());
	}
	
	@Test
	@Order(7)
	void studentsCountMarkLessTest() {
		// student1 has 13 marks
		// student2 has 12 marks
		List<Student> actual = collegeService.deleteStudentsMarksCountLess(0);
		assertEquals(0,  actual.size());
		actual = collegeService.deleteStudentsMarksCountLess(13);
		assertEquals(1, actual.size());
		assertEquals("student2", actual.get(0).name);
		
	}
}
