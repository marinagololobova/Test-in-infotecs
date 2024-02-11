package testInInfotecs.utilsFTP;

import testInInfotecs.entity.Student;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.stream.Collectors;

public class RequestsHandlerFtp {
    private static final String NAMEFILE = "studentsList.json";
    private final String path;

    public RequestsHandlerFtp(String login, String pass, String host, int port) {
        this.path = "ftp://" + login + ":" + pass + "@" + host + "/" + NAMEFILE + ";type=i";
    }

    public String getValue() throws IOException {

        URL url = new URL(path);
        InputStream inputStream = url.openStream();
        return new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
    }

    public StatusCode saveValue(HashSet<Student> students) {
        try {
            URL url = new URL(path);
            StringBuilder valueToUpload = new StringBuilder();
            valueToUpload.append("{\n");
            valueToUpload.append("\"students\": [");
            for (Student student : students) {
                valueToUpload.append(student.toJsonObject());
            }
            valueToUpload.setLength(valueToUpload.length() - 1);
            valueToUpload.append("\n]\n}");

            // Загрузка файлов

            URLConnection connection = url.openConnection();
            OutputStream outputStream = connection.getOutputStream();
            InputStream inputStream = new ByteArrayInputStream(
                    valueToUpload.toString().getBytes(StandardCharsets.UTF_8));
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            System.out.println(StatusCode.OK.getMessage());
            return StatusCode.OK;
        } catch (IOException e) {
            return StatusCode.SYNTAX_ERROR;
        }
    }
}
