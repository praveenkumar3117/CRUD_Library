package CRUD.Operations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;
import CRUD.Xml;
public class Insert {
    public int insert(String queryId, Object queryParam) {
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver");          //making database connection
            Connection connection = DriverManager.getConnection(     
            "jdbc:mysql://localhost:3306/sakila?characterEncoding=latin1","root","iitropar3117");  
            Statement statement = connection.createStatement();
            int rows = 0;     //used to return the affected rows
            
            String[] temp = ((String) queryParam).split(",");      //spiltting the queryparam
            HashMap<String,String> hashMap = Xml.extractXml();
            for (Entry<String, String> set : hashMap.entrySet()) { //searching queryid in the hashmap
                if (queryId.equals(set.getKey())) {
                    String t = set.getValue();
                    int i=0;
                    while(i<temp.length)
                    {
                        temp[i] = temp[i].trim();        //trimming part
                        i++; 
                    }
                    int checkBrackets = t.indexOf("${");       //used to find the index of brackets
                    String p = "";
                    int s = 0;
					while (checkBrackets != -1) {
                        p = t.substring(0, t.indexOf("${")) + temp[s] + t.substring(t.indexOf("}")+1, t.length()); //tranforming the query
						checkBrackets = p.indexOf("${");
                        t=p;
                        s++;
					}
                    rows = statement.executeUpdate(t);  //executing the query
                }
            }
            connection.close();     //close the database connection
            return rows;
             
        }
        catch(Exception e) {         //error handling
            return 0;
        }
    }
    
}
