package Bitrix

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.SQLException
import java.sql.Statement

class DataBase {

    public static final String dbURL = "jdbc:mysql://localhost:3306/bitrixat?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false"
    public static final String userName = Config.getProperty("dataBaseUserName")
    public static final String userPass = Config.getProperty("dataBaseRootPassword")
    private static Logger logger = LogManager.getLogger(BitrixSpec.logger.getName())
     static Connection createConnection(){
        try {
            Connection connection = DriverManager.getConnection(dbURL,userName,userPass)
            connection
        }
        catch (SQLException e){
            e.printStackTrace()
        }

    }

    static boolean doDbScript(String query){
        Statement sql
        Connection connection
        logger.info("sql: $query")
        try{
            connection = createConnection()
            sql = connection.createStatement()
            sql.execute(query)
        }
        catch (SQLException e){
            e.printStackTrace()
        }
        finally {
            if(sql!=null){
                try {
                    sql.close()
                }
                catch (SQLException e){
                    throw new RuntimeException(e)
                }
            }
            if(connection!=null){
                try{
                    connection.close()
                }
                catch (SQLException e){
                    throw new RuntimeException(e)
                }

            }
        }
    }

    static List<LinkedHashMap<String,String>> doSelectQuery(String query){
        ResultSet res
        Statement statement
        Connection connection = createConnection()
        List<LinkedHashMap<String,String>> results = new ArrayList<>()
        logger.info("sql: $query")
        try {
            statement=connection.createStatement()
            res=statement.executeQuery(query)
            ResultSetMetaData resultSetMetaData = res.getMetaData()
            while (res.next()){
                LinkedHashMap rows = new LinkedHashMap()
                for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                    rows.put(resultSetMetaData.getColumnName(i),res.getString(i))
                }
                results.add(rows)
            }
        }
        catch(SQLException e){
            logger.info("Ошибка: ${Arrays.toString(e.getStackTrace())}")
        }
        finally {
            return results
        }
    }



}
