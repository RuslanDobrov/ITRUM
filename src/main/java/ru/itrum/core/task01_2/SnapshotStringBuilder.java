package ru.itrum.core.task01_2;

class SnapshotStringBuilder {
    private final StringBuilder state;

    public SnapshotStringBuilder(StringBuilder state) {
        this.state = new StringBuilder(state);
    }

    public StringBuilder getState() {
        return state;
    }
}
