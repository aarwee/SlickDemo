package com.knoldus.repo

import com.knoldus.connection.MyH2DbComponent
import org.scalatest.FunSuite
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span, Millis}

/**
  * Created by knoldus on 7/3/16.
  */
class SubjectRepoTest extends FunSuite with SubjectCrud with MyH2DbComponent with ScalaFutures {



  implicit val defaultPatience = PatienceConfig(timeout = Span(5, Seconds), interval = Span(500, Millis))

  test("Add new subject info") {
    val response = insert(11,"data mining")
    whenReady(response) { output =>
      assert(output === 1)
    }
  }
  test(" invalid delete subject") {
    val response = delete(4)
    whenReady(response) { output =>
      assert(output === 0)
    }
  }
  test("valid delete subject") {
    val response = delete(2)
    whenReady(response) { output =>
      assert(output === 1)
    }

  }
    test("valid update") {
      val response = update(1,"java")
      whenReady(response) { output =>
        assert(output === 1)

       }
    }
  test("invalid update") {
    val response = update(6,"java")
    whenReady(response) { output =>
      assert(output === 0)

    }
  }
      test("retrieve all"){
        val response = getAll()
        whenReady(response) { output =>
          assert(output === List(Subject(1,"java"),Subject(2,"c++")))

        }
      }
  test("retrieve by ID"){
    val response = getById(1)
    whenReady(response) { output =>
      assert(output === List(Subject(1,"java")))

    }
  }

}
