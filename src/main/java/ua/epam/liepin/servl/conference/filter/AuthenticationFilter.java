package ua.epam.liepin.servl.conference.filter;

import ua.epam.liepin.servl.conference.constant.Constants;
import ua.epam.liepin.servl.conference.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String path = httpServletRequest.getRequestURL().toString();
        Role currentRole = (Role) httpServletRequest.getSession().getAttribute(Constants.ROLE);
        if (currentRole == null) {
            httpServletResponse.sendRedirect("redirect: /api/login");
        } else if (path.contains("admin/") && currentRole.equals(Role.ROLE_ADMIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (path.contains("speaker/") && currentRole.equals(Role.ROLE_SPEAKER)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (path.contains("user/") && currentRole.equals(Role.ROLE_USER)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.sendRedirect("/api/403");
        }
    }

    @Override
    public void destroy() {

    }
}
