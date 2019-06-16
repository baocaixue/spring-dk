package com.isaac.ch3.annotated;

import com.isaac.ch3.ContentHolder;
import org.springframework.stereotype.Service;

@Service("lyricHolder")
public class LyricHolder implements ContentHolder {
    private String value = "'You be the DJ, I'll be the driver'";

    @Override public String toString() {
        return "LyricHolder: { " + value + "}";
    }
}
