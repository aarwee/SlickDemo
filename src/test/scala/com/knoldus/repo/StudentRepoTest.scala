
package com.knoldus.repo

import com.knoldus.connection.MyH2DbComponent
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Millis, Span}

/**
  * Created by knoldus on 7/3/16.
  */
class StudentRepoTest extends FunSuite with StudentCrud with MyH2DbComponent with ScalaFutures {



  implicit val defaultPatience = PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  test("Add new subject info") {
    val response = insertStudent(11,"kunal","k@k.com")
    whenReady(response) { output =>
      assert(output === 1)
    }
  }
  test(" invalid delete subject") {
    val response = deleteStudent(4)
    whenReady(response) { output =>
      assert(output === 0)
    }
  }
  test("valid delete subject") {
    val response = deleteStudent(2)
    whenReady(response) { output =>
      assert(output === 1)
    }

  }
  test("valid update") {
    val response = updateStudent(1,"rishabh","aarwee@gmail.com")
    whenReady(response) { output =>
      assert(output === 1)

    }
  }
  test("invalid update") {
    val response = updateStudent(6,"abc","abc")
    whenReady(response) { output =>
      assert(output === 0)

    }
  }
  test("retrieve all"){
    val response = getAllStudent()
    whenReady(response) { output =>
      assert(output === List(Student(1,"rishabh","r@r.com"),Student(2,"verma","v@v.com")))

    }
  }
  test("retrieve by ID"){
    val response = getByIdStudent(1)
    whenReady(response) { output =>
      assert(output === List(Student(1,"rishabh","r@r.com")))

    }
  }
}
