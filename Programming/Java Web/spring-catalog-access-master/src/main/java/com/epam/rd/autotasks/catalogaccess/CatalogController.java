package com.epam.rd.autotasks.catalogaccess;

import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/catalog")
public class CatalogController {

    @GetMapping
    public List<String> goods() {
        return ImmutableList.of("Portrait of Shakespeare", "Notebook A4");
    }

}