package com.prongbang.aslprocessor

import com.prongbang.aslprocessor.annotation.AndroidSheetLocalization
import com.prongbang.aslprocessor.core.UseCase
import com.prongbang.aslprocessor.sheet.Injector
import com.prongbang.aslprocessor.sheet.domain.Sheet
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@SupportedSourceVersion(SourceVersion.RELEASE_8)
class SheetLocalizationProcessor : AbstractProcessor() {

	private var sheet = Sheet()
	private val generateLocalizeResourcesUseCase: UseCase<Sheet, Boolean> = Injector.provideGenerateLocalizeResourcesUseCase()

	override fun getSupportedAnnotationTypes() =
			mutableSetOf(AndroidSheetLocalization::class.java.canonicalName)

	override fun process(annotations: MutableSet<out TypeElement>?,
	                     roundEnv: RoundEnvironment): Boolean {
		if (roundEnv.processingOver()) {
			return true
		}

		for (element in roundEnv.getElementsAnnotatedWith(AndroidSheetLocalization::class.java)) {
			element.getAnnotation(AndroidSheetLocalization::class.java)
					?.let {
						sheet = Sheet(it.sheetId, it.documentId)
					}
		}
		return generateLocalizeResourcesUseCase.execute(sheet)
	}

}