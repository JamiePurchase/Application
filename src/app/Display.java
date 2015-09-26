package app;

import debug.Console;
import input.Mouse;
import input.Keyboard;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import tools.BooleanTools;

public class Display
{
    private static JFrame frame;
    private JPanel panel;
    private static Canvas canvas;
    private boolean fullscreen, resize, decorated;
    private int width, height;
    private String icon;
    private Keyboard keyboard;
    private Mouse mouse;

    public Display()
    {
        this.fullscreen = true;
        this.decorated = false;
        this.width = 1366;
        this.height = 768;
        this.resize = false;
        this.icon = null;
        this.keyboard = new Keyboard();
        this.mouse = new Mouse();
        createDisplay();
        this.panel.requestFocus();
    }
    
    public Display(int width, int height, boolean resize, String icon, boolean decorated)
    {
        this.fullscreen = false;
        this.decorated = decorated;
        this.width = width;
        this.height = height;
        this.resize = resize;
        this.icon = icon;
        this.keyboard = new Keyboard();
        this.mouse = new Mouse();
        createDisplay();
        this.panel.requestFocus();
    }

    private void createDisplay()
    {
        // Create the frame
        frame = new JFrame(Engine.getAppName());
        frame.setSize(this.width, this.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(BooleanTools.flip(this.decorated));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(this.resize);
        if(this.fullscreen) {frame.setLocationRelativeTo(null);}
        else
        {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation((dim.width - this.width) / 2, (dim.width - this.height) / 2);
        }
        if(this.icon != null)
        {
            java.net.URL iconPath = ClassLoader.getSystemResource(this.icon);
            Image iconImage = Toolkit.getDefaultToolkit().createImage(iconPath);
            frame.setIconImage(iconImage);
        }
        frame.setVisible(true);

        // Create a JPanel
        panel = new JPanel();
        panel.addKeyListener(this.keyboard);
        frame.add(panel);
        panel.requestFocusInWindow();

        // Create the canvas
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(this.width, this.height));
        canvas.setMaximumSize(new Dimension(this.width, this.height));
        canvas.setMinimumSize(new Dimension(this.width, this.height));
        canvas.addMouseListener(this.mouse);
        canvas.addMouseMotionListener(this.mouse);

        // Add the canvas to the frame
        frame.add(canvas);
        frame.pack();
    }
    
    public static void frameMove(int moveX, int moveY)
    {
        frame.setLocation(frame.getLocationOnScreen().x + moveX, frame.getLocationOnScreen().y + moveY);
    }

    public static Canvas getCanvas()
    {
        return canvas;
    }
    
    public JPanel getPanel()
    {
        return panel;
    }
    
    public void setDisplaySize(int sizeX, int sizeY)
    {
        // Update Values
        this.width = sizeX;
        this.height = sizeY;
        
        // Resize Frame
        frame.setSize(this.width, this.height);
        frame.setLocation(0, 0);
        
        // Resize Canvas
        canvas.setPreferredSize(new Dimension(this.width, this.height));
        canvas.setMaximumSize(new Dimension(this.width, this.height));
        canvas.setMinimumSize(new Dimension(this.width, this.height));
    }

}