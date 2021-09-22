package pattern_package;

import java.util.*;

public class Saver {
    ArrayList<Memento> savedText = new ArrayList<Memento>();

    public void addMemento(Memento m){ savedText.add(m); }

    public Memento getMemento(int index){ return savedText.get(index); }
}
