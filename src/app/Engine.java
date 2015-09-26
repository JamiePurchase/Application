package app;

import audio.AudioManager;
import audio.AudioPlayer;
import debug.Console;
import exceptions.StateUnmaintained;
import extend.keyboard.KeyboardExtension;
import extend.keyboard.KeyboardType;
import extend.window.WindowExtension;
import extend.window.WindowManager;
import gfx.Clipboard;
import gfx.Colour;
import input.Keyboard;
import input.Mouse;
import java.awt.Canvas;
import states.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.JPanel;

public class Engine extends JPanel implements Runnable
{
    private static String appName, appAuthor, appVersion, appResource;
    private static State appInitial;
    private static HashMap<String, Object> appVariables = new HashMap();
    private static final long serialVersionUID = 1L;
    public static Display display, displayCustom;
    private boolean modal, resize, decorated;
    public static int width, height;
    private String icon;
    private Thread thread;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
    private static AudioManager audio;
    private static Keyboard keyboard;
    private static Mouse mouse;
    private static Point mousePoint = new Point(0, 0);
    private static boolean tooltipActive = false;
    private static State state, stateWait = null;
    private static Clipboard clipboard;
    
    // Development Mode
    private static boolean devActive = false;
    
    // Extensions
    private static KeyboardExtension extendKeyboard;
    public static WindowExtension extendWindow;

    public Engine(String name, String author, String version, String resource)
    {
        // Application Details
        appName = name;
        appAuthor = author;
        appVersion = version;
        appResource = resource;
        appInitial = null;
        appVariables = new HashMap();
        
        // Main Settings
        modal = false;
        width = 1366;
        height = 768;
        resize = false;
        icon = null;
        decorated = false;
        
        // Audio
        audio = new AudioManager();

        // Input Devices
        keyboard = new Keyboard();
        mouse = new Mouse();
        
        // Styles
        Colour.loadColours();
        
        // Extensions
        extendKeyboard = null;
        extendWindow = null;
    }

    public Engine(String name, String author, String version, String resource, State initial)
    {
        // Application Details
        appName = name;
        appAuthor = author;
        appVersion = version;
        appResource = resource;
        appInitial = initial;
        appVariables = new HashMap();
        
        // Main Settings
        modal = false;
        width = 1366;
        height = 768;
        resize = false;
        icon = null;
        decorated = false;
        
        // Audio
        audio = new AudioManager();

        // Input Devices
        keyboard = new Keyboard();
        mouse = new Mouse();
        
        // Styles
        Colour.loadColours();
        
        // Extensions
        extendKeyboard = null;
        extendWindow = null;
    }

    public Engine(String name, String author, String version, String resource, State initial, int modalW, int modalH, boolean modalR, String modalI)
    {
        // Application Details
        appName = name;
        appAuthor = author;
        appVersion = version;
        appResource = resource;
        appInitial = initial;
        appVariables = new HashMap();
        
        // Main Settings
        modal = true;
        width = modalW;
        height = modalH;
        resize = modalR;
        icon = modalI;
        decorated = true;
        
        // Audio
        audio = new AudioManager();

        // Input Devices
        keyboard = new Keyboard();
        mouse = new Mouse();
        
        // Styles
        Colour.loadColours();
        
        // Extensions
        extendKeyboard = null;
        extendWindow = null;
    }

    public Engine(String name, String author, String version, String resource, State initial, int modalW, int modalH, boolean modalR, boolean modalD, String modalI)
    {
        // Application Details
        appName = name;
        appAuthor = author;
        appVersion = version;
        appResource = resource;
        appInitial = initial;
        appVariables = new HashMap();
        
        // Main Settings
        modal = true;
        width = modalW;
        height = modalH;
        resize = modalR;
        icon = modalI;
        decorated = modalD;
        
        // Audio
        audio = new AudioManager();

        // Input Devices
        keyboard = new Keyboard();
        mouse = new Mouse();
        
        // Styles
        Colour.loadColours();
        
        // Extensions
        extendKeyboard = null;
        extendWindow = null;
    }

    public Engine(String name, String author, String version, String resource, State initial, int modalW, int modalH, boolean modalR, String modalTitle, String modalWindow, String modalI)
    {
        // Application Details
        appName = name;
        appAuthor = author;
        appVersion = version;
        appResource = resource;
        appInitial = initial;
        appVariables = new HashMap();
        
        // Main Settings
        modal = true;
        width = modalW;
        height = modalH;
        resize = modalR;
        icon = modalI;
        decorated = false;
        
        // Audio
        audio = new AudioManager();

        // Input Devices
        keyboard = new Keyboard();
        mouse = new Mouse();
        
        // Styles
        Colour.loadColours();
        
        // Extensions
        extendKeyboard = null;
        extendWindow = WindowManager.createWindow(modalTitle, modalWindow);
    }
    
    public static boolean checkAppVariable(String index)
    {
        return appVariables.containsKey(index);
    }
    
    public static void extendKeyboard(int length, KeyboardType type)
    {
        extendKeyboard = new KeyboardExtension(length, type);
        // NOTE: need to grab the canvas, turn it into a BufferedImage and pass it to the constructor
    }
    
    public static boolean extendKeyboardActive()
    {
        if(extendKeyboard != null) {return true;}
        return false;
    }
    
    public static void extendKeyboardDone()
    {
        extendKeyboard = null;
    }
    
