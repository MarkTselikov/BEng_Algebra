package DAL.SQL;

import javax.sql.DataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class DataSourceSingleton {

    /*private static final String DATABASE_NAME = "OutpatientManagementDB";
    private static final String SERVER_NAME = ".\\sqlexpress";
    private static final String USER = "Login";
    private static final String PASSWORD = "password";*/

    private static final String DATABASE_NAME = "outpatientdb";
    private static final String SERVER_NAME = "den1.mssql5.gear.host";
    private static final String USER = "outpatientdb";
    private static final String PASSWORD = "Dn14rj-WU_vt";

    private static DataSource dsInstance;

    private static DataSource createInstance() {

        SQLServerDataSource dataSource = new SQLServerDataSource();

        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);

        return dataSource;
        }

    public static DataSource getInstance() {
        if(dsInstance == null)
            dsInstance = createInstance();
        return dsInstance;
    }

    private DataSourceSingleton() {
    }
}
