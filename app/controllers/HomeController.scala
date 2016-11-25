package app.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import anorm._
import play.api.db._
import anorm.SqlParser._
import play.api.Play.current
import app.models._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(dbapi: DBApi) extends Controller {
 
  /**
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    
    DB.withConnection {implicit conn =>
    
      //Test query
      println("Test query Result: " + SQL("Select 1").execute())
      
      //Dummy Consumer object created
      val consumer = Consumer(111, "aaa", "aaa@gmail.com", "9876543210")
      
      println("Purchase history of a Consumer" + Purchase.purchases(consumer.id))
      
      println("Consumer with most purchases: " + Purchase.MostPurchase())
    }
      
   Ok("Consumer Concept")
  }

}
