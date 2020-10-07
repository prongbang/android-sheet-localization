package com.prongbang.aslprocessor.sheet

import com.prongbang.aslprocessor.core.UseCase
import com.prongbang.aslprocessor.sheet.data.remote.GoogleSheetApi
import com.prongbang.aslprocessor.sheet.data.remote.SheetRemoteDataSource
import com.prongbang.aslprocessor.sheet.domain.GenerateLocalizeResourcesUseCase
import com.prongbang.aslprocessor.sheet.domain.GenerateStringUseCase
import com.prongbang.aslprocessor.sheet.domain.Sheet
import com.prongbang.aslprocessor.utils.ResourceFileUtility

object Injector {

	fun provideGenerateLocalizeResourcesUseCase(): UseCase<Sheet, Boolean> =
			GenerateLocalizeResourcesUseCase(
					SheetRemoteDataSource(GoogleSheetApi()),
					GenerateStringUseCase(),
					ResourceFileUtility()
			)
}