package com.prongbang.aslprocessor.core

interface UseCase<Param, Result> {
	fun execute(parameter: Param): Result
}