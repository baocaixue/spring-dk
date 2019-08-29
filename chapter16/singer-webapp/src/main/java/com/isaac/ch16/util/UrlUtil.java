package com.isaac.ch16.util;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {
    public static String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();

        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }

        pathSegment = UriUtils.encodePathSegment(pathSegment, enc);

        return pathSegment;
    }
}
