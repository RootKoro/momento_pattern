package pattern_package;

public class Generator {
    private String text;

    public void setText(String text){
        System.out.println("Generateur: text courent\n" + text + "\n");
        this.text = text;
    }

    public Memento saveInMemento(){
        System.out.println("Generateur : sauvegarde du memento\n");
        return new Memento(text);
    }

    public String restoreFromMemento(Memento memento){
        this.text = memento.getText();
        System.out.println("Generateur : text precedemment sauvegarder dans memento\n"+ this.text + "\n");
        return text;
    }
}
