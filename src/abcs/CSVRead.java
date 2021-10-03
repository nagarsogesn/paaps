package abcs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVRead 
{
	public static void main(String[] args) throws IOException 
	{
		String s,r;
		int cnt=0;
		BufferedReader br=new BufferedReader(new FileReader("E:\\Workspace\\mini\\src\\abcs\\abc"));
		while((s=br.readLine())!=null)
		{
				r=s;
				cnt++;
		}
		String aa[][]=new String[cnt][];
		br.close();
		int i=0,j=0;
		BufferedReader br1=new BufferedReader(new FileReader("E:\\Workspace\\mini\\src\\abcs\\abc"));
		while((s=br1.readLine())!=null)
		{
				aa[i]=s.split(",");
				i++;
		}
		for(i=0;i<cnt;i++)
		{
			for(j=0;j<5;j++)
				System.out.print(aa[i][j]+" ");
			System.out.println();
		}
	}
}
