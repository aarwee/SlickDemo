package com.knoldus.repo

import com.knoldus.connection.DbComponent

import scala.concurrent.{Future}

/**
  * Created by knoldus on 7/3/16.
*/
 case class SubStud(id:Int,sub_id:Int,stud_id:Int)

trait SubjectStudentrepo extends StudentCrud with SubjectCrud { this: DbComponent =>

  import driver.api._

  class SubStudDetails(tag: Tag) extends Table[SubStud](tag, "sub_stud") {

    val id = column[Int]("id", O.PrimaryKey)
    val sub_id = column[Int]("sub_id")
    val stud_id = column[Int]("stud_id")

    private def key1 = foreignKey("sub_id_fk", sub_id, subjectDetails)(_.s_id)

    private def key2 = foreignKey("stud_id_fk", stud_id, studentDetails)(_.id)

     def * = (id, sub_id, stud_id) <>(SubStud.tupled, SubStud.unapply)

  }


  val sub_studDetails = TableQuery[SubStudDetails]

  // val db = Database.forConfig("postgres")

}
  trait SubStudCrud extends SubjectStudentrepo{ this: DbComponent =>

    import driver.api._

    def insertMapping(id:Int,sub_id:Int,stud_id:Int):Future[Int] = {

      val insertStatement = sub_studDetails += SubStud(id,sub_id,stud_id)
      val result = db.run {
        insertStatement
      }
      result
    }

    def deleteMapping(id:Int):Future[Int]={

      val deleteStatement = sub_studDetails.filter(_.id === id).delete
      val result = db.run{deleteStatement}
      result
    }

    def updateMapping (id:Int,sub_id:Int,stud_id:Int):Future[Int]={

      val updateStatement = sub_studDetails.filter(_.id === id).update(SubStud(id,sub_id,stud_id))
      val result  = db.run{updateStatement}
      result
    }

    def getAllMapping:Future[List[SubStud]]={

      val searchStatement = sub_studDetails.to[List].result
      val result = db.run{searchStatement}
      result
    }
   def getByIdMapping(id:Int):Future[List[SubStud]]={

      val search = sub_studDetails.filter(_.id === id).to[List].result
      val result = db.run{search}
      result
    }
   def getStudentSubject(id:Int):Future[List[Subject]] ={

      val subjectList = sub_studDetails.filter(_.stud_id === id).join(subjectDetails).on(_.sub_id === _.s_id).map{
        case(subject,mapping) => mapping
      }.to[List].result
      val result = db.run{subjectList}
      result
    }

}



