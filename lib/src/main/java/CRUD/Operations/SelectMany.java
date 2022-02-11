
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
            List<Actor> x= new ArrayList<Actor>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(     
            "jdbc:mysql://localhost:3306/sakila?characterEncoding=latin1","root","iitropar3117");  
            Statement statement = connection.createStatement();
            String[] temp = ((String) queryParam).split(",");
            HashMap<String,String> hashMap = Xml.extractXml();
            for (Entry<String, String> set : hashMap.entrySet()) {
                if (queryId.equals(set.getKey())) {
                    String t = set.getValue();
                    int i=0;
                    while(i<temp.length)
                    {
                        temp[i] = temp[i].trim();
                        i++;
                    }
                    int checkBrackets = t.indexOf("${");
                    String p = "";
                    int s = 0;
					while (checkBrackets != -1) {
                        p = t.substring(0, t.indexOf("${")) + temp[s] + t.substring(t.indexOf("}")+1, t.length());
						checkBrackets = p.indexOf("${");
                        t=p;
                        s++;
					}
                    ResultSet resultSet = statement.executeQuery(t);
                    
                    while (resultSet.next()) {
                        Actor x1=new Actor();
                        x1.actor_id=resultSet.getInt(1);
                        x1.first_name = resultSet.getString(2);
                        x1.last_name=resultSet.getString(3);
                        x1.last_update=resultSet.getString(4);
                        x.add(x1);
                    }
                }
            }
            connection.close();
            return x;
             
        }
        catch(Exception e) {
            return null;
        }
    }
    
}
