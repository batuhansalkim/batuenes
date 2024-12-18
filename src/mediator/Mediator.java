package mediator;

import java.awt.Component;

public interface Mediator {
    void notify(Component sender, String event);
}
