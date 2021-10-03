package abcs;
import abcs.Data;
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.HashMap; 
import java.util.Iterator; 
public class Driver 
{
	 
	public static void main(String[] args) throws IOException  
	{
		Data xx=new Data();
		String dataKey = xx.datas.keySet().iterator().next();
		System.out.println("datakey:"+dataKey);
	DataSet dataSet = new DataSet(xx.datas.get(dataKey));
	System.out.println("[" +dataKey+" DATASET]\n"+dataSet);
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); 
	boolean flag = true; 
	while (flag) 
	{
		System.out.println("> What do you want to do (c, change dataset, exit) ?");
		String command = bufferedReader.readLine(); 
		switch (command) 
		{
			case "c":
				System.out.print("> Please enter values for: ");
				System.out.print(""+dataSet.getData());
				for (int i = 0; i < dataSet.getData()[0].length-2; i++) System.out.print(dataSet.getData()[0][i] + ",");
				System.out.println(dataSet.getData()[0][ dataSet.getData()[0].length-2] + " (separated by commas)");
				String[] values = (bufferedReader.readLine()).split(",");
				HashMap<String, String> instMap = new HashMap<String, String>();
				for (int i = 0; i < dataSet.getData()[0].length-1; i++) instMap.put(dataSet.getData()[0][i], values[i].trim()); 
				HashMap<String, Double> condProbs = dataSet.calcCondProbs(instMap); 
				double allProbs = 0;
				Iterator<Double> probslterator = condProbs.values().iterator(); 
				while(probslterator.hasNext()) allProbs += probslterator.next();
				Iterator<String> keyIterator = condProbs.keySet().iterator(); 
				while (keyIterator. hasNext()) 
				{
				String next = keyIterator.next();
				System.out.println("P("+ next+"|"+ DataSet.getInstanceStr(dataSet, instMap)+")  ="+
				String.format("%.5f ", condProbs.get(next))+"/"+String.format("%.5f", allProbs )+
				"=" +	String.format("%.5f",condProbs.get(next)/allProbs));
				}
				System.out.println(); 
				break;
			case "change dataset":
				System.out.println("> Choose dataset ("+xx.datas.keySet()+" ?");
				String value = bufferedReader.readLine(); 
				if (xx.datas.keySet().contains(value)) 
				{ 
				dataKey = value;
				dataSet = new DataSet(xx.datas.get(dataKey));
				System.out.println(dataSet);
				} else System.out.println("please enter valid dataset name"); break;
				case "exit":
				flag = false; 
			break;
		}
		}
		System.exit(0);
	}
}
