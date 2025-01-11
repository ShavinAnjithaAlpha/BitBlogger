package org.bitmonsters.contentservice.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Service;

@Service
public class SanitizationService {

    public String sanitizeInput(String input) {
        return Jsoup.clean(input, Safelist.basicWithImages());
    }
}
