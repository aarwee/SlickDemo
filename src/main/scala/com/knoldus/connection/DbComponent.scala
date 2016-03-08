package com.knoldus.connection

import slick.driver.JdbcProfile
/**
  * Created by knoldus on 7/3/16.
  */
trait DbComponent {

  val driver: JdbcProfile
    import slick.driver.PostgresDriver.api._
    val db:Database

}
