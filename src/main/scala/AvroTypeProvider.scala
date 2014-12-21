package com.julianpeeters.avro.runtime.provider

import com.julianpeeters.caseclass.generator._

import java.io.File
import org.apache.avro.Schema
import scala.collection.JavaConverters._


object AvroTypeProvider {

  def schemaToCaseClass(infile: File): DynamicCaseClass = {
 
    val classStore = new SchemaToClassStore
    val schema: Schema = SchemaParser.getSchema(infile)
    val recordSchemas: List[Schema] = schema::(SchemaParser.getNestedSchemas(schema))
    val namespace: ClassNamespace = ClassNamespace(SchemaParser.getNamespace(schema))

    val classData: List[DynamicCaseClass] = recordSchemas.reverse.map(s => {
      val name: ClassName = ClassName(s.getName)
      val fields: ClassFieldData = ClassFieldData(s.getFields.asScala.toList.map(avroField => {
        AvroTypeMatcher.parseField(classStore, namespace, avroField)
      }))
    val cd = ClassData(namespace, name, fields)
    val dcc = new DynamicCaseClass(cd)
    classStore.accept(s, dcc)
    dcc
    })

    // having loaded the most nested classes first, take the root class.
    classData.last
  }

}
