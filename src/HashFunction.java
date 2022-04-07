import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HashFunction {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // thing breaks for text sizes larger than 64 characters
        // test on:
        // testing the minecraft gameplay because it can be really fun and funny and the guy is fireball jumping all over the place i dont like

        System.out.print("text: ");
        String text = input.nextLine();
        int arrayCount = (int) Math.ceil(text.length() * 8.0 / 512);
        System.out.println(arrayCount);

        String h1 = "147ecb88"; // 9th root of 2 in hex
        String h2 = "2aa1a5aa"; // 9th root of 4
        String h3 = "386497f3"; // 9th root of 6
        String h4 = "428a2f98"; // 9th root of 8
        String h5 = "4aa2ffb4"; // 9th root of 10

        int div = 1;

        ArrayList<String> binary = new ArrayList<>();
        byte[] bits = new byte[512];
        byte[] bitsCopy = new byte[512];
        for(int i = 0; i < text.length(); i++) {
            binary.add(Integer.toBinaryString(text.charAt(i)));
            while(binary.get(i).length() < 8) binary.set(i, "0" + binary.get(i));

            for(int j = 0; j < 8; j++) {
                int k = i * 8 + j;
                if(k >= 512) div++;
                if(div == 2) bitsCopy = Arrays.copyOf(bits, bits.length);

                if(div > 1) bits[k / div] = (byte) (bits[k / div] ^ (byte) (binary.get(i).charAt(j) - 48));
                else bits[k / div] = (byte) (binary.get(i).charAt(j) - 48);
            }
        }

        for(int i = 0; i < bits.length; i++) {
            bits[i] = (byte) (bits[i] | bitsCopy[i]);
        }
        for(int i = 0; i < bits.length; i++) {
            bits[i] = (byte) (bits[i] & bitsCopy[i]);
        }

        for(int i = 0; i < bits.length; i++) {
            if(i % 32 == 0) System.out.println();
            System.out.print(bits[i] + " ");
        }
    }
}
