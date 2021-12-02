package com.example.foodshop.web.interceptor;

import com.example.foodshop.service.ViewCategoriService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CategoryInterceptor implements HandlerInterceptor {

    private final ViewCategoriService viewCategoriService;

    public CategoryInterceptor(ViewCategoriService viewCategoriService) {
        this.viewCategoriService = viewCategoriService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       viewCategoriService.onRequest(request.getRequestURI());
        return true;
    }

}
