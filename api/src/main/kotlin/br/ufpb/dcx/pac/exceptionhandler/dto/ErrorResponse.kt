package br.ufpb.dcx.pac.exceptionhandler.dto

data class ErrorResponse(
    val status: Int,
    val message: String,
    val timestamp: Long
)
