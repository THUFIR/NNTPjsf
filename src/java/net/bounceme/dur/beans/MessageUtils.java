package net.bounceme.dur.beans;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.mail.Header;
import javax.mail.Message;

public class MessageUtils {

    private static List<Header> getHeaders(Message message) throws Exception {
        Enumeration allHeaders = message.getAllHeaders();
        List<Header> headers = new ArrayList<Header>();
        while (allHeaders.hasMoreElements()) {
            Header hdr = (Header) allHeaders.nextElement();
            headers.add(hdr);
        }
        return headers;
    }

    public static URL getUrl(Message message) throws Exception {
        List<Header> headers = getHeaders(message);
        URL url = new URL("http://www.google.com/");
        Header header = null;
        for (Header h : headers) {
            if ("Archived-at".equals(h.getName())) {
                String stringUrl = h.getValue();
                stringUrl = stringUrl.substring(1, stringUrl.length() - 1);
                url = new URL(stringUrl);
            }
        }
        return url;
    }

    public static int getMessageId(Message message) throws Exception {
        List<Header> headers = getHeaders(message);
        Header header = null;
        for (Header h : headers) {
            if ("Message-ID".equals(h.getName())) {
                header = h;
            }
        }
        return Integer.parseInt(header.getValue());
    }
}
