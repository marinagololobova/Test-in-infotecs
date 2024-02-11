package testInInfotecs.menuCommands;

import testInInfotecs.utilsFTP.DataControllerFtp;

public class ExitAppCommand extends Command{

    @Override
    public void perform(DataControllerFtp dataControllerFtp) {
        System.out.println("Завершение работы...");
    }
}
