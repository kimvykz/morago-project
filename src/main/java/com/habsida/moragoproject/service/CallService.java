package com.habsida.moragoproject.service;


import com.habsida.moragoproject.model.entity.Call;
import com.habsida.moragoproject.model.input.CallCreateInput;
import com.habsida.moragoproject.model.input.CallUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CallService {
    List<Call> getAll ();
    Page<Call> getAllByPaging (PageRequest pageRequest);
    Call getById (Long id);
    Call create (CallCreateInput callCreateInput);
    Call update (CallUpdateInput callUpdateInput);
    Boolean deleteById (Long id);
}
