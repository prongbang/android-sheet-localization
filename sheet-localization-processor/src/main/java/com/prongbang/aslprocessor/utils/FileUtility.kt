package com.prongbang.aslprocessor.utils

import javax.annotation.processing.ProcessingEnvironment

abstract class FileUtility {
	open fun create(dir: String, value: String): Boolean = false
	open fun create(processingEnv: ProcessingEnvironment, targetClassName: String,
	                value: String): Boolean = false
}


