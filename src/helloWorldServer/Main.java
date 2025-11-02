package helloWorldServer;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import HelloWorldApp.*;

public class Main {

	public static void main(String[] args) {
		 try {
			 // Utilise les arguments passés au programme
			 if (args.length == 0) {
				 // Si pas d'arguments, on les définit en dur
				 args = new String[]{"-ORBInitialPort", "9999"};
			 }
	         // Initialisation de l'ORB
	         ORB orb = ORB.init(args, null);
	            
	         // Récupération du POA root
	         POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
	         rootpoa.the_POAManager().activate();
	            
	         // Création du servant
	         HelloServant helloServant = new HelloServant();
	         helloServant.setOrb(orb);
	            
	         // Conversion du servant en référence CORBA
	         org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloServant);
	         Hello href = HelloHelper.narrow(ref);
	            
	         // Récupération du service de nommage
	         org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	         NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	            
	         // Enregistrement de l'objet dans le service de nommage
	         String name = "Hello";
	         NameComponent path[] = ncRef.to_name(name);
	         ncRef.rebind(path, href);
	            
	         System.out.println("HelloWorldServer ready and waiting ...");
	            
	         // Attente des requêtes
	         for (;;) {
	             orb.run();
	         }
	            
	     } catch (Exception e) {
	         System.err.println("ERROR: " + e);
	         e.printStackTrace(System.out);
	    }
	}

}
