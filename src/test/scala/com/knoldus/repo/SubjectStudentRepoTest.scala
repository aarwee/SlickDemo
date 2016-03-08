
package com.knoldus.repo

import com.knoldus.connection.MyH2DbComponent
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}

/**
  * Created by knoldus on 7/3/16.
  */
class SubjectStudentRepoTest extends FunSuite with SubStudCrud with MyH2DbComponent with ScalaFutures {

  implicit val defaultPatience = PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  test("valid insert"){
    val response = insertMapping(3,1,1)
    whenReady(response){ output =>
      assert(output === 1)
    }

  }

  test(" invalid delete subject") {
    val response = deleteMapping(4)
    whenReady(response) { output =>
      assert(output === 0)
    }
  }
  test("valid delete subject") {
    val response = deleteMapping(2)
    whenReady(response) { output =>
      assert(output === 1)
    }

  }
  test("valid update") {
    val response = updateMapping(1,2,2)
    whenReady(response) { output =>
      assert(output === 1)

    }
  }
  test("invalid update") {
    val response = updateMapping(6,22,33)
    whenReady(response) { output =>
      assert(output === 0)

    }
  }
  test("retrieve all"){
    val response = getAllMapping
    whenReady(response) { output =>
      assert(output === List(SubStud(1,1,2),SubStud(2,2,1)))

    }
  }
  test("retrieve by ID"){
    val response = getByIdMapping(1)
    whenReady(response) { output =>
      assert(output === List(SubStud(1,1,2)))
    }
  }
  test("get subject") {
    val resonse = getStudentSubject(2)
    whenReady(resonse) { output =>
      assert(output === List(Subject(1, "java")))
    }
  }
  test("invalid get subject") {
    val resonse = getStudentSubject(5)
    whenReady(resonse) { output =>
      assert(output === List())
    }
  }

}

