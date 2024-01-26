package ru.itrum.core.task01_2;

import java.util.Stack;

public class CustomStringBuilder {
    private StringBuilder stringBuilder;
    private Stack<SnapshotStringBuilder> snapshots;

    public CustomStringBuilder() {
        stringBuilder = new StringBuilder();
        snapshots = new Stack<>();
    }

    public void append(String string) {
        saveSnapshot();
        stringBuilder.append(string);
    }

    public void undo() {
        if (!snapshots.isEmpty()) {
            SnapshotStringBuilder snapshot = snapshots.pop();
            stringBuilder = snapshot.getState();
        }
    }

    private void saveSnapshot() {
        SnapshotStringBuilder snapshot = new SnapshotStringBuilder(stringBuilder);
        snapshots.push(snapshot);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
