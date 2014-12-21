package com.julianpeeters.avro.runtime.provider

import com.julianpeeters.caseclass.generator._
import org.apache.avro.Schema
import scala.collection.JavaConversions._
import scala.reflect.runtime.universe._


object AvroTypeMatcher {

  def parseField(store: SchemaToClassStore, namespace: ClassNamespace, field: Schema.Field): FieldData = {

    def avroToScalaType(schema: org.apache.avro.Schema): Type = {
      schema.getType match { 
        case Schema.Type.ARRAY    => { 
          typeOf[List[Any]] match {
            case x @ TypeRef(pre, sym, args) => { 
              TypeRef(pre, sym, List(avroToScalaType(schema.getElementType)))
            }
          }
        }
        case Schema.Type.BOOLEAN  => typeOf[Boolean]
        //case Schema.Type.BYTES    => //TODO
        case Schema.Type.DOUBLE   => typeOf[Double]
        //case Schema.Type.FIXED    => //TODO
        case Schema.Type.FLOAT    => typeOf[Float]
        case Schema.Type.LONG     => typeOf[Long]
        case Schema.Type.INT      => typeOf[Int]
        //case Schema.Type.MAP      => //TODO
        case Schema.Type.NULL     => typeOf[Null]
        case Schema.Type.STRING   => typeOf[String]
        case Schema.Type.RECORD   => { 
          field.schema.getType match {
            // cases where a record is found as a field vs found as a member of a union vs 
            // found as an element of an array
            case Schema.Type.ARRAY | Schema.Type.UNION => store.generatedClasses(schema).tpe
            case _        => store.generatedClasses(field.schema).tpe 
          }

        }
        case Schema.Type.UNION  => { 
          val unionSchemas = schema.getTypes.toList
          if (unionSchemas.length == 2 && unionSchemas.exists(schema => schema.getType == Schema.Type.NULL)) {
            val maybeSchema = unionSchemas.find(schema => schema.getType != Schema.Type.NULL)
            if (maybeSchema.isDefined ) {
              typeOf[Option[Any]] match {
                case x @ TypeRef(pre, sym, args) => { 
                  TypeRef(pre, sym, List(avroToScalaType(maybeSchema.get)))
                }
              }
            }
            else error("no avro type found in this union")  
          }
          else error("not a union field")
        }
        case x => error( x +  " is not a valid Avro type")
      }
    }
    
    val fieldType = avroToScalaType(field.schema)

    FieldData(field.name, fieldType)
  }
}
