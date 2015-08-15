package exceptions;

public class StateUnmaintained extends Exception
{

    public StateUnmaintained()
    {
        super("Failed to resume previous state (no maintained data).");
    }

}