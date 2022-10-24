package ramsales.Abstract;

import java.util.ArrayList;

public abstract class Python {
    public String smtpServer;
    public String smtpPort;
    public ArrayList<String> sendTo = new ArrayList<String>();
    public String addressListLength;
    public String sendFrom;
    public String username;
    public String password;
    //public String HTMLPath;
}
