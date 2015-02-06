package pruebas.manuel.geekstorming.util;

import java.util.ArrayList;

public class Entry {

    private String title = null, link = null, content = null, creator = null;
    private ArrayList<String> categories = new ArrayList<>();

    //Mirar que es el media:content


    public Entry() {

    }

    //<editor-fold desc="Getters">
    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getContent() {
        return content;
    }

    public String getCreator() {
        return creator;
    }

    public String getCategorias() {
        String respuesta = "";
        for ( int i = 0; i < categories.size();i++){
            respuesta +=  categories.get(i) + " ";
        }
        return respuesta;
    }
    //</editor-fold>

    //<editor-fold desc="Setters">
    public void setTitle(String title) {
        if(this.title == null){
            this.title = quitarBlancos(title);
            //Log.d("Title", quitarBlancos(title));
        }
    }

    public void setLink(String link) {
        this.link = quitarBlancos(link);
        //Log.d("Link", quitarBlancos(link));
    }

    public void setContent(String content) {
        this.content = quitarBlancos(content);
        //Log.d("Content", quitarBlancos(content));
    }

    public void setCreator(String creator) {
        this.creator = quitarBlancos(creator);
        //Log.d("Creator", quitarBlancos(creator));
    }

    public void setCategorias(String categories) {
        this.categories.add(quitarBlancos(categories));
        //Log.d("Categories", quitarBlancos(categories));
    }
    //</editor-fold>

    //<editor-fold desc="Equals and HashCode">
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (categories != null ? !categories.equals(entry.categories) : entry.categories != null)
            return false;
        if (content != null ? !content.equals(entry.content) : entry.content != null) return false;
        if (creator != null ? !creator.equals(entry.creator) : entry.creator != null) return false;
        if (link != null ? !link.equals(entry.link) : entry.link != null) return false;
        if (!title.equals(entry.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        return result;
    }
    //</editor-fold>

    public String quitarBlancos(String cadena){
        return cadena.trim();
    }
}
