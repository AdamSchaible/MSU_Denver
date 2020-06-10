//NAME: ADAM SCHAIBLE
//CS 3750
import java.util.Scanner;
import java.lang.*;
public class TEA_Encryption
{
    private static final int DeltaOne = 0x11111111;
    private static final int DeltaTwo = 0x22222222;

    public static void main(String[] args)
    {
        TEA_Encryption tea = new TEA_Encryption();
        long[] K = new long[4];
        Scanner input = new Scanner(System.in).useDelimiter("\\n");

        for(int i=0; i<4; i++)
        {
            System.out.print("Please input K[" + i + "] in Hex String (without \"0x\"):  ");
            String inputKey = input.nextLine();
            inputKey = "0x" + inputKey;
            K[i] = Long.decode(inputKey);
        }
        long[] L = new long[3];
        long[] R = new long[3];

        System.out.print("Please input L[0] in Hex String (without \"0x\"):  ");
        String L0 = input.nextLine();
        L0 = "0x" + L0;
        L[0] = Long.decode(L0);

        System.out.print("Please input R[0] in Hex String (without \"0x\"):  ");
        String R0 = input.nextLine();
        R0 = "0x" + R0;
        R[0] = Long.decode(R0);
        L[1] = 0x00000000;
        L[2] = 0x00000000;
        R[1] = 0x00000000;
        R[2] = 0x00000000;


        L[1] = R[0];
        R[1] = tea.additionMod32(L[0], tea.F(R[0],K[0],K[1],DeltaOne));
        L[2] = R[1];
        R[2] = tea.additionMod32(L[1], tea.F(R[1],K[2],K[3],DeltaTwo));

        System.out.print("L2 = " + Long.toHexString(L[2]).toUpperCase() + "\t");
        System.out.println("R2 = " + Long.toHexString(R[2]).toUpperCase());
        System.out.print("L1 = " + Long.toHexString(L[1]).toUpperCase() + "\t");
        System.out.println("R1 = " + Long.toHexString(R[1]).toUpperCase());
        System.out.print("L0 = " + Long.toHexString(L[0]).toUpperCase() + "\t");
        System.out.println("RO = " + Long.toHexString(R[0]).toUpperCase());




        input.close();
    }
    public long additionMod32(long number1, long number2)
    {
        long sum = number1 + number2;
        String sumString = Long.toBinaryString(sum);
        String bit32String = ShortenStringLengthTo32Bit(sumString);
        long decimalSum = Long.parseLong(bit32String,2);
        return decimalSum;
    }
    public String ShortenStringLengthTo32Bit(String y)
    {
        String bit32 = "";
        if(y.length()>32)
        {
            bit32 = y.substring(y.length() - 33,y.length() - 1);
        }
        else
        {
            bit32 = y;
        }
        return bit32;
    }

    public long F(long x, long km, long kn, int delta)
    {
        long t1, t2, t3, t4, t5, t6, t7;
        t1 = x<<4;
        t2 = additionMod32(t1,km);
        t3 = x>>5;
        t4 = additionMod32(t3,kn);
        t5 = additionMod32(x,delta);
        t6 = t2^t4;
        t7 = t6^t5;
        return t7;
    }
}
