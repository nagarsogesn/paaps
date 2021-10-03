package abcs;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class Data
{
		String s="";
		String r="";
		static int cnt=0;
		static String aa[][]=new String[30][];
		BufferedReader br,br1;
		Data() throws IOException
		{
			
				br = new BufferedReader(new FileReader("E:\\Workspace\\mini\\src\\abcs\\placement"));
			
		while((s=br.readLine())!=null)
		{
				r=s;
				cnt++;
		}
		br.close();
		int i=0,j=0;
		
				br1 = new BufferedReader(new FileReader("E:\\Workspace\\mini\\src\\abcs\\placement"));
			
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
		
		static Map<String,String[][]> datas =(Map) Collections.unmodifiableMap(new HashMap<String,String[][]>()
				{
			private static final long serialVersionUID=1L;
			{
				put("CONTACT-LENSES",aa);
			}
		});
		
		
}