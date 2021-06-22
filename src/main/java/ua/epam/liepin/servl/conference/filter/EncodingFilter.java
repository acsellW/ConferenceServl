package ua.epam.liepin.servl.conference.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String encodingParam;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encodingParam = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encodingParam);
        servletResponse.setCharacterEncoding(encodingParam);
        servletResponse.setContentType("text/html; charset=" + encodingParam);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
