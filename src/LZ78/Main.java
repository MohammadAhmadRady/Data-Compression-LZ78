package LZ78;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
public class Main
{
    static ArrayList <String> dictionary = new ArrayList<String>();
    static Vector<Integer> Index=new Vector<Integer>();
    static Vector<Character> Next=new Vector<Character>();
    public void print_data()
    {
        System.out.println("***Dictionary***");
        for(int i=0;i<dictionary.size();i++)
            System.out.println(i + "  " + dictionary.get(i));
        System.out.println("***Tags***");
        for(int i=0;i<Index.size();i++)
            System.out.println("<" + Index.elementAt(i) + "," + Next.elementAt(i) + ">");
        dictionary.clear();
    }
    public void compress(String input)
    {
        char[] name = input.toCharArray();
        input="";
        dictionary.add("");
        int index = 0;
        for(int i=0 ; i<name.length ; i++)
        {
            input+=name[i];
            if(dictionary.contains(input))
                index = dictionary.indexOf(input);
            else
            {
                Index.addElement(index);
                Next.addElement(name[i]);
                dictionary.add(input);
                input="";
                index=0;
                continue;
            }
            if(i==name.length-1)
            {
                Index.addElement(index);
                Next.addElement(null);
            }
        }
        print_data();
    }
    public void decompress()
    {
        String FINAL = "";
        dictionary.add("");
        for(int i=0;i<Next.size();i++)
        {
            if(Next.elementAt(i)==null)
            {
                FINAL+=dictionary.get(Index.elementAt(i));
                break;
            }
            if(Index.elementAt(i)==0)
            {
                dictionary.add(Next.elementAt(i).toString());
                FINAL+=Next.elementAt(i);
            }
            else
            {
                dictionary.add(dictionary.get(Index.elementAt(i))+Next.elementAt(i));
                FINAL+=dictionary.get(Index.elementAt(i))+Next.elementAt(i);
            }
        }
        System.out.println("Original data after Decompress: "+FINAL);
    }
    public void de()
    {
        Scanner in = new Scanner(System.in);
        char p = ' ';
        int I;
        char []N = new char[1];
        String z;
        Index.clear();
        Next.clear();
        while (p != '*')
        {
            I = in.nextInt();
            z = in.next();
            N = z.toCharArray();
            p=N[0];
            if(p=='*')
                break;
            Index.add(I);
            Next.add(N[0]);
        }
        decompress();
        in.close();
    }
    public static  void main(String[] args)
    {
        System.out.print("Enter the TEXT: ");
        Scanner inputs = new Scanner(System.in);
        String  input = new String();
        input = inputs.nextLine();
        Main obj = new Main();
        obj.compress(input);
        obj.decompress();
        obj.de();
        inputs.close();
    }
}