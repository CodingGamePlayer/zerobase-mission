package kr.co.zerobasemission.wifi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "wifiListController", urlPatterns = "/wifi/list")
public class WifiListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/todo/list");

        List<WifiDTO> dtoList = WifiService.INSTANCE.getList();
        WifiService.INSTANCE.test();

        req.setAttribute("list", dtoList);

        req.getRequestDispatcher("/WEB-INF/wifi/list.jsp").forward(req, resp);
    }
}
