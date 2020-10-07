package com.prongbang.aslprocessor.sheet.data.remote

import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.prongbang.aslprocessor.sheet.data.SheetDataSource
import com.prongbang.aslprocessor.sheet.domain.Sheet
import java.io.StringReader


class SheetRemoteDataSource(
		private val sheetApi: SheetApi
) : SheetDataSource {

	override fun download(sheet: Sheet): CSVReader {
		val sheets = sheetApi.download(sheet)
		val stringReader = StringReader(sheets)
		val builder = CSVReaderBuilder(stringReader)
		return builder.build()

//		try {
//			val rows = csvReader.readAll()
//		} catch (e: Exception) {
//		}

//		val inputString = StringReader(sheets)
//		return BufferedReader(inputString)
	}
}