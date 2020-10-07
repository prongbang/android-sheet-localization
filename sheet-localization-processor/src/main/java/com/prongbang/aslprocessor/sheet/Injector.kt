package com.prongbang.aslprocessor.sheet

import com.prongbang.aslprocessor.core.UseCase
import com.prongbang.aslprocessor.sheet.data.SheetDataSource
import com.prongbang.aslprocessor.sheet.data.remote.GoogleSheetApi
import com.prongbang.aslprocessor.sheet.data.remote.SheetApi
import com.prongbang.aslprocessor.sheet.data.remote.SheetRemoteDataSource
import com.prongbang.aslprocessor.sheet.domain.GenerateLocalizeResourcesUseCase
import com.prongbang.aslprocessor.sheet.domain.Localizations
import com.prongbang.aslprocessor.sheet.domain.Sheet

object Injector {
	fun provideSheetApi(): SheetApi = GoogleSheetApi()

	fun provideSheetDataSource(): SheetDataSource =
			SheetRemoteDataSource(provideSheetApi())

	fun provideGenerateLocalizeResourcesUseCase(): UseCase<Sheet, Localizations> =
			GenerateLocalizeResourcesUseCase(provideSheetDataSource())
}