package testInInfotecs.utilsFTP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ConnectionControllerFtp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Integer PORT = 21;
    private DataControllerFtp dataControllerFtp;



    public DataControllerFtp getDataControllerFtp() {
        return dataControllerFtp;
    }

    public StatusCode connectToServer(String login, String password, String address) {
        try {
            RequestsHandlerFtp requestsHandlerFtp = new RequestsHandlerFtp(login, password, address, PORT);
            dataControllerFtp = new DataControllerFtp(requestsHandlerFtp);
            return StatusCode.OK;
        } catch (UnknownHostException e) {
            return StatusCode.NOT_FOUND_HOST;
        } catch (NumberFormatException e) {
            return StatusCode.FILE_STATUS;
        } catch (IllegalArgumentException e) {
            return StatusCode.LOGIN_HAS_NOT_BEEN_COMPLETED;
        } catch (MalformedURLException e) {
            return StatusCode.SYNTAX_ERROR;
        } catch (NullPointerException e) {
            return StatusCode.FILE_NOT_AVAILABLE;
        } catch (FileNotFoundException e) {
            return StatusCode.INVALID_PASSWORD;
        } catch (IOException e) {
            return StatusCode.INTERNAL_SERVER_ERROR;
        }
    }

    public StatusCode getDataForConnection() {
        System.out.println("Введите IP-адрес сервера: ");
        String address = scanner.nextLine().split(" ")[0];
        System.out.println("Введите логин: ");
        String login = scanner.nextLine().split(" ")[0];
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine().split(" ")[0];

        return connectToServer(login, password, address);
    }
}
