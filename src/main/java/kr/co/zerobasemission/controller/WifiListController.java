package kr.co.zerobasemission.controller;

import kr.co.zerobasemission.service.WifiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "wifiListController", urlPatterns = "/wifi/list")
public class WifiListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String list = WifiService.INSTANCE.getTotalNumber();
        WifiService.INSTANCE.registerDB();
        req.setAttribute("list", list);

        req.getRequestDispatcher("/WEB-INF/wifi/list.jsp").forward(req, resp);
    }
}
