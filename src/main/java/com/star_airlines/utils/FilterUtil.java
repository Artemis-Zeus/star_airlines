package com.star_airlines.utils;

import com.alibaba.fastjson.JSONObject;
import com.star_airlines.pojo.Result;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class FilterUtil implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURL().toString();
        log.info("请求的url:{}", url);


        if (!url.contains("account") && !url.contains("book")){
            log.info("不需要对用户进行操作");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        String jwt = req.getHeader("token");
        log.info(jwt);

        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("Not login");
            String notLogin = JSONObject.toJSONString(error);
            res.getWriter().write(notLogin);
            return;
        }
//        检测令牌是否有效
//        快捷键 ctrl+alt+t
        try {
            JwtUtil.parseJWT(jwt);
        } catch (Exception e) {
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            res.getWriter().write(notLogin);
            return;
//            throw new RuntimeException(e);
        }
//        继续执行后续操作
        log.info("continue");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
