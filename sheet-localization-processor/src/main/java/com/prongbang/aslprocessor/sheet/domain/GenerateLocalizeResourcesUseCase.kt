package com.prongbang.aslprocessor.sheet.domain

import com.prongbang.aslprocessor.core.UseCase
import com.prongbang.aslprocessor.sheet.data.SheetDataSource
import com.prongbang.aslprocessor.utils.FileUtility

class GenerateLocalizeResourcesUseCase(
		private val sheetDataSource: SheetDataSource,
		private val generateStringUseCase: UseCase<Body, Unit>,
		private val fileResourceUtility: FileUtility
) : UseCase<Sheet, Boolean> {

	override fun execute(parameter: Sheet): Boolean {
		val reader = sheetDataSource.download(parameter)
		val localizations = hashMapOf<String, String>()
		val supportedLocale = arrayListOf<String>()

		try {
			// Header
			var line = reader.readNext()
			if (line != null) {
				val header: Array<String> = line
				for (i in header.withIndex()) {
					supportedLocale.add(header[i.index])
				}
			}

			// Body
			line = reader.readNext()
			while (line != null) {
				generateStringUseCase.execute(Body(line, supportedLocale, localizations))
				line = reader.readNext()
			}

			// Create
			var value: String
			var result: String
			for (m in localizations) {
				value = "<resources>\n" + m.value + "</resources>"
				result = if (fileResourceUtility.create(m.key, value)) "SUCCESS" else "ERROR"
				println("> Create file ${m.key}/strings.xml\t-> $result")
			}
		} catch (e: Exception) {
			println("Error: ${e.message}")
		}
		return true
	}
}