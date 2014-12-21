avro-scala-runtime-type-provider
================================

Uses [Case-Class-Generator](https://github.com/julianpeeters/case-class-generator) and data found in Avro schemas to define and load Scala case classes at runtime.

The dynamically generated class can serve as as a type parameter, or be used to reflectively instantiate new objects at runtime. Please see warnings below.



###Usage:

Add the following dependency for Scala 2.10
  

    `"com.julianpeeters" %% "avro-scala-runtime-type-provider" % "0.0.2"`

Then get a `DynamicCaseClass` instance from an Avro `.avsc` or `.avro` file with:


    import com.julianpeeters.avro.runtime.provider._

    val file = new File("twitter.avsc")
    val dcc: DynamicCaseClass = AvroTypeProvider.schemaToCaseClass(file)



with which you will be a able to:


* Use the `newInstance(varargs: Any*)` method to get an instance of the newly-generated class:
    `val record = dcc.newInstance("Griselda", 4)`


* Use a "zero-arg constructor" to get and instance of the newly-generated class with default parameters:
    `val record = dcc.runtimeInstance`


* Use the singleton type as a type parameter (doesn't carry a type tag, but does carry a manifest):
    `val x = myParamterizedThing[record.type]`


* Import the new  class' implicits and use the `.TYPE` type member as a type parameter (carries a type tag and manifest, implicit scoping rules apply e.g. one `tag` per scope):



    `import scala.reflect.runtime.universe._`

    `import dcc.implicits.{ tag, manifest }`

    `typeOf[dcc.TYPE]`


* Retrieve previously generated classes by Schema from a `Map[Schema, DynamicCaseClass]`

     `SchemaToClassStore.generatedClasses.get(schema)`



Supports generating case classes with arbitrary fields of the following datatypes: 


* INT -> Int
* LONG -> Long
* FLOAT -> Float
* DOUBLE -> Double
* STRING -> String
* BOOLEAN -> Boolean
* NULL  -> Null
* MAP -> //TODO
* BYTES -> //TODO
* FIXED -> //TODO
* ARRAY -> List
* UNION -> Option
* RECORD -> case class




##Warnings: 

1) Reflection circumvents type-saftey. If you find yourself here, please consider if you truly need to define classes at runtime. For example, a file that is accessed at runtime is often *also* accessible at compile-time, and therefore is candidate for a [Avro-Scala-Macro-Annotations](https://github.com/julianpeeters/avro-scala-macro-annotations), which is type-safe.

2) This was developed with the intention of providing a type parameter to Scalavro and Salat-Avro, which use case classes to represent Avro records. Any other utility is incidental.

3) If you only need a single class per Schema/package, please see [scala-avro-toolbox-type-provider](https://github.com/julianpeeters/scala-avro-toolbox-type-provider) for a tool that uses the official (yet still experimental) way to generate classes at runtime. Keep an eye on [scala.meta])(http://scalameta.org/) to solve the single-class-per-package issue.

4) ObjectWeb's ASM classwriter, and thus this project, is _not_ thread-safe.


##In the Future:

1) More support more Avro datatypes such as `Map`, `Fixed` and `Bytes`.

2) Support for generating case classes directly from Schema objects passed via rpc. 

3) Full test coverage.


###Fork away, just make sure the tests pass. Criticism is appreciated.


