import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

	public String id_filme = "tt1285016";
	public String api_key = "52ec71bf";
	
    private final static String title = "The Social Network";
    private final static String year = "2010";
    private final static String language = "English, French";
    
    @Test
	public void omdbApiTest() {	
	
	given().when().	get("http://www.omdbapi.com/?i="+id_filme+"&apikey="+api_key+"").then().
		assertThat().
		body("Title", equalTo(title)).
		assertThat().
		body("Year", equalTo(year)).
		assertThat().
		body("Language", equalTo(language)).log().all();	
	
	}
    
    @Test
	public void omdbApiTestNoMovie() {	
	
	given().when().	get("http://www.omdbapi.com/?i=00000&apikey="+api_key+"").then().
		assertThat().
		body("Error", equalTo("Incorrect IMDb ID.")).log().all();	
	
	}

}