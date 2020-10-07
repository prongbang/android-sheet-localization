package com.prongbang.aslprocessor.sheet.data

import com.prongbang.aslprocessor.sheet.domain.Localizations
import com.prongbang.aslprocessor.sheet.domain.Sheet

interface SheetDataSource {
	fun download(sheet: Sheet): Localizations
}