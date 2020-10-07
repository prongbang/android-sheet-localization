package com.prongbang.aslprocessor.sheet.domain

import com.prongbang.aslprocessor.core.UseCase
import java.util.*

class GenerateStringUseCase : UseCase<Body, Unit> {

	override fun execute(parameter: Body) {
		val body = parameter.csvLine
		var keyName = ""
		var formatted = ""
		var value = ""
		var dir = ""
		var locale = ""

		for (i in body.withIndex()) {
			if (i.index == 0) {
				keyName = i.value
						.trim()
						.replace(" ", "_")
						.replace(".", "_")
						.toLowerCase(Locale.ENGLISH)
			} else {
				if (i.index > parameter.supportedLocale.size - 1) break

				locale = parameter.supportedLocale[i.index]
				dir = if (locale == "en") "values" else "values-$locale"

				if (parameter.localization[dir] == null) {
					parameter.localization[dir] = ""
				}

				value = i.value
						.replace("[\"]".toRegex(), "\\\"")
						.replace("[&]".toRegex(), "&amp;")
						.replace("[<]".toRegex(), "&lt;")
						.replace("[>]".toRegex(), "&gt;")
						.replace("[']".toRegex(), "\\\\'")
						.replace("\\{([0-9])\\}".toRegex()) {
							val s = it.value.indexOf("{")
							val e = it.value.lastIndexOf("}")
							val n = it.value.substring(s + 1, e)
							"%${n}\$s"
						}

				val regex = "\\{([0-9])\\}".toRegex()
				val match = regex.find(i.value)
				if (match != null) {
					formatted = " formatted=\"true\""
				}

				if (value.indexOf("%s") > -1 || value.indexOf("%d") > -1) {
					formatted = " formatted=\"false\""
				}

				parameter.localization[dir] += "\t<string name=\"$keyName\"${formatted}>$value</string>\n"
			}
		}
	}
}