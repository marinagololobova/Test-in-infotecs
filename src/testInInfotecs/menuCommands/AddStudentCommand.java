package testInInfotecs.menuCommands;

import testInInfotecs.entity.Student;
import testInInfotecs.utilsFTP.DataControllerFtp;

public class AddStudentCommand extends Command{

    public AddStudentCommand(String param) {
        super(param);
    }

    @Override
    public void perform(DataControllerFtp dataControllerFtp) {
        Student student = new Student(dataControllerFtp.generateId(), getParam());
        dataControllerFtp.addStudent(student);
    }
}
