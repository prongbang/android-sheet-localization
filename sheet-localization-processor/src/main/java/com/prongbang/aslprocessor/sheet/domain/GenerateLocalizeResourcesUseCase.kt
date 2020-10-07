package com.prongbang.aslprocessor.sheet.domain

import com.prongbang.aslprocessor.core.UseCase
import com.prongbang.aslprocessor.sheet.data.SheetDataSource

class GenerateLocalizeResourcesUseCase(
		private val sheetDataSource: SheetDataSource
) : UseCase<Sheet, Localizations> {

	override fun execute(parameter: Sheet): Localizations {
		return sheetDataSource.download(parameter)
	}
}