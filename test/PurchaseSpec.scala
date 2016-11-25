import org.scalatest._
import play.api.db.evolutions._
import play.api.db._
import app.models._

class PurchaseSpec extends FlatSpec{
  
  "A Counsumer " should "be created" in{
    val c = Consumer(7, "dummyName", "dummyEmail@gmail.com", "9876543210")
    
    Assertions.assert(
      c.isInstanceOf[Consumer], 
      "This is not the Consumer we are looking for.") 
    }
  
  
  "Database connection " should " be to retrieve data" in{
    Databases.withInMemory() { database =>
      Evolutions.withEvolutions(database){
        val conn = database.getConnection()
        Assertions.assert(
          conn.prepareStatement("SELECT * FROM PURCHASE").executeQuery().next(),
          "unable to retrieve records from DB"
        )
      }
    }
  }
}