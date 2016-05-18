package ui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private int WINDOW_X = 800;
	private int WINDOW_Y = 800;
	
	private int paddleSizeX = 20;
	private int paddleSizeY = 60;
	
	private final int paddlePositionOffset = 50;
	
	private int paddle1Y = (WINDOW_Y / 2) - (paddleSizeY / 2);
	private int paddle2Y = (WINDOW_Y / 2) - (paddleSizeY / 2);
	
	private int paddle1X = paddlePositionOffset;
	private int paddle2X = ((WINDOW_X - paddleSizeX) - paddlePositionOffset);
	
	private int p1Move = 0;
	private int p2Move = 0;
	
	private int player1Delta = 4;
	private int player2Delta = 3;
	
	private int player1Score = 0;
	private int player2Score = 0;
	
	private Image paddleImg;
	
	private Timer timer = new Timer(10, this);
	
	public GamePanel(int x, int y) {
		this.addKeyListener(this);
		timer.start();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.BLACK);
		
		g.setColor(Color.WHITE);
		
		g.drawLine(WINDOW_X / 2, 0, WINDOW_X / 2, WINDOW_Y); // Line in the middle of the game board
		
		try {
			paddleImg = ImageIO.read(new File("img/Paddle_IMG.png"));
		} catch (IOException e) {
			// RIP
		}
		
		g.setFont(new Font("Courier Regular", Font.PLAIN, 30));
		g.drawString(Integer.toString(player1Score), (WINDOW_X / 2) - 50, 50); // Player 1 score
		g.drawString(Integer.toString(player2Score), (WINDOW_X / 2) + 50, 50); // Player 2 score
		
		g.drawImage(paddleImg, paddle1X, paddle1Y, Color.BLACK, null); // Paddle 1
		g.drawImage(paddleImg, paddle2X, paddle2Y, Color.BLACK, null); // Paddle 2
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		requestFocus(); // requesting focus for key events
		
		if (p1Move == -1) {
			this.paddle1Y -= player1Delta;
		}
		else if (p1Move == 1) {
			this.paddle1Y += player1Delta;
		}
		
		if (p2Move == -1) {
			this.paddle2Y -= player2Delta;
		}
		else if (p2Move == 1) {
			this.paddle2Y += player2Delta;
		}
		
		// Collision for paddle 1
		if (this.paddle1Y >= (WINDOW_Y - this.paddleSizeY)) {
			this.paddle1Y -= this.player1Delta;
		}
		
		if (this.paddle1Y <= 0) {
			this.paddle1Y += this.player1Delta;
		}
		
		// Collision for paddle 2
		if (this.paddle2Y >= (WINDOW_Y - this.paddleSizeY)) {
			this.paddle2Y -= this.player2Delta;
		}
		
		if (this.paddle2Y <= 0) {
			this.paddle2Y += this.player2Delta;
		}
		
		this.repaint();
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == 87) {
			p1Move = -1;
		}
		else if (key.getKeyCode() == 83) {
			p1Move = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent key) {
		if (key.getKeyCode() == 87) {
			p1Move = 0;
		}
		else if (key.getKeyCode() == 83) {
			p1Move = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent key) {
	}
}
