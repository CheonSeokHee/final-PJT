package kr.or.fineapple.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

//@Controller
public class WebErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(
            HttpServletRequest request,
            Model model
        ) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        String statusMsg = "������ �߻��߽��ϴ�.";
        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            model.addAttribute("statusCode", statusCode);
            if(statusCode == 404){
                statusMsg = "�������� ã�� �� �����ϴ�.";
            }
        }
        model.addAttribute("statusMsg", statusMsg);

        return "error/error";
    }
}
