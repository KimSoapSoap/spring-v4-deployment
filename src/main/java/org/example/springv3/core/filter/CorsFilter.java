package org.example.springv3.core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;



        //로그인 안 했을 수도 있으니까 필터에서 잡아서 유저 정보 꺼내기. 이건 하나의 예시고 적다가 못 적음
        //로그인 한 유저의 정보를 필터에서 잡아서 최초로 유저 정보를 획득하는 것. 어느 디바이스에서 접속했는지
        //어느 아이피에서 접속했는지 등등. 그런 정보를 db에 남기는 것도 한다. 여기와 JWTFilter에서 예시만 남겨놨다.
        //그냥 이런 흐름도 필요하다고 생각하면 된다.
        /*
        HttpSession session = request.getSession();

        HashMap<String, Object> userinfo = new HashMap<>();
        */



        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, PATCH, GET, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Api-Key, X-Requested-With, Content-Type, Accept, Authorization");

        // 웹소켓: OPTIONS 메서드에 대한 응답 헤더 설정
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        }else {
            chain.doFilter(req, res);
        }
    }
}