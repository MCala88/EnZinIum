package enzinium;

/**
 * enZinium
 *
 */
public class App 
{
     
    public static void main( String[] args )
    {
        /**
         * Crea una Address en nuestro sistema para Rick
         * Genera las claves privada y publica de la direccion
         * El balance de enZinium de su direccion es cero 
         */

        Address rick = new Address();
        rick.generateKeyPair();

        /**
         * Visualiza la direccion de Rick y su balance
         */
        
        System.out.println("\n" + "Address de Rick" + "\n" + 
                                  "==============="        );
        System.out.println(rick.toString());

        /**
         * Creamos una Address en nuestro sistema para Morty
         * y visualizamos su direccion y balance
         */
        
        Address morty = new Address();
        morty.generateKeyPair();
        System.out.println("\n" + "Address de Morty" + "\n" + 
                                  "==============="        );
        System.out.println(morty.toString());        

        /**
         * Crea una contrato inteligente de tipo TokenContract en nuestro 
         * sistema para que Rick pueda vender 100 entradas para 
         * el concierto de "los Ricknillos". 
         *  
         * El nombre del token es Ricknillos
         * Su simbolo es RNiLL
         * 
         * El propietario del contrato es Rick. Asigna su Public Key a la 
         * propiedad owner del contrato mediante eñ contructor de TokenContract.
         * 
         * En la clase TokenContract programa las funciones a utilizar en 
         * el metodo toString():
         * 
         * name() 
         * @return devuelve el nombre del token de forma human-readable (p.e., “US Dollars”).
         * 
         * symbol()
         * @return el nombre del símbolo del token de forma human-readable (p.e., “USD”).
         * 
         * totalSupply()
         * @return el total de unidades de este token que actualmente existen.
         */

        TokenContract ricknillos = new TokenContract(rick);
        ricknillos.setName("Ricknillos");
        ricknillos.setSymbol("RNiLL");
        ricknillos.setTotalSupply(100);

        System.out.println("\n" + "Contrato de los Rickillos" + "\n" + 
                                  "========================="        );
        System.out.println(ricknillos.toString());

        /**
         * El contrato TokenContract contiene una tabla de balances 
         * de token por propietario:
         * mapping(propietario => numero de unidades que posee)
         * Permite al contrato llevar el seguimiento de quien
         * posee los tokens. 
         * Cada transferencia es una deducción en un balance 
         * y una adicion en el otro.
         * 
         * Crea una tabla "balances" que mapee cada propietario 
         * (su Public Key) al numero de tokens que posee.
         * Añade a Rick con sus 100 entradas y a Morty con cero.
         * Asegurate de que si el propietario (su Public Key) 
         * ya existe en la tabla, sus unidades no se actualicen.
         * 
         * addOwner()
         * añade un propietario y sus tokens
         * @param PublicKey del propietario
         * @param cantidad de tokens que posee
         */

        ricknillos.addOwner(rick.getPK(), 100d);
        ricknillos.addOwner(morty.getPK(), 0d);
        // chequea que Morty no se actualiza a 500 unidades
        ricknillos.addOwner(morty.getPK(), 500d);
        
        /**
         * Consulta los balances
         * 
         * numOwners()
         * @return numero de propietarios registrados en la tabla balances
         * 
         * balanceOf()
         * @param PublicKey del propietario
         * @return cantidad de tokens que posee
         * Dada una direccion, devuelve su balance de tokens
         */
        System.out.println("\n" + "Consulta de balances" + "\n" + 
                                  "===================="        );

        System.out.println("\n" + "Numero de propietarios: " + ricknillos.numOwners());

        System.out.println("Entradas de Rick: " + ricknillos.balanceOf(rick.getPK()) 
                                                + " "
                                                + ricknillos.symbol());

        System.out.println("Entradas de Morty: " + ricknillos.balanceOf(morty.getPK())
                                                 + " "
                                                 + ricknillos.symbol());

        /**
         * Morty quiere comprarle 2 entradas a Rick
         * 
         * transfer()
         * @param PublicKey del destinatario
         * @param cantidad de tokens
         * Dada una direccion y una cantidad, transfiere esa cantidad
         * de tokens a esa direccion, desde el balance de la direccion
         * propietaria del contrato.
         * LLama a la funcion require() para comprobar si el propietario 
         * del contrato tiene suficientes tokens. Si no hay suficientes,
         * falla silenciosamente (no hace nada).
         * 
         * require(condicion)
         * @param una condicion que ha de verificarse (ser cierta)
         * lanza una excepcion si no se cumple la condicion
         */

        System.out.println("\n" + "Transferencia de entradas" + "\n" + 
                                  "========================="        );

        ricknillos.transfer(morty.getPK(), 2d);

        System.out.println("Entradas de Rick: " + ricknillos.balanceOf(rick.getPK()) 
                                                + " "
                                                + ricknillos.symbol());

        System.out.println("Entradas de Morty: " + ricknillos.balanceOf(morty.getPK())
                                                 + " "
                                                 + ricknillos.symbol());

        ricknillos.transfer(morty.getPK(), 300d);

        System.out.println("Entradas de Morty: " + ricknillos.balanceOf(morty.getPK())
                                                 + " "
                                                 + ricknillos.symbol());



        /**
         * A veces, hay reventa ;)
         * Dado un remitente, un destinatario, y una cantidad, 
         * transfiere tokens de una cuenta a la otra.
         *  
         * transferFrom()
         * @param sender PK
         * @param recipient PK
         * @param cantidad de tokens
         */

    }
}
