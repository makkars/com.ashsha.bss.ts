package com.ashsha.bss.ts.rest.service;

import com.ashsha.bss.ts.entity.dto.BaseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface IEntityResource
{
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<? extends BaseDTO> get(@PathVariable ("id") long id);
}
