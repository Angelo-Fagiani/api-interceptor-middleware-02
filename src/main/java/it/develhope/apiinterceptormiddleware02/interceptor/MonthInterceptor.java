package it.develhope.apiinterceptormiddleware02.interceptor;

import it.develhope.apiinterceptormiddleware02.entities.Month;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class MonthInterceptor implements HandlerInterceptor {
    private static List<Month> monthList = new ArrayList<>(Arrays.asList(
            new Month(1,"January","Gennaio","Genuar"),
            new Month(2,"February","Febbraio","Februar"),
            new Month(3,"March","Marzo","Marsch"),
            new Month(4,"April","Aprile","April")
    ));

    //-Questo metodono l'ho un pò scopiazzato , non riesco a capire secondo quale criterio vengano impostati i return con TRUE o FALSE
    //potresti spiegarmi?
    //-Che utilità ha il "request.setAttribute"?
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumberString = request.getHeader("monthNumber");
        if (monthNumberString == null || monthNumberString.isEmpty()) {
            response.setStatus(400);
            request.getHeader("BAD REQUEST");
            return true;
        }
        int monthNumber = Integer.parseInt(monthNumberString);
        Optional<Month> month = monthList.stream().filter(SingleMonth -> {
            return SingleMonth.getMonthNumber() == monthNumber;
        }).findAny();
        if (month.isPresent()) {
                request.setAttribute("MonthInterceptor-month", month.get());
        } else {
            response.setStatus(400);
        }
        if (month.isEmpty()) {
            request.setAttribute("MonthInterceptor-month", new Month(0, "nope", "nope", "nope"));
        }

        return true;

    }
}

