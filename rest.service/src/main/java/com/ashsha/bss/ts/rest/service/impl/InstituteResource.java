package com.ashsha.bss.ts.rest.service.impl;

import com.ashsha.bss.ts.business.handler.impl.common.InstituteOperation;
import com.ashsha.bss.ts.entity.db.common.Institute;
import com.ashsha.bss.ts.entity.dto.QueryDTO;
import com.ashsha.bss.ts.entity.dto.common.InstituteDTO;
import com.ashsha.bss.ts.rest.service.IInstituteResource;
import com.ashsha.bss.ts.rest.service.validators.InstituteValidator;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class InstituteResource extends BaseEntityResource<Institute, QueryDTO> implements IInstituteResource
{
    @Autowired
    private InstituteOperation instituteOperation;

    @Autowired
    private InstituteValidator instituteValidator;

    protected Institute getById(Long id)
    {
        return (Institute) instituteOperation.getById(id);
    }

    /*@InitBinder
    protected void initBinder(WebDataBinder webDataBinder)
    {
        webDataBinder.addValidators(instituteValidator);
    }
*/
    @Override
    public ResponseEntity<InstituteDTO> get(@PathVariable ("id") long id)
    {
        Institute result = getById(id);
        if (result != null)
        {
            InstituteDTO resultDto = getEntityConverter().convert(InstituteDTO.class, result);
            return new ResponseEntity<InstituteDTO>(resultDto, HttpStatus.OK);
        }
        return new ResponseEntity<InstituteDTO>(HttpStatus.NOT_FOUND);
    }

    @Override
    @Transactional
    public ResponseEntity<?> post(@Valid @RequestBody List<InstituteDTO> inputResourceRequest)
    {
        InstituteValidator i = instituteValidator;
        InstituteDTO onlyFirstInstitute = inputResourceRequest.get(0);
        instituteOperation.registerInstitute(onlyFirstInstitute);
        return new ResponseEntity<String>("Resource Created successfully", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> getTestString()
    {
        return new ResponseEntity<String>("Test Controller", HttpStatus.OK);
    }
}
