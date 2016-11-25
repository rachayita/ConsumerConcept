package app.models

case class Product(val id: Long,
                   val name: String, 
                   var items: Int, 
                   val price: Int, 
                   val desc: String) {
    
  def buy(items: Int): Boolean = {
    if(this.items >= items){
      this.items -= items
      true
    }
    false
  }
  
}