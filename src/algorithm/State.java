package algorithm;

import java.util.Objects;

public class State<T> implements Comparable{
    T state;
    State<T> cameFrom;
    double cost = 0;

    public State() { }

    public State(T state) {
        this.state = state;
        cameFrom = null;
    }

    public State(T state, State<T> cameFrom, double cost) {
        this.state = state;
        this.cameFrom = cameFrom;
        this.cost = cost;
    }

    @Override
    public int compareTo(Object o) {
        State<T> otherState = (State<T>) o;

        if (this.getCost() == otherState.getCost())
            return 0;
        if (this.getCost() > otherState.getCost())
            return 1;
        else
            return -1;
    }

    public T getState() {
        return state;
    }
    public void setState(T state) {
        this.state = state;
    }
    public State<T> getCameFrom() {
        return cameFrom;
    }
    public void setCameFrom(State<T> cameFrom) {
        this.cameFrom = cameFrom;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State<?> state1 = (State<?>) o;
        return Objects.equals(state, state1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return this.state.toString();
    }
}
