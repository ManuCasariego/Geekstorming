package pruebas.manuel.geekstorming.util;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class RssHandler extends DefaultHandler {
    private List<Entry> entradas;
    private Entry entradaActual;
    private StringBuilder sbTexto;

    public List<Entry> getEntradas() {
        return this.entradas;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        super.characters(ch, start, length);

        if (this.entradaActual != null)
            sbTexto.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {

        super.endElement(uri, localName, name);

        if (this.entradaActual != null) {
            if (localName.equals("title")) {
                entradaActual.setTitle(sbTexto.toString());
            } else if (localName.equals("link")) {
                entradaActual.setLink(sbTexto.toString());
            } else if (localName.equals("creator")) {
                entradaActual.setCreator(sbTexto.toString());
            } else if (localName.equals("category")) {
                entradaActual.setCategorias(sbTexto.toString());
            } else if (localName.equals("encoded")) {
                entradaActual.setContent(sbTexto.toString());
            } else if (localName.equals("item")) {
                entradas.add(entradaActual);
            }
            sbTexto.setLength(0);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        entradas = new ArrayList<Entry>();
        sbTexto = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName,
                             String name, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, name, attributes);
        if (localName.equals("item")) {
            entradaActual = new Entry();
        }
    }
}