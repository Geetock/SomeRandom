package dev.geetock.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.geetock.tilegame.display.Display;

public class Game implements Runnable {

	private Display display;
	
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public String title;
	public int width;
	public int height;
	
	private boolean running = false;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
	}

	private void init() {
		display = new Display(title, width, height);

	}
	
	private void tick() {
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		// Clear screen
		g.clearRect(0, 0, width, height);
		
		// Draw here
		
		
		
		// End drawing		
		bs.show();
		g.dispose();
	}

	public void run() {
		
		init();
		
		while (running) {
			tick();
			render();
		}
		
		stop();		
	}
	
	public synchronized void start() {
		if (running) return;
		
		running = true;
		thread = new Thread(this);		
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
