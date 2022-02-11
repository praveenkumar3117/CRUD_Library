package CRUD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import CRUD.Operations.Delete;
import CRUD.Operations.Insert;
import CRUD.Operations.SelectMany;
import CRUD.Operations.SelectOne;
import CRUD.Operations.Update;


public class Database implements SqlRunner{
    
    static HashMap<String, String> hashMap = Xml.extractXml();         //extract and store the queries file

    public static void main(String args[]) {

        if (hashMap == null) {                                      //Error Handling
            System.out.println("Error!!");
        }
        
        
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver");                    //connecting to the database
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/sakila?characterEncoding=latin1","root","iitropar3117");  
            Statement statement = connection.createStatement();          
            String Operation;                       //used to fetch the query from the hashmap
            ResultSet resultSet;                  //to store the result of select query 
            int rowsAffected;                      //number of rows affected
            for (Entry<String, String> set : hashMap.entrySet()) {
                Operation = set.getValue().substring(0, set.getValue().indexOf(' '));
				
                if (Operation.toUpperCase().equals("SELECT")) {         //changing the query to uppercase to avoid any type of error
                    resultSet = statement.executeQuery(set.getValue());          //executing the select query
                    while (resultSet.next()) {         //printing the resultset contents
                        System.out.println(resultSet.getInt(1) + "  " + resultSet.getString(2) + "  " + resultSet.getString(3));
                    }
                }
                else if (Operation.toUpperCase().equals("INSERT") || Operation.toUpperCase().equals("DELETE") ||Operation.toUpperCase().equals("UPDATE")) {
                    rowsAffected = statement.executeUpdate(set.getValue());          //executing the other queries
                    System.out.println("Rows affected: " + rowsAffected);
                }
            }
            connection.close();
        }
        catch(Exception e) {                    //error handling
            System.out.println(e);
        } 
    }

    @Override
    public Object selectOne(String queryId, Object queryParam, Class resultType) {         //selectone function call
        
       SelectOne a=new SelectOne();
       return a.selectOne(queryId, queryParam, resultType);  
    }

    @Override
    public List<?> selectMany(String queryId, Object queryParam, Class resultItemType) {   //selectmany   function call
        SelectMany a=new SelectMany();
        return a.selectMany(queryId, queryParam, resultItemType);

        
    }

    @Override
    public int insert(String queryId, Object queryParam) {             //insert function call
        Insert a=new Insert();
        return a.insert(queryId, queryParam);
    }

    @Override
    public int update(String queryId, Object queryParam) {            //update function call
        Update a=new Update();
        return a.update(queryId, queryParam);
        
    }

    @Override
    public int delete(String queryId, Object queryParam) {            //delete function call
        Delete a=new Delete();
        return a.delete(queryId, queryParam);
        
    }
    
}
