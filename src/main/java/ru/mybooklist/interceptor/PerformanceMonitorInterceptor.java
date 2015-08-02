package ru.mybooklist.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Daniyar Itegulov
 */
public class PerformanceMonitorInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger();
    private ThreadLocal<StopWatch> stopWatchLocal = new ThreadLocal<>();
    private static final DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StopWatch stopWatch = new StopWatch(handler.toString());
        stopWatch.start(handler.toString());
        stopWatchLocal.set(stopWatch);

        logger.info("Accessing URL path: " + getUrl(request));
        logger.info("Request processing started on: " + getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("Request processing ended on: " + getTime());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        StopWatch stopWatch = stopWatchLocal.get();
        stopWatch.stop();

        logger.info("Total time taken for processing: " + stopWatch.getTotalTimeMillis() + " ms");
        stopWatchLocal.set(null);
        logger.info("===============================");
    }

    private static String getUrl(HttpServletRequest request) {
        return request.getRequestURI() + (request.getQueryString() == null ? "" : request.getQueryString());
    }

    private static String getTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return dateFormatter.format(calendar.getTime());
    }
}
