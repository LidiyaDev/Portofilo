package com.dashenbank.mttodb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashenbank.mttodb.Entity.FetchedData;
import com.dashenbank.mttodb.Repository.FetchedDataRepository;

@Service
public class FetchedDataService {
    @Autowired
    FetchedDataRepository fetchedDataRepository;
    
    public void saveMessage(FetchedData data)
    {
        fetchedDataRepository.save(data);
    }

    public void deleteMessage()
    {
        fetchedDataRepository.deleteAll();
    }
}
