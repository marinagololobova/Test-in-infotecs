package testInInfotecs.utilsFTP;

import testInInfotecs.entity.Student;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class DataControllerFtp {
    private final HashSet<Student> students;
    private final RequestsHandlerFtp requestsHandlerFtp;

    public DataControllerFtp(RequestsHandlerFtp requestsHandlerFtp) throws IOException {
        JsonParser jsonParser = new JsonParser();
        this.requestsHandlerFtp = requestsHandlerFtp;
        this.students = jsonParser.parseValue(requestsHandlerFtp.getValue());
    }

    public HashSet<Student> getStudents() {
        return students;
    }

    public Student getStudentById(Integer id) {
        return students.stream()
                .filter(student -> Objects.equals(student.getId(), id))
                .findFirst().get();
    }

    public void deleteStudentById(Integer id) {
        students.remove(getStudentById(id));
    }

    public Integer generateId() {
        AtomicInteger counter = new AtomicInteger();
        Integer numericID = counter.getAndIncrement();
        for (; ; numericID++) {
            Integer finalNumericID = numericID;
            if (students.stream().noneMatch(student -> Objects.equals(student.getId(), finalNumericID))) {
                return numericID;
            }
        }
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    protected RequestsHandlerFtp getServerControllerFtp() {
        return requestsHandlerFtp;
    }
}
