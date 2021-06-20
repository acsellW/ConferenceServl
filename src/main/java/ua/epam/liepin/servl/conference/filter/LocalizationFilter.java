package ua.epam.liepin.servl.conference.filter;

import ua.epam.liepin.servl.conference.constant.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter {
    private String defaultLocale;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        defaultLocale = filterConfig.getInitParameter(Constants.LOCALE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String localeParameter = httpServletRequest.getParameter(Constants.LOCALE);

        if (isNotBlank(localeParameter)) {
            httpServletRequest.getSession().setAttribute(Constants.LOCALE, localeParameter);

        } else {
            String sessionLocale = (String) httpServletRequest.getSession().getAttribute(Constants.LOCALE);
            if (isBlank(sessionLocale)) {
                httpServletRequest.getSession().setAttribute(Constants.LOCALE, defaultLocale);
            }
        }
        httpServletRequest.getSession().getAttribute(Constants.LOCALE);
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }

    private boolean isBlank(String locale) {
        return locale == null || locale.isEmpty();
    }

    private boolean isNotBlank(String locale) {
        return !isBlank(locale);
    }
}
