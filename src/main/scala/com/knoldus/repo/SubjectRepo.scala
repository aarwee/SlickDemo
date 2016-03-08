package com.knoldus.repo

import com.knoldus.connection.{DbComponent}
import scala.concurrent.Future

/**
  * Created by knoldus on 7/3/16.
  */

case class Subject(sub_id:Int, name:String)



trait SubjectRepo { this: DbComponent =>

  import driver.api._

  class SubjectDetails(tag: Tag) extends Table[Subject](tag, "subject") {

    val s_id = column[Int]("sub_id", O.PrimaryKey)
    val name = column[String]("name", O.SqlType("VARCHAR(50)"))

    def * = (s_id, name) <>(Subject.tupled, Subject.unapply)
  }

  val subjectDetails = TableQuery[SubjectDetails]
}

  trait SubjectCrud extends SubjectRepo  { this: DbComponent =>

    import driver.api._


  //val db = Database.forConfig("postgres")

      def insert(s_id:Int,name:String):Future[Int] = {

      val insertStatement = subjectDetails += Subject(s_id,name)
      val result = db.run {
        insertStatement
      }
      result
    }

    def delete (id:Int):Future[Int]={

     val deleteStatement = subjectDetails.filter(_.s_id === id).delete
      val result = db.run{deleteStatement}

      result
    }

    def update (id:Int,name:String):Future[Int]={

      val updateStatement = subjectDetails.filter(_.s_id === id).update(Subject(id,name))
      val result  = db.run{updateStatement}
      result
    }

    def  getAll():Future[List[Subject]]={

     val searchStatement = subjectDetails.to[List].result
      val result = db.run{searchStatement}
      result
    }

    def getById(id:Int):Future[List[Subject]]={

      val search = subjectDetails.filter(_.s_id === id).to[List].result
      val result = db.run{search}
      result

    }
  }














