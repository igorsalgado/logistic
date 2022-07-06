package com.project.logistic.api.exceptionhandler;


import com.project.logistic.domain.exception.LogisticExceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice // indica que a classe é um advice para todos os controladores
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Problema.Campo> campos = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()){ // percorre todos os erros de validação
            String nome = ((FieldError) error).getField(); // pega o nome do campo que gerou o erro
            String mensagem = error.getDefaultMessage(); // pega a mensagem de erro
            campos.add(new Problema.Campo(nome,mensagem)); // adiciona o campo e a mensagem de erro a lista de campos
        }

        Problema problema = new Problema(); // cria um novo objeto Problema
        problema.setStatus(status.value()); // seta o status da exceção
        problema.setDataHora(OffsetDateTime.now()); // seta a data e hora da exceção
        problema.setTitulo("Um ou mais campos inválidos no corpo da requisição"); // seta o título da exceção
        problema.setCampos(campos); // seta os campos inválidos

        return handleExceptionInternal(ex, problema ,headers,status,request);
    }
    @ExceptionHandler(LogisticExceptions.class) // indica que a classe trata exceções de LogisticExceptions
    public ResponseEntity<Object> handleLogisticExceptions(LogisticExceptions ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problema problema = new Problema(); // cria um novo objeto Problema
        problema.setStatus(status.value()); // seta o status da exceção
        problema.setDataHora(OffsetDateTime.now()); // seta a data e hora da exceção
        problema.setTitulo(ex.getMessage()); // seta o título da exceção

        return handleExceptionInternal(ex,problema, new HttpHeaders(), status, request);
        // retorna o problema como resposta da requisição
    }
}
