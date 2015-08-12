package test

import org.specs2.mutable.Specification
import com.julianpeeters.avro.runtime.provider._
import java.io.File
import com.novus.salat._
import global._
import com.mongodb.casbah.Imports._

class AddNamespaceSpec extends Specification {

  "A case class that was generated at runtime from the schema of an Avro Datafile" should {
    "serialize and deserialize correctly with a Salat" in {

      val file = new File("src/test/resources/enron_head.avro")
      val runtimeClass = AvroTypeProvider.schemaToCaseClass(file)
      val record = runtimeClass.runtimeInstance
      type MyAvroRecord = record.type

      val dbo = grater[MyAvroRecord].asDBObject(record)
      val sameRecord = grater[MyAvroRecord].asObject(dbo)

      sameRecord must ===(record)
    }
  }
}
