import java.math.BigInteger;
import java.util.Random;

public class Main {

	static int prime1, prime2, privateKey;
	static BigInteger publicKey1, publicKey2;
	static BigInteger[] msg, encrMsg;
	static String message;
	
	public static void main(String[] args) {
		
		message = "Thomas";
		int size = message.length();
		
		encrMsg = new BigInteger[size];
		msg = new BigInteger[size];
		
		generateEncryptionKeys();
		
		System.out.print("INPUT: \t\t");
		//Eingabe in ASCII umspeichern
		for(int i = 0; i < size; i++){
			msg[i] = BigInteger.valueOf(((int)message.charAt(i)));
			System.out.print((char)(msg[i].intValue()));
		}
		
		//Verschluesselung
		for(int i = 0; i < size; i++){
			encrMsg[i] = msg[i].modPow(publicKey1, publicKey2);
		}
		
		System.out.print("\nOUTPUT: \t");
		//Entschluesselung
		for(int i = 0; i < size; i++){	
			msg[i] = encrMsg[i].modPow(BigInteger.valueOf(privateKey), publicKey2);
			System.out.print((char)(msg[i].intValue()));
		}
		
	}

	/**
	 * Ueberpruefung, ob eine Zahl zu den Primzahlen gehoert oder nicht
	 * @param prime die zu ueberpruefende Primzahl
	 * @return ob die Zahl eine Primzahl ist (true/false)
	 */
	private static Boolean isPrime(int prime){
		
		if(prime == 2 || prime == 3 || prime == 5 || prime == 7)
			return true;
		else if(!(prime%2 == 0 || prime%3 == 0 || prime%5 == 0 || prime%7 == 0) && prime != 1)
			return true;
	
		return false;
	}
	
	/**
	 * Ermittlung des groessten gemeinsamen Teilers zweier Zahlen
	 * @param var1 
	 * @param var2
	 * @return der groesste gemeinsame Teiler
	 */
	private static int ggTeiler(int var1, int var2) {
		 
		while (var2 != 0){
			if (var1 > var2) 
				var1 = var1 - var2;
			else
				var2 = var2 - var1;	
		}
		
		return var1;
	}
	 
	/**
	 * Die RSA-Schluessel werden anhand ihrer spezifischen
	 * Voraussetzungen ermittelt.
	 */
	private static void generateEncryptionKeys(){
		 
		 Random generator = new Random();
		 
			do{
				prime1 = generator.nextInt(100);
			}while(!isPrime(prime1));
			
			do{
				prime2 = generator.nextInt(100);
			}while(!isPrime(prime2));
			
			do{
				publicKey1 = BigInteger.valueOf(generator.nextInt(100));
			}while((ggTeiler(((prime1-1)*(prime2-1)), publicKey1.intValue()) != 1));
			
			privateKey = 0;
			
			do{
				privateKey++;
			}while(1 != ((publicKey1.intValue()*privateKey) % ((prime1 - 1) * (prime2 - 1))));
			
			publicKey2 = BigInteger.valueOf(prime1*prime2);
	 }
	
}
	
