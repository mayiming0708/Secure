package com.shtel.secure.platform.receive.service;

import com.shtel.secure.platform.receive.model.Temp;
import com.shtel.secure.platform.receive.model.mapper.TempMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiveService {
    @Autowired
    private TempMapper tempMapper;

    public int insert(String value) {
        Temp temp = new Temp();
        temp.setValue(value);

        return tempMapper.insert(temp);
    }
}
