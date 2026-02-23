package br.ufpb.dcx.pac.exceptionhandler

import br.ufpb.dcx.pac.exceptionhandler.dto.ErrorResponse
import br.ufpb.dcx.pac.exceptionhandler.exceptions.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionsHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.message?:"Entity not found",
            System.currentTimeMillis()
        )

        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.message?:"Entity not found",
            System.currentTimeMillis()
        )

        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}