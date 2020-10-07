package com.prongbang.aslprocessor.sheet.data.remote

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.prongbang.aslprocessor.sheet.domain.Sheet

interface SheetApi {
	fun download(sheet: Sheet): String
}

class GoogleSheetApi : SheetApi {

	override fun download(sheet: Sheet): String {
		val url = "https://docs.google.com/spreadsheets/d/${sheet.documentId}/export?format=csv&id=${sheet.documentId}&gid=${sheet.id}"
		val (_, _, result) = url.httpGet()
				.responseString()
		return when (result) {
			is Result.Success -> result.get()
			else -> ""
		}
	}
}