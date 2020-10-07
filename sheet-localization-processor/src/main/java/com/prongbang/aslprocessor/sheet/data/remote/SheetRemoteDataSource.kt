package com.prongbang.aslprocessor.sheet.data.remote

import com.prongbang.aslprocessor.sheet.data.SheetDataSource
import com.prongbang.aslprocessor.sheet.domain.Localizations
import com.prongbang.aslprocessor.sheet.domain.Sheet
import java.io.BufferedReader
import java.io.Reader
import java.io.StringReader
import java.util.*

class SheetRemoteDataSource(
		private val sheetApi: SheetApi
) : SheetDataSource {

	override fun download(sheet: Sheet): Localizations {
		val sheets = sheetApi.download(sheet)

		val localeMap = hashMapOf<String, String>()
		val supportedLanguageCodes = arrayListOf<String>()

		var reader: Reader? = null
		try {
			val inputString = StringReader(sheets)
			reader = BufferedReader(inputString)

			// Read CSV header
			var line = reader.readLine()
			if (line != null) {
				val header: List<String> = line.split(",")
				for (i in header.withIndex()) {
					supportedLanguageCodes.add(header[i.index])
				}
			}

			// Read the file line by line starting from the second line
			line = reader.readLine()
			while (line != null) {
				val body: List<String> = line.split(",")
				var key = ""
				var locale = ""
				for (i in body.withIndex()) {
					if (i.index == 0) {
						key = i.value.replace(" ", "_")
								.replace(".", "_")
								.toLowerCase(Locale.ENGLISH)
					} else {
						locale = supportedLanguageCodes[i.index]
						var dir = ""
						if (locale == "en") {
							dir = "values"
						}

						if (localeMap[dir] == null) {
							localeMap[dir] = """"""
						} else {

						}
					}
				}
				if (body.isNotEmpty()) {
					println("body -> $body")
				}
				line = reader.readLine()
			}

		} catch (e: Exception) {
			println("Reading CSV Error!: ${e.message}")
		} finally {
			reader?.close()
			println("Closing fileReader Error!")
		}

		return Localizations()
	}

}