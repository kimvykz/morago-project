package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Call;
import com.habsida.moragoproject.model.input.CreateCallInput;
import com.habsida.moragoproject.model.input.UpdateAppVersionInput;
import com.habsida.moragoproject.model.input.UpdateCallInput;
import com.habsida.moragoproject.repository.CallRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallServiceImpl implements CallService{

    private CallRepository callRepository;
    private ModelMapper modelMapper;

    public CallServiceImpl (CallRepository callRepository, ModelMapper modelMapper) {
        this.callRepository = callRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Call> getAll () {
        return callRepository.findAll();
    }

    @Override
    public Page<Call> getAllPaged (PageRequest pageRequest) {
        return callRepository.findAll(pageRequest);
    }

    @Override
    public Call getById (Long id) {
        return callRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Call is not found for the id - " + id);
        });
    }

    @Override
    public Call create (CreateCallInput createCallInput) {
        Call call = modelMapper.map(createCallInput, Call.class);

        if (call.getCallStatus() == null) {
            throw new IllegalArgumentException("Field callStatus cannot be null");
        }
        if (call.getChannelName() == null) {
            throw new IllegalArgumentException("Field channelName cannot be null");
        }
        if (call.getCommission() == null) {
            throw new IllegalArgumentException("Field commission cannot be null");
        }
        if (call.getDuration() == null) {
            throw new IllegalArgumentException("Field duration cannot be null");
        }
        if (call.getIsEndCall() == null) {
            throw new IllegalArgumentException("Field isEndCall cannot be null");
        }
        if (call.getStatus() == null) {
            throw new IllegalArgumentException("Field status cannot be null");
        }
        if (call.getSum() == null) {
            throw new IllegalArgumentException("Field sum cannot be null");
        }
        if (call.getTranslatorHasRated() == null) {
            throw new IllegalArgumentException("Field translatorHasRated cannot be null");
        }
        if (call.getUserHasRated() == null) {
            throw new IllegalArgumentException("Field userHasRated cannot be null");
        }
        if (call.getFile() == null) {
            //throw new IllegalArgumentException("Field file cannot be null");
        }
        if (call.getTheme() == null) {
            //throw new IllegalArgumentException("Field theme cannot be null");
        }
        if (call.getCaller() == null) {
            throw new IllegalArgumentException("Field caller cannot be null");
        }
        if (call.getRecipient() == null) {
            throw new IllegalArgumentException("Field recipient cannot be null");
        }

        return callRepository.save(call);
    }

    @Override
    public Call update (UpdateCallInput updateCallInput) {
        Call call = getById(updateCallInput.getId());
        modelMapper.map(updateCallInput, call);

        if (call.getCallStatus() == null) {
            throw new IllegalArgumentException("Field callStatus cannot be null");
        }
        if (call.getChannelName() == null) {
            throw new IllegalArgumentException("Field channelName cannot be null");
        }
        if (call.getCommission() == null) {
            throw new IllegalArgumentException("Field commission cannot be null");
        }
        if (call.getDuration() == null) {
            throw new IllegalArgumentException("Field duration cannot be null");
        }
        if (call.getIsEndCall() == null) {
            throw new IllegalArgumentException("Field isEndCall cannot be null");
        }
        if (call.getStatus() == null) {
            throw new IllegalArgumentException("Field status cannot be null");
        }
        if (call.getSum() == null) {
            throw new IllegalArgumentException("Field sum cannot be null");
        }
        if (call.getTranslatorHasRated() == null) {
            throw new IllegalArgumentException("Field translatorHasRated cannot be null");
        }
        if (call.getUserHasRated() == null) {
            throw new IllegalArgumentException("Field userHasRated cannot be null");
        }
        if (call.getFile() == null) {
            //throw new IllegalArgumentException("Field file cannot be null");
        }
        if (call.getTheme() == null) {
            //throw new IllegalArgumentException("Field theme cannot be null");
        }
        if (call.getCaller() == null) {
            throw new IllegalArgumentException("Field caller cannot be null");
        }
        if (call.getRecipient() == null) {
            throw new IllegalArgumentException("Field recipient cannot be null");
        }
        
        return callRepository.save(call);
    }

    @Override
    public Boolean deleteById (Long id) {
        callRepository.deleteById(id);
        return true;
    }
}
