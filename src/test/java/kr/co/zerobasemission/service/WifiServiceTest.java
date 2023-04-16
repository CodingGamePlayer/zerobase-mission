package kr.co.zerobasemission.service;

import kr.co.zerobasemission.dto.WifiDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WifiServiceTest {

    private WifiService wifiService = WifiService.INSTANCE;

    @Test
    void selectAll() throws IOException {

        List<WifiDTO> wifiDTOS = wifiService.selectAll();

        wifiDTOS.forEach(dto -> System.out.println(dto));
    }
}