
package CRUD.Operations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import CRUD.Actor;
import CRUD.Xml;
import java.util.ArrayList;
public class SelectMany {
    public List<?> selectMany(String queryId, Object queryParam, Class resultItemType) {
        
        try {  
            List<Actor> x= new ArrayList<Actor>();                            //list that will be used to return
            Class.forName("com.mysql.cj.jdbc.Driver");              //making database connection
            Connection connection = DriverManager.getConnection(     
            "jdbc:mysql://localhost:3306/sakila?characterEncoding=latin1","root","iitropar3117");  
            Statement statement = connection.createStatement();
            String[] temp = ((String) queryParam).split(",");            //splitting the queryparam
            HashMap<String,String> hashMap = Xml.extractXml();
            for (Entry<String, String> set : hashMap.entrySet()) {   //searching queryid in hashmap
                if (queryId.equals(set.getKey())) {
                    String t = set.getValue();
                    int i=0;
                    while(i<temp.length)
                    {
                        temp[i] = temp[i].trim();           //trimming part
                        i++;
                    }
                    int checkBrackets = t.indexOf("${");    //used to find the index of the brackets
                    String p = "";
                    int s = 0;
					while (checkBrackets != -1) {
                        p = t.substring(0, t.indexOf("${")) + temp[s] + t.substring(t.indexOf("}")+1, t.length()); //transforming the query
						checkBrackets = p.indexOf("${");
                        t=p;
                        s++;
					}
                    ResultSet resultSet = statement.executeQuery(t);    //executing the query
                    
                    while (resultSet.next()) {          //set the attributes of the actor 
                        Actor x1=new Actor();
                        x1.actor_id=resultSet.getInt(1);
                        x1.first_name = resultSet.getString(2);
                        x1.last_name=resultSet.getString(3);
                        x1.last_update=resultSet.getString(4);
                        x.add(x1);         //add to the list
                    }
                }
            }
            connection.close();           //closing the database connection
            return x;
             
        }
        catch(Exception e) {          //error handling
            return null;
        }
    }
    
}
