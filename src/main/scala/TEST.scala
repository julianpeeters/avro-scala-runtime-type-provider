package com.julianpeeters.avro.runtime.provider

import com.julianpeeters.caseclass.generator._
import java.io.File

object Example extends App {
      val file = new File("twitter.avro")
      val runtimeCaseClass = RuntimeTypeProvider.schemaToCaseClass(file)
      val record = runtimeCaseClass.runtimeInstance
      type MyAvroRecord = record.type
println(record)
/*
      ctx.registerClassLoader(runtimeClass.loader)
      val dbo = grater[MyAvroRecord].asDBObject(record)
      val sameRecord = grater[MyAvroRecord].asObject(dbo)

      sameRecord must ===(record)
*/

}
