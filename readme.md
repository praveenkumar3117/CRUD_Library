NOTE:: Insert, Update, Delete works for all the tables in database, but the selectone and selectmany only works for the actor table.


Steps for Unit Testing

1) selectone: 
you need to provide parameter in the form ("queryID","queryparam",Actor.class)

Example
@Test
    void selectOne(){

        Actor a=(Actor) d.selectOne("findActor", "182, \"DEBBIE\"", Actor.class);
        assertEquals(a.last_name,"AKROYD");
    }

I am passsing the queryParam as string and the way is shown above with an example.
Sample query correspond the queryID is given below.
<sql id="findActor" paramType="org.foo.Bar">
		<![CDATA[
		SELECT * FROM actor where actor_id =${x} AND first_name=${y};
		]]>
	</sql>

2) selectmany: 

same steps as of the selectOne

3) insert:                   (This works for all the tables). I am giving an example of the actor table

@Test
    void insert() {
        int a  = d.insert("addActor", "277, \"Pratima\", \"Singh\", \"2020-02-10 00:00:00\"");
        assertEquals(a,1);
    }
Sample Query corresponding to the uper queryID is given below
<sql id="addActor" paramType="org.foo.Bar">
		<![CDATA[
		INSERT INTO actor VALUES (${propX}, ${propY}, ${propZ}, ${propA});
		]]>
	</sql>

4) update:       (This works for all the tables). I am giving an example of city table
@Test
    void update1()
    {
        int a  = d.update("updateCity", "\"Wakanda\",184");
        assertEquals(a,1);
    }
Sample Query corresponding to the upper queryID is given below
<sql id="updateCity" paramType="org.foo.Bar">
		<![CDATA[
		UPDATE city SET city=${propy} WHERE city_id=${propX};
		]]>
	</sql>

5) delete:   (This works for all the tables). I am giving an example of actor table.
@Test
    void delete() {
        int a  = d.delete("deleteActor", "\"Manan\",\"Munda\"");
        assertEquals(a,0);
    }
Sample query corresponding to the upper queryID is given below
<sql id="deleteActor" paramType="org.foo.Bar">
		<![CDATA[
		DELETE FROM actor WHERE first_name=${propX} AND last_name=${propY};
		]]>
	</sql>
