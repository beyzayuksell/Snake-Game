// Beyza YÜKSEL , No: 201611066
// Can ÖZAL , No: 201611043

/**
 * @Author : BEYZA YÜKSEL 
**/
package com.SnakeGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyListener implements KeyListener {

	// Direction is keyboard key (a,s,d,w)
	// First direction is "s" key.
	// "s" key means : Direction is Down
    char direction = 's';

    @Override
    // Keyboard Listener 
    // Listens of user pressed key
    // Assigned to direction attribute.
    public void keyPressed(KeyEvent e) {
        direction = e.getKeyChar();

    }

    // We takes the char('s' as key) to as string.
    public String getDirection() {
        return String.valueOf(direction);
    }

    // Not Used
    @Override
    public void keyTyped(KeyEvent e) {

    }
    // Not Used
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
