package com.prongbang.aslprocessor

import com.prongbang.aslprocessor.annotation.AndroidSheetLocalization
import com.prongbang.aslprocessor.core.UseCase
import com.prongbang.aslprocessor.sheet.Injector
import com.prongbang.aslprocessor.sheet.domain.Localizations
import com.prongbang.aslprocessor.sheet.domain.Sheet
import java.io.File
import java.io.IOException
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.StandardLocation

@SupportedSourceVersion(SourceVersion.RELEASE_8)
class SheetLocalizationProcessor : AbstractProcessor() {

	private var sheet = Sheet()
	private val generateLocalizeResourcesUseCase: UseCase<Sheet, Localizations> = Injector.provideGenerateLocalizeResourcesUseCase()

	override fun getSupportedAnnotationTypes() =
			mutableSetOf(AndroidSheetLocalization::class.java.canonicalName)

	override fun process(annotations: MutableSet<out TypeElement>?,
	                     roundEnv: RoundEnvironment): Boolean {
		println("roundEnv.processingOver() -> " + roundEnv.processingOver())
		println("roundEnv.rootElements() -> " + roundEnv.rootElements)
		println("roundEnv.errorRaised() -> " + roundEnv.errorRaised())
		if (roundEnv.processingOver()) {
			return true
		}


		val builder = StringBuilder()
				.append("package com.prongbang.aslprocessor.generated;\n\n")
				.append("public class GeneratedClass {\n\n") // open class
				.append("\tpublic String getMessage() {\n") // open method
				.append("\t\treturn \"")

		for (element in roundEnv.getElementsAnnotatedWith(AndroidSheetLocalization::class.java)) {
			element.getAnnotation(AndroidSheetLocalization::class.java)
					?.let {
						sheet = Sheet(it.sheetId, it.documentId)
					}
		}

		// for each javax.lang.model.element.Element annotated with the AndroidSheetLocalization
		for (element in roundEnv.getElementsAnnotatedWith(AndroidSheetLocalization::class.java)) {
			val objectType = element.simpleName
					.toString()
			// this is appending to the return statement
			builder.append(objectType)
					.append(" says hello!")
		}
		builder.append("\";\n")
				.append("\t}\n")
				.append("}\n")

		println("id: " + sheet.id)
		println("documentId: " + sheet.documentId)

		val locale = generateLocalizeResourcesUseCase.execute(sheet)

		try {
			val fileSeparator = System.getProperty("file.separator")
			val folderName = "values"
			val absoluteFilePth = "app/src/main/res/$folderName/strings.xml"
			val file = File(absoluteFilePth)

			val resource = processingEnv.filer.createResource(StandardLocation.SOURCE_OUTPUT, "",
					"dummy")
			val source = processingEnv.filer.createSourceFile(
					"com.prongbang.aslprocessor.generated.GeneratedClass")

//			file.writeText(builder.toString())

//			val writer = source.openWriter()
//			writer.write(builder.toString())
//			writer.flush()
//			writer.close()
		} catch (e: IOException) {
			e.printStackTrace()
		}
		return true
	}

}