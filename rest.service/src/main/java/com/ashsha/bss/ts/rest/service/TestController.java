package com.ashsha.bss.ts.rest.service;

import com.ashsha.bss.ts.business.handler.common.IInstituteOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
    @Autowired
    private IInstituteOperation instituteOperation;

    public IInstituteOperation getInstituteOperation()
    {
        return instituteOperation;
    }

    /* ------------ Test controller ------------ */
    @RequestMapping (value = "/testServices/", method = RequestMethod.GET)
    public ResponseEntity<String> getTestString()
    {
        return new ResponseEntity<String>("Test Controller", HttpStatus.OK);
    }
}
