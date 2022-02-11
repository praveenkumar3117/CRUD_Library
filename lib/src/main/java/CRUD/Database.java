package CRUD;
import java.util.List;
import CRUD.Operations.Delete;
import CRUD.Operations.Insert;
import CRUD.Operations.SelectMany;
import CRUD.Operations.SelectOne;
import CRUD.Operations.Update;


public class Database implements SqlRunner{
    
    //static HashMap<String, String> hashMap = Xml.extractXml();         //extract and store the queries file


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
