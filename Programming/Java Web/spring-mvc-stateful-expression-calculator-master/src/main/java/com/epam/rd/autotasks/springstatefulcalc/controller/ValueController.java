package com.epam.rd.autotasks.springstatefulcalc.controller;

import com.epam.rd.autotasks.springstatefulcalc.service.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/calc/{*}")
public class ValueController {

    @PutMapping
    public ResponseEntity putValue(@PathVariable("*") String str, ServletRequest req, HttpSession session)
            throws IOException {
        String value = req.getReader().readLine();
        if (Validation.validationVariable(value)) {
            if (session.getAttribute(str) == null) {
                session.setAttribute(str, value);
                return new ResponseEntity(HttpStatus.CREATED);
            } else {
                session.setAttribute(str, value);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);

    }

    @DeleteMapping
    public ResponseEntity deleteValue(@PathVariable("*") String str, HttpSession session) {
        session.setAttribute(str, null);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
