package com.epam.rd.autotasks.springstatefulcalc.controller;

import com.epam.rd.autotasks.springstatefulcalc.service.Calc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static com.epam.rd.autotasks.springstatefulcalc.service.Result.processingRequest;

@Controller
@RequestMapping("/calc")
public class ResultController {

    @GetMapping("/result")
    public ResponseEntity getResult(ServletResponse resp, HttpSession session)
            throws IOException {
        PrintWriter writer = resp.getWriter();
        String result = processingRequest(session);
        try {
            writer.print(Calc.calculateExpression(result));
            writer.close();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

}
