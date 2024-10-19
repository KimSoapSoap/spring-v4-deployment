package org.example.springv3.core.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.springv3.core.util.JwtUtil;
import org.example.springv3.core.util.Resp;
import org.example.springv3.user.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// 책임 : 인가!!
public class JwtAuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String accessToken = req.getHeader("Authorization");

        if(accessToken == null || accessToken.isBlank()){
            resp.setHeader("Content-Type","application/json; charset=utf-8");
            PrintWriter out = resp.getWriter();
            Resp fail = Resp.fail(401, "토큰이 없어요");
            String responseBody = new ObjectMapper().writeValueAsString(fail);
            out.println(responseBody);
            out.flush();
            return;
        }

        try {
            User sessionUser = JwtUtil.verify(accessToken); // 위조, 만료
            HttpSession session = req.getSession();
            session.setAttribute("sessionUser", sessionUser);
            chain.doFilter(req, resp); // 다음 필터로 가!! 없으면 DS로 감.


            //토큰 인증에서 유저 정보 session에서 꺼내기. 이건 예시다 이 방식을 쓰는 것이 아니고 라이브러리를 사용 한다.
            // 최초에 접속할 때 유저 정보 획득하는 것.
           /* HashMap<String, Object> userinfo = ((HashMap<String, Object>) session.getAttribute("userinfo"));
            userinfo.put("username", sessionUser.getUsername());
           */


        }catch (Exception e){
            resp.setHeader("Content-Type","application/json; charset=utf-8");
            PrintWriter out = resp.getWriter();
            Resp fail = Resp.fail(401, e.getMessage());
            String responseBody = new ObjectMapper().writeValueAsString(fail);
            out.println(responseBody);
            out.flush();
        }

    }
}