    public static String getAppAuthor()
    {
        return appAuthor;
    }
    
    public static String getAppName()
    {
        return appName;
    }
    
    public static Object getAppVariable(String index)
    {
        return appVariables.get(index);
    }
    
    public static HashMap<String, Object> getAppVariables()
    {
        return appVariables;
    }
    
    public static String getAppVersion()
    {
        return appVersion;
    }
    
    public static AudioManager getAudio()
    {
        return audio;
    }
    
    public static Canvas getCanvas()
    {
        return display.getCanvas();
    }
    
    public static Clipboard getClipboard()
    {
        return clipboard;
    }
    
    public static Display getDisplay()
    {
        return display;
    }
    
    public static Keyboard getKeyboard()
    {
        return keyboard;
    }
    
    public static Mouse getMouse()
    {
        return mouse;
    }
    
    public static boolean getMouseIdle()
    {
        if(mouse.getIdleTick() >= 300) {return true;}
        return false;
    }
    
    public static Point getMousePoint()
    {
        return mousePoint;
    }

    public static String getResourcePath()
    {
        return appResource;
    }
    
    public static State getState()
    {
        return state;
    }
    
    public static boolean getTooltipActive()
    {
        return tooltipActive;
    }
    
    public static int getScreenCenterX()
    {
        return width / 2;
    }
    
    public static int getScreenCenterY()
    {
        return height / 2;
    }
    
    public static String getSystemProperty(String property)
    {
        return System.getProperty(property);
    }

    private void init()
    {
        // Create Display
        if(modal) {display = new Display(width, height, resize, icon, decorated);}
        else {display = new Display();}
        
        // Initial State
        this.setState(appInitial);
    }
    
    public static void inputKey(String key, String action)
    {
        /*if(key.equals("ESCAPE")) {System.exit(0);}
        else {if(getState() != null) {getState().inputKey(key, action);}}*/
        getState().inputKey(key, action);
    }
    
    public static void inputMouse(MouseEvent e, String action, String button)
    {
        // Extensions: Window
        if(extendWindow != null)
        {
            // Check for input events on the window
            extendWindow.inputMouse(e, action);
            
            // Create a new MouseEvent with coordinates relevant to the subcanvas
            Point newPoint = Engine.extendWindow.getCanvasPoint(e.getPoint());
            e = new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(), newPoint.x, newPoint.y, e.getClickCount(), e.isPopupTrigger());
        }
        
        // Extensions: Keyboard
        if(extendKeyboard != null)
        {
            if(action.equals("CLICK")) {extendKeyboard.inputClick(e);}
        }
        
        // State
        else if(getState() != null) {getState().inputMouse(e, action, button);}
    }
    
    public static void newClipboard()
    {
        clipboard = new Clipboard();
    }

    private void render()
    {
        // Buffer Strategy
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        // Graphics Start
        g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        
        // Extensions: Window
        if(extendWindow != null)
        {
            extendWindow.render(g);
            if(this.state != null)
            {
                // Secondary Canvas
                BufferedImage g2image = new BufferedImage(extendWindow.getRenderArea().width, extendWindow.getRenderArea().height, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2canvas = g2image.createGraphics();
                getState().render(g2canvas);
                
                // Extensions: Keyboard
                if(extendKeyboard != null) {extendKeyboard.render(g2canvas);}
                
                // Graphics Draw
                g.drawImage(g2image, extendWindow.getRenderArea().x, extendWindow.getRenderArea().y, this);
            }
        }
        else
        {
            // Extensions: Keyboard
            if(extendKeyboard != null) {extendKeyboard.render(g);}

            // Graphics Draw
            else if(this.state != null) {getState().render(g);}
        }
        
        // Graphics Done
        bs.show();
        g.dispose();
    }
    
    public static void resumeState() throws StateUnmaintained
    {
        if(stateWait != null)
        {
            setState(stateWait);
            stateWait = null;
        }
        else {throw new StateUnmaintained();}
    }

    public void run()
    {
        // Render speed
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        // Create window
        init();

        // Main game loop
        while(running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if(delta >= 1)
            {			
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000)
            {
                ticks = 0;
                timer = 0;
            }
        }

        // End game
        stop();
    }
    
    public static void setAppVariable(String index, Object value)
    {
        appVariables.put(index, value);
    }
    
    public static void setDisplaySize(int sizeX, int sizeY)
    {
        width = sizeX;
        height = sizeY;
        display.setDisplaySize(sizeX, sizeY);
    }
    
    public static void setMousePoint(Point newPoint)
    {
        mousePoint = newPoint;
    }
    
    public static void setState(State newState)
    {
        state = newState;
    }
    
    public static void setState(State newState, boolean maintain)
    {
        if(maintain) {stateWait = state;}
        state = newState;
    }
    
    public static void setTooltipActive(boolean value)
    {
        tooltipActive = value;
    }

    public synchronized void start(boolean dev)
    {
        if(running == false)
        {
            running = true;
            devActive = dev;
            thread = new Thread(this);
            thread.start();
        }
    }

    public synchronized void stop()
    {
        if(running == true)
        {
            try
            {
                thread.join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void tick()
    {
        // Network Update
        //tickNetwork();

        // State
        if(getState() != null) {getState().tick();}
        
        // Mouse Idle
        if(!tooltipActive) {getMouse().tick();}
    }

}