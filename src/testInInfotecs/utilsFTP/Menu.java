package testInInfotecs.utilsFTP;

import testInInfotecs.menuCommands.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
    private static final Scanner SCANNER = new Scanner(System.in);
    private boolean starting;

    public Menu() {
        this.starting = true;
    }

    public static final String[] options = {
            "1.\tПолучить список студентов по имени;",
            "2 id.\tПолучение информации о студенте по id;",
            "3 имя.\tДобавление студента;",
            "4 id.\tУдаление студента по id;",
            "5.\tЗавершение работы."
    };

    public void listenCommands(DataControllerFtp dataControllerFtp) {
        while (starting) {
            try {
                Command command = readCommand();
                if (command == null) {
                    System.out.println("Такой команды не существует.");
                    continue;
                }
                command.perform(dataControllerFtp);
                if (dataControllerFtp.getServerControllerFtp().saveValue(
                        dataControllerFtp.getStudents()).equals(StatusCode.SYNTAX_ERROR)) {
                    starting = false;
                    System.out.println("Сервер закрыт, соединение потеряно");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Параметр неверен или пуст!");
            } catch (NoSuchElementException e) {
                System.out.println("Студент с таким id не найден");
            }
        }
    }


    private Command readCommand() {
        for (String option : options) {
            System.out.println(option);
        }
        System.out.print("Выберите действие: ");
        String[] commandLine = SCANNER.nextLine().split(" ");
        String command = commandLine[0];
        String param = null;
        if (commandLine.length > 1) {
            param = commandLine[1];
        }
        switch (command) {
            case "1":
                return new GetStudentsCommand();
            case "2":
                if (param == null) {
                    throw new IllegalArgumentException();
                }
                return new InfoStudentCommand(param);
            case "3":
                if (param == null) {
                    throw new IllegalArgumentException();
                }
                return new AddStudentCommand(param);
            case "4":
                if (param == null) {
                    throw new IllegalArgumentException();
                }
                return new DeleteStudentCommand(param);
            case "5":
                starting = false;
                return new ExitAppCommand();
            default:
                return null;
        }
    }
}
