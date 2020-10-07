package com.prongbang.aslprocessor.sheet.data

import com.opencsv.CSVReader
import com.prongbang.aslprocessor.sheet.domain.Sheet

interface SheetDataSource {
	fun download(sheet: Sheet): CSVReader
}