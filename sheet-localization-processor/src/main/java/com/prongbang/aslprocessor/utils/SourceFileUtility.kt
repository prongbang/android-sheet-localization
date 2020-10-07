package com.prongbang.aslprocessor.utils

import java.io.IOException
import javax.annotation.processing.ProcessingEnvironment

/**
 * val sourceFile = SourceFileUtility()
 * sourceFile.create(processingEnv, "com.prongbang.aslprocessor.generated.GeneratedClass", "String = \"Hello\";")
 */
class SourceFileUtility : FileUtility() {

	override fun create(processingEnv: ProcessingEnvironment, targetClassName: String,
	                    value: String): Boolean {
		try {
			val source = processingEnv.filer.createSourceFile(value)
			val writer = source.openWriter()
			writer.write(value)
			writer.flush()
			writer.close()
		} catch (e: IOException) {
			e.printStackTrace()
		}
		return true
	}

}