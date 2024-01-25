package ru.itrum.core.task01;

import ru.itrum.core.task01.interfaces.ChangeState;

import java.util.ArrayList;
import java.util.List;


public class CustomStringBuilder implements ChangeState {
    private StringBuilder stringBuilder;
    private List<String> history;
    private EnumState state;
    private AppendStateClass appendState;
    private UndoStateClass undoState;

    public CustomStringBuilder() {
        stringBuilder = new StringBuilder();
        history = new ArrayList<>();
        state = EnumState.APPEND;
        appendState = new AppendStateClass(this);
        undoState = new UndoStateClass(this);
    }

    public void append(String str) {
        appendState.append(str);
    }

    public void undo() {
        undoState.undo();
    }

    public String toString() {
        return stringBuilder.toString();
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public List<String> getHistory() {
        return history;
    }

    @Override
    public void changeState() {
        if (state.equals(EnumState.APPEND)) {
            state = EnumState.UNDO;
        } else {
            state = EnumState.APPEND;
        }
    }
}