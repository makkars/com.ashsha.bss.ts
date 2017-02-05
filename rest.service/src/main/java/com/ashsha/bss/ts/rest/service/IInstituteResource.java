package com.ashsha.bss.ts.rest.service;

import com.ashsha.bss.ts.entity.dto.common.InstituteDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping (value = "v1/institute")
public interface IInstituteResource extends IEntityResource
{
    @RequestMapping (value = "/testServices/", method = RequestMethod.GET)
    public ResponseEntity<String> getTestString();

    @RequestMapping (method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> post(@RequestBody List<InstituteDTO> inputResourceRequest);
}
