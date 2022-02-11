package CRUD.Operations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;
import CRUD.Xml;
public class Update {
    public int update(String queryId, Object queryParam) {
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(     
            "jdbc:mysql://localhost:3306/sakila?characterEncoding=latin1","root","iitropar3117");  
            Statement statement = connection.createStatement();
            int rows = 0;
            
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
                    rows = statement.executeUpdate(t);
                }
            }
            connection.close();
            return rows;
             
        }
        catch(Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    
}
