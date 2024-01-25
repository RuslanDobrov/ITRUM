package ru.itrum.core.task01;

import ru.itrum.core.task01.interfaces.AppendState;
import ru.itrum.core.task01.interfaces.ChangeState;

public class AppendStateClass implements ChangeState, AppendState {

    private CustomStringBuilder stringBuilder;
    private EnumState state;

    public AppendStateClass(CustomStringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
        this.state = EnumState.APPEND;
    }

    public void changeState() {
        this.state = EnumState.UNDO;
    }

    @Override
    public void append(String str) {
        stringBuilder.getStringBuilder().append(str);
        stringBuilder.getHistory().add(str);
    }

    public EnumState getState() {
        return state;
    }
}
