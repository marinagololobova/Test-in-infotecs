package testInInfotecs.menuCommands;

import testInInfotecs.entity.Student;
import testInInfotecs.utilsFTP.DataControllerFtp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GetStudentsCommand extends Command{


    @Override
    public void perform(DataControllerFtp dataControllerFtp) {
        List<Student> studentList = new ArrayList<>(dataControllerFtp.getStudents());
        studentList.stream().sorted(Comparator.comparing(Student::getName)).forEach(System.out::println);
    }
}
