package com.revature.erms_project1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Whenever this exception is thrown, the corresponding request
// should send back a 404 (NOT FOUND status code)
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="The corresponding Pet was not found")
public class TicketNotFoundException extends Exception{
}
