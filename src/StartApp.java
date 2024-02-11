import testInInfotecs.utilsFTP.ConnectionControllerFtp;
import testInInfotecs.utilsFTP.DataControllerFtp;
import testInInfotecs.utilsFTP.Menu;
import testInInfotecs.utilsFTP.StatusCode;

import java.util.Objects;

public class StartApp {
    public static void main(String[] args) {
        ConnectionControllerFtp connectionControllerFtp = new ConnectionControllerFtp();
        Menu menu = new Menu();
        StatusCode statusCode = null;
        while (!Objects.equals(statusCode, StatusCode.OK)) {
            statusCode = connectionControllerFtp.getDataForConnection();
            System.out.println(statusCode.getMessage());
        }
        DataControllerFtp dataControllerFtp = connectionControllerFtp.getDataControllerFtp();
        menu.listenCommands(dataControllerFtp);
    }
}