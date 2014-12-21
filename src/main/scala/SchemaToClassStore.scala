package com.julianpeeters.avro.runtime.provider
import com.julianpeeters.caseclass.generator._
import java.util.concurrent.ConcurrentHashMap
import org.apache.avro.Schema

class SchemaToClassStore {

  val generatedClasses: scala.collection.concurrent.Map[Schema, DynamicCaseClass] = scala.collection.convert.Wrappers.JConcurrentMapWrapper(new ConcurrentHashMap[Schema, DynamicCaseClass]())

  def accept(schema: Schema, dcc: DynamicCaseClass) {
    if (!generatedClasses.contains(schema)) {
      generatedClasses += schema -> dcc
    }
  }
}
