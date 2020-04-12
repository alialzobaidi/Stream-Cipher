
import java.util.Scanner;
public class linear_feedback_shift_register {
	private static Scanner input;
	static int r;
	public static void main(String[] args) {
		input = new Scanner(System.in);
		String str, msg_bin;
		System.out.print("Enter Message::");
		str = input.nextLine();
		msg_bin = char_binary(str);
		System.out.println("Binary::" + msg_bin);
		String key = key_gen();
		System.out.println("key as binary::" + key);
		String cipher = Stream_Enc_Dec(msg_bin, key);
		System.out.println("cipher as bin::" + cipher);
		String msg_char = binary_char(cipher);
		System.out.println("cipher as char::" + msg_char);
		String plan_bin = Stream_Enc_Dec(cipher, key);
		System.out.println("plan as bin::" + plan_bin);
		String plan_char = binary_char(plan_bin);
		System.out.println("plan as char::" + plan_char);
	}

	// Convert Message from Char to Binary
	public static String char_binary(String str) {
		String binary = "", bin = "";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
	
			int asc = ((int) ch) - 64;
			bin = "";
			for (int j = 1; j <= 5; j++) {
				bin = (asc % 2) + bin;

				asc /= 2;
			}
			binary = binary + bin;
		}
		return binary;
	}

	// Convert Message From Binary to Char
	public static String binary_char(String bin) {
		String msg = "", str;
		int k, res;
		for (int i = 0; i < bin.length(); i += 5) {
			str = bin.substring(i, i + 5);
			res = 0;
			k = 0;
			for (int j = 4; j >= 0; j--) {
				char ch = str.charAt(j);
				// convert from char to int

				int num = Character.getNumericValue(ch);
				res = res + (num * (int) (Math.pow(2, k)));
				k++;
			}
			if (res == 26)
				msg = msg + (char) (90);
			else 
				msg = msg + (char) ((res % 26) + 64);
			
			
			
		}
		return msg;
	}

	// Key Generation

	public static String key_gen() {
		int bit1, bit2;
		System.out.print("Enter the number of intial bit:");
		
		int len = input.nextInt(); // exp 3
		
		String intial[] = new String[len];
		System.out.println("Enter value of Intial:");
		
		for (int i = 0; i < intial.length; i++) {
			intial[i] = input.next(); // exp 011
		}
		
		System.out.print("Enter Bit1:");
		bit1 = input.nextInt();
		System.out.print("Enter Bit2:");
		bit2 = input.nextInt();
		
		int re = ((int) (Math.pow(2, len))) - 1;
		String key = "", k;
		for (int i = 0; i < re; i++) {
			key = key + intial[len - 1];
			if (intial[bit1].equals(intial[bit2]))
				k = "0";
			else
				k = "1";
			for (int j = len - 1; j >= 1; j--)
				intial[j] = intial[j - 1];
			intial[0] = k;
		}
		return key;
	}

	// Encryption and Decryption Method
	public static String Stream_Enc_Dec(String msg, String key)

	{
		String cipher = "";
		int j = 0;
		for (int i = 0; i < msg.length(); i++) {
			char ch = msg.charAt(i);
			char k = key.charAt(j++);
			// XOR operation
			if (ch == k)
				cipher = cipher + '0';
			else
				cipher = cipher + '1';

			// If Key Length less than Message Length

			if (j == key.length())
				j = 0;
		}
		return cipher;
	}
}