package ru.itrum.core.task01;

import ru.itrum.core.task01.interfaces.ChangeState;
import ru.itrum.core.task01.interfaces.UndoState;

public class UndoStateClass implements ChangeState, UndoState {

    private CustomStringBuilder stringBuilder;
    private EnumState state;

    public UndoStateClass(CustomStringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
        this.state = EnumState.UNDO;
    }

    @Override
    public void undo() {
        if (!stringBuilder.getHistory().isEmpty()) {
            String lastAppended = stringBuilder.getHistory().remove(stringBuilder.getHistory().size() - 1);
            int lengthToRemove = lastAppended.length();
            stringBuilder.getStringBuilder().delete(stringBuilder
                            .getStringBuilder().length() - lengthToRemove, stringBuilder.getStringBuilder().length());
        }

        stringBuilder.changeState();
    }

    @Override
    public void changeState() {
        this.state = EnumState.APPEND;
    }

    public EnumState getState() {
        return state;
    }
}
