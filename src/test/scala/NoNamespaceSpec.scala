
import org.specs2.mutable.Specification
import com.julianpeeters.avro.runtime.provider._
import java.io.File
import com.novus.salat._
import global._
import com.mongodb.casbah.Imports._

class NoNamespaceSpec extends Specification {

  "A case class that was generated at runtime from the schema of an Avro Datafile" should {
    "serialize and deserialize correctly with a Salat" in {

      val file = new File("src/test/resources/AvroTypeProviderTestNoNamespace.avro")
      val runtimeClass = AvroTypeProvider.schemaToCaseClass(file)
      val record = runtimeClass.runtimeInstance
      type MyAvroRecord = record.type

      val dbo = grater[MyAvroRecord].asDBObject(record)
      val sameRecord = grater[MyAvroRecord].asObject(dbo)

      sameRecord must ===(record)
    }
  }
}
