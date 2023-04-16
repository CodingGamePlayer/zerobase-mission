package kr.co.zerobasemission.controller;

import kr.co.zerobasemission.dto.WifiDTO;
import kr.co.zerobasemission.service.WifiService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(name = "wifiListController", urlPatterns = "/wifi/list")
public class WifiListController extends HttpServlet {

    private WifiService wifiService = WifiService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String list = wifiService.getTotalNumber();

        List<WifiDTO> wifiDTOS = wifiService.selectAll();

        wifiDTOS.forEach(dto -> log.info(dto));

        req.setAttribute("list", list);

        log.info("start..................server.............");
        req.getRequestDispatcher("/WEB-INF/wifi/list.jsp").forward(req, resp);
    }
}
