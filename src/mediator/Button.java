package mediator;

import javax.swing.JButton;

public class Button extends JButton {
    private Mediator mediator;

    public Button(String text, Mediator mediator) {
        super(text); // JButton'un metni ayarlanır
        this.mediator = mediator;
        this.addActionListener(e -> mediator.notify(this, "click")); // Mediator'a bildirim gönder
    }
}
