package com.prongbang.aslprocessor.utils

import java.io.File

class ResourceFileUtility : FileUtility() {

	override fun create(dir: String, value: String): Boolean {
		try {
			val absoluteFilePth = "app/src/main/res/$dir/"
			val file = File(absoluteFilePth)
			if (!file.exists()) {
				file.mkdirs()
			}
			val fileRes = File(file, "strings.xml")
			if (!fileRes.exists()) {
				fileRes.createNewFile()
			}
			fileRes.writeText(value)
		} catch (e: Exception) {
			return false
		}
		return true
	}
}