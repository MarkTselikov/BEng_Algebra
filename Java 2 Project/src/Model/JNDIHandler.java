package Model;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Properties;

public class JNDIHandler {

    public static String getConfigParam(String name) {

        try {

            Hashtable hashtableEnvironment = new Hashtable();
            hashtableEnvironment.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.fscontext.RefFSContextFactory");
            hashtableEnvironment.put(Context.PROVIDER_URL, "file:config");
            Context context = new InitialContext(hashtableEnvironment);

            NamingEnumeration listBindings = context.listBindings("");

            while (listBindings.hasMoreElements()) {

                Binding binding = (Binding) listBindings.nextElement();
                String propsPath = binding.getObject().toString();
                //System.out.println(propsPath);

                Properties props = new Properties();
                props.load(new FileReader(propsPath));

                String prop = props.getProperty(name);
                if(prop != null) {
                    context.close();
                    return prop;
                }

                context.close();
            }


        }
        catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}
