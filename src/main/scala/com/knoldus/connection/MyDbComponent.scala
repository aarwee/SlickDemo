package com.knoldus.connection

import slick.driver.PostgresDriver

/**
  * Created by knoldus on 7/3/16.
  */
trait MyDbComponent extends DbComponent {

  val driver : PostgresDriver

  import driver.api._
  val db:Database = PostgresDB.connectionPool


}


 object PostgresDB {

  import slick.driver.PostgresDriver.api._

  val connectionPool = Database.forConfig("postgres")

}
