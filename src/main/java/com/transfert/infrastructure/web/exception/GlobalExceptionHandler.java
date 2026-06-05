// GlobalExceptionHandler.java corrigé (messages utilisateur clairs)
package com.transfert.infrastructure.web.exception;

import com.transfert.application.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex) {
log.error("Erreur métier: {}", ex.getMessage());
String userMessage = ex.getMessage();
if (userMessage != null) {
if (userMessage.contains("Mot de passe obligatoire")) {
userMessage = "Le mot de passe est obligatoire pour l'inscription.";
} else if (userMessage.contains("n'a pas encore de compte")) {
userMessage = "Cet étudiant n'a pas de compte. Veuillez l'inviter à s'inscrire d'abord.";
} else if (userMessage.contains("Email étudiant invalide")) {
userMessage = "L'email de l'étudiant est invalide.";
}
}
return ResponseEntity.badRequest().body(new ApiResponse<>(false, userMessage, null));
}

@ExceptionHandler(IllegalStateException.class)
public ResponseEntity<ApiResponse<Void>> handleIllegalState(IllegalStateException ex) {
log.error("État invalide: {}", ex.getMessage());
String userMessage = ex.getMessage();
if (userMessage != null && userMessage.contains("Un dossier de transfert actif existe déjà")) {
userMessage = "Un dossier de transfert actif existe déjà pour cet étudiant. Veuillez attendre la finalisation de celui-ci avant d'en créer un nouveau.";
}
return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(false, userMessage, null));
}

@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
Map<String, String> errors = new HashMap<>();
ex.getBindingResult().getAllErrors().forEach((error) -> {
String fieldName = ((FieldError) error).getField();
String errorMessage = error.getDefaultMessage();
errors.put(fieldName, errorMessage);
});
return ResponseEntity.badRequest().body(new ApiResponse<>(false, "Erreur de validation", errors));
}

@ExceptionHandler(Exception.class)
public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex) {
log.error("Erreur interne", ex);
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
.body(new ApiResponse<>(false, "Une erreur interne est survenue. Veuillez réessayer plus tard.", null));
}
}