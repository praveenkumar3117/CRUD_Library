package CRUD;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;


public class SqlRunnerTest {

    public Database d=new Database();

   

    @Test
    void selectOne(){

        Actor a=(Actor) d.selectOne("findActor", "196, \"BELA\"", Actor.class);
        assertEquals(a.last_name,"WALKEN");
    }
    
    @SuppressWarnings("unchecked")
    @Test
    void selectMany() {
        List<Actor> a= (List<Actor>) d.selectMany("findActor2", "", Actor.class);
        assertEquals(a.size(), 206);
    }

   @Test
    void update() {
        int a  = d.update("updateActor", "\"udhsjf\", \"nnfngb\", 768");
        assertEquals(a,0);
    }

    @Test
    void insert() {
        int a  = d.insert("addActor", "277, \"Pratima\", \"Singh\", \"2020-02-10 00:00:00\"");
        assertEquals(a,0);
    }

    @Test
    void delete() {
        int a  = d.delete("deleteActor", "\"Praveen\",\"Ku\"");
        assertEquals(a,0);
    }
    @Test
    void update1()
    {
        int a  = d.update("updateCity", "\"Xandar\",184");
        assertEquals(a,1);
    }
    @Test
    void delete1(){
        int a=d.delete("deleteCity","700");
        assertEquals(a,0);
    }

}
