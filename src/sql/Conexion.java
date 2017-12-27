
package sql;

import java.sql.*;

public class Conexion {
    
     public static Connection getConection()
    {
        Connection connection = null;
        
        try
        {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String driverUrl = "jdbc:oracle:thin:@localhost:1521:XE";
            connection = DriverManager.getConnection(driverUrl, "jaime", "1234");
            System.out.println("Conexión exitosa!!!");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return connection;
    }
    
}
