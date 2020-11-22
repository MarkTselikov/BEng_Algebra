package Model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Binding;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Properties;

public class Indexing {

    public static final int EMPTY = -1;

    public static final int WHITE_SHORT_SOLID_SQUARE = 1111;
    public static final int WHITE_SHORT_HOLLOW_SQUARE = 1121;
    public static final int WHITE_TALL_SOLID_SQUARE = 1211;
    public static final int WHITE_TALL_HOLLOW_SQUARE = 1221;
    public static final int WHITE_SHORT_SOLID_CIRCLE = 1112;
    public static final int WHITE_SHORT_HOLLOW_CIRCLE = 1122;
    public static final int WHITE_TALL_SOLID_CIRCLE = 1212;
    public static final int WHITE_TALL_HOLLOW_CIRCLE = 1222;

    public static final int BLACK_SHORT_SOLID_SQUARE = 2111;
    public static final int BLACK_SHORT_HOLLOW_SQUARE = 2121;
    public static final int BLACK_TALL_SOLID_SQUARE = 2211;
    public static final int BLACK_TALL_HOLLOW_SQUARE = 2221;
    public static final int BLACK_SHORT_SOLID_CIRCLE = 2112;
    public static final int BLACK_SHORT_HOLLOW_CIRCLE = 2122;
    public static final int BLACK_TALL_SOLID_CIRCLE = 2212;
    public static final int BLACK_TALL_HOLLOW_CIRCLE = 2222;

    public static final int[][] VICTORY_CONDITIONS = {
        {0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15},
        {0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 15}, {3, 7, 11, 15},
        {0, 5, 10, 15}, {3, 6, 9, 12}
    };


    public static void jndiSetup() {

        try {

            Hashtable hashtableEnvironment = new Hashtable();
            hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.fscontext.RefFSContextFactory");
            hashtableEnvironment.put(Context.PROVIDER_URL, "file:config");
            Context context = new InitialContext(hashtableEnvironment);

            NamingEnumeration listBindings = context.listBindings("");

            while (listBindings.hasMoreElements()) {
                //Binding p = (Binding) flist.next();
                //System.out.println(p.getName());

                Binding binding = (Binding) listBindings.nextElement();
                String propsPath = binding.getObject().toString();
                System.out.println(propsPath);

                //Object obj = context.lookup("config.txt"); //BLACK_SHORT_SOLID_SQUARE
                //System.out.println(obj);


                Properties props = new Properties();
                props.load(new FileReader(propsPath));

                System.out.println(props);

                context.close();
            }


        }
        catch (Exception e) { e.printStackTrace(); }
    }


    public static void main(String[] args) {
        jndiSetup();
    }

}
