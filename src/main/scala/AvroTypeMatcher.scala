package com.julianpeeters.avro.runtime.provider

import com.julianpeeters.caseclass.generator._
import org.apache.avro.Schema
import scala.collection.JavaConversions._
import scala.reflect.runtime.universe._


object AvroTypeMatcher {

//  def parseField(namespace: Option[String], field: Schema.Field): FieldData = {
  def parseField(namespace: ClassNamespace, field: Schema.Field): FieldData = {

  //  def avroToScalaType(schema: org.apache.avro.Schema): String = {
//    def avroToScalaType(schema: org.apache.avro.Schema): Class[_] = {
    def avroToScalaType(schema: org.apache.avro.Schema): Type = {
/*
      schema.getType match { 
        case Schema.Type.ARRAY    => "List[" + avroToScalaType(schema.getElementType) + "]"
        case Schema.Type.BOOLEAN  => typeOf[Boolean]
        //case Schema.Type.BYTES    => //TODO
        case Schema.Type.DOUBLE   => typeOf[Double]
        //case Schema.Type.FIXED    => //TODO
        case Schema.Type.FLOAT    => typeOf[Float]
        case Schema.Type.LONG     => "Long"//scala.Long//"Long"
        case Schema.Type.INT      => typeOf[Int]
        //case Schema.Type.MAP      => //TODO
        case Schema.Type.NULL     => typeOf[Null]
        case Schema.Type.STRING   => "String"
        case Schema.Type.RECORD   => { 

          field.schema.getName match {
            // cases where a record is found as a field vs found as a member of a union vs 
            // found as an element of an array
            case "array" | "union" => schema.getName
            case recordName        => recordName 
          }

        }
        case Schema.Type.UNION    => { 

          val unionSchemas = schema.getTypes.toList

          if (unionSchemas.length == 2 && unionSchemas.exists(schema => schema.getType == Schema.Type.NULL)) {
            val maybeSchema = unionSchemas.find(schema => schema.getType != Schema.Type.NULL)
            if (maybeSchema.isDefined ) "Option[" + avroToScalaType(maybeSchema.get) + "]"
            else error("no avro type found in this union")  
          }
          else error("not a union field")
        }
        case x => error( x +  " is not a valid Avro type")
      }
*/

 schema.getType match { 
/*
        case Schema.Type.ARRAY    => "List[" + avroToScalaType(schema.getElementType) + "]"
        case Schema.Type.BOOLEAN  => typeOf[Boolean]
        //case Schema.Type.BYTES    => //TODO
        case Schema.Type.DOUBLE   => typeOf[Double]
        //case Schema.Type.FIXED    => //TODO
        case Schema.Type.FLOAT    => typeOf[Float]
*/
        case Schema.Type.LONG     => typeOf[Long]//classOf[Long]//"Long"//scala.Long//"Long"
      //  case Schema.Type.INT      => typeOf[Int]
        //case Schema.Type.MAP      => //TODO
    //    case Schema.Type.NULL     => typeOf[Null]
        case Schema.Type.STRING   => typeOf[String]//"String"
/*
        case Schema.Type.RECORD   => { 

          field.schema.getName match {
            // cases where a record is found as a field vs found as a member of a union vs 
            // found as an element of an array
            case "array" | "union" => schema.getName
            case x        => x 
          }

        }
        case Schema.Type.UNION    => { 

          val unionSchemas = schema.getTypes.toList

          if (unionSchemas.length == 2 && unionSchemas.exists(schema => schema.getType == Schema.Type.NULL)) {
            val maybeSchema = unionSchemas.find(schema => schema.getType != Schema.Type.NULL)
            if (maybeSchema.isDefined ) "Option[" + avroToScalaType(maybeSchema.get) + "]"
            else error("no avro type found in this union")  
          }
          else error("not a union field")
        }
*/
        case x => error( x +  " is not a valid Avro type")
      }
    }
    
  val fieldType = avroToScalaType(field.schema)
//    FieldData(field.name, avroToScalaType(field.schema))
    FieldData(field.name, fieldType)
   // EnrichedField(field.name, fieldType, FieldMatcher.getTypeData(namespace, fieldType))
  }
}
