package test;
import domain.Student;
import org.junit.Before;
import org.junit.Test;
import repository.StudentFileRepository;
import repository.StudentXMLRepo;
import service.Service;
import validation.StudentValidator;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class StudentServiceTest {

    private Service studentService;
    private StudentXMLRepo studentFileRepository;
    private StudentValidator studentValidator;

    @Before
    public void setUp() {
        studentFileRepository = mock(StudentXMLRepo.class);
        studentValidator = mock(StudentValidator.class);
        studentService = new Service(studentFileRepository, studentValidator, null, null, null, null);
    }

    @Test
    public void testAddNewStudent() {
        Student newStudent = new Student("1", "Doe", 937, "email@gmail.com");
        when(studentFileRepository.save(newStudent)).thenReturn(null);

        assertNull(studentService.addStudent(newStudent));
        verify(studentValidator, times(1)).validate(newStudent);
        verify(studentFileRepository, times(1)).save(newStudent);
    }

    @Test
    public void testAddExistingStudent() {
        Student existingStudent = new Student("1", "Doe", 936, "email@gmail.com");

        when(studentFileRepository.save(existingStudent)).thenReturn(existingStudent);
        Student result = studentService.addStudent(existingStudent);
        assertNotNull(result);
        assertEquals(existingStudent, result);
    }



}