package com.knoldus.connection

import java.util.UUID

/**
  * Created by knoldus on 7/3/16.
  */
trait MyH2DbComponent extends DbComponent {


  val driver = slick.driver.H2Driver

  import driver.api._

  val randomDb =  "jdbc:h2:mem:test" + UUID.randomUUID().toString() + ";"
  val h2Url = randomDb + "MODE=PostgreSQL;DATABASE_TO_UPPER=false;INIT=runscript from 'src/test/resources/schema.sql'\\;runscript from 'src/test/resources/schemadata.sql'"

  val db: Database = {

    Database.forURL(url = h2Url, driver = "org.h2.Driver")
  }
}

