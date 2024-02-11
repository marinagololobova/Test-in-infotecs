package testInInfotecs.menuCommands;

import testInInfotecs.utilsFTP.DataControllerFtp;

public abstract class Command {

    private final String param;

    public Command(String param) {
        this.param = param;
    }

    public Command() {
        this.param = null;
    }

    public abstract void perform(DataControllerFtp dataControllerFtp);

    protected String getParam() {
        return param;
    }
}
