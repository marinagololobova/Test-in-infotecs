package testInInfotecs.menuCommands;

import testInInfotecs.utilsFTP.DataControllerFtp;

public class InfoStudentCommand extends Command{

    public InfoStudentCommand(String param) {
        super(param);
    }
    @Override
    public void perform(DataControllerFtp dataControllerFtp) {
        System.out.println(dataControllerFtp.getStudentById(Integer.parseInt(getParam())));
    }
}
