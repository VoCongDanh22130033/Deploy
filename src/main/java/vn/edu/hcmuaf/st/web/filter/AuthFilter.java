package vn.edu.hcmuaf.st.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.st.web.entity.GoogleAccount;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false); // Chỉ lấy session nếu đã tồn tại
        if (session != null) {
            GoogleAccount googleAccount = (GoogleAccount) session.getAttribute("googleAccount");
            request.setAttribute("googleAccount", googleAccount);
        }
        chain.doFilter(request, response);
    }

    public void destroy() {}
}
