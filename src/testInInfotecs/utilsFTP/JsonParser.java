package testInInfotecs.utilsFTP;

import testInInfotecs.entity.Student;

import java.util.HashSet;

public class JsonParser {

    public HashSet<Student> parseValue(String value) {
        HashSet<Student> students = new HashSet<>();
        value = value.replaceAll("\\s+", "");
        int idIndex = value.indexOf("\"id\":");
        int nameIndex;
        int indexClosingParenthesis;
        while (idIndex != -1) {
            nameIndex = value.indexOf("\"name\":");
            indexClosingParenthesis = value.indexOf("}");
            Integer id = Integer.parseInt(value.substring(idIndex + 5, nameIndex - 1));
            String name = value.substring(nameIndex + 8, indexClosingParenthesis - 1);
            if (name.contains("\"name\"") || name.contains("\"id\"") || name.contains("}")) {
                throw new NumberFormatException();
            }
            Student student = new Student(id, name);
            students.add(student);
            value = value.substring(indexClosingParenthesis + 1);
            idIndex = value.indexOf("\"id\":");
        }
        return students;
    }
}
