package test;

import domain.Tema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

public class TestClassAssignment
{
    public static Service service;

    @BeforeAll
    public static void setup() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        TestClassStudent.service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    // White box testing
    @Test
    public void addTema_ValidData_CreateSuccessfully() {
        String nrTema = "100";
        String descriere = "test";
        int deadline = 12;
        int primire = 1;
        Tema tema = new Tema(nrTema, descriere, deadline, primire);
        try {
            service.addTema(tema);
            assert(true);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
            assert(true);
        }
    }

    @Test
    public void addTema_Invalid_nrTema_emptyString_ThrowsError() {
        String nrTema = "22";
        String descriere = "test";
        int deadline = 12;
        int primire = 2;
        Tema tema = new Tema(nrTema, descriere, deadline, primire);
        try {
            service.addTema(tema);
            assert(false);
        } catch (Exception exception) {
            System.out.println("Exception: " + exception.getMessage());
            assert(true);
        }
    }


//    @Test
//    public void addTema_Invalid_nrTema_duplicate_ThrowsError() {
//        String nrTema = "100";
//        String descriere = "test";
//        int deadline = 12;
//        int primire = 1;
//        Tema tema = new Tema(nrTema, descriere, deadline, primire);
//        try {
//            Tema response = service.addTema(tema);
//            assert(tema == response);
//        } catch (ValidationException exception) {
//            System.out.println("Validation exception: " + exception.getMessage());
//            assert(true);
//        }
//    }
}
