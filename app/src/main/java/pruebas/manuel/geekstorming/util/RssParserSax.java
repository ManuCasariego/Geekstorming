package pruebas.manuel.geekstorming.util;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class RssParserSax {
    private URL rssUrl;

    public RssParserSax(String url) {
        try {
            this.rssUrl = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Entry> parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser parser = factory.newSAXParser();
            RssHandler handler = new RssHandler();
            parser.parse(this.getInputStream(), handler);
            return handler.getEntradas();
        } catch (Exception e) {
            //throw new RuntimeException(e);
            Log.d("Error en parse", "Error en parse");
            return new ArrayList<>();
        }

    }

    private InputStream getInputStream() {
        try {
            return rssUrl.openConnection().getInputStream();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            Log.d("Error en getInputStream", "Error en getInputStream");
            return null;
        }
    }
}