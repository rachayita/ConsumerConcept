package app.models

import anorm._
import play.api.db.DB
import anorm.SqlParser._
import java.sql.Connection
import java.util.Date
import play.api.Play.current

case class Purchase(val id: Long, 
                    val custId: Long, 
                    val productId: Long, 
                    val items: Int, 
                    val buyDate: Date = new Date()
                   )

object Purchase {
    
  val purchParser = long("id") ~ long("custId") ~ long("productId") ~ int("items") ~ date("buyDate") map {
    case id ~ custId ~ productId ~ items ~ buyDate => Purchase(id, custId, productId, items, buyDate)
  }
  
  val mostPurchParser = long("custId") ~ int("most") map {
    case custId ~ most => (custId, most)
  }
  
  /**
   * Retrieves history of purchases for a consumer
   */
  def purchases(consumerId: Long)(implicit conn: Connection): List[Purchase] = {
    val query = SQL(
      "SELECT * FROM PURCHASE WHERE custId = {id}").on('id -> consumerId
    )
    query.as(purchParser.*)
  }
  
  /**
   * Retrieves record having consumer with most purchases
   */
  
  def MostPurchase()(implicit conn: Connection): Tuple2[Long, Int]= {
    val query = SQL(
      "SELECT custId, COUNT(*) AS most FROM PURCHASE GROUP BY custId ORDER BY COUNT(*) DESC LIMIT 1"
    )
    query.as(mostPurchParser.single)
  }

}

