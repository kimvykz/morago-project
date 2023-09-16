package com.habsida.moragoproject.service;


import com.habsida.moragoproject.model.entity.Call;
import com.habsida.moragoproject.model.input.CreateCallInput;
import com.habsida.moragoproject.model.input.UpdateCallInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CallService {
    List<Call> getAll ();
    Page<Call> getAllByPaging (PageRequest pageRequest);
    Call getById (Long id);
    Call create (CreateCallInput createCallInput);
    Call update (UpdateCallInput updateCallInput);
    Boolean deleteById (Long id);
}
