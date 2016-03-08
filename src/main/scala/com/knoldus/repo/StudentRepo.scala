
package com.knoldus.repo


import com.knoldus.connection.DbComponent

import scala.concurrent.Future


/**
  * Created by knoldus on 7/3/16.
*/
case class Student(id:Int, name:String,email:String)

trait StudentRepo { this: DbComponent =>

  import driver.api._
  class StudentDetails(tag: Tag) extends Table[Student](tag, "student_info") {

    val id = column[Int]("id", O.PrimaryKey)
    val name = column[String]("name", O.SqlType("VARCHAR(50)"))
    val email = column[String]("email", O.SqlType("VARCHAR(50)"))

   def * = (id, name, email) <>(Student.tupled, Student.unapply)
  }

  val studentDetails = TableQuery[StudentDetails]
}

  trait StudentCrud extends  StudentRepo{ this: DbComponent=>

    import driver.api._
   def insertStudent(id:Int,name:String,email:String):Future[Int] = {
   val insertStatement = studentDetails+= Student(id,name,email)
   val result = db.run {
     insertStatement
   }
   result
 }
   def deleteStudent (id:Int):Future[Int]={

      val deleteStatement = studentDetails.filter(_.id === id).delete
      val result = db.run{deleteStatement}
      result
    }

  def updateStudent (id:Int,name:String,email:String):Future[Int]={

      val updateStatement = studentDetails.filter(_.id === id).update(Student(id,name,email))
      val result  = db.run{updateStatement}
      result
    }

  def  getAllStudent():Future[List[Student]]={

      val searchStatement = studentDetails.to[List].result
      val result = db.run{searchStatement}
      result
    }

    def getByIdStudent(id:Int):Future[List[Student]]={

      val search = studentDetails.filter(_.id === id).to[List].result
      val result = db.run{search}
      result
    }
  }


