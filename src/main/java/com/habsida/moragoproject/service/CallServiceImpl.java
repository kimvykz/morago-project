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

    public CallServiceImpl (CallRepository callRepository) {
        this.callRepository = callRepository;
    }

    @Override
    public List<Call> getAll () {
        return callRepository.findAll();
    }

    @Override
    public Page<Call> getAllByPaging (PageRequest pageRequest) {
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
        Call call = new Call();

        if (createCallInput.getCallStatus() == null) {
            throw new IllegalArgumentException("Field callStatus cannot be null");
        } else {
            call.setCallStatus(createCallInput.getCallStatus());
        }
        if (createCallInput.getChannelName() == null) {
            throw new IllegalArgumentException("Field channelName cannot be null");
        } else {
            call.setChannelName(createCallInput.getChannelName());
        }
        if (createCallInput.getCommission() == null) {
            throw new IllegalArgumentException("Field commission cannot be null");
        } else {
            call.setCommission(createCallInput.getCommission());
        }
        if (createCallInput.getDuration() == null) {
            throw new IllegalArgumentException("Field duration cannot be null");
        } else {
            call.setDuration(createCallInput.getDuration());
        }
        if (createCallInput.getIsEndCall() == null) {
            throw new IllegalArgumentException("Field isEndCall cannot be null");
        } else {
            call.setIsEndCall(createCallInput.getIsEndCall());
        }
        if (createCallInput.getStatus() == null) {
            throw new IllegalArgumentException("Field status cannot be null");
        } else {
            call.setStatus(createCallInput.getStatus());
        }
        if (createCallInput.getSum() == null) {
            throw new IllegalArgumentException("Field sum cannot be null");
        } else {
            call.setSum(createCallInput.getSum());
        }
        if (call.getTranslatorHasRated() == null) {
            throw new IllegalArgumentException("Field translatorHasRated cannot be null");
        } else {
            call.setTranslatorHasRated(createCallInput.getTranslatorHasRated());
        }
        if (createCallInput.getUserHasRated() == null) {
            throw new IllegalArgumentException("Field userHasRated cannot be null");
        } else {
            call.setUserHasRated(createCallInput.getUserHasRated());
        }
        if (createCallInput.getFile() == null) {
            //throw new IllegalArgumentException("Field file cannot be null");
        } else {
            call.setFile(createCallInput.getFile());
        }
        if (createCallInput.getTheme() == null) {
            //throw new IllegalArgumentException("Field theme cannot be null");
        } else {
            call.setTheme(createCallInput.getTheme());
        }
        if (createCallInput.getCaller() == null) {
            throw new IllegalArgumentException("Field caller cannot be null");
        } else {
            call.setCaller(createCallInput.getCaller());
        }
        if (createCallInput.getRecipient() == null) {
            throw new IllegalArgumentException("Field recipient cannot be null");
        } else {
            call.setRecipient(createCallInput.getRecipient());
        }

        return callRepository.save(call);
    }

    @Override
    public Call update (UpdateCallInput updateCallInput) {
        Call call = getById(updateCallInput.getId());


        if (updateCallInput.getCallStatus() != null
            && !call.getCallStatus().equals(updateCallInput.getCallStatus())
            ) {
            call.setCallStatus(updateCallInput.getCallStatus());
        }
        if (updateCallInput.getChannelName() != null
            && !updateCallInput.getChannelName().isBlank()
            && !call.getChannelName().equals(updateCallInput.getChannelName())) {
            call.setChannelName(updateCallInput.getChannelName());
        }
        if (updateCallInput.getCommission() != null
            && !call.getCommission().equals(updateCallInput.getCommission())) {
            call.setCommission(updateCallInput.getCommission());
        }
        if (updateCallInput.getDuration() != null
            && !call.getDuration().equals(updateCallInput.getDuration())) {
            call.setDuration(updateCallInput.getDuration());
        }
        if (updateCallInput.getIsEndCall() != null
            && !call.getIsEndCall().equals(updateCallInput.getIsEndCall())) {
            call.setIsEndCall(updateCallInput.getIsEndCall());
        }
        if (updateCallInput.getStatus() != null
            && !call.getStatus().equals(updateCallInput.getStatus())) {
            call.setStatus(updateCallInput.getStatus());
        }
        if (updateCallInput.getSum() != null
            && !call.getSum().equals(updateCallInput.getSum())) {
            call.setSum(updateCallInput.getSum());
        }
        if (updateCallInput.getTranslatorHasRated() != null
            && !call.getTranslatorHasRated().equals(updateCallInput.getTranslatorHasRated())) {
            call.setTranslatorHasRated(updateCallInput.getTranslatorHasRated());
        }
        if (updateCallInput.getUserHasRated() != null
            && !call.getUserHasRated().equals(updateCallInput.getUserHasRated())) {
            call.setUserHasRated(updateCallInput.getUserHasRated());
        }
        if (updateCallInput.getFile() != null
            && !call.getFile().equals(updateCallInput.getFile())) {
            call.setFile(updateCallInput.getFile());
        }
        if (updateCallInput.getTheme() != null
            && !call.getTheme().equals(updateCallInput.getTheme())) {
            call.setTheme(updateCallInput.getTheme());
        }
        if (updateCallInput.getCaller() != null
            && !call.getCaller().equals(updateCallInput.getCaller())) {
            call.setCaller(updateCallInput.getCaller());
        }
        if (updateCallInput.getRecipient() != null
            && !call.getRecipient().equals(updateCallInput.getRecipient())) {
            call.setRecipient(updateCallInput.getRecipient());
        }
        
        return callRepository.save(call);
    }

    @Override
    public Boolean deleteById (Long id) {
        callRepository.deleteById(id);
        return true;
    }
}
