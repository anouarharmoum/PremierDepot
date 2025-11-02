package helloWorldServer;

import org.omg.CORBA.ORB;
import HelloWorldApp.HelloPOA;

public class HelloServant extends HelloPOA{
	
	private String message = "Bonjour tous le monde !!";
    private ORB orb;
    
    //setOrb est une méthode qui permet à l'objet HelloServant 
    //de stocker une référence à l'objet ORB.
    
    public void setOrb(ORB orb) {
        this.orb = orb;
    }

	@Override
	public String HelloMessage() {
		return message;
	}

	@Override
	public void HelloMessage(String newHelloMessage) {
		message = newHelloMessage;
	}

}
