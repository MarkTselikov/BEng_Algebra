package Utils;

import Model.Cell;
import Model.GameSerializer;
import Model.GameState;
import Model.Indexing;

import java.io.*;
import java.lang.reflect.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reflector {

    public static void getReflectionDocument() {

        Cell cell = new Cell();
        Indexing indexing = new Indexing();
        GameState state = new GameState(null, null, false);
        GameSerializer serializer = new GameSerializer();

        StringBuffer buffer = new StringBuffer();

        buffer.append("<!DOCTYPE html>\n");
        buffer.append("<html>\n");
        buffer.append("<head>\n");
        buffer.append("<title>Quarto documentation</title>\n");
        buffer.append("</head>\n");
        buffer.append("<body>\n");
        buffer.append("<h1>Class List: </h1>\n");

        try {
            FileWriter writer = new FileWriter(new File("documentation.html"), false);

            Class stateClass = state.getClass();
            buffer.append("<h2>" + stateClass.getName() +  "</h2>\n");

            appendConstructors(buffer, stateClass);
            appendFields(buffer, stateClass);
            appendMethods(buffer, stateClass);
            buffer.append("<hr /><br />");

            Class serializerClass = serializer.getClass();
            buffer.append("<h2>" + serializerClass.getName() +  "</h2>\n");

            appendConstructors(buffer, serializerClass);
            appendFields(buffer, serializerClass);
            appendMethods(buffer, serializerClass);
            buffer.append("<hr /><br />");

            Class indexingClass = indexing.getClass();
            buffer.append("<h2>" + indexingClass.getName() +  "</h2>\n");

            appendConstructors(buffer, indexingClass);
            appendFields(buffer, indexingClass);
            appendMethods(buffer, indexingClass);
            buffer.append("<hr /><br />");

            Class cellClass = cell.getClass();
            buffer.append("<h2>" + cellClass.getName() +  "</h2>\n");

            appendConstructors(buffer, cellClass);
            appendFields(buffer, cellClass);
            appendMethods(buffer, cellClass);

            buffer.append("</body>\n");
            buffer.append("</html>\n");

            writer.append(buffer.toString());

            writer.flush();
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(Reflector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private static void appendConstructors(StringBuffer buffer, Class obj) {

        buffer.append("<h3>Constructors list: </h3>\n");
        buffer.append("<table border='1'>\n");
        buffer.append("<th>Constructor name</th>"
                + "<th>Constructor parameters</th>");

        for(Constructor c : obj.getConstructors()) {
            buffer.append("<tr><td>\n");
            buffer.append(c.getName());
            buffer.append("</td><td>\n");
            if(c.getParameterCount() > 0) {
                for(Parameter parameter : c.getParameters()) {
                    buffer.append(parameter.getName() + " (" + parameter.getType().getName() + ")\n");

                }
            }
            buffer.append("</td></tr>\n");
        }
        buffer.append("</table>\n");
    }

    private static void appendMethods(StringBuffer buffer, Class obj) {

        buffer.append("<h3>Methods List: </h3>\n");
        buffer.append("<table border='1'>\n");
        buffer.append("<th>Method name</th>"
                + "<th>Method parameters</th>"
                + "<th>Return value</th>");

        for(Method method : obj.getMethods()) {
            buffer.append("<tr><td>\n");
            buffer.append(method.getName());
            buffer.append("</td><td>\n");
            if(method.getParameterCount() > 0) {
                for(Parameter parameter : method.getParameters()) {
                    buffer.append(parameter.getType().getName() + " "
                            + parameter.getName() + "\n");

                }
            }
            buffer.append("</td><td>\n");
            buffer.append(method.getReturnType().getName() + "</td>");
        }

        buffer.append("</table>\n");
    }

    private static void appendFields(StringBuffer buffer, Class obj) {

        buffer.append("<h3>Fields List: </h3>\n");
        buffer.append("<table border='1'>\n");
        buffer.append("<th>Field name</th><th>Access modifier</th>");

        for(Field field : obj.getDeclaredFields()) {
            buffer.append("<tr><td>\n");
            buffer.append(field.getName());
            buffer.append("</td><td>\n");
            buffer.append(Modifier.toString(field.getModifiers()));
            buffer.append("</td></tr>\n");
        }

        buffer.append("</table>\n");
    }
}
