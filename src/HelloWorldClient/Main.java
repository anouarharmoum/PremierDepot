package HelloWorldClient;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import HelloWorldApp.*;


public class Main {
static Hello hello;
    
    public static void main(String args[]) {
        try {
        	
        	if (args.length == 0) {
                // Si pas d'arguments, on les définit en dur
                args = new String[]{"-ORBInitialPort", "9999"};
            }
        	
            // Initialisation de l'ORB
            ORB orb = ORB.init(args, null);
            
            // Récupération du service de nommage
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            // Recherche de l'objet "Hello" dans le service de nommage
            String name = "Hello";
            hello = HelloHelper.narrow(ncRef.resolve_str(name));
            
            // Appel de la méthode et affichage du résultat
            String message = hello.HelloMessage();
            System.out.println("Message du serveur: " + message);
            
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
    }

}
