package testInInfotecs.menuCommands;

import testInInfotecs.utilsFTP.DataControllerFtp;

import java.util.NoSuchElementException;

public class DeleteStudentCommand extends Command{


    public DeleteStudentCommand(String param) {
        super(param);
    }

    @Override
    public void perform(DataControllerFtp dataControllerFtp) {
        try {
            dataControllerFtp.deleteStudentById(Integer.parseInt(getParam()));
        } catch (NoSuchElementException e) {
            System.out.println("Студент с таким id не найден.");
        }
    }
}
