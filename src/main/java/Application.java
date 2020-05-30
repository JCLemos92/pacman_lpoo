import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(59, 17)).createTerminal();

            Screen screen = new TerminalScreen(terminal);


            TextGraphics graphics = screen.newTextGraphics();




            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

            KeyStroke key = screen.readInput();

            screen.clear();
            //wall
            graphics.setBackgroundColor(TextColor.Factory.fromString("#0071bc"));
            graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(1, 1), ' ');
            //pacman
            graphics.setBackgroundColor(TextColor.Factory.fromString("#ffe118"));
            graphics.fillRectangle(new TerminalPosition(0, 3), new TerminalSize(1, 1), ' ');
            //ghost normal
            graphics.setBackgroundColor(TextColor.Factory.fromString("#ed1d28"));
            graphics.fillRectangle(new TerminalPosition(0, 6), new TerminalSize(1, 1), ' ');
            //ghost scared
            graphics.setBackgroundColor(TextColor.Factory.fromString("#ccff10"));
            graphics.fillRectangle(new TerminalPosition(0, 9), new TerminalSize(1, 1), ' ');
            //pallet
            screen.setCharacter(10, 10, new TextCharacter('o'));
            //spetial pallet
            screen.setCharacter(10, 15, new TextCharacter('0'));

            screen.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
