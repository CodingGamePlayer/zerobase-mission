package kr.co.zerobasemission.dao;

import kr.co.zerobasemission.domain.WifiVO;
import org.junit.jupiter.api.Test;

import java.util.List;

class WifiDAOTest {

    private WifiDAO wifiDAO;

    public WifiDAOTest() {
        this.wifiDAO = new WifiDAO();
    }

    @Test
    void selectAll() {

        List<WifiVO> list = wifiDAO.selectAll();

        list.forEach(wifiVO -> System.out.println(wifiVO.toString()));
    }

    @Test
    void selectOne() {

        WifiVO wifiVO = wifiDAO.selectOne(1L);

        System.out.println(wifiVO.toString());
    }
}