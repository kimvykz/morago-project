package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Call;
import com.habsida.moragoproject.model.input.CallCreateInput;
import com.habsida.moragoproject.model.input.CallUpdateInput;
import com.habsida.moragoproject.repository.CallRepository;
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
    public Call create (CallCreateInput callCreateInput) {
        Call call = new Call();

        if (callCreateInput.getCallStatus() == null) {
            throw new IllegalArgumentException("Field callStatus cannot be null");
        } else {
            call.setCallStatus(callCreateInput.getCallStatus());
        }
        if (callCreateInput.getChannelName() == null) {
            throw new IllegalArgumentException("Field channelName cannot be null");
        } else {
            call.setChannelName(callCreateInput.getChannelName());
        }
        if (callCreateInput.getCommission() == null) {
            throw new IllegalArgumentException("Field commission cannot be null");
        } else {
            call.setCommission(callCreateInput.getCommission());
        }
        if (callCreateInput.getDuration() == null) {
            throw new IllegalArgumentException("Field duration cannot be null");
        } else {
            call.setDuration(callCreateInput.getDuration());
        }
        if (callCreateInput.getIsEndCall() == null) {
            throw new IllegalArgumentException("Field isEndCall cannot be null");
        } else {
            call.setIsEndCall(callCreateInput.getIsEndCall());
        }
        if (callCreateInput.getStatus() == null) {
            throw new IllegalArgumentException("Field status cannot be null");
        } else {
            call.setStatus(callCreateInput.getStatus());
        }
        if (callCreateInput.getSum() == null) {
            throw new IllegalArgumentException("Field sum cannot be null");
        } else {
            call.setSum(callCreateInput.getSum());
        }
        if (call.getTranslatorHasRated() == null) {
            throw new IllegalArgumentException("Field translatorHasRated cannot be null");
        } else {
            call.setTranslatorHasRated(callCreateInput.getTranslatorHasRated());
        }
        if (callCreateInput.getUserHasRated() == null) {
            throw new IllegalArgumentException("Field userHasRated cannot be null");
        } else {
            call.setUserHasRated(callCreateInput.getUserHasRated());
        }
        if (callCreateInput.getFile() == null) {
            //throw new IllegalArgumentException("Field file cannot be null");
        } else {
            call.setFile(callCreateInput.getFile());
        }
        if (callCreateInput.getTheme() == null) {
            //throw new IllegalArgumentException("Field theme cannot be null");
        } else {
            call.setTheme(callCreateInput.getTheme());
        }
        if (callCreateInput.getCaller() == null) {
            throw new IllegalArgumentException("Field caller cannot be null");
        } else {
            call.setCaller(callCreateInput.getCaller());
        }
        if (callCreateInput.getRecipient() == null) {
            throw new IllegalArgumentException("Field recipient cannot be null");
        } else {
            call.setRecipient(callCreateInput.getRecipient());
        }

        return callRepository.save(call);
    }

    @Override
    public Call update (CallUpdateInput callUpdateInput) {
        Call call = getById(callUpdateInput.getId());


        if (callUpdateInput.getCallStatus() != null
            && !call.getCallStatus().equals(callUpdateInput.getCallStatus())
            ) {
            call.setCallStatus(callUpdateInput.getCallStatus());
        }
        if (callUpdateInput.getChannelName() != null
            && !callUpdateInput.getChannelName().isBlank()
            && !call.getChannelName().equals(callUpdateInput.getChannelName())) {
            call.setChannelName(callUpdateInput.getChannelName());
        }
        if (callUpdateInput.getCommission() != null
            && !call.getCommission().equals(callUpdateInput.getCommission())) {
            call.setCommission(callUpdateInput.getCommission());
        }
        if (callUpdateInput.getDuration() != null
            && !call.getDuration().equals(callUpdateInput.getDuration())) {
            call.setDuration(callUpdateInput.getDuration());
        }
        if (callUpdateInput.getIsEndCall() != null
            && !call.getIsEndCall().equals(callUpdateInput.getIsEndCall())) {
            call.setIsEndCall(callUpdateInput.getIsEndCall());
        }
        if (callUpdateInput.getStatus() != null
            && !call.getStatus().equals(callUpdateInput.getStatus())) {
            call.setStatus(callUpdateInput.getStatus());
        }
        if (callUpdateInput.getSum() != null
            && !call.getSum().equals(callUpdateInput.getSum())) {
            call.setSum(callUpdateInput.getSum());
        }
        if (callUpdateInput.getTranslatorHasRated() != null
            && !call.getTranslatorHasRated().equals(callUpdateInput.getTranslatorHasRated())) {
            call.setTranslatorHasRated(callUpdateInput.getTranslatorHasRated());
        }
        if (callUpdateInput.getUserHasRated() != null
            && !call.getUserHasRated().equals(callUpdateInput.getUserHasRated())) {
            call.setUserHasRated(callUpdateInput.getUserHasRated());
        }
        if (callUpdateInput.getFile() != null
            && !call.getFile().equals(callUpdateInput.getFile())) {
            call.setFile(callUpdateInput.getFile());
        }
        if (callUpdateInput.getTheme() != null
            && !call.getTheme().equals(callUpdateInput.getTheme())) {
            call.setTheme(callUpdateInput.getTheme());
        }
        if (callUpdateInput.getCaller() != null
            && !call.getCaller().equals(callUpdateInput.getCaller())) {
            call.setCaller(callUpdateInput.getCaller());
        }
        if (callUpdateInput.getRecipient() != null
            && !call.getRecipient().equals(callUpdateInput.getRecipient())) {
            call.setRecipient(callUpdateInput.getRecipient());
        }
        
        return callRepository.save(call);
    }

    @Override
    public Boolean deleteById (Long id) {
        callRepository.deleteById(id);
        return true;
    }
}
